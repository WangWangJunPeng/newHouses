// $(document).ready(function($) {

	
// });

function startTime()
{
	var today=new Date()
	var h=today.getHours()
	var m=today.getMinutes()
	var s=today.getSeconds()
	var today = getDay(1);
	m=checkTime(m)
	s=checkTime(s)
	document.getElementById('toadyTime').innerHTML=today+ ' ' + h+":"+m+":"+s
	t=setTimeout('startTime()',1000)
	}

function checkTime(i)
{
if (i<10) 
  {i="0" + i}
  return i
}

function getDay(type){
	var oDate = new Date();
	var year = oDate.getFullYear();   //获取系统的年；
  	var mon = oDate.getMonth()+1;   //获取系统月份，由于月份是从0开始计算，所以要加1
	var day = oDate.getDate(); // 获取系统日，
	var hour = oDate.getHours(); //获取系统时，
	var min = oDate.getMinutes(); //分
	var sec = oDate.getSeconds(); //秒
	//返回时分秒
	if (type==0) {
		return hour + ':' + min + ':' + sec;
	}else if (type==1) {
		//返回年月日
		return year + '年' + mon + '月' + day + '日';
	}else if (type==2) {
		//返回年月日
		return dateFormat(oDate);
	}
	return oDate;
}

function dateFormat (date, pattern) {
	  var week = {'0':'日', '1':'一', '2':'二', '3':'三', '4':'四', '5':'五', '6':'六'};
	  pattern = pattern == null ? 'yyyy-MM-dd HH:mm:ss' : pattern;
	  var o = {
	    'M+': date.getMonth() + 1, // 月份
	    'd+': date.getDate(), // 日
	    'h+': date.getHours() % 12 === 0 ? 12 : date.getHours() % 12, // 小时
	    'H+': date.getHours(), // 小时
	    'm+': date.getMinutes(), // 分
	    's+': date.getSeconds(), // 秒
	    'q+': Math.floor((date.getMonth() + 3) / 3), // 季度
	    'S': date.getMilliseconds() // 毫秒
	  };
	  if (/(y+)/.test(pattern)) {
	    pattern = pattern.replace(RegExp.$1, (date.getFullYear() + '').substring(4 - RegExp.$1.length));
	  }
	  if (/(E+)/.test(pattern)) {
	    pattern = pattern.replace(RegExp.$1, ((RegExp.$1.length > 1) ? (RegExp.$1.length > 2 ? '星期' : '周') : '') + week[date.getDay() + '']);
	  }
	  for (var k in o) {
	    if (new RegExp('(' + k + ')').test(pattern)) {
	      pattern = pattern.replace(RegExp.$1, (RegExp.$1.length === 1) ? (o[k]) : (('00' + o[k]).substring(('' + o[k]).length)));
	    }
	  }
	  return pattern;
	}