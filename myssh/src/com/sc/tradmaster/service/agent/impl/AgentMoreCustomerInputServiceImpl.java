package com.sc.tradmaster.service.agent.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.sc.tradmaster.bean.GuideRecords;
import com.sc.tradmaster.bean.Mydynamic;
import com.sc.tradmaster.bean.Project;
import com.sc.tradmaster.bean.ProjectCustomers;
import com.sc.tradmaster.bean.ProjectGuide;
import com.sc.tradmaster.bean.ShopCustomers;
import com.sc.tradmaster.bean.User;
import com.sc.tradmaster.bean.VisitRecords;
import com.sc.tradmaster.dao.BaseDao;
import com.sc.tradmaster.service.agent.AgentMoreCustomerInputService;
import com.sc.tradmaster.service.agent.impl.visitDTO.AgentMoreCustomerInputDTO;
import com.sc.tradmaster.service.dynamic.DynamicUtil;
import com.sc.tradmaster.utils.DateTime;
import com.sc.tradmaster.utils.DateUtil;
import com.sc.tradmaster.utils.ObjClone;
import com.sc.tradmaster.utils.SysContent;


/**
 * 2017-10-10
 * @author grl
 *
 */
@Service("agentMoreCustomerInputService")
public class AgentMoreCustomerInputServiceImpl implements AgentMoreCustomerInputService {

	@Resource(name="baseDao")
	private BaseDao baseDao;
	
	//获取任务信息
	@Override
	public List<AgentMoreCustomerInputDTO> findVisitDataByUser(User user, String dateStr) {
		List<AgentMoreCustomerInputDTO> visitList = new ArrayList<AgentMoreCustomerInputDTO>();
		if(user!=null && user.getParentId()!=null && !user.getParentId().equals("")){
			if(dateStr==null || dateStr.equals("")){
				dateStr = DateUtil.format(new Date(),DateUtil.PATTERN_CLASSICAL_SIMPLE);
			}
			String hql = "from VisitRecords where projectId = '"+user.getParentId()+"' and (batchVisitNo = visitNo or batchVisitNo is null ) and "
					+ "userId = '"+user.getUserId()+"' and visitStatus !=2 and arriveTime like '%"+dateStr+"%' and writeState is null";
			List<VisitRecords> list = baseDao.findByHql(hql);
			for(VisitRecords vd : list){
				AgentMoreCustomerInputDTO amcDto = new AgentMoreCustomerInputDTO();
				amcDto = amcDto.createVisitObj(vd, null);
				visitList.add(amcDto);
				//接访方式预留扩展
				//if(1==0 && vd.getCustomerId()!=null && !vd.getCustomerId().equals("")){
				//	ProjectCustomers pc = (ProjectCustomers) baseDao.loadById(ProjectCustomers.class, vd.getCustomerId());
				//}
			}
		}
		return visitList;
	}

	//获取同批次到访客户列表
	@Override
	public List<AgentMoreCustomerInputDTO> findBatchVisitDataByBatchVisitNo(Integer batchVisitNo) {
		 List<AgentMoreCustomerInputDTO> visitList= new ArrayList<>();
		if(batchVisitNo!=null && !batchVisitNo.equals("")){
			String hql = "from VisitRecords where batchVisitNo = " + batchVisitNo + " and visitStatus !=2 and writeState is null";
			List<VisitRecords> list = baseDao.findByHql(hql);
			for(VisitRecords v : list){
				if(v.getPhone()!=null && !v.getPhone().equals("")){
					AgentMoreCustomerInputDTO amcDto = new AgentMoreCustomerInputDTO();
					amcDto.createBatchVisitObj(v);
					visitList.add(amcDto);
				}
			}
		}
		return visitList;
	}

	//多客户录入    号码录入确认操作
	@Override
	public Map addOrUpdateVisitAndProCus(User user,String phone, Integer visitNo) throws Exception {
		Map map = new HashMap<>();
		boolean flag = true;
		//1.验证同组到访中手机号码是否存在，若存在，返回“同组到访号码不能重复添加”提示。
		String phoneCheckHQL = "from VisitRecords where 1=1 and batchVisitNo = "+visitNo+" and phone = '"+phone+"'";
		VisitRecords existSameBatchVisitCus = (VisitRecords) baseDao.loadObject(phoneCheckHQL);
		if(existSameBatchVisitCus!=null){
			flag = false;
		}
		//2.验证同组到访中主次到访信息;主到访手机号码为空时，更新主到访信息，如果主到访号码有有效值，添加新的次到访信息。
		//3.验证添加的该手机号码是否已是案场客户，如果是更新客户信息，如果不是添加新的案场客户。
		if(flag){
			VisitRecords plateForVisit = null;
			//2判断主到访手机号码填写情况，如果没填写，则更新phone到主到访中，否则添加新的到访
			VisitRecords mainVisit = (VisitRecords) baseDao.loadById(VisitRecords.class, visitNo);
			if(mainVisit.getPhone()!=null && !mainVisit.getPhone().equals("")){//主导方号码存在，添加新的次到访信息
				VisitRecords minorVisit = new VisitRecords();
				minorVisit.setUserId(mainVisit.getUserId());
				minorVisit.setProjectId(mainVisit.getProjectId());
				minorVisit.setVisitStatus(mainVisit.getVisitStatus());
				minorVisit.setCustomerCount(mainVisit.getCustomerCount());
				minorVisit.setPhone(phone);
				minorVisit.setArriveTime(mainVisit.getArriveTime());
				minorVisit.setReceptTime(mainVisit.getReceptTime());
				minorVisit.setAppointUserId(mainVisit.getAppointUserId());
				minorVisit.setIsNew(mainVisit.getIsNew());
				baseDao.save(minorVisit);
				plateForVisit = (VisitRecords) ObjClone.myClone(VisitRecords.class.getName(), minorVisit);
			}else{
				mainVisit.setPhone(phone);
				baseDao.saveOrUpdate(mainVisit);
				plateForVisit = (VisitRecords) ObjClone.myClone(VisitRecords.class.getName(), mainVisit);
			}
			//3判断该号码是否是案场客户，pc！=null则是案场客户，反之不是案场客户
			String hql ="from ProjectCustomers where projectId = '"+user.getParentId()+"' and projectCustomerPhone = '"+phone+"'";
			ProjectCustomers pc = (ProjectCustomers) baseDao.loadObject(hql);
			Boolean notOut = false;//未超出客户保护期(ture未超出，false超出)
			String arriveTime = mainVisit.getArriveTime();//获取客户的当前到访时间
			if(pc!=null){
				ProjectGuide pg = (ProjectGuide) baseDao.loadById(ProjectGuide.class, user.getParentId());
				Integer cusProtectDate = Integer.valueOf(pg.getCustormerProtectDays());//获取客户保护期，单位为月
				String lastTime = pc.getLastTime();//客户上一次的到访时间
				if(cusProtectDate!=null){
					notOut = SysContent.addSameMouthComperWithToday(arriveTime,lastTime,cusProtectDate);//获取判断结果
				}
			}
			if (pc != null && notOut) {// 是案场客户并且未超出客户保护期
				// 将案场客户的id回填到到访记录表中
				plateForVisit.setCustomerId(pc.getProjectCustomerId());
				baseDao.saveOrUpdate(plateForVisit);
				// 更新案场客户表
				pc.setLastTime(plateForVisit.getArriveTime());
				// 持久化案场客户信息
				baseDao.saveOrUpdate(pc);
			} else {// 不是案场客户或者已超出客户保护期
				// 获取带看备案信息
				String grhql = "from GuideRecords where applyStatus = 0 and projectId = '" + user.getParentId() + "' and customerPhone = '"+ phone + "'";
				GuideRecords gr = (GuideRecords) baseDao.loadObject(grhql);
				//判断是否是案场客户
				if(pc!=null){
					// 该用户在带看备案记录中存在且为申请状态时
					if (gr != null) {
						String shopCusId = gr.getShopCustomerId();//获取中介客户id
						ShopCustomers sc = (ShopCustomers) baseDao.loadById(ShopCustomers.class, shopCusId);//获取中介客户
						String scName = sc.getShopCustomerName();//获取中介客户姓名
						Integer vd = null;
						HashMap grMap = JSON.parseObject(gr.getRules(), HashMap.class);
						if (grMap != null) {
							// ②获取带看有效期
							vd = Integer.parseInt(grMap.get("validDays").toString());
						}
						// 获取带看是否有效
						// ①获取带看记录中的申请时间
						String applyTime = gr.getApplyTime();
						// 通过(到访时间<=有效时间+申请时间（有效）)，判断此次带看是否有效（单位：天）true未超出及有效，false超出及无效
						Boolean NoOutOf = false;
						if (vd != null) {
							NoOutOf = SysContent.addSameDaysComperWithToday(applyTime, arriveTime, vd);
						}
						Project p = (Project) baseDao.loadById(Project.class, user.getParentId());// 获取项目信息
						String otherMdcStr = "您的客户:" + scName + "到访案场。";
						if (NoOutOf) {
							// 更新带看备案记录中客户的申请状态为1:确认状态
							gr.setApplyStatus(1);
							gr.setVisitNo(visitNo);
							gr.setProjectCustomerId(pc.getProjectCustomerId());
							// 给有效到访的带看客户添加客户动态
							Mydynamic otherMdc = DynamicUtil.createOneDynamic(gr.getUserId(), user.getPhone(), otherMdcStr,
									0, DateTime.toString1(new Date()), p.getProjectId(), null, "到访案场", "0",
									pc.getProjectCustomerId(), scName, phone, p.getProjectName());
							baseDao.saveOrUpdate(otherMdc);
						} else {
							// 超过备案有效期，修改备案状态为失败
							gr.setApplyStatus(4);
							// 给置业顾问添加到访动态
							Mydynamic otherMdc = DynamicUtil.createOneDynamic(user.getUserId(), user.getPhone(),
									otherMdcStr, 0, DateTime.toString1(new Date()), p.getProjectId(), null, "到访案场", "0",
									pc.getProjectCustomerId(), scName, phone, p.getProjectName());
							baseDao.saveOrUpdate(otherMdc);
						}
						baseDao.saveOrUpdate(gr);
					}
					// 将案场客户的id回填到到访记录表中
					plateForVisit.setCustomerId(pc.getProjectCustomerId());
					baseDao.saveOrUpdate(plateForVisit);
				}else{
					String pcId = SysContent.uuid();//生成uuid
					// 该用户在带看备案记录中存在且为申请状态时
					if (gr != null) {
						String shopCusId = gr.getShopCustomerId();//获取中介客户id
						ShopCustomers sc = (ShopCustomers) baseDao.loadById(ShopCustomers.class, shopCusId);//获取中介客户
						String scName = sc.getShopCustomerName();//获取中介客户姓名
						Integer vd = null;
						HashMap grMap = JSON.parseObject(gr.getRules(), HashMap.class);
						if (grMap != null) {
							// ②获取带看有效期
							vd = Integer.parseInt(grMap.get("validDays").toString());
						}
						// 获取带看是否有效
						// ①获取带看记录中的申请时间
						String applyTime = gr.getApplyTime();
						// 通过(到访时间<=有效时间+申请时间（有效）)，判断此次带看是否有效（单位：天）true未超出及有效，false超出及无效
						Boolean NoOutOf = false;
						if (vd != null) {
							NoOutOf = SysContent.addSameDaysComperWithToday(applyTime, arriveTime, vd);
						}
						Project p = (Project) baseDao.loadById(Project.class, user.getParentId());// 获取项目信息
						String otherMdcStr = "您的客户:" + scName + "到访案场。";
						if (NoOutOf) {
							// 更新带看备案记录中客户的申请状态为1:确认状态
							gr.setApplyStatus(1);
							gr.setVisitNo(visitNo);
							gr.setProjectCustomerId(pcId);
							// 给有效到访的带看客户添加客户动态
							Mydynamic otherMdc = DynamicUtil.createOneDynamic(gr.getUserId(), user.getPhone(), otherMdcStr,
									0, DateTime.toString1(new Date()), p.getProjectId(), null, "到访案场", "0",
									pcId, scName, phone, p.getProjectName());
							baseDao.saveOrUpdate(otherMdc);
						} else {
							// 超过备案有效期，修改备案状态为失败
							gr.setApplyStatus(4);
							// 给置业顾问添加到访动态
							Mydynamic otherMdc = DynamicUtil.createOneDynamic(user.getUserId(), user.getPhone(),
									otherMdcStr, 0, DateTime.toString1(new Date()), p.getProjectId(), null, "到访案场", "0",
									pcId, scName, phone, p.getProjectName());
							baseDao.saveOrUpdate(otherMdc);
						}
						baseDao.saveOrUpdate(gr);
					}
					ProjectCustomers newPC = new ProjectCustomers();
					//String cId = SysContent.uuid();
					newPC.setProjectId(user.getParentId());
					newPC.setCreateUserId(user.getUserId());
					newPC.setCreateTime(plateForVisit.getArriveTime());
					newPC.setOwnerUserId(user.getUserId());
					newPC.setOwnerStartTime(DateTime.toString1(new Date()));
					newPC.setProjectCustomerId(pcId);
					newPC.setProjectCustomerPhone(phone);
					newPC.setLastTime(plateForVisit.getArriveTime());
					// 持久化案场客户信息
					baseDao.saveOrUpdate(newPC);
					// 将案场客户的id回填到到访记录表中
					plateForVisit.setCustomerId(pcId);
					baseDao.saveOrUpdate(plateForVisit);
				}
			}
			map.put("code", "200");
		}else{
			map.put("code","同组到访号码不能重复添加");
		}
		return map;
	}

}
