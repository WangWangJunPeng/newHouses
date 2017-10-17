<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<html>

	<head>
		<meta charset="UTF-8">
		<title>标签管理</title>
		<meta name="renderer" content="webkit">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/static/layui/plugins/layui/css/layui.css" media="all" />
		<link rel="stylesheet" href="<%=request.getContextPath()%>/static/layui/css/begtable.css" />
	  	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/SimpleTree.css"/>
		<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery-3.1.1.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/SimpleTree.js"></script>
		<style type="text/css">
		.ui:after{content:"."; height:0; visibility:hidden; display:block; clear:both;position:relative;}
		
		</style>
	</head>
	<body>
		<fieldset class="layui-elem-field layui-field-title">
	  	<legend>标签管理</legend>
	  	<blockquote class="layui-elem-quote  ui">
	  	<div  class="st_tree" style="float:left;width:50%;">
				<ul id="0">
					<img alt="" src="static/images/5-1503130Q911.gif">
				</ul>
		</div>
		<div style="float:left;width:45%;margin-top:10px;" id="leftDiv">
		
		 
		
		</div>
	  	</blockquote>
	  	
		</fieldset>
		<script type="text/javascript" src="<%=request.getContextPath()%>/static/layui/plugins/layui/layui.js"></script>
		<script type="text/javascript">
		layui.config({
			base: 'static/layui/js/'
		});
		
		layui.use(['form','element'], function() {
			 $ = layui.jquery;
			form = layui.form(),
			layer = layui.layer,
			$form = $('form');
			//验证规则
			form.verify({
				tagTypeName: function(value, item){ //value：表单的值、item：表单的DOM对象
				    if(!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(value)){
				      return '不能有特殊字符';
				    }
/* 				    if(/(^\_)|(\__)|(\_+$)/.test(value)){
				      return '用户名首尾不能出现下划线\'_\'';
				    }
				    if(/^\d+\d+\d$/.test(value)){
				      return '用户名不能全为数字';
				    } */
				  },
				limitLength:[
				             /^[.]{0,255}$/
				             ,'字段过长'
				           ] 
				});
			
			//监听提交
			form.on('submit(demo1)', function(data) {
			/* 	layer.alert(JSON.stringify(data.field), {
					title: '最终的提交信息'
				}) */
				return true;
			});
			$(document).on('click','.layui-btn',function(){
				var flag = $(this).attr('name');
				var targetId = $(this).parent().attr('name');
				if(flag=='delTagTypebtn'){//标签类目删除
					var tagName = $(this).attr('ref');
					layer.open({
						  title: '提示'
						  ,content: '您确定要删除 <u>'+tagName+'</u> 标签类目下所有标签吗？'
						  ,btn: ['确认', '取消']
						  ,btn1: function(index, layero){
							  $.ajax({
				          			 url: 'deleteTagType',
				                       type: 'post',
				                       dataType: 'json',
				                       data:{
				                    	   'tagTypeId':targetId,
				                    	   'projectId':'0000'
				                       },
				                       success: function (data) {
				                               if(data.status==200){
				                            	   alert('删除成功');
				                            	   window.location.reload();
				                               }
				                       }
				          			});
						    return true;
						  }
						  ,btn2: function(index, layero){
						    return true;
						  }
						});
				}
				if(flag=='altTagTypebtn'){//标标签类目修改
					$('#alertDelDiv').empty();
					$.post("showTagTypeById",{
						'tagTypeId':targetId
					},
					  function(data){
						var fieldset = '<fieldset class="layui-elem-field layui-field-title" >'+
       								   '<legend>修改 '+data.tagTypeName+' 标签类目</legend>'+
       								   '<blockquote class="layui-elem-quote  ui">'+
       								   '<form class="layui-form" action="alertTagType" method="post">'+
		       								'<div class="layui-form-item">'+
		       									'<label class="layui-form-label">类目名称</label>'+
		       									'<div class="layui-input-block">'+
		       										'<input type="text"  name="tagTypeName" class="layui-input"  lay-verify="tagTypeName|required|limitLength" value="'+data.tagTypeName+'">'+
		       										'<input type="hidden" name="tagTypeId" value="'+data.tagTypeId+'" >'+
		       									'</div>'+
		       								'</div>'+
		       								'<div class="layui-form-item">'+
	       									'<label class="layui-form-label">状态</label>'+
	       									'<div class="layui-input-block">';
						if(data.tagTypeStatus==1){
							fieldset+='<input type="radio" name="tagTypeStatus" value="1" title="启用" checked="">';
						}else{
							fieldset+='<input type="radio" name="tagTypeStatus" value="1" title="启用">';
						}
		       			if(data.tagTypeStatus==0){
		       				fieldset+='<input type="radio" name="tagTypeStatus" value="0" title="禁用" checked="">';
		       			}else{
		       				fieldset+='<input type="radio" name="tagTypeStatus" value="0" title="禁用" >'
		       			}
		       			fieldset+='</div>'+
							'</div>'+
							'<div class="layui-form-item">'+
								'<label class="layui-form-label">是否单选</label>'+
								'<div class="layui-input-block">';
		       			if(data.isMultiple==0){
		       				fieldset+='<input type="radio" name="isMultiple" value="0" title="单选" checked="">'
		       			}else{
		       				fieldset+='<input type="radio" name="isMultiple" value="0" title="单选" >'
		       			}
		       			if(data.isMultiple==1){
		       				fieldset+='<input type="radio" name="isMultiple" value="1" title="多选" checked="">'
		       			}else{
		       				fieldset+='<input type="radio" name="isMultiple" value="1" title="多选" >'
		       			}
		       			
	       				fieldset+='</div>'+
						'</div>';
		       			fieldset+='</div>'+
							'</div>'+
							'<div class="layui-form-item">'+
								'<label class="layui-form-label">是否必填</label>'+
								'<div class="layui-input-block">';
		       			if(data.isRequied==0){
		       				fieldset+='<input type="radio" name="isRequied" value="0" title="不必填" checked="">';
		       				fieldset+='<input type="radio" name="isRequied" value="1" title="必填" >';
		       			}else{
		       				fieldset+='<input type="radio" name="isRequied" value="0" title="不必填">';
		       				fieldset+='<input type="radio" name="isRequied" value="1" title="必填"  checked="">';
		       			}
		       			
	       				fieldset+='</div>'+
						'</div>'+
	       					'<input type="hidden" name="projectId" value="'+data.projectId+'" >'+
	       						  '<input type="hidden" name="sortNum" value="'+data.sortNum+'" >'+
	       						'<div class="layui-form-item">'+
									'<div class="layui-input-block">'+
									'<button class="layui-btn" lay-submit="" lay-filter="demo1">确定修改</button>'+
									'<button type="button" class="layui-btn layui-btn-primary" id="quxiaobtn">取消</button>'+
									'</div>'+
								'</div>'
		       						'</form>';
		       			$(fieldset).appendTo('#alertDelDiv');
		       			form.render();
					  });
				}
				if(flag=='addTagbtn'){//添加子标签
					$('#alertDelDiv').empty();
					var tagName = $(this).attr('ref');
					var fieldset = '<fieldset class="layui-elem-field layui-field-title" id="alertTagTypeF">'+
			    	'<legend>添加 '+tagName+' 标签类目的子标签</legend>'+
			    	'<blockquote class="layui-elem-quote  ui">'+
			    		'<form class="layui-form" action="addTag" method="post">'+
			    			'<div class="layui-form-item">'+
						    	'<label class="layui-form-label">标签名</label>'+
						    	'<div class="layui-input-block">'+
						      	'<input type="text" name="tagName" lay-verify="tagTypeName|required|limitLength"  class="layui-input">'+
						      	'<input type="hidden" name="tagTypeId" class="layui-input" value="'+targetId+'">'+
						      	'<input type="hidden" name="originalTag" class="layui-input" value="0">'+
						      	'<input type="hidden" name="tagStatus" class="layui-input" value="1">'+
						      	'<input type="hidden" name="projectId" class="layui-input" value="0000">'+
						    	'</div>'+
						  	'</div>'+
			    			'<div class="layui-form-item">'+
						    	'<label class="layui-form-label">对标签的描述</label>'+
						    	'<div class="layui-input-block">'+
						      	'<input type="text" name="dic" lay-verify="title"  class="layui-input">'+
						    	'</div>'+
						  	'</div>'+
			    			'<div class="layui-form-item">'+
						    	'<div class="layui-input-block">'+
						    	'<button class="layui-btn" lay-submit="" lay-filter="demo1">确定</button>'+
								'<button type="button" class="layui-btn layui-btn-primary" id="quxiaobtn">取消</button>'+
						    	'</div>'+
						  	'</div>'+
			    		'</form>'+
					'</blockquote>'+
					'</fieldset>';
					$(fieldset).appendTo('#alertDelDiv');
	       			form.render();
				}
				if(flag=='addTagTypebtn'){//添加子标签类目
					$('#alertDelDiv').empty();
					var tagName = $(this).attr('ref');
					$.post("showTagTypeById",{
						'tagTypeId':targetId
					},function(data){
						var fieldset = '<fieldset class="layui-elem-field layui-field-title" id="alertTagTypeF">'+
				    	'<legend>添加 '+tagName+' 标签类目的子类目</legend>'+
				    	'<blockquote class="layui-elem-quote  ui">'+
				    		'<form class="layui-form" action="addTagType" method="post">'+
				    			'<div class="layui-form-item">'+
							    	'<label class="layui-form-label">标签类目名</label>'+
							    	'<div class="layui-input-block">'+
							      	'<input type="text" name="tagTypeName" lay-verify="tagTypeName|required|limitLength"  class="layui-input">'+
							      	'<input type="hidden" name="parentTagTypeId" class="layui-input" value="'+targetId+'">'+
							      	'<input type="hidden" name="tagTypeStatus" class="layui-input" value="1">'+
							      	'<input type="hidden" name="projectId" class="layui-input" value="0000">'+
							      	'<input type="hidden" name="originalTagType" class="layui-input" value="0">'+
							    	'</div>'+
							  	'</div>'+
				    			'<div class="layui-form-item">'+
							    	'<label class="layui-form-label">是否单选</label>'+
							    	'<div class="layui-input-block">'+
							      	'<input type="radio" name="isMultiple" value="0" title="单选" checked>'+
							      	'<input type="radio" name="isMultiple" value="1" title="多选" >'+
							    	'</div>'+
							  	'</div>'+
				    			'<div class="layui-form-item">'+
							    	'<label class="layui-form-label">是否为必填项</label>'+
							    	'<div class="layui-input-block">';
							  	if(data.isRequied==0){
							  		fieldset += '<input type="radio" name="isRequied" value="0" title="不必填" checked>'+
							      				'<input type="radio" name="isRequied" value="1" title="必填" >';
							  	}else{
							  		fieldset += '<input type="radio" name="isRequied" value="0" title="不必填" disabled>'+
				      				'<input type="radio" name="isRequied" value="1" title="必填" checked>';
							  	}
							      	
							  	fieldset += '</div>'+
							  	'</div>'+
				    			'<div class="layui-form-item">'+
							    	'<div class="layui-input-block">'+
							    	'<button class="layui-btn" lay-submit="" lay-filter="demo1">确定</button>'+
									'<button type="button" class="layui-btn layui-btn-primary" id="quxiaobtn">取消</button>'+
							    	'</div>'+
							  	'</div>'+
				    		'</form>'+
						'</blockquote>'+
						'</fieldset>';
						$(fieldset).appendTo('#alertDelDiv');
		       			form.render();
					})
				}
				if(flag=='delTagbtn'){//删除标签
					var tagName = $(this).attr('ref');
					layer.open({
						  title: '提示'
						  ,content: '您确定要删除 <u>'+tagName+'</u> 标签吗？'
						  ,btn: ['确认', '取消']
						  ,btn1: function(index, layero){
							  $.ajax({
				          			 url: 'deleteTag',
				                       type: 'post',
				                       dataType: 'json',
				                       data:{
				                    	   'tagId':targetId,
				                    	   'projectId':'0000'
				                       },
				                       success: function (data) {
				                               if(data.status==200){
				                            	   alert('删除成功');
				                            	   window.location.reload();
				                               }
				                       }
				          			});
						    return true;
						  }
						  ,btn2: function(index, layero){
						    return true;
						  }
						});
				}
				if(flag=='altTagbtn'){//修改标签
					$('#alertDelDiv').empty();
					$.post("showTagById",{
						'tagId':targetId
					},
					  function(data){
						var fieldset = '<fieldset class="layui-elem-field layui-field-title" >'+
       								   '<legend>修改 '+data.tagName+' 标签</legend>'+
       								   '<blockquote class="layui-elem-quote  ui">'+
       								   '<form class="layui-form" action="alertTag" method="post">'+
		       								'<div class="layui-form-item">'+
		       									'<label class="layui-form-label">标签名称</label>'+
		       									'<div class="layui-input-block">'+
		       										'<input type="text"  name="tagName" class="layui-input"  lay-verify="tagTypeName|required|limitLength" value="'+data.tagName+'">'+
		       										'<input type="hidden" name="tagId" value="'+data.tagId+'" >'+
		       										'<input type="hidden" name="tagTypeId" value="'+data.tagTypeId+'" >'+
		       									'</div>'+
		       								'</div>'+
		       								'<div class="layui-form-item">'+
		       									'<label class="layui-form-label">描述</label>'+
		       									'<div class="layui-input-block">'+
		       										'<input type="text"  name="dic" class="layui-input"  value="'+data.dic+'">'+
		       									'</div>'+
		       								'</div>'+
											'</div>'+
	       									'<input type="hidden" name="projectId" value="'+data.projectId+'" >'+
	       									'<div class="layui-form-item">'+
											'<div class="layui-input-block">'+
											'<button class="layui-btn" lay-submit="" lay-filter="demo1">确定修改</button>'+
											'<button type="button" class="layui-btn layui-btn-primary" id="quxiaobtn">取消</button>'+
											'</div>'+
											'</div>'+
		       								'</form>'+
		       								'</blockquote>'+
											'</fieldset>';
		       			$(fieldset).appendTo('#alertDelDiv');
		       			form.render();
					  });
				}
				
					
			})
			//返回按钮
			$(document).on('click',"#quxiaobtn",function(){
				$('#alertDelDiv').empty();
			})
			//遍历
			function forE(data,tagTypeId){
        		$.each(data,function(v, o) {
        			$('<li><a href="#" ref="'+o.tagTypeId+'" >'+o.tagTypeName+'</a></li>'+
                		'<ul id="'+o.tagTypeId+'"></ul>').appendTo($("#"+tagTypeId));
        			if(o.tags){
        				var tag = "";
        				$.each(o.tags,function(i,t){
        					tag += '<li><a href="#" ref="'+t.tagId+'">'+t.tagName+'</a></li>';
        				})
        				$(tag).appendTo("#"+o.tagTypeId);
        			}
        			forE(o.tagLibs,o.tagTypeId);
        		})
        	}
			//获取所有标签
			$.post("showTagLib",{
						'status':1
					},
					  function(data){
						$("#0").empty();
			        		$.each(data,function(v, o) {
			        			$('<li><a href="###" ref="'+o.tagTypeId+'">'+o.tagTypeName+'</a></li>'+
			                		'<ul id="'+o.tagTypeId+'"></ul>').appendTo($("#0"));
			        			if(o.tags){
			        				var tag = "";
			        				$.each(o.tags,function(i,t){
			        					tag += '<li><a href="#" ref="'+t.tagId+'">'+t.tagName+'</a></li>';
			        				})
			        				$(tag).appendTo("#"+o.tagTypeId);
			        			}
			        			forE(o.tagLibs,o.tagTypeId);
			        		})
			        		//$('<li><a href="#" name="addtagType_a" >新增标签类目</a></li>').appendTo($("#0"));
			        		//$('<li><a href="#" name="addtag_a" >新增标签</a></li>').appendTo($("#0"));
			        		
				        	$(".st_tree").SimpleTree({
				       		 click:function(a){
				       			// console.log(a)
				        		$('#leftDiv').empty();
				       			 var tagName = $(a).html();
				       			// console.log(tagName);
			       				var targetId = $(a).attr("ref");
				       			 $('<fieldset class="layui-elem-field layui-field-title">'+
				       					'<legend>对 '+tagName+' 进行以下操作</legend>'+
				       					'<blockquote class="layui-elem-quote  ui">'+
				       						'<div name="'+targetId+'">'+
				       						'</div>'+
				       					'</blockquote>'+
				       					'</fieldset><div id="alertDelDiv"></div>').appendTo('#leftDiv');
				       			var b = $(a).attr("hasChild");
				       			if(b!="true"){//属于标签
				       				$('<button class="layui-btn layui-btn-primary layui-btn-small" name="delTagbtn" ref="'+tagName+'">删除</button>').appendTo('div[name="'+targetId+'"]');
				       				$('<button class="layui-btn layui-btn-primary layui-btn-small" name="altTagbtn" ref="'+tagName+'">修改</button>').appendTo('div[name="'+targetId+'"]');
				       			}else{
				       				$('<button class="layui-btn layui-btn-primary layui-btn-small" name="delTagTypebtn" ref="'+tagName+'">删除</button>').appendTo('div[name="'+targetId+'"]');
				       				$('<button class="layui-btn layui-btn-primary layui-btn-small" name="altTagTypebtn" ref="'+tagName+'">修改</button>').appendTo('div[name="'+targetId+'"]');
				       				$('<button class="layui-btn layui-btn-primary layui-btn-small" name="addTagbtn" ref="'+tagName+'">添加子标签</button>').appendTo('div[name="'+targetId+'"]');
				       				$('<button class="layui-btn layui-btn-primary layui-btn-small" name="addTagTypebtn" ref="'+tagName+'">添加子标签类目</button>').appendTo('div[name="'+targetId+'"]');
				       			}
					        	} 
				        	});
				        	
				        	
					  });			
		});
		</script>
	</body>
</html>