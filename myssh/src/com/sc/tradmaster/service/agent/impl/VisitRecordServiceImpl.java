package com.sc.tradmaster.service.agent.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.sc.tradmaster.bean.AgentRank;
import com.sc.tradmaster.bean.AgentRankSeting;
import com.sc.tradmaster.bean.GuideRecords;
import com.sc.tradmaster.bean.Mydynamic;
import com.sc.tradmaster.bean.Project;
import com.sc.tradmaster.bean.ProjectCustomers;
import com.sc.tradmaster.bean.ProjectGuide;
import com.sc.tradmaster.bean.Role;
import com.sc.tradmaster.bean.SignRecords;
import com.sc.tradmaster.bean.User;
import com.sc.tradmaster.bean.VisitRecords;
import com.sc.tradmaster.dao.BaseDao;
import com.sc.tradmaster.service.agent.AgentVisitRecordService;
import com.sc.tradmaster.service.agent.impl.visitDTO.AgentRecessionRecordsDTO;
import com.sc.tradmaster.service.agent.impl.visitDTO.AgentVisitRecordDTO;
import com.sc.tradmaster.service.agent.impl.visitDTO.Customer;
import com.sc.tradmaster.service.agent.impl.visitDTO.ProjectCustomerDTO;
import com.sc.tradmaster.service.agent.impl.visitDTO.UserDTO;
import com.sc.tradmaster.service.dynamic.DynamicUtil;
import com.sc.tradmaster.service.visitRecords.impl.machine.AgentDTO;
import com.sc.tradmaster.utils.DateTime;
import com.sc.tradmaster.utils.DateUtil;
import com.sc.tradmaster.utils.ObjClone;
import com.sc.tradmaster.utils.Page;
import com.sc.tradmaster.utils.SysContent;

/**
 * 2017-02-03
 * @author grl
 *
 */
@Service("agentVisitRecordService")
public class VisitRecordServiceImpl implements AgentVisitRecordService {

	@Resource(name="baseDao")
	private BaseDao baseDao;
	
	@Override
	public List<VisitRecords> findVisitInfoByUser(User user,String data) {
		if(user!=null && user.getParentId()!=null && !user.getParentId().equals("")){
			String hql = "from VisitRecords where projectId = '"+user.getParentId()+"' and "
					+ "userId = '"+user.getUserId()+"' and visitStatus !=2 and arriveTime like '%"+data+"%' and writeState is null";
			return baseDao.findByHql(hql);
		}else{
			return new ArrayList<VisitRecords>();
		}
		
	}

	@Override
	public ProjectCustomerDTO findCustomerInfoByPhone(User u ,String phone) {
		if(phone!=null && !phone.equals("")){
			String hql = "from ProjectCustomers where projectId = '"+u.getParentId()+"' and projectCustomerPhone = '"+phone+"'";
			ProjectCustomers pc = (ProjectCustomers) baseDao.loadObject(hql);
			User agent = u;
			if(pc!=null){
				if(pc.getOwnerUserId()!=null){
					agent = (User) baseDao.loadById(User.class, pc.getOwnerUserId());
				}
				ProjectCustomerDTO pcDto = new ProjectCustomerDTO();
				ProjectCustomerDTO creatyPcDto = pcDto.creatyProjectCustomer(pc,agent);
				return creatyPcDto;
			}
		}
		return null;
	}
	
	@Override
	public void addorUpdataVistInfo(User user, Integer visitNo, String phone){
		if(user!=null && phone!=null && !phone.equals("")){
			VisitRecords vr = (VisitRecords) baseDao.loadById(VisitRecords.class, visitNo);
			vr.setPhone(phone);
			baseDao.saveOrUpdate(vr);
		}
	}

	@Override
	public ProjectCustomers addOrUpdateAgentInsertCustomerInfo(User user,String cName, String phone,String desc,Integer visitNo) throws ParseException {
		if(user!=null && phone!=null && !phone.equals("")){
			ProjectCustomers procu = new ProjectCustomers();
			//获取当前phone的用户信息
			String hql ="from ProjectCustomers where projectId = '"+user.getParentId()+"' and projectCustomerPhone = '"+phone+"'";
			ProjectCustomers pc = (ProjectCustomers) baseDao.loadObject(hql);
			//获取该项目带看信息
			String chql = "from ProjectGuide where projectId = '"+user.getParentId()+"'";
			//ProjectGuide pg = (ProjectGuide) baseDao.loadObject(chql);
			//获取到访记录信息
			VisitRecords vsr = (VisitRecords) baseDao.loadById(VisitRecords.class, visitNo);
			//获取带看备案信息
			String grhql = "from GuideRecords where projectId = '"+user.getParentId()+"' and customerPhone = '"+phone+"'";
			GuideRecords gr = (GuideRecords) baseDao.loadObject(grhql);
			Integer cpd = null;
			Integer vd = null;
			if(gr!=null){
				HashMap grMap = JSON.parseObject(gr.getRules(), HashMap.class);
				//获取该项目的客户保护期
				//int cpd = Integer.parseInt(pg.getCustormerProtectDays());
				if(grMap!=null){
					cpd = Integer.parseInt(grMap.get("custormerProtectDays").toString());
					//②获取带看有效期
					vd = Integer.parseInt(grMap.get("validDays").toString());
				}
			}
			//获取客户的当前到访时间
			String arriveTime = vsr.getArriveTime();
			//判断是否超过客户保护期，未超出true，超出false
			Boolean noOut = false;
			if(pc!=null){
				//客户上一次的到访时间
				String lastTime = pc.getLastTime();
				if(cpd!=null){
					noOut = SysContent.addSameMouthComperWithToday(arriveTime,lastTime,cpd);
				}
			}
			//判断该用户是否为该案场客户,pc!=null,则该用户为当前案场的客户，未超出案场客户保护期时回填客户id到到访记录中
			if(pc!=null && noOut){
				// 将案场客户的id回填到到访记录表中
				vsr.setCustomerId(pc.getProjectCustomerId());
				// 更新案场客户表
				pc.setProjectCustomerName(cName);
				if(pc.getCreateUserId()==null || pc.getCreateUserId().equals("")){
					pc.setCreateUserId(user.getUserId());
				}
				if(pc.getCreateTime()==null || pc.getCreateTime().equals("")){
					pc.setCreateTime(vsr.getArriveTime());
				}
				if(pc.getOwnerUserId()==null || pc.getOwnerUserId().equals("")){
					pc.setOwnerUserId(user.getUserId());
				}
				if(pc.getOwnerStartTime()==null || pc.getOwnerStartTime().equals("")){
					pc.setOwnerStartTime(DateTime.toString1(new Date()));
				}
				pc.setProjectCustomerPhone(phone);
				pc.setDescription(desc);
				pc.setLastTime(vsr.getArriveTime());
				// 持久化案场客户信息
				baseDao.saveOrUpdate(pc);
			}else{
				//生成uuid
				String cId = SysContent.uuid();
				//该用户在带看备案记录中存在且为申请状态时
				if(gr!=null && gr.getApplyStatus().equals(0)){
					//获取带看是否有效
					//①获取带看记录中的申请时间
					String applyTime = gr.getApplyTime();
					//通过(到访时间<=有效时间+申请时间（有效）)，判断此次带看是否有效（单位：天）true未超出及有效，false超出及无效
					Boolean NoOutOf = false;
					if(vd!=null){
						NoOutOf = SysContent.addSameDaysComperWithToday(applyTime,arriveTime,vd);
					}
					Project p = (Project) baseDao.loadById(Project.class,user.getParentId());//获取项目信息
					String otherMdcStr = "您的客户:"+cName+"到访案场。";
					if(NoOutOf){
						//更新带看备案记录中客户的申请状态为1:确认状态
						gr.setApplyStatus(1);
						gr.setVisitNo(visitNo);
						gr.setProjectCustomerId(cId);
						//给有效到访的带看客户添加客户动态
						Mydynamic otherMdc = DynamicUtil.createOneDynamic(gr.getUserId(), user.getPhone(), otherMdcStr, 0, DateTime.toString1(new Date()),
								p.getProjectId(),null,"到访案场","0",cId,cName,phone,p.getProjectName());
						baseDao.saveOrUpdate(otherMdc);
					}else{
						//超过备案有效期，修改备案状态为失败
						gr.setApplyStatus(4);
						//给置业顾问添加到访动态
						Mydynamic otherMdc = DynamicUtil.createOneDynamic(user.getUserId(), user.getPhone(), otherMdcStr, 0, DateTime.toString1(new Date()),
								p.getProjectId(),null,"到访案场","0",cId,cName,phone,p.getProjectName());
						baseDao.saveOrUpdate(otherMdc);
					}
					baseDao.saveOrUpdate(gr);
				}
				//以上条件不成立时和成立时都要执行的逻辑
				
				// 将到访记录表中的填写转台更改为1
				vsr.setWriteState(1);
				if(pc!=null){
					// 将案场客户的id回填到到访记录表中
					vsr.setCustomerId(pc.getProjectCustomerId());
					
					// 更新案场客户表
					pc.setProjectCustomerName(cName);
					pc.setProjectCustomerPhone(phone);
					if(pc.getCreateUserId()==null || pc.getCreateUserId().equals("")){
						pc.setCreateUserId(user.getUserId());
					}
					if(pc.getCreateTime()==null || pc.getCreateTime().equals("")){
						pc.setCreateTime(vsr.getArriveTime());
					}
					if(pc.getOwnerUserId()==null || pc.getOwnerUserId().equals("")){
						pc.setOwnerUserId(user.getUserId());
					}
					if(pc.getOwnerStartTime()==null || pc.getOwnerStartTime().equals("")){
						pc.setOwnerStartTime(DateTime.toString1(new Date()));
					}
					pc.setDescription(desc);
					pc.setLastTime(vsr.getArriveTime());
					// 持久化案场客户信息
					baseDao.saveOrUpdate(pc);
				}else{
					// 新增一个案场客户
					procu = new ProjectCustomers();
					//String cId = SysContent.uuid();
					procu.setProjectId(user.getParentId());
					procu.setCreateUserId(user.getUserId());
					procu.setCreateTime(vsr.getArriveTime());
					procu.setOwnerUserId(user.getUserId());
					procu.setOwnerStartTime(DateTime.toString1(new Date()));
					procu.setProjectCustomerId(cId);
					procu.setProjectCustomerName(cName);
					procu.setProjectCustomerPhone(phone);
					procu.setDescription(desc);
					procu.setLastTime(vsr.getArriveTime());
					// 持久化案场客户信息
					baseDao.saveOrUpdate(procu);
					// 将案场客户的id回填到到访记录表中
					vsr.setCustomerId(cId);
				}
			}
			//将到访记录保存到到访记录表中
			vsr.setVisitStatus(1);
			vsr.setCustomerName(cName);
			vsr.setPhone(phone);
			vsr.setReceptTime(vsr.getArriveTime());
			// 将到访记录表中的填写转台更改为1
			vsr.setWriteState(1);
			// 持久化客户到访信息
			baseDao.saveOrUpdate(vsr);
			return procu;
		}
		return null;
	}

	/**
	 * 通过projectId获得当前案场的所有置业顾问列表
	 * @return
	 */
	@Override
	public List findAgents(String projectId) {
		String hql = "from User where parentId = '" + projectId + "' and userStatus = 1";
		List<User> list = baseDao.findByHql(hql);
		List<UserDTO> udtoList = new ArrayList<>();
		for (User user : list) {
			Set<Role> roleId = user.getRoleId();
			for (Role role : roleId) {
				if (role.getRoleId() == 3) {
					UserDTO udto = new UserDTO();
					UserDTO uu = udto.creatUserDTO(user);
					udtoList.add(uu);
				}
			}
		}
		return udtoList;
	}
	
	/**
	 * 通过projectId获得当前案场的所有置业顾问列表
	 * @return
	 */
	@Override
	public void findAgentsPageList(String projectId,Page page) {
		String todayStr = DateUtil.format(new Date(), DateUtil.PATTERN_CLASSICAL_SIMPLE);
		String sql = "SELECT u.userId userId,u.userCaption userCaption,u.photo photo,u.phone phone,u.groupName groupName"
				+ " FROM t_users u,(SELECT u_id userId,r_id FROM user_role WHERE r_id = 3) r"
				+ " WHERE u.userStatus = 1 and u.userId = r.userId and u.parentId = '"+projectId+"' LIMIT "+page.getStart()+","+page.getLimit();
		String totalSql = "SELECT count(*) FROM t_users u,(SELECT u_id userId,r_id FROM user_role WHERE r_id = 3) r"
				+ " WHERE u.userStatus = 1 and u.userId = r.userId and u.parentId = '"+projectId+"'";
		Integer total = baseDao.findCountBySql(totalSql);
		String[] colums = {"userId","userCaption","photo","phone","groupName"};
		String[] types = {"String","String","String","String","String"};
		List<UserDTO> list = baseDao.queryDTOBySql(sql,UserDTO.class, colums, types);
		List li = new ArrayList<>();
		for(UserDTO ud : list){
			String uId = ud.getUserId();
			String hql = "from VisitRecords where projectId = '"+projectId+"' and userId = '"+uId+"' and arriveTime "
					+ " like '"+todayStr+"%' and leaveTime is null";//接访中的
			VisitRecords vr = (VisitRecords) baseDao.loadObject(hql);
			if(vr!=null){
				ud.setStutas("接访中");
			}else{
				String signInHql = "from SignRecords where parentId = '"+projectId+"' and userId = '"+uId+"' "
								+ "and signInTime like '"+todayStr+"%' and signOutTime is null";
				SignRecords sr = (SignRecords) baseDao.loadObject(signInHql);
				if(sr!=null){
					ud.setStutas("排队中");
				}else{
					ud.setStutas("休息中");
				}
			}
			li.add(ud);
		}
		page.setTotal(total);
		page.setRoot(li);
	}
	
	//获取待签到顾问列表
	@Override
	public void findAgents(String projectId,Page page) {
		String todayStr = DateUtil.format(new Date(), DateUtil.PATTERN_CLASSICAL_SIMPLE);
		String todayStartTime = SysContent.getStartTime();
		String todayEndTime = SysContent.getEndTime();
		Integer start = page.getStart();
		Integer limit = page.getLimit();
		String totalSql = "SELECT count(*) FROM (SELECT u.userId,u.parentId,u.phone,u.photo FROM t_users u , user_role ur WHERE u.parentId = '"+projectId+"' "
				+ "and u.userId = ur.u_id and ur.r_id = 3 and u.userStatus = 1) "
				+ "agent where agent.userId not in (SELECT userId FROM t_signrecords  WHERE parentId = '"+projectId+"' AND signInTime like '"+todayStr+"%' "
				+ " and  signOutTime is null)";
		String sql = "SELECT agent.groupName groupName, agent.userCaption name,agent.userId id,agent.parentId proId,agent.phone phone,agent.photo photo FROM "
				+ "(SELECT * FROM t_users u , user_role ur WHERE u.parentId = '"+projectId+"' "
				+ "and u.userId = ur.u_id and ur.r_id = 3 and u.userStatus = 1) "
				+ "agent where agent.userId not in (SELECT userId FROM t_signrecords  WHERE parentId = '"+projectId+"' AND signInTime like '"+todayStr+"%' "
				+ " and signOutTime is null) limit "+page.getStart()+","+page.getLimit();
		int total = baseDao.findCountBySql(totalSql);
		String[] colums = {"id","name","photo","phone","proId","groupName"};
		String[] types = {"String","String","String","String","String","String"};
		List<User> agentList = baseDao.queryDTOBySql(sql, AgentDTO.class, colums, types);
		page.setTotal(total);
		page.setRoot(agentList);
	}

	/**
	 * 获取签到签退记录
	 * @return 
	 */
	@Override
	public List<SignRecords> findAllSignAndOutRecordList(User user, String projectId, String checkTime) {
			Date date = DateUtil.parse(checkTime);
			String todayStr = DateUtil.format(new Date(), DateUtil.PATTERN_CLASSICAL_SIMPLE);
			String start = SysContent.getStartTime2(date);
			String end = SysContent.getEndTime2(date);
			String hql = "from SignRecords where parentId = '"+projectId+"' and signInTime like '"+todayStr+"%' and signOutTime is null";
			return baseDao.findByHql(hql);
	}

	//多客户录入	上传到访(更新内容：到方时，回填到访批次号；送别时，送别同批次的所有到访客户)
	@Override
	public Integer addOrUpdateMoreVisitReocrdFromExcel(VisitRecords vr,Integer checkTeam) throws Exception {
		if(vr!=null){
			String userId = null;
			Integer visitNo = null;
			if(vr.getVisitNo()!=null && !vr.getVisitNo().equals("")){//送别或者录入客户手机号码
				VisitRecords newVr = (VisitRecords) baseDao.loadById(VisitRecords.class, vr.getVisitNo());//获取到访记录
				if(newVr!=null){//到访记录存在
					userId = newVr.getUserId();
					if(vr.getArriveTime()!=null){
						newVr.setArriveTime(vr.getArriveTime());
					}
					if(vr.getReceptTime()!=null){
						newVr.setReceptTime(vr.getReceptTime());
					}
					if(vr.getPhone()!=null){
						newVr.setPhone(vr.getPhone());
					}
					if(vr.getProjectId()!=null){
						newVr.setProjectId(vr.getProjectId());
					}
					if(vr.getUserId()!=null){
						newVr.setUserId(vr.getUserId());
					}
					if(vr.getVisitStatus()!=null){
						newVr.setVisitStatus(vr.getVisitStatus());
					}
					if(vr.getCustomerCount()!=null){
						newVr.setCustomerCount(vr.getCustomerCount());
					}
					if(vr.getLeaveTime()!=null && !vr.equals("")){//送别客户
						newVr.setLeaveTime(vr.getLeaveTime());
						String sameBatchVisitHQL = "from VisitRecords where batchVisitNo != visitNo and batchVisitNo = "+vr.getVisitNo();
						List<VisitRecords> sameBatchVisit = baseDao.findByHql(sameBatchVisitHQL);//获取同批次的次到访记录
						//送别同批次的次到访客户
						for(VisitRecords svr : sameBatchVisit){
							svr.setVisitStatus(vr.getVisitStatus());
							svr.setLeaveTime(vr.getLeaveTime());
							baseDao.saveOrUpdate(svr);
						}
					}
					if(vr.getPhone()!=null && !vr.getPhone().equals("")){//到访phone存在
						//更新到访记录时，可能存在customid不存在的情况，这是需要通过phone去load客户，如果客户不存在则添加客户信息，并将costormid回填到到访记录中
						if(newVr.getCustomerId()!=null && !newVr.getCustomerId().equals("")){//到访客户存在案场客户信息
							//获取案场客户信息
							ProjectCustomers newPc = (ProjectCustomers) baseDao.loadById(ProjectCustomers.class, newVr.getCustomerId());
							if(newPc!=null){
								//更新该客户的最后到访记录
								newPc.setLastTime(newVr.getArriveTime());
								newPc.setProjectCustomerPhone(newVr.getPhone());
								baseDao.saveOrUpdate(newPc);
							}
						}else{//到访客户不存在案场客户信息
							String proCusHql = "from ProjectCustomers where projectId = '"+vr.getProjectId()+"' and (projectCustomerPhone = '"+vr.getPhone()+"'"
									+ " or morePhoneNum like '"+vr.getPhone()+"')";
							ProjectCustomers newPc = (ProjectCustomers) baseDao.loadObject(proCusHql);
							if(newPc!=null){//案场客户存在
								//更新该客户的最后到访记录
								newPc.setLastTime(newVr.getArriveTime());
								baseDao.saveOrUpdate(newPc);
								newVr.setCustomerId(newPc.getProjectCustomerId());
								newVr.setCustomerName(newPc.getProjectCustomerName());
								newVr.setPhone(newPc.getProjectCustomerPhone());
								newVr.setMorePhoneNum(newPc.getMorePhoneNum());
							}else{//案场客户不存在
								ProjectCustomers newPc1 = new ProjectCustomers();
								String customerId = SysContent.uuid();
								newPc1.setProjectCustomerId(customerId);
								newPc1.setCreateTime(vr.getArriveTime());
								if(vr.getUserId()!=null && !vr.getUserId().equals("")){
									newPc1.setCreateUserId(vr.getUserId());
									newPc1.setOwnerUserId(vr.getUserId());
								}else{
									newPc1.setCreateUserId(newVr.getUserId());
									newPc1.setOwnerUserId(newVr.getUserId());
								}
								if(vr.getCustomerName()!=null){
									newPc1.setProjectCustomerName(vr.getCustomerName());
								}
								if(vr.getPhone()!=null){
									newPc1.setProjectCustomerPhone(vr.getPhone());
								}
								if(vr.getProjectId()!=null){
									newPc1.setProjectId(vr.getProjectId());
								}
								if(vr.getArriveTime()!=null){
									newPc1.setLastTime(vr.getArriveTime());
									newPc1.setOwnerStartTime(vr.getArriveTime());
								}
								if(vr.getArriveTime()!=null && !vr.getArriveTime().equals("")){
									newPc1.setLastTime(vr.getArriveTime());
								}else{
									newPc1.setLastTime(newVr.getArriveTime());
								}
								//添加新的案场客户
								baseDao.saveOrUpdate(newPc1);
								//将新客户的id回填到到访记录表
								newVr.setCustomerId(customerId);
								if(vr.getIsNew()!=null && !vr.getIsNew().equals("")){
									newVr.setIsNew(vr.getIsNew());
								}else{
									newVr.setIsNew(true);
								}
							}
						}
						
					}
					baseDao.saveOrUpdate(newVr);
					visitNo = newVr.getVisitNo();
					vr.setCheckWay(newVr.getCheckWay());//送别时把接访方法给前台传过来的对象中，在下面的送别排序会用到
				}
			}else{//接访
				userId = vr.getUserId();
				if(vr.getPhone()!=null && !vr.getPhone().equals("")){//上传的vr到访phone存在
					String proCusHql = "from ProjectCustomers where projectId = '"+vr.getProjectId()+"' and ( projectCustomerPhone = '"+vr.getPhone()+"'"
							+ " or morePhoneNum like '"+vr.getPhone()+"')";
					ProjectCustomers pc = (ProjectCustomers) baseDao.loadObject(proCusHql);//获取该号码的案场客户
					if(pc!=null){//客户存在
						pc.setLastTime(vr.getArriveTime());//更新客户的最后一次到访时间
						baseDao.saveOrUpdate(pc);
						//回填案场客户信息到到访表
						vr.setCustomerId(pc.getProjectCustomerId());
						vr.setCustomerName(pc.getProjectCustomerName());
						vr.setPhone(pc.getProjectCustomerPhone());
						vr.setMorePhoneNum(pc.getMorePhoneNum());
					}else{//客户不存在
						ProjectCustomers newPc = new ProjectCustomers();//添加一个新的案场客户
						String customerId = SysContent.uuid();
						newPc.setProjectCustomerId(customerId);
						newPc.setCreateTime(vr.getArriveTime());
						if(vr.getUserId()!=null && !vr.getUserId().equals("")){
							newPc.setCreateUserId(vr.getUserId());
							newPc.setOwnerUserId(vr.getUserId());
						}
						if(vr.getCustomerName()!=null){
							newPc.setProjectCustomerName(vr.getCustomerName());
						}
						if(vr.getPhone()!=null){
							newPc.setProjectCustomerPhone(vr.getPhone());
						}
						if(vr.getProjectId()!=null){
							newPc.setProjectId(vr.getProjectId());
						}
						if(vr.getArriveTime()!=null){
							newPc.setLastTime(vr.getArriveTime());
							newPc.setOwnerStartTime(vr.getArriveTime());
						}
						//添加新的案场客户
						baseDao.saveOrUpdate(newPc);
						//将新客户的id回填到到访记录表
						vr.setCustomerId(customerId);
						if(vr.getIsNew()!=null && !vr.getIsNew().equals("")){
							vr.setIsNew(vr.getIsNew());
						}else{
							vr.setIsNew(true);
						}
					}
				}else{
					if(vr.getIsNew()!=null && !vr.getIsNew().equals("")){
						vr.setIsNew(vr.getIsNew());
					}else{
						vr.setIsNew(true);
					}
				}
				baseDao.saveOrUpdate(vr);//保存到访，生成到访数据
				visitNo = vr.getVisitNo();
				//VisitRecords cloneVisit = (VisitRecords) ObjClone.myClone(VisitRecords.class.getName(), vr);
				//cloneVisit.setBatchVisitNo(visitNo);
				vr.setBatchVisitNo(visitNo);
				baseDao.saveOrUpdate(vr);//更新到访批次编号
			}
			// 如果phone在带看备案记录里，则把到访编号更新到带看备案记录里
			if (vr.getPhone() != null && !vr.getPhone().equals("")) {
				String hql = "from GuideRecords where projectId = '" + vr.getProjectId() + "' and customerPhone = '"
						+ vr.getPhone() + "'";
				GuideRecords gr = (GuideRecords) baseDao.loadObject(hql);
				if(gr!=null){
					gr.setVisitNo(vr.getVisitNo());
					baseDao.saveOrUpdate(gr);
				}
			}
			//更新置业顾问排队信息
			//①获取签到记录
			String todayStr = DateUtil.format(new Date(), DateUtil.PATTERN_CLASSICAL_SIMPLE);
			String signHql = "from SignRecords where userId = '" + userId + "' and parentId='"+vr.getProjectId()+"' and "
					+ "(signInTime like '%"+todayStr+"%' and signOutTime is null)";
			SignRecords sr = (SignRecords) baseDao.loadObject(signHql);//获取已签到,未签退置业顾问
			if(sr!=null){
				String rankHql = "from AgentRank where recordId = "+sr.getRecordId();
				AgentRank ar = (AgentRank) baseDao.loadObject(rankHql);
				if(vr.getLeaveTime()!=null && !vr.getLeaveTime().equals("")){//送别
					String hql = "from VisitRecords where userId = '"+userId+"' and projectId = '"+vr.getProjectId()+"'"
							+ "and arriveTime like '"+todayStr+"%' and leaveTime is null";
					VisitRecords haveVr =  (VisitRecords)baseDao.loadObject(hql);
					if(haveVr==null){//判断同一个顾问不存在接访任务时，设置其为送别，并更新最后送别的时间
						ar.setSignInVisitStatus(2);
						ar.setLeaveTime(DateUtil.format(new Date()));
					}
					AgentRankSeting ars = (AgentRankSeting) baseDao.loadObject("from AgentRankSeting where projectId = '"+vr.getProjectId()+"'");
					if(ars!=null){//获取排序规则
						if(ars.getRankValue().equals(0)){//顺序
							if(ars.getRankWay().equals(0)){//全部
								String maxRankNumSql = "SELECT * FROM t_agentrank WHERE rankNumber = (SELECT MAX(rankNumber) rankNumber FROM t_agentrank) ";
								AgentRank maxRank = (AgentRank) baseDao.queryObjBySql(maxRankNumSql, AgentRank.class);
								ar.setRankNumber(maxRank.getRankNumber()+1);
							}else if(ars.getRankWay().equals(1)){//部分（手机搜索接访的，排序号不变）
								if(vr.getCheckWay()==null || !vr.getCheckWay().equals(1)){//完全顺序，更新排序号；否则不更新（1是搜索手机号的接访）
									String maxRankNumSql = "SELECT * FROM t_agentrank WHERE rankNumber = (SELECT MAX(rankNumber) rankNumber FROM t_agentrank) ";
									AgentRank maxRank = (AgentRank) baseDao.queryObjBySql(maxRankNumSql, AgentRank.class);
									ar.setRankNumber(maxRank.getRankNumber()+1);
								}
							}
						}else{//轮排
							//下一个签到顾问，先判断组别
							int num = 1;
							User uRank = (User) baseDao.loadById(User.class, userId);
							if(uRank.getGroupName()!=null && !uRank.equals("")){
								num = Integer.valueOf(uRank.getGroupName());
							}
							String lastRankHql = "from AgentRank where signInTime LIKE '"+todayStr+"%' and groupNum = '"+num+"' ORDER BY rankNumber desc";
							AgentRank rank = (AgentRank) baseDao.loadObject(lastRankHql);//获取同组的最后一个对象
							//添加顾问排序对象
							if(rank!=null){//同组的最后一个对象
								int nextGp = rank.getRankNumber()+1;
								String lastRankNextHql = "from AgentRank where signInTime LIKE '"+todayStr+"%' and rankNumber = '"+nextGp+"'";
								AgentRank lastRankNext = (AgentRank) baseDao.loadObject(lastRankNextHql);//获取同组的下一个
								if(lastRankNext!=null){//同组下一个存在
									String rankNextAllHql = "from AgentRank where signInTime LIKE '"+todayStr+"%' and rankNumber > '"+nextGp+"' ORDER BY rankNumber desc";
									List<AgentRank> rankNextAllList = baseDao.findByHql(rankNextAllHql);
									for(AgentRank upar : rankNextAllList){
										upar.setRankNumber(upar.getRankNumber()+1);
										baseDao.saveOrUpdate(upar);
									}
									ar.setRankNumber(lastRankNext.getRankNumber()+1);
								}else{//同组下一个不存在
									ar.setRankNumber(rank.getRankNumber()+1);
								}
							}
						}
					}else{
						String maxRankNumSql = "SELECT * FROM t_agentrank WHERE rankNumber = (SELECT MAX(rankNumber) rankNumber FROM t_agentrank) ";
						AgentRank maxRank = (AgentRank) baseDao.queryObjBySql(maxRankNumSql, AgentRank.class);
						ar.setRankNumber(maxRank.getRankNumber()+1);
					}
				}else{//接访
					ar.setSignInVisitStatus(1);
					ar.setArriveTime(vr.getArriveTime());
				}
				baseDao.saveOrUpdate(ar);
			}
			return vr.getVisitNo();
		}
		return null;
	}
	
	//单客户录入 上传到访
	@Override
	public Integer addOrUpdateVisitReocrdFromExcel(VisitRecords vr,Integer checkTeam) {
		if(vr!=null){
			String userId = null;
			Integer visitNo = null;
			if(vr.getVisitNo()!=null && !vr.getVisitNo().equals("")){
				VisitRecords newVr = (VisitRecords) baseDao.loadById(VisitRecords.class, vr.getVisitNo());
				//持久化对象
				if(newVr==null){
					//判断vr有没有phone
					if(vr.getPhone()!=null && !vr.getPhone().equals("")){
						String proCusHql = "from ProjectCustomers where projectId = '"+vr.getProjectId()+"' "
								+ " and (projectCustomerPhone = '"+vr.getPhone()+"' or morePhoneNum like '"+vr.getPhone()+"')";
						/*if(vr.getMorePhoneNum()!=null && !vr.getMorePhoneNum().equals("")){
							proCusHql += " or morePhoneNum like '"+vr.getPhone()+"'";
						}*/
						ProjectCustomers pc = (ProjectCustomers) baseDao.loadObject(proCusHql);
						if(pc!=null){
							pc.setLastTime(vr.getArriveTime());
							//更新该用户信息
							baseDao.saveOrUpdate(pc);
							//回填案场客户id到到访记录中
							vr.setCustomerId(pc.getProjectCustomerId());
							vr.setCustomerName(pc.getProjectCustomerName());
							vr.setPhone(pc.getProjectCustomerPhone());
							vr.setMorePhoneNum(pc.getMorePhoneNum());
						}else{
							ProjectCustomers newPc = new ProjectCustomers();
							String customerId = SysContent.uuid();
							newPc.setProjectCustomerId(customerId);
							newPc.setCreateTime(vr.getArriveTime());
							if(vr.getUserId()!=null && !vr.getUserId().equals("")){
								newPc.setCreateUserId(vr.getUserId());
								newPc.setOwnerUserId(vr.getUserId());
							}
							/*if(vr.getAppointUserId()!=null){
								newPc.setOwnerUserId(vr.getAppointUserId());
							}*/
							if(vr.getCustomerName()!=null){
								newPc.setProjectCustomerName(vr.getCustomerName());
							}
							if(vr.getPhone()!=null){
								newPc.setProjectCustomerPhone(vr.getPhone());
							}
							if(vr.getProjectId()!=null){
								newPc.setProjectId(vr.getProjectId());
							}
							if(vr.getArriveTime()!=null){
								newPc.setLastTime(vr.getArriveTime());
								newPc.setOwnerStartTime(vr.getArriveTime());
							}
							if(vr.getArriveTime()!=null && !vr.getArriveTime().equals("")){
								newPc.setLastTime(vr.getArriveTime());
							}else{
								newPc.setLastTime(newVr.getArriveTime());
							}
							//添加新的案场客户
							baseDao.saveOrUpdate(newPc);
							//将新客户的id回填到到访记录表
							vr.setCustomerId(customerId);
						}
						
					}
					//保存新的到访记录
					baseDao.save(vr);
				}else{
					userId = newVr.getUserId();
					if(vr.getArriveTime()!=null){
						newVr.setArriveTime(vr.getArriveTime());
					}
					if(vr.getReceptTime()!=null){
						newVr.setReceptTime(vr.getReceptTime());
					}
					if(vr.getPhone()!=null){
						newVr.setPhone(vr.getPhone());
					}
					if(vr.getProjectId()!=null){
						newVr.setProjectId(vr.getProjectId());
					}
					if(vr.getUserId()!=null){
						newVr.setUserId(vr.getUserId());
					}
					if(vr.getVisitStatus()!=null){
						newVr.setVisitStatus(vr.getVisitStatus());
					}
					if(vr.getCustomerCount()!=null){
						newVr.setCustomerCount(vr.getCustomerCount());
					}
					if(vr.getLeaveTime()!=null){
						newVr.setLeaveTime(vr.getLeaveTime());
					}
					if(vr.getPhone()!=null && !vr.getPhone().equals("")){
						//更新到访记录时，可能存在customid不存在的情况，这是需要通过phone去load客户，如果客户不存在则添加客户信息，并将costormid回填到到访记录中
						if(newVr.getCustomerId()!=null && !newVr.getCustomerId().equals("")){
							//获取案场客户信息
							ProjectCustomers newPc = (ProjectCustomers) baseDao.loadById(ProjectCustomers.class, newVr.getCustomerId());
							if(newPc!=null){
								//更新该客户的最后到访记录
								newPc.setLastTime(newVr.getArriveTime());
								newPc.setProjectCustomerPhone(newVr.getPhone());
								baseDao.saveOrUpdate(newPc);
							}
						}else{
							String proCusHql = "from ProjectCustomers where projectId = '"+vr.getProjectId()+"' and (projectCustomerPhone = '"+vr.getPhone()+"'"
									+ " or morePhoneNum like '"+vr.getPhone()+"')";
							ProjectCustomers newPc = (ProjectCustomers) baseDao.loadObject(proCusHql);
							if(newPc!=null){
								//更新该客户的最后到访记录
								newPc.setLastTime(newVr.getArriveTime());
								baseDao.saveOrUpdate(newPc);
								newVr.setCustomerId(newPc.getProjectCustomerId());
								newVr.setCustomerName(newPc.getProjectCustomerName());
								newVr.setPhone(newPc.getProjectCustomerPhone());
								newVr.setMorePhoneNum(newPc.getMorePhoneNum());
							}else{
								ProjectCustomers newPc1 = new ProjectCustomers();
								String customerId = SysContent.uuid();
								newPc1.setProjectCustomerId(customerId);
								newPc1.setCreateTime(vr.getArriveTime());
								if(vr.getUserId()!=null && !vr.getUserId().equals("")){
									newPc1.setCreateUserId(vr.getUserId());
									newPc1.setOwnerUserId(vr.getUserId());
								}else{
									newPc1.setCreateUserId(newVr.getUserId());
									newPc1.setOwnerUserId(newVr.getUserId());
								}
								/*if(vr.getAppointUserId()!=null){
									newPc1.setOwnerUserId(vr.getAppointUserId());
								}*/
								if(vr.getCustomerName()!=null){
									newPc1.setProjectCustomerName(vr.getCustomerName());
								}
								if(vr.getPhone()!=null){
									newPc1.setProjectCustomerPhone(vr.getPhone());
								}
								if(vr.getProjectId()!=null){
									newPc1.setProjectId(vr.getProjectId());
								}
								if(vr.getArriveTime()!=null){
									newPc1.setLastTime(vr.getArriveTime());
									newPc1.setOwnerStartTime(vr.getArriveTime());
								}
								if(vr.getArriveTime()!=null && !vr.getArriveTime().equals("")){
									newPc1.setLastTime(vr.getArriveTime());
								}else{
									newPc1.setLastTime(newVr.getArriveTime());
								}
								//添加新的案场客户
								baseDao.saveOrUpdate(newPc1);
								//将新客户的id回填到到访记录表
								newVr.setCustomerId(customerId);
								if(vr.getIsNew()!=null && !vr.getIsNew().equals("")){
									newVr.setIsNew(vr.getIsNew());
								}else{
									newVr.setIsNew(true);
								}
							}
						}
						
					}
					baseDao.saveOrUpdate(newVr);
					visitNo = newVr.getVisitNo();
					vr.setCheckWay(newVr.getCheckWay());//送别时把接访方法给前台传过来的对象中，在下面的送别排序会用到
				}
			}else{//接访
				userId = vr.getUserId();
				if(vr.getPhone()!=null && !vr.getPhone().equals("")){
					String proCusHql = "from ProjectCustomers where projectId = '"+vr.getProjectId()+"' and ( projectCustomerPhone = '"+vr.getPhone()+"'"
							+ " or morePhoneNum like '"+vr.getPhone()+"')";
					/*if(vr.getMorePhoneNum()!=null && !vr.getMorePhoneNum().equals("")){
						proCusHql += " or projectCustomerPhone like '"+vr.getMorePhoneNum()+"'";
					}*/
					ProjectCustomers pc = (ProjectCustomers) baseDao.loadObject(proCusHql);
					if(pc!=null){
						pc.setLastTime(vr.getArriveTime());
						//pc.setProjectCustomerPhone(vr.getPhone());
						//pc.setOwnerUserId(vr.getAppointUserId());
						baseDao.saveOrUpdate(pc);
						vr.setCustomerId(pc.getProjectCustomerId());
						vr.setCustomerName(pc.getProjectCustomerName());
						vr.setPhone(pc.getProjectCustomerPhone());
						vr.setMorePhoneNum(pc.getMorePhoneNum());
					}else{
						ProjectCustomers newPc = new ProjectCustomers();
						String customerId = SysContent.uuid();
						newPc.setProjectCustomerId(customerId);
						newPc.setCreateTime(vr.getArriveTime());
						if(vr.getUserId()!=null && !vr.getUserId().equals("")){
							newPc.setCreateUserId(vr.getUserId());
							newPc.setOwnerUserId(vr.getUserId());
						}
						/*if(vr.getAppointUserId()!=null){
							newPc.setOwnerUserId(vr.getAppointUserId());
						}*/
						if(vr.getCustomerName()!=null){
							newPc.setProjectCustomerName(vr.getCustomerName());
						}
						if(vr.getPhone()!=null){
							newPc.setProjectCustomerPhone(vr.getPhone());
						}
						if(vr.getProjectId()!=null){
							newPc.setProjectId(vr.getProjectId());
						}
						if(vr.getArriveTime()!=null){
							newPc.setLastTime(vr.getArriveTime());
							newPc.setOwnerStartTime(vr.getArriveTime());
						}
						//添加新的案场客户
						baseDao.saveOrUpdate(newPc);
						//将新客户的id回填到到访记录表
						vr.setCustomerId(customerId);
						if(vr.getIsNew()!=null && !vr.getIsNew().equals("")){
							vr.setIsNew(vr.getIsNew());
						}else{
							vr.setIsNew(true);
						}
					}
				}else{
					if(vr.getIsNew()!=null && !vr.getIsNew().equals("")){
						vr.setIsNew(vr.getIsNew());
					}else{
						vr.setIsNew(true);
					}
				}
				baseDao.saveOrUpdate(vr);
				visitNo = vr.getVisitNo();
			}
			// 如果phone在带看备案记录里，则把到访编号更新到带看备案记录里
			if (vr.getPhone() != null && !vr.getPhone().equals("")) {
				String hql = "from GuideRecords where projectId = '" + vr.getProjectId() + "' and customerPhone = '"
						+ vr.getPhone() + "'";
				GuideRecords gr = (GuideRecords) baseDao.loadObject(hql);
				if(gr!=null){
					gr.setVisitNo(vr.getVisitNo());
					baseDao.saveOrUpdate(gr);
				}
			}
			//更新置业顾问排队信息
			//①获取签到记录
			String todayStr = DateUtil.format(new Date(), DateUtil.PATTERN_CLASSICAL_SIMPLE);
			String signHql = "from SignRecords where userId = '" + userId + "' and parentId='"+vr.getProjectId()+"' and "
					+ "(signInTime like '%"+todayStr+"%' and signOutTime is null)";
			SignRecords sr = (SignRecords) baseDao.loadObject(signHql);//获取已签到,未签退置业顾问
			if(sr!=null){
				String rankHql = "from AgentRank where recordId = "+sr.getRecordId();
				AgentRank ar = (AgentRank) baseDao.loadObject(rankHql);
				if(vr.getLeaveTime()!=null && !vr.getLeaveTime().equals("")){//送别
					String hql = "from VisitRecords where userId = '"+userId+"' and projectId = '"+vr.getProjectId()+"'"
							+ "and arriveTime like '"+todayStr+"%' and leaveTime is null";
					VisitRecords haveVr =  (VisitRecords)baseDao.loadObject(hql);
					if(haveVr==null){//判断同一个顾问不存在接访任务时，设置其为送别，并更新最后送别的时间
						ar.setSignInVisitStatus(2);
						ar.setLeaveTime(DateUtil.format(new Date()));
					}
					AgentRankSeting ars = (AgentRankSeting) baseDao.loadObject("from AgentRankSeting where projectId = '"+vr.getProjectId()+"'");
					if(ars!=null){//获取排序规则
						if(ars.getRankValue().equals(0)){//顺序
							if(ars.getRankWay().equals(0)){//全部
								String maxRankNumSql = "SELECT * FROM t_agentrank WHERE rankNumber = (SELECT MAX(rankNumber) rankNumber FROM t_agentrank) ";
								AgentRank maxRank = (AgentRank) baseDao.queryObjBySql(maxRankNumSql, AgentRank.class);
								ar.setRankNumber(maxRank.getRankNumber()+1);
							}else if(ars.getRankWay().equals(1)){//部分（手机搜索接访的，排序号不变）
								if(vr.getCheckWay()==null || !vr.getCheckWay().equals(1)){//完全顺序，更新排序号；否则不更新（1是搜索手机号的接访）
									String maxRankNumSql = "SELECT * FROM t_agentrank WHERE rankNumber = (SELECT MAX(rankNumber) rankNumber FROM t_agentrank) ";
									AgentRank maxRank = (AgentRank) baseDao.queryObjBySql(maxRankNumSql, AgentRank.class);
									ar.setRankNumber(maxRank.getRankNumber()+1);
								}
							}
						}else{//轮排
							//下一个签到顾问，先判断组别
							int num = 1;
							User uRank = (User) baseDao.loadById(User.class, userId);
							if(uRank.getGroupName()!=null && !uRank.equals("")){
								num = Integer.valueOf(uRank.getGroupName());
							}
							String lastRankHql = "from AgentRank where signInTime LIKE '"+todayStr+"%' and groupNum = '"+num+"' ORDER BY rankNumber desc";
							AgentRank rank = (AgentRank) baseDao.loadObject(lastRankHql);//获取同组的最后一个对象
							//添加顾问排序对象
							if(rank!=null){//同组的最后一个对象
								int nextGp = rank.getRankNumber()+1;
								String lastRankNextHql = "from AgentRank where signInTime LIKE '"+todayStr+"%' and rankNumber = '"+nextGp+"'";
								AgentRank lastRankNext = (AgentRank) baseDao.loadObject(lastRankNextHql);//获取同组的下一个
								if(lastRankNext!=null){//同组下一个存在
									String rankNextAllHql = "from AgentRank where signInTime LIKE '"+todayStr+"%' and rankNumber > '"+nextGp+"' ORDER BY rankNumber desc";
									List<AgentRank> rankNextAllList = baseDao.findByHql(rankNextAllHql);
									for(AgentRank upar : rankNextAllList){
										upar.setRankNumber(upar.getRankNumber()+1);
										baseDao.saveOrUpdate(upar);
									}
									ar.setRankNumber(lastRankNext.getRankNumber()+1);
								}else{//同组下一个不存在
									ar.setRankNumber(rank.getRankNumber()+1);
								}
							}
						}
					}else{
						String maxRankNumSql = "SELECT * FROM t_agentrank WHERE rankNumber = (SELECT MAX(rankNumber) rankNumber FROM t_agentrank) ";
						AgentRank maxRank = (AgentRank) baseDao.queryObjBySql(maxRankNumSql, AgentRank.class);
						ar.setRankNumber(maxRank.getRankNumber()+1);
					}
				}else{//接访
					ar.setSignInVisitStatus(1);
					ar.setArriveTime(vr.getArriveTime());
				}
				baseDao.saveOrUpdate(ar);
			}
			return vr.getVisitNo();
		}
		return null;
	}

	@Override
	public Customer findCustomerVisitRecord(String projectId, String phone) {
		String hql = "from ProjectCustomers pc where pc.projectId = '" + projectId + "'"
				+ " and (pc.projectCustomerPhone = '" + phone + "' or morePhoneNum LIKE '"+phone+"')";
		ProjectCustomers pc = (ProjectCustomers) baseDao.loadObject(hql);
		Customer c = null;
		if (pc != null) {
			String agentStatus = "未知";
			String todayStr = DateUtil.format(new Date(), DateUtil.PATTERN_CLASSICAL_SIMPLE);
			String agentId = pc.getOwnerUserId();
			//判断顾问状态
			String signHQL = "from SignRecords WHERE userId = '"+agentId+"' and signInTime LIKE '"+todayStr+"%' and signOutTime IS NULL"; 
			SignRecords sr = (SignRecords) baseDao.loadObject(signHQL);
			if(sr!=null){
				String visitStatusHql = "from VisitRecords WHERE userId='"+agentId+"' and projectId = '"+projectId+"' and arriveTime LIKE '"+todayStr+"%' AND leaveTime is NULL";
				VisitRecords vrs = (VisitRecords) baseDao.loadObject(visitStatusHql);//接访中的
				if(vrs!=null){
					agentStatus = "接访中";
				}else{
					agentStatus = "空闲中";
				}
			}else{
				agentStatus = "休息";
			}
			String vHQL = "select count(*) from VisitRecords vr where vr.projectId = '" + projectId + "' and customerId = '"+pc.getProjectCustomerId()+"'";
			int count = baseDao.countQuery(vHQL);
			c = new Customer();
			c.setCustomerId(pc.getProjectCustomerId());
			c.setUserId(agentId);
			User u = (User) baseDao.loadObject("from User where userId = '"+agentId+"' ");
			c.setAgentName(u.getUserCaption());
			c.setCustomerName(pc.getProjectCustomerName());
			c.setFirstVisitTime(pc.getCreateTime());
			c.setLastVisitTime(pc.getLastTime());
			c.setVisitCount(count);
			c.setStatus(agentStatus);
			c.setAgentPhoto(u.getPhoto());
		}
		return c;
	}

	//获取待签退顾问列表
	@Override
	public void findAgentsNotSignOut(String projectId, Page page) {
		String todayStartTime = SysContent.getStartTime();
		String todayEndTime = SysContent.getEndTime();
		Integer start = page.getStart();
		Integer limit = page.getLimit();
		String totalSql = "SELECT count(*) FROM (SELECT u.userId,u.parentId,u.phone,u.photo FROM t_users u , user_role ur WHERE u.parentId = '"+projectId+"' "
				+ "and u.userId = ur.u_id and ur.r_id = 3 and userStatus = 1) "
				+ "agent where agent.userId in (SELECT userId FROM t_signrecords  WHERE signOutTime is null and signInTime > '"+todayStartTime+"' "
				+ " and  signInTime < '"+todayEndTime+"')";
		String sql = "SELECT agent.groupName groupName, agent.userCaption name,agent.userId id,agent.parentId proId,agent.phone phone,agent.photo photo FROM "
				+ "(SELECT * FROM t_users u , user_role ur WHERE u.parentId = '"+projectId+"' and u.userId = ur.u_id and ur.r_id = 3 and userStatus = 1) "
				+ "agent where agent.userId in (SELECT userId FROM t_signrecords  WHERE signOutTime is null and signInTime > '"+todayStartTime+"' "
				+ " and  signInTime < '"+todayEndTime+"') limit "+page.getStart()+","+page.getLimit();
		int total = baseDao.findCountBySql(totalSql);
		String[] colums = {"id","name","photo","phone","proId","groupName"};
		String[] types = {"String","String","String","String","String","String"};
		List<AgentDTO> agentList = baseDao.queryDTOBySql(sql, AgentDTO.class, colums, types);
		page.setTotal(total);
		page.setRoot(agentList);
	}

	/**
	 * 置业顾问排队列表
	 */
	@Override
	public void findQueueUpAgents(Integer checkTeam, String projectId, Page page) {
		String todayStr = DateUtil.format(new Date(), DateUtil.PATTERN_CLASSICAL_SIMPLE);
		Integer start = page.getStart();
		Integer limit = page.getLimit();
		String[] colums = {"id","name","photo","phone","proId","groupName","leaveTime","signInTime"};
		String[] types = {"String","String","String","String","String","String","String","String"};
		String waitSql = "SELECT u.userId id,u.userCaption name,u.phone phone,u.photo photo,u.parentId proId,u.groupName groupName,ar.leaveTime leaveTime ,ar.signInTime signInTime "
				+ "FROM t_users u,t_agentrank ar WHERE ar.workingStatus = 0 and (ar.signInVisitStatus = 0 or ar.signInVisitStatus = 2)and ar.projectId = '"+projectId+"' and ar.signInTime "
				+ " like '"+todayStr+"%' and u.userId = ar.userId ";
		
		List<AgentDTO> list = new ArrayList<>();
		AgentRankSeting ars = (AgentRankSeting) baseDao.loadObject("from AgentRankSeting where projectId = '"+projectId+"'");
		if(ars==null || ars.getRankValue().equals(0)){//签到时间排序
			waitSql += " ORDER BY ar.rankNumber ASC";
			list = baseDao.queryDTOBySql(waitSql, AgentDTO.class, colums, types);
		}else{//小组轮排
			waitSql += " ORDER BY ar.rankNumber ASC";
			list = baseDao.queryDTOBySql(waitSql, AgentDTO.class, colums, types);
		}
		//假分页
		page.setTotal(list.size());
		List root = page.getPageList(list, page.getStart(), page.getLimit());
		page.setRoot(root);
	}
	
	/**
	 * 顺序接访集合
	 */
	@Override
	public List findAllQueueUpAgents(Integer checkTeam, String projectId, Page page) {
		String todayStr = DateUtil.format(new Date(), DateUtil.PATTERN_CLASSICAL_SIMPLE);
		//Integer start = page.getStart();
		//Integer limit = page.getLimit();
		String[] colums = {"id","name","photo","phone","proId","groupName","leaveTime","signInTime"};
		String[] types = {"String","String","String","String","String","String","String","String"};
		String waitSql = "SELECT u.userId id,u.userCaption name,u.phone phone,u.photo photo,u.parentId proId,u.groupName groupName,ar.leaveTime leaveTime ,ar.signInTime signInTime "
				+ "FROM t_users u,t_agentrank ar WHERE ar.workingStatus = 0 and (ar.signInVisitStatus = 0 or ar.signInVisitStatus = 2)and ar.projectId = '"+projectId+"' and ar.signInTime "
				+ " like '"+todayStr+"%' and u.userId = ar.userId ";
		
		List<AgentDTO> list = new ArrayList<>();
		AgentRankSeting ars = (AgentRankSeting) baseDao.loadObject("from AgentRankSeting where projectId = '"+projectId+"'");
		if(ars.getRankValue().equals(0)){//签到时间排序
			waitSql += " ORDER BY ar.rankNumber ASC";
			list = baseDao.queryDTOBySql(waitSql, AgentDTO.class, colums, types);
		}else{//小组轮排
			waitSql += " ORDER BY ar.rankNumber ASC";
			list = baseDao.queryDTOBySql(waitSql, AgentDTO.class, colums, types);
		}
		//假分页
		//page.setTotal(list.size());
		//List root = page.getPageList(list, page.getStart(), page.getLimit());
		//page.setRoot(list);
		return list;
	}

	@Override
	public void findReceivIngAgents(Integer checkTeam, String projectId, Page page) {
		String todayStr = DateUtil.format(new Date(), DateUtil.PATTERN_CLASSICAL_SIMPLE);
		Integer start = page.getStart();
		Integer limit = page.getLimit();
		/*String totalSql = "SELECT count(*) FROM "
				+ "(SELECT agent.groupName, agent.userCaption,agent.userId,agent.parentId,agent.phone,agent.photo FROM "
				+ "(SELECT u.groupName, u.userCaption, u.userId,u.parentId,u.phone,u.photo FROM t_users u , user_role ur WHERE u.parentId = '"+projectId+"' "
				+ "and u.userId = ur.u_id and ur.r_id = 3) agent where agent.userId in (SELECT userId FROM t_signrecords  WHERE signOutTime is null and signInTime "
				+ "like '"+todayStr+"%' ORDER BY signInTime ASC)) signed WHERE signed.userId IN (SELECT userId FROM t_visitrecords WHERE projectId = '"+projectId+"' and "
				+ "arriveTime like '"+todayStr+"%' and receptTime is not null and leaveTime IS null GROUP BY userId)";
		String sql = "SELECT signed.groupName groupName, signed.userCaption name,signed.userId id,signed.parentId proId,signed.phone phone,signed.photo photo FROM "
				+ "(SELECT agent.groupName, agent.userCaption,agent.userId,agent.parentId,agent.phone,agent.photo FROM "
				+ "(SELECT u.groupName, u.userCaption, u.userId,u.parentId,u.phone,u.photo FROM t_users u , user_role ur WHERE u.parentId = '"+projectId+"' "
				+ "and u.userId = ur.u_id and ur.r_id = 3) agent where agent.userId in (SELECT userId FROM t_signrecords  WHERE signOutTime is null and signInTime "
				+ "like '"+todayStr+"%' ORDER BY signInTime ASC)) signed WHERE signed.userId IN (SELECT userId FROM t_visitrecords WHERE projectId = '"+projectId+"' and "
				+ "arriveTime like '"+todayStr+"%' and receptTime is not null and leaveTime IS null GROUP BY userId) LIMIT "+start+","+limit;*/
		//接访中
		String totalSql = "SELECT  count(*)"
				+ "FROM t_users u,t_agentrank ar WHERE ar.workingStatus = 0 and ar.signInVisitStatus = 1 and ar.projectId = '"+projectId+"' and ar.signInTime "
				+ " like '"+todayStr+"%' and u.userId = ar.userId ";
		String visitingdSql =  "SELECT u.userId id,u.userCaption name,u.phone phone,u.photo photo,u.parentId proId,u.groupName groupName,ar.leaveTime leaveTime ,ar.signInTime signInTime "
				+ "FROM t_users u,t_agentrank ar WHERE ar.workingStatus = 0 and ar.signInVisitStatus = 1 and ar.projectId = '"+projectId+"' and ar.signInTime "
				+ " like '"+todayStr+"%' and u.userId = ar.userId ";
		String[] colums = {"id","name","photo","phone","proId","groupName","leaveTime","signInTime"};
		String[] types = {"String","String","String","String","String","String","String","String"};
		AgentRankSeting ars = (AgentRankSeting) baseDao.loadObject("from AgentRankSeting where projectId = '"+projectId+"'");
		if(ars.getRankValue().equals(0)){//签到时间排序
			totalSql += " ORDER BY ar.signInTime ASC";
			visitingdSql += " ORDER BY ar.rankNumber ASC LIMIT "+start+","+limit;
		}else{//小组轮排
			totalSql += " ORDER BY ar.rankNumber ASC";
			visitingdSql += " ORDER BY ar.rankNumber ASC LIMIT "+start+","+limit;
		}
		List<AgentDTO> agentList = baseDao.queryDTOBySql(visitingdSql, AgentDTO.class, colums, types);
		int total = baseDao.findCountBySql(totalSql);
		page.setTotal(total);
		page.setRoot(agentList);
	}

	//接待中
	@Override
	public void findRecessionAgents(String projectId, Page page) {
		String todayStr = DateUtil.format(new Date(), DateUtil.PATTERN_CLASSICAL_SIMPLE);
		String totalSql = "SELECT count(*) FROM t_visitrecords v,t_users u WHERE v.userId = u.userId and projectId = '"+projectId+"' and (batchVisitNo is null or "
				+ " batchVisitNo = visitNo) and arriveTime like '"+todayStr+"%' and receptTime is not null and leaveTime IS null ";
		String sql = "SELECT v.visitNo visitNo,v.userId userId,v.projectId projectId, v.arriveTime arriveTime,v.receptTime receptTime,v.leaveTime leaveTime,"
				+ "v.customerCount visitCount,v.phone phone,"
				+ "u.userCaption name,u.photo photo FROM t_visitrecords v,t_users u WHERE v.userId = u.userId and projectId = '"+projectId+"' and (batchVisitNo is null or "
				+ " batchVisitNo = visitNo) and arriveTime like '"+todayStr+"%' and receptTime is not null and leaveTime IS null LIMIT "+page.getStart()+","+page.getLimit();
		
		String[] colums = {"visitNo","userId","projectId","arriveTime","receptTime","leaveTime","name","photo","visitCount","phone"};
		String[] types = {"Integer","String","String","String","String","String","String","String","Integer","String"};
		Integer total = baseDao.findCountBySql(totalSql);
		List<AgentRecessionRecordsDTO> list = baseDao.queryDTOBySql(sql, AgentRecessionRecordsDTO.class, colums, types);
		page.setTotal(total);
		page.setRoot(list);
	}

	//已送别
	@Override
	public void findFarewelledRecords(String projectId, Page page) {
		String todayStr = DateUtil.format(new Date(), DateUtil.PATTERN_CLASSICAL_SIMPLE);
		String totalSql = "SELECT count(*) FROM t_visitrecords v,t_users u WHERE v.userId = u.userId and projectId = '"+projectId+"' and (batchVisitNo is null or "
				+ " batchVisitNo = visitNo) and arriveTime like '"+todayStr+"%' and receptTime is not null and leaveTime IS not null ";
		String sql = "SELECT v.visitNo visitNo,v.userId userId,v.projectId projectId, v.arriveTime arriveTime,v.receptTime receptTime,v.leaveTime leaveTime,"
				+ "v.customerCount visitCount,v.phone phone,"
				+ "u.userCaption name,u.photo photo,TIMESTAMPDIFF(SECOND,STR_TO_DATE(arriveTime,'%Y-%m-%d %H:%i:%s'),str_to_date(leaveTime,'%Y-%m-%d %H:%i:%s')) recessionTimeDiff "
				+ " FROM t_visitrecords v,t_users u WHERE v.userId = u.userId and projectId = '"+projectId+"' and (batchVisitNo is null or "
				+ " batchVisitNo = visitNo) and arriveTime like '"+todayStr+"%' and receptTime is not null and leaveTime IS not null LIMIT "+page.getStart()+","+page.getLimit();
		String[] colums = {"visitNo","userId","projectId","arriveTime","receptTime","leaveTime","name","photo","recessionTimeDiff","visitCount","phone"};
		String[] types = {"Integer","String","String","String","String","String","String","String","Integer","Integer","String"};
		Integer total = baseDao.findCountBySql(totalSql);
		List<AgentRecessionRecordsDTO> list = baseDao.queryDTOBySql(sql, AgentRecessionRecordsDTO.class, colums, types);
		page.setTotal(total);
		page.setRoot(list);
	}

	@Override
	public AgentVisitRecordDTO findCurrentRecessionRecordDetail(Integer visitNo) {
		AgentVisitRecordDTO avrDto = new AgentVisitRecordDTO();
		Integer visitCount = 1;
		VisitRecords vr = (VisitRecords) baseDao.loadById(VisitRecords.class, visitNo);
		String cusPhone = "暂无";
		String cusName = "暂无";
		String agentName = "暂无";
		String receptTime = "暂无";
		String photo = null;
		Integer checkWay = null;
		Integer writeState = null;
		String leaveTime = null;
		if(vr!=null){
			User u = (User) baseDao.loadById(User.class, vr.getUserId());
			agentName = u.getUserCaption();
			receptTime = vr.getReceptTime();
			checkWay = vr.getCheckWay();
			writeState = vr.getWriteState();
			leaveTime = vr.getLeaveTime();
			if(u.getPhoto()!=null && !u.getPhoto().equals("")){
				//顾问头像
				photo = u.getPhoto();
			}
			
			if(vr.getPhone()!=null && !vr.getPhone().equals("")){
				//客户手机号
				cusPhone = vr.getPhone();
				//客户姓名
				cusName = vr.getCustomerName();
			}
			
			if(vr.getCustomerId()!=null && !vr.getCustomerId().equals("")){
				//到访次数
				String visitCountHql = "SELECT COUNT(*) FROM VisitRecords WHERE customerId = '"+vr.getCustomerId()+"'";
				visitCount = baseDao.countQuery(visitCountHql);
			}
		}
		avrDto.setAgentName(agentName);
		avrDto.setCusName(cusName);
		avrDto.setCusPhone(cusPhone);
		avrDto.setAgentPhoto(photo);
		avrDto.setReceptTime(receptTime);
		avrDto.setVisitCount(visitCount.toString());
		avrDto.setCheckWay(checkWay);
		avrDto.setWriteState(writeState);
		avrDto.setLeaveTime(leaveTime);
		return avrDto;
	}

	//当日业务一览-到岗信息
	@Override
	public List<AgentRecessionRecordsDTO> findWorkAgentByDataStr(String proId, String dataStr) {
		Date d = DateUtil.parse(dataStr,DateUtil.PATTERN_CLASSICAL_SIMPLE_YMD);
		String dStr = DateUtil.format(d,DateUtil.PATTERN_CLASSICAL_SIMPLE);
		String sql = "SELECT u.userCaption name,s.signInTime arriveTime,s.signOutTime leaveTime FROM t_users u,t_signrecords s WHERE"
				+ " u.parentId = '"+proId+"' and u.userId = s.userId AND s.signInTime like '"+dStr+"%'";
		String[] colums = {"name","arriveTime","leaveTime"};
		String[] types = {"String","String","String"};
		List<AgentRecessionRecordsDTO> list = baseDao.queryDTOBySql(sql, AgentRecessionRecordsDTO.class, colums, types);
		return list;
	}

	//当日业务一览-接访信息
	@Override
	public List<AgentRecessionRecordsDTO> findTodayVisitByDataStr(String proId, String dataStr) {
		Date d = DateUtil.parse(dataStr,DateUtil.PATTERN_CLASSICAL_SIMPLE_YMD);
		String dStr = DateUtil.format(d,DateUtil.PATTERN_CLASSICAL_SIMPLE);
		String sql = "SELECT v.arriveTime arriveTime,v.customerCount visitCount,v.phone phone,u.userCaption name,v.leaveTime leaveTime FROM t_visitrecords v LEFT JOIN"
				+ " t_users u ON v.userId=u.userId WHERE v.projectId='"+proId+"' AND v.arriveTime LIKE '"+dStr+"%'";
		String[] colums = {"arriveTime","visitCount","phone","name","leaveTime"};
		String[] types = {"String","Integer","String","String","String"};
		List<AgentRecessionRecordsDTO> list = baseDao.queryDTOBySql(sql, AgentRecessionRecordsDTO.class, colums, types);
		return list;
	}

	@Override
	public VisitRecords findCurrentAgentIsReplete(String projectId, String userId) {
		String todayStr = DateUtil.format(new Date(), DateUtil.PATTERN_CLASSICAL_SIMPLE);
		String hql = "from VisitRecords where projectId = '"+projectId+"' and userId = '"+userId+"' and arriveTime "
				+ " like '"+todayStr+"%' and leaveTime is null";//接访中的
		VisitRecords vr = (VisitRecords) baseDao.loadObject(hql);
		return vr;
	}

	@Override
	public void addSignInAndUpdateSignOut(User user,Integer type,String projectId,String userId,String time) {
		if(type.equals(0)){//签到
			SignRecords sr = new SignRecords();
			sr.setParentId(projectId);
			sr.setUserId(userId);
			sr.setSignInTime(time);
			baseDao.save(sr);//新增签到记录
			//签到是先判断该顾问今天有无接访历史
			String todayStr = DateUtil.format(new Date(), DateUtil.PATTERN_CLASSICAL_SIMPLE);
			String hql = "from VisitRecords where userId = '"+userId+"' and projectId = '"+projectId+"'"
					+ "and arriveTime like '"+todayStr+"%' and leaveTime is not null";
			VisitRecords haveVr =  (VisitRecords) baseDao.loadObject(hql);
			//添加顾问排序对象
			AgentRank ar = new AgentRank();
			ar.setProjectId(projectId);
			ar.setRecordId(sr.getRecordId());
			ar.setSignInTime(time);
			if(haveVr!=null){//如果有接访历史，标记为送别
				ar.setSignInVisitStatus(2);
			}else{//反之为签到
				ar.setSignInVisitStatus(0);
			}
			ar.setUserId(userId);
			ar.setWorkingStatus(0);
			baseDao.save(ar);
		}else if(type.equals(1)){//签退
			//签到新增，签退更新	
			String signHql = "from SignRecords where userId = '" + user.getUserId() + "' and parentId='"+projectId+"' and "
					+ "(signInTime like '%"+DateTime.toString(DateUtil.parse(time))+"%' and signOutTime is null)";
			SignRecords sr = (SignRecords) baseDao.loadObject(signHql);//获取已签到,未签退置业顾问
			if(sr!=null){
				if(sr.getSignInTime()==null || sr.getSignInTime().equals("")){
					sr.setSignInTime(time);
				}
				sr.setSignOutTime(time);
				baseDao.saveOrUpdate(sr);//更新签退记录
				//通过签到id获取排队对象
				String rankHql = "from AgentRank where recordId = "+sr.getRecordId();
				AgentRank ar = (AgentRank) baseDao.loadObject(rankHql);
				if(ar!=null){
					ar.setLeaveTime(time);
					ar.setSignInVisitStatus(3);
					ar.setWorkingStatus(1);
					baseDao.saveOrUpdate(ar);
				}
			}
		}
	}
	
	@Override
	public void addSignInAndUpdateSignOutNew(User user, Integer type, String projectId, String userId, String time,Integer checkTeam) {
		if(type.equals(0)){//签到
			SignRecords sr = new SignRecords();
			sr.setParentId(projectId);
			sr.setUserId(userId);
			sr.setSignInTime(time);
			baseDao.save(sr);//新增签到记录
			String todayStr = DateUtil.format(new Date(), DateUtil.PATTERN_CLASSICAL_SIMPLE);
			//判断是否是第一个签到，如果是清空排队表
			String rankHql = "from AgentRank where signInTime LIKE '"+todayStr+"%'";
			AgentRank haveAr = (AgentRank) baseDao.loadObject(rankHql);
			if(haveAr!=null){//更新排序号
				//下一个签到顾问，先判断组别
				int num = 1;
				User uRank = (User) baseDao.loadById(User.class, userId);
				if(uRank.getGroupName()!=null && !uRank.equals("")){
					num = Integer.valueOf(uRank.getGroupName());
				}
				//添加顾问排序对象
				AgentRank ar = new AgentRank();
				ar.setProjectId(projectId);
				ar.setRecordId(sr.getRecordId());
				ar.setSignInTime(time);
				ar.setSignInVisitStatus(0);
				ar.setUserId(userId);
				ar.setWorkingStatus(0);
				ar.setGroupNum(num);
				AgentRankSeting ars = (AgentRankSeting) baseDao.loadObject("from AgentRankSeting where projectId = '"+projectId+"'");
				if(ars!=null){//获取排序规则
					if(ars.getRankValue().equals(0)){//顺序
						String maxRankNumSql = "SELECT * FROM t_agentrank WHERE rankNumber = (SELECT MAX(rankNumber) rankNumber FROM t_agentrank"
								+ " WHERE projectId = '"+projectId+"') ";
						AgentRank maxRank = (AgentRank) baseDao.queryObjBySql(maxRankNumSql, AgentRank.class);
						if(maxRank!=null){
							ar.setRankNumber(maxRank.getRankNumber()+1);
						}else{
							ar.setRankNumber(0);
						}
					}else{//轮排
						String lastRankHql = "from AgentRank where signInTime LIKE '"+todayStr+"%' and groupNum = '"+num+"' ORDER BY rankNumber desc";
						AgentRank rank = (AgentRank) baseDao.loadObject(lastRankHql);//获取同组的最后一个对象
						if(rank!=null){//同组的最后一个对象
							int nextGp = rank.getRankNumber()+1;
							String lastRankNextHql = "from AgentRank where signInTime LIKE '"+todayStr+"%' and rankNumber = '"+nextGp+"'";
							AgentRank lastRankNext = (AgentRank) baseDao.loadObject(lastRankNextHql);//获取同组的下一个
							if(lastRankNext!=null){//同组下一个存在
								ar.setRankNumber(lastRankNext.getRankNumber()+1);
								String rankNextAllHql = "from AgentRank where signInTime LIKE '"+todayStr+"%' and rankNumber > '"+nextGp+"' ORDER BY rankNumber desc";
								List<AgentRank> rankNextAllList = baseDao.findByHql(rankNextAllHql);
								for(AgentRank upar : rankNextAllList){
									upar.setRankNumber(upar.getRankNumber()+1);
									baseDao.saveOrUpdate(upar);
								}
							}else{//同组下一个不存在
								ar.setRankNumber(rank.getRankNumber()+1);
							}
						}else{
							int nextGp = num-1;
							if(nextGp<=0){//第一组的 2 2 2 [1]
								String rankNextAllHql = "from AgentRank where signInTime LIKE '"+todayStr+"%' and groupNum = '"+(num+1)+"' ORDER BY rankNumber ASC";
								List<AgentRank> otherGpLast = baseDao.findByHql(rankNextAllHql);
								ar.setRankNumber(otherGpLast.get(0).getRankNumber());
								for(AgentRank otAr : otherGpLast){
									otAr.setRankNumber(otAr.getRankNumber()+1);
									baseDao.saveOrUpdate(otAr);
								}
							}else{//第二组或更高组
								String rankNextAllHql = "from AgentRank where signInTime LIKE '"+todayStr+"%' and groupNum = '"+nextGp+"' ORDER BY rankNumber ASC";
								AgentRank otherGpLast = (AgentRank) baseDao.loadObject(rankNextAllHql);
								ar.setRankNumber(otherGpLast.getRankNumber()+1);
								String otherRankNextAllHql = "from AgentRank where signInTime LIKE '"+todayStr+"%' and rankNumber > '"+otherGpLast.getRankNumber()+"' ORDER BY rankNumber ASC";
								List<AgentRank> otherGpLastList = baseDao.findByHql(otherRankNextAllHql);
								for(AgentRank otAr : otherGpLastList){
									otAr.setRankNumber(otAr.getRankNumber()+1);
									baseDao.saveOrUpdate(otAr);
								}
							}
						}
					}
				}else{//如果没有设置排序规则，默认顺序接访
					String maxRankNumSql = "SELECT * FROM t_agentrank WHERE rankNumber = (SELECT MAX(rankNumber) rankNumber FROM t_agentrank"
							+ " WHERE projectId = '"+projectId+"') ";
					AgentRank maxRank = (AgentRank) baseDao.queryObjBySql(maxRankNumSql, AgentRank.class);
					if(maxRank!=null){
						ar.setRankNumber(maxRank.getRankNumber()+1);
					}else{
						ar.setRankNumber(0);
					}
				}
				baseDao.save(ar);
			}else{//清表，添加第一个签到顾问
				User uRank = (User) baseDao.loadById(User.class, userId);
				String sql = "DELETE FROM t_agentrank WHERE 1=1";
				baseDao.excuteBySql(sql);
				AgentRank ar = new AgentRank();
				ar.setProjectId(projectId);
				ar.setRecordId(sr.getRecordId());
				ar.setSignInTime(time);
				ar.setSignInVisitStatus(0);
				ar.setUserId(userId);
				ar.setWorkingStatus(0);
				ar.setRankNumber(0);
				if(uRank.getGroupName()!=null && !uRank.getGroupName().equals("")){
					ar.setGroupNum(Integer.valueOf(uRank.getGroupName()));
				}else{
					ar.setGroupNum(1);
				}
				baseDao.save(ar);
			}
		}else if(type.equals(1)){//签退
			//签到新增，签退更新	
			String signHql = "from SignRecords where userId = '" + user.getUserId() + "' and parentId='"+projectId+"' and "
					+ "(signInTime like '%"+DateTime.toString(DateUtil.parse(time))+"%' and signOutTime is null)";
			SignRecords sr = (SignRecords) baseDao.loadObject(signHql);//获取已签到,未签退置业顾问
			if(sr!=null){
				if(sr.getSignInTime()==null || sr.getSignInTime().equals("")){
					sr.setSignInTime(time);
				}
				sr.setSignOutTime(time);
				baseDao.saveOrUpdate(sr);//更新签退记录
				//通过签到id和用户id获取签退对象
				String rankHql = "from AgentRank where recordId = "+sr.getRecordId()+" and userId = '"+user.getUserId()+"'";
				AgentRank ar = (AgentRank) baseDao.loadObject(rankHql);
				AgentRankSeting ars = (AgentRankSeting) baseDao.loadObject("from AgentRankSeting where projectId = '"+projectId+"'");
				if(ar!=null){
					if(ars.getRankValue().equals(0)){//顺序
						baseDao.delete(ar);
					}else{
						String upRankHql = "from AgentRank where rankNumber = '"+(ar.getRankNumber()-1)+"'";
						AgentRank upRank = (AgentRank) baseDao.loadObject(upRankHql);
						if(upRank!=null){//签退客户不是排位中的第一个，删除其排位信息，其下面的顾问需要重新排序
							//获取签退客户后面的排队顾问
							String downRankHql = "from AgentRank where rankNumber > '"+ar.getRankNumber()+"'";
							List<AgentRank> downRankList = baseDao.findByHql(downRankHql);
							List<AgentRank> results = new ArrayList<>();
					        List<AgentRank> teamA = new ArrayList<>();
					        List<AgentRank> teamB = new ArrayList<>();
							//排序重组
							for (AgentRank ad : downRankList) {
								if (ad.getGroupNum() != null && !ad.getGroupNum().equals("")
										&& ad.getGroupNum().equals(2)) {
									teamB.add(ad);
								} else {
									teamA.add(ad);
								}
							}
							//标识下一个排队的顾问是哪个组的
							boolean lastIsTeamA = false;
							if(upRank.getGroupNum()!=null && !upRank.equals("") && upRank.equals(2)){//删除对象上一个是第一组顾问时，下一个应从第二组排起
								lastIsTeamA = true;
							}
							//组装排序集合
							while (teamA.size() > 0 || teamB.size() > 0) {
								if (lastIsTeamA) {
									if (teamA.size() > 0) {
										results.add(teamA.get(0));
										teamA.remove(0);
									}
								} else {
									if (teamB.size() > 0) {
										results.add(teamB.get(0));
										teamB.remove(0);
									}
								}
								lastIsTeamA = !lastIsTeamA;
							}
							//更新排序
							Integer rankNum = ar.getRankNumber();
							baseDao.delete(ar);
							while(!results.isEmpty()){
								AgentRank rAr = results.get(0);
								rAr.setRankNumber(rankNum);
								baseDao.save(rAr);
								rankNum += 1;
								results.remove(0);
							}
						}else{//签退客户是第一个，删除其排位信息，其下客户无需重新排序，
							baseDao.delete(ar);
						}
					}
				}
			}
		}
		
	}
	
	public void agentVisitRank(String proId){
		String todayStr = DateUtil.format(new Date(), DateUtil.PATTERN_CLASSICAL_SIMPLE);
		//获取今天签到顾问
		String signAgentHql = "from SignRecords where signInTime like '"+todayStr+"'% and signOutTime is not null";
		List<SignRecords> signAgentList = baseDao.findByHql(signAgentHql);
		//String
	}

	//获取签到顾问
	@Override
	public SignRecords IsSignged(User user, String projectId,String time) {
		String signHql = "from SignRecords where userId = '" + user.getUserId() + "' and parentId='"+projectId+"' and "
				+ "(signInTime like '%"+DateTime.toString(DateUtil.parse(time))+"%' and signOutTime is null)";
		return (SignRecords) baseDao.loadObject(signHql);
	}
	
	
	
	
}
