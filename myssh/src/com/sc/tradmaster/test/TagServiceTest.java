package com.sc.tradmaster.test;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sc.tradmaster.bean.House;
import com.sc.tradmaster.bean.Tag;
import com.sc.tradmaster.bean.TagType;
import com.sc.tradmaster.bean.User;
import com.sc.tradmaster.service.projectcustomer.ProjectCustomerService;
import com.sc.tradmaster.service.projectcustomer.impl.dto.CustomerAnalyze;
import com.sc.tradmaster.service.projectcustomer.impl.dto.CustomerManagerInfoDTO;
import com.sc.tradmaster.service.tagService.TagService;
import com.sc.tradmaster.service.tagService.impl.dto.TagLib;
import com.sc.tradmaster.service.user.UserService;
import com.sc.tradmaster.service.visitRecords.VisitRecordsService;
import com.sc.tradmaster.utils.SysContent;

public class TagServiceTest {
	private ClassPathXmlApplicationContext ac = null;
	private TagService ts = null;
	private UserService us = null;
	private ProjectCustomerService pc = null;
	private VisitRecordsService vr = null;
	
	@Before
	public void init(){
		ac = new ClassPathXmlApplicationContext("spring*.xml");
		ts = ac.getBean("tagService",TagService.class);
		us = ac.getBean("userService",UserService.class);
		pc = ac.getBean("projectCustomerService",ProjectCustomerService.class);
		vr = ac.getBean("visitRecordsService", VisitRecordsService.class);
		
	}
	
	//展示所有标签
	@Test
	public void showTagLib(){
		List<TagLib> showTagLib = ts.showTagLib(null, "10000", 1,null);
		System.out.println(showTagLib);
	}
	//给目标添加标签
	@Test
	public void addTagRelationTest(){
		Integer[] tags = {104,124};
		ts.addTagRelation(tags,"ssss");
	}
	
	@Test
	//添加标签
	public void test1(){
		Tag tag = new Tag();
		tag.setParentTagId(87);
		tag.setProjectId("1111");
		tag.setTagName("测试所用");
		ts.addTag(tag);
	}
	
	@Test
	//添加标签类目
	public void test2(){
		TagType tt = new TagType();
		tt.setParentTagTypeId(97);
		tt.setProjectId("1111");
		tt.setTagTypeName("测试阿萨德");
		tt.setTagTypeStatus(1);
		tt.setIsMultiple(1);
		ts.addTagType(tt);
	}
	
	@Test
	public void test3(){
		ts.dropTag(113, "1111");
	}
	
	@Test
	public void test4(){
		ts.dropTagType(106, "1111");
	}
	
	
	@Test
	public void test5(){
		List<TagType> showRootTagType = ts.showRootTagType("1111",97);
		System.out.println(showRootTagType);
	}
	
	@Test
	public void test6(){
		Tag tag = new Tag();
		tag.setProjectId("1111");
		tag.setTagId(107);
		tag.setTagName("老城区");
		tag.setDic("我是老城区");
		ts.updateTag(tag);
	}
	
	@Test
	public void test7(){
		TagType tt = new TagType();
		tt.setIsMultiple(0);
		tt.setParentTagTypeId(11);
		tt.setProjectId("1111");
		ts.updateTagType(tt);
	}
	@Test
	public void test8(){
		List<TagLib> showTagLib_use = ts.showTagLib_use("asdwd", 97, "1111");
		System.out.println(showTagLib_use);
	}
	
	@Test
	public void test9(){
		ts.add_copyTagLib("10000");
//		ts.add_copyTagLib("2c1ce8b2432d489a89ba28143d6b99e2");
//		ts.add_copyTagLib("079af40add134de88e41fe716bffb03c");
//		ts.add_copyTagLib("48a91ac3c316400c9aab7efa83478dbc");
//		ts.add_copyTagLib("527fa0a4d3094458abf72d0179d53060");
//		ts.add_copyTagLib("7015dda84cd046d19c2d09644a90a484");
//		ts.add_copyTagLib("731cdd5779784f6eaa4aa8d22a4cce91");
//		ts.add_copyTagLib("8a4f9ee6f4914564b1ef339f7c90f414");
//		ts.add_copyTagLib("8a5f00c11a5744a1b18463f2161f542e");
//		ts.add_copyTagLib("8bfea1c5416d4dd8bff604ec472f4128");
//		ts.add_copyTagLib("933c5d2e9cf2491292efaf8107981394");
//		ts.add_copyTagLib("9e77a0281b1e460dbeb7180c0e17972b");
//		ts.add_copyTagLib("b0d5436210ba4a73b99b5a18baa747b9");
//		ts.add_copyTagLib("b446a2f6360f42e3ba922852df6c6e43");
//		ts.add_copyTagLib("bb41ade7a1d74b24807ed1ca48f1c8bc");
//		ts.add_copyTagLib("ce89517a0e164230b2f9b9699a7b9b86");
//		ts.add_copyTagLib("f1331c2cd24841f1a5256c92b1a379e4");
		ts.add_copyTagLib("1408776faf1446b2a16493af0d43dcef");
		ts.add_copyTagLib("46b95d25f95642dbb6d68852b8efebf6");
		ts.add_copyTagLib("f5786ee5c000448cb35877e4578c9f10");
		ts.add_copyTagLib("4a87f96bff9a4da8be4a2b91253c7dca");
		ts.add_copyTagLib("bf9aff3fdbe14730a2861032d72d5a87");
		ts.add_copyTagLib("0058610814e93ca546e274e8");
	}
	
	@Test
	public void test10(){
		List<Tag> findTargetTag = ts.findTargetTag("0862548c775941e6bba0632b967975c5");
		System.out.println(findTargetTag);
	}
	
	@Test
	public void Test11(){
		String paw = SysContent.md5("2059");
		System.out.println(paw);
	}
	@Test
	public void Test12(){
		Integer[] customerRandom = ts.getCustomerRandom("2c1ce8b2432d489a89ba28143d6b99e2");
		System.out.println(Arrays.toString(customerRandom));
	}
	
	@Test
	public void Test13(){
		List<House> matchingHouseTag = ts.matchingHouseTag(new Integer[]{8850,8908},"sss");
		System.out.println(matchingHouseTag.size());
		
	}
	@Test
	public void Test14(){
		ts.update_maintainTagType("8333078caf314bdb951bde86f047d4ac");
	}
	@Test
	public void Test15(){
		ts.update_maintainTagRelation();
	}
	@Test
	public void Test16(){
		List<User> findProjectAgentByLimit = us.findProjectAgentByLimit(5,"10000");
		for(User u : findProjectAgentByLimit){
			System.out.println(u.getUserId()+","+u.getUserCaption());
		}
	}
	@Test
	public void Test17(){
		Map<String, Object> searchProjectCoustomers = pc.searchProjectCoustomers(new Integer[]{}, new Integer[]{},new  String[]{}, new Integer[]{}, "10000", 1, 10);
		System.out.println(searchProjectCoustomers);
		System.out.println(1);
	}
	@Test
	public void Test18(){
		Map<String, List<Tag>> findTagTypeAndTag2Map = ts.findTagTypeAndTag2Map("45dafafb37834b939144fc22bde9fdd6");
		System.out.println(findTagTypeAndTag2Map);
	}
	@Test
	public void Test19(){
		CustomerManagerInfoDTO findOneCustomerInfo = pc.findOneCustomerInfo("cac4c3d8249e4a93913cbd09c8d59343");
		System.out.println(findOneCustomerInfo.getTracks().size());
		System.out.println(findOneCustomerInfo);
	}
	
	@Test
	public void Test20(){
		CustomerAnalyze analyze = pc.getAnalyze("10000");
		System.out.println(analyze);
		System.out.println(1);
	}
	
	@Test
	public void Test21() throws ParseException{
		Map<String, Object> map = vr.findOneDayVisitNum("2017-08-24", "2017-09-23","ac500158ccd148e9a9e9fd3c42f5d76a");
		
		System.out.println(map);
	}

	@Test
	public void Test22() throws ParseException{
		Map<String, Object> map = vr.findOneDayVisitNumTwo("2017-08-24", "2017-09-23","ac500158ccd148e9a9e9fd3c42f5d76a");
		
		System.out.println(map);
	}
}
