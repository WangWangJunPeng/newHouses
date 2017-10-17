package com.sc.tradmaster.service.director.impl;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.sc.tradmaster.bean.ContractRecords;
import com.sc.tradmaster.bean.EnterBuy;
import com.sc.tradmaster.bean.GuideRecords;
import com.sc.tradmaster.bean.House;
import com.sc.tradmaster.bean.HouseType;
import com.sc.tradmaster.bean.Project;
import com.sc.tradmaster.bean.ProjectCustomers;
import com.sc.tradmaster.bean.Role;
import com.sc.tradmaster.bean.User;
import com.sc.tradmaster.bean.VisitRecords;
import com.sc.tradmaster.dao.BaseDao;
import com.sc.tradmaster.service.director.ProjectAnalyzeService;
import com.sc.tradmaster.service.director.impl.dto.DataAnalysis;
import com.sc.tradmaster.service.director.impl.dto.HouseData;
import com.sc.tradmaster.service.director.impl.dto.StorageCustomerData;
import com.sc.tradmaster.utils.DateUtil;
import com.sc.tradmaster.utils.SysContent;

@Service("projectAnalyzeService")
public class ProjectAnalyzeServiceImpl implements ProjectAnalyzeService {

	@Resource
	private BaseDao baseDao;

	/**
	 * 获取两个日期日期之间相差几个月
	 * 
	 * @param start
	 * @param end
	 * @return
	 * @throws ParseException
	 */
	private int getTwoDateSubtractMonth(String start, String end) throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat(DateUtil.PATTERN_CLASSICAL);
		Date startTime = sdf.parse(start);
		Date endTime = sdf.parse(end);
		return DateUtil.getMonth(startTime, endTime);

	}

	/**
	 * 根据时间区间判断使用时间单位
	 * 
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	private List<String> getDateList(String startTime, String endTime) {
		List<String> dateList = new ArrayList<>();
		try {

			// 时间轴的集合
			Date startDate = DateUtil.parse(startTime, DateUtil.PATTERN_CLASSICAL_SIMPLE);
			Date endDate = DateUtil.parse(endTime, DateUtil.PATTERN_CLASSICAL_SIMPLE);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			int vsCount = getTwoDateSubtractMonth(DateUtil.format(startDate), DateUtil.format(endDate));

			// 当startTime和endTime之间间隔三个月之内并且大于一个月就以周为单位
			if (vsCount < 3 && vsCount > 1) {

				// 获取时间段内的从开始Date计算的7七天的日期
				long i = startDate.getTime();
				dateList.add(df.format(new Date(i)));// 添加开始时间
				while (DateUtil.rollDay(new Date(i), 7).getTime() < endDate.getTime()) {
					// System.out.println(df.format(new Date(i)));

					// 如果不是第七天就放空值
					for (int x = 0; x <= 5; x++) {
						dateList.add("");
					}

					i = DateUtil.rollDay(new Date(i), 7).getTime();// 添加间隔7天的时间

					// System.out.println(df.format(new Date(i)));
					dateList.add(df.format(new Date(i)));

					// 计算最后一次开始时间和结束时间的相隔天数
					int vv = DateUtil.getOffsetDays(new Date(i), endDate);

					if (vv <= 7) {
						for (int v = 1; v < vv; v++) {

							dateList.add("");// 当最后遍历只剩下七天或者7天以下就放空的
						}

						dateList.add(df.format(new Date(endDate.getTime())));
					}

				}
			} else if (vsCount > 12) {// 当相隔时间大于一年，就按照季度为单位

				long i = startDate.getTime();
				dateList.add(df.format(new Date(i)));
				while (DateUtil.rollMonth(new Date(i), 3).getTime() < endDate.getTime()) {

					for (int x = 0; x < DateUtil.getOffsetDays(new Date(i), DateUtil.rollMonth(new Date(i), 3))
							- 1; x++) {
						dateList.add("");
					}

					i = DateUtil.rollMonth(new Date(i), 3).getTime();

					dateList.add(df.format(new Date(i)));

					int vv = DateUtil.getOffsetDays(new Date(i), endDate);

					if (vv <= 90) {
						for (int v = 1; v < vv; v++) {
							dateList.add("");
						}

						dateList.add(df.format(new Date(endDate.getTime())));
					}

				}
			} else if (vsCount > 3 && vsCount < 12) {// 如果是一年内，三个月外就按月为单位

				long i = startDate.getTime();
				dateList.add(df.format(new Date(i)));
				while (DateUtil.rollMonth(new Date(i), 1).getTime() < endDate.getTime()) {

					for (int x = 0; x < DateUtil.getOffsetDays(new Date(i), DateUtil.rollMonth(new Date(i), 1))
							- 1; x++) {
						dateList.add("");
					}

					i = DateUtil.rollMonth(new Date(i), 1).getTime();

					dateList.add(df.format(new Date(i)));
					int vv = DateUtil.getOffsetDays(new Date(i), endDate);
					if (vv <= 30) {
						for (int v = 1; v < vv; v++) {
							dateList.add("");
						}
						dateList.add(df.format(new Date(endDate.getTime())));
					}

				}
			} else {// 当小于一个月就按天

				long i = startDate.getTime();
				dateList.add(df.format(new Date(startDate.getTime())));
				while (DateUtil.rollDay(new Date(i), 1).getTime() <= endDate.getTime()) {

					i = DateUtil.rollDay(new Date(i), 1).getTime();

					dateList.add(df.format(new Date(i)));

				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return dateList;

	}

	/**
	 * 获取两个日期之间的每一天
	 * 
	 * @param startTime yyyy-MM-dd
	 * @param endTime	yyyy-MM-dd
	 * @return
	 */
	private List<String> getTwoDateEveryDay(String startTime, String endTime) {
		List<String> list = new ArrayList<>();

		Date startDate = DateUtil.parse(startTime, DateUtil.PATTERN_CLASSICAL_SIMPLE);
		Date endDate = DateUtil.parse(endTime, DateUtil.PATTERN_CLASSICAL_SIMPLE);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

		Long i = startDate.getTime();
		list.add(df.format(startDate));
		list.add(df.format(DateUtil.rollDay(startDate, 1)));
		while (DateUtil.rollDay(new Date(i), 1).getTime() < endDate.getTime()) {

			i = DateUtil.rollDay(new Date(i), 1).getTime();
			list.add(df.format(DateUtil.rollDay(new Date(i), 1)));

		}

		return list;

	}

	@Override
	public Map<String, Object> findVisitData(User user, String startTime, String endTime, String oneDay)
			throws ParseException {

		// TODO 数据查询
		if (startTime == null || "".equals(startTime)) {
			startTime = DateUtil.format(DateUtil.rollDay(new Date(), -6), DateUtil.PATTERN_CLASSICAL_SIMPLE);
		}
		if (endTime == null || "".equals(endTime)) {
			endTime = DateUtil.format(new Date(), DateUtil.PATTERN_CLASSICAL_SIMPLE);
		}

		Map<String, Object> map = new HashMap<>();

		List<DataAnalysis> list = new ArrayList<>();

		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		List<String> dateList = new ArrayList<>();

		List<String> dayList = new ArrayList<>();
		if (oneDay != null && !"".equals(oneDay)) {
			dateList.add(oneDay);
			dayList.add(oneDay);
		} else {
			// 获取时间集合
			dateList = getTwoDateEveryDay(startTime, endTime);
			dayList = getTwoDateEveryDay(startTime, endTime);
		}
		map.put("date", dateList);

		// 获取当前用户所属的案场
		Project project = (Project) baseDao.loadById(Project.class, user.getParentId());

		if (project == null) {
			return null;
		}

		for (String s : dayList) {// 按照区间查找每天的数据 如果没有的话放空值

			// 查出一天内的所有到访
			String visitHql = " from VisitRecords where projectId = '" + project.getProjectId() + "'"
					+ " and arriveTime like '%" + s + "%'";

			List<VisitRecords> visitList = baseDao.findByHql(visitHql);

			DataAnalysis dataAnalysis = new DataAnalysis();

			// 再到访
			Long visitAgain = 0L;
			// 新增（指定接访）
			Long appointNewCustomerCount = 0L;

			// 新增接访
			Long newCustomerCount = 0L;

			// 指定流失
			Long appointLosedCount = 0L;

			// 流失
			Long losedCount = 0L;

			for (VisitRecords cv : visitList) {

				// 然后查找当前的到访客户是否是老客户
				String newVisitHql = "from VisitRecords where projectId = '" + project.getProjectId() + "'"
						+ " and phone = '" + cv.getPhone() + "' and arriveTime like '%" + s + "%'";

				List<VisitRecords> visitRecordList = baseDao.findByHql(newVisitHql);

				// 这段时间之前是否有到访
				String ssHql = "from VisitRecords where projectId = '" + project.getProjectId() + "'" + " and phone = '"
						+ cv.getPhone() + "' and arriveTime <= '" + getThisTimeStartTime(s) + "'";
				List<VisitRecords> ssList = baseDao.findByHql(ssHql);

				String appointVisitNo = cv.getAppointUserId();
				String visitUserNo = cv.getUserId();
				if (visitRecordList.size() > 1) {// 如果到访次数大于1说明是老客户到访

					// 如果指定接待的置业顾问编码不为空说明是指定接待
					if (visitUserNo != null && !"".equals(visitUserNo)) {

						boolean flag = false;
						if (ssList.size() == 0) {
							// 如果当天有一条以上的到访记录，要查找在这个时间之前没有有到访记录，要进行区分那次到访是第一次到访了
							String thisVisitHql = "from VisitRecords where projectId = '" + project.getProjectId() + "'"
									+ " and phone = '" + cv.getPhone() + "' and arriveTime like '%" + s
									+ "%' and visitNo != " + cv.getVisitNo();
							List<VisitRecords> thisVisitListt = baseDao.findByHql(thisVisitHql);
							for (VisitRecords vcc : thisVisitListt) {
								Date aTime = DateUtil.parse(cv.getArriveTime());
								Date bTime = DateUtil.parse(vcc.getArriveTime());

								if (aTime.getTime() < bTime.getTime()) {
									flag = true;
								} else {
									flag = false;
								}
							}
						}
						if (appointVisitNo != null && !"".equals(appointVisitNo)) {
							if ((cv.getWriteState() == null || "".equals(cv.getWriteState())
									|| cv.getWriteState() == 0)) {// 老客户指定流失
								appointLosedCount += 1;
							} else if ((cv.getWriteState() != null || !"".equals(cv.getWriteState())
									|| cv.getWriteState() == 1)) {
								if (ssList.size() > 0) {// 如果这个时间段之前有到访过，那么这个时间段之内的所有到访都是再到访
									visitAgain += 1;
								} else if (flag == true) {// 当天第一次到访
									appointNewCustomerCount++;
								} else if (flag == false) {// 当天第二次到访
									visitAgain++;
								}
							}
						} else {
							if (cv.getWriteState() == null || "".equals(cv.getWriteState())
									|| cv.getWriteState() == 0) {

								losedCount += 1;
							} else if (cv.getWriteState() != null || !"".equals(cv.getWriteState())
									|| cv.getWriteState() == 1) {
								if (ssList.size() > 0) {// 如果这个时间段之前有到访过，那么这个时间段之内的所有到访都是再到访
									visitAgain += 1;
								} else if (flag == true) {// 当天第一次到访
									newCustomerCount++;
								} else if (flag == false) {// 当天第二次到访
									visitAgain++;
								}
							}

						}

					}

				} else if (visitRecordList.size() == 1) {

					if (ssList.size() >= 1) {

						// 如果指定接待的置业顾问编码和接访置业顾问编码相等说明是指定接待
						if (visitUserNo != null && !"".equals(visitUserNo)) {
							if (appointVisitNo != null && !"".equals(appointVisitNo)) {
								if ((cv.getWriteState() == null || "".equals(cv.getWriteState())
										|| cv.getWriteState() == 0)) {// 老客户指定流失
									appointLosedCount += 1;
								} else if (visitUserNo.equals(appointVisitNo) && (cv.getWriteState() != null
										|| !"".equals(cv.getWriteState()) || cv.getWriteState() == 1)) {
									visitAgain += 1;
								}
							} else {
								if (cv.getWriteState() == null || "".equals(cv.getWriteState())
										|| cv.getWriteState() == 0) {
									losedCount += 1;
								} else if (cv.getWriteState() != null || !"".equals(cv.getWriteState())
										|| cv.getWriteState() == 1) {
									visitAgain += 1;
								}

							}
						}
					} else {// 新登
							// 如果指定接待的置业顾问编码和接访置业顾问编码相等说明是指定接待
						if (visitUserNo != null && !"".equals(visitUserNo)) {

							if (appointVisitNo != null && !"".equals(appointVisitNo)) {

								if ((cv.getWriteState() != null && !"".equals(cv.getWriteState()))
										&& cv.getWriteState() == 1) { // 有效指定接访
									appointNewCustomerCount += 1;
								} else if ((cv.getWriteState() == null || cv.getWriteState() == 0)) {// 指定流失
									appointLosedCount += 1;
								}

							} else {

								if ((cv.getWriteState() != null && !"".equals(cv.getWriteState()))
										&& cv.getWriteState() == 1) {
									newCustomerCount += 1;
								} else {
									losedCount += 1;
								}

							}

						}
					}
				} else {
					losedCount++;
				}

			}
			dataAnalysis.setDataDate(s);
			dataAnalysis.setLoseCustomer(losedCount);
			dataAnalysis.setLoseCustomerAppoint(appointLosedCount);
			dataAnalysis.setNewCustomer(newCustomerCount);
			dataAnalysis.setNewCustomerAppoint(appointNewCustomerCount);
			dataAnalysis.setVisitAgain(visitAgain);

			list.add(dataAnalysis);
		}

		map.put("data", list);

		return map;
	}

	@Override
	public Map<String, Object> findMomeryCusData(User user, String startTime, String endTime, String oneDay) {

		// TODO
		if (startTime == null || "".equals(startTime)) {
			startTime = DateUtil.format(DateUtil.rollDay(new Date(), -6), DateUtil.PATTERN_CLASSICAL_SIMPLE);
		}
		if (endTime == null || "".equals(endTime)) {
			endTime = DateUtil.format(new Date(), DateUtil.PATTERN_CLASSICAL_SIMPLE);
		}

		Map<String, Object> map = new HashMap<>();
		List<StorageCustomerData> list = new ArrayList<>();

		// 获取时间集合
		List<String> dateList = new ArrayList<>();
		List<String> dayList = new ArrayList<>();

		if (oneDay != null && !"".equals(oneDay)) {// 左侧日历选某天数据

			dateList.add(oneDay);
			dayList.add(oneDay);

		} else {
			dateList = getTwoDateEveryDay(startTime, endTime);
			// 获取区间每一天的时间
			dayList = getTwoDateEveryDay(startTime, endTime);
		}

		map.put("date", dateList);

		Project project = (Project) baseDao.loadById(Project.class, user.getParentId());

		if (project == null)
			return null;

		for (String s : dayList) {

			StorageCustomerData scd = new StorageCustomerData();

			Long oldCount = 0L;
			Long newCount = 0L;

			Set<String> cuSet = new HashSet<>();
			String thisVisitHql = "from VisitRecords where projectId = '" + project.getProjectId() + "'";
			thisVisitHql += identifyString(s, "", "");

			List<VisitRecords> thisList = baseDao.findByHql(thisVisitHql);

			for (VisitRecords vr : thisList) {
				// 只有有效接访的客户才算储客
				if (vr.getWriteState() != null && !"".equals(vr.getWriteState()) && vr.getWriteState() == 1) {

					cuSet.add(vr.getPhone());
				}
			}

			for (String str : cuSet) {
				String thisTwoVisitHql = "from VisitRecords where projectId = '" + project.getProjectId()
						+ "' and phone = '" + str + "' ";
				thisTwoVisitHql += identifyString(s, "", "");
				List<VisitRecords> listThisTwo = baseDao.findByHql(thisTwoVisitHql);
				if (listThisTwo.size() > 1) {
					oldCount++;
				} else if (listThisTwo.size() == 1) {
					String notThisHql = "from VisitRecords where projectId = '" + project.getProjectId()
							+ "' and phone = '" + str + "' ";
					notThisHql += " and arriveTime <= '" + getThisTimeStartTime(s) + "'";
					// 如果开始时间之前有一次到访记录，说明是老客户
					List<VisitRecords> notThisList = baseDao.findByHql(notThisHql);
					if (notThisList.size() >= 1) {
						oldCount++;
					} else {
						newCount++;
					}
				}
			}
			/*
			 * String momeryHql = "from ProjectCustomers where projectId = '" +
			 * project.getProjectId() + "'" + " and createTime like '%" + s +
			 * "%'";
			 * 
			 * List<ProjectCustomers> momeryList = baseDao.findByHql(momeryHql);
			 * 
			 * for (ProjectCustomers p : momeryList) {
			 * 
			 * String visitHql = "from VisitRecords where projectId = '" +
			 * project.getProjectId() + "'" + " and phone = '" +
			 * p.getProjectCustomerPhone() + "'"; List<VisitRecords> visitList =
			 * baseDao.findByHql(visitHql); if (visitList.size() > 1) {// 老客户
			 * 
			 * oldCount += 1; } else {// 新客户 newCount += 1; }
			 * 
			 * }
			 */

			scd.setNewCustomer(newCount);
			scd.setOldCustomer(oldCount);
			scd.setCustomerCreateDate(s);

			list.add(scd);

		}

		map.put("data", list);
		return map;
	}

	@Override
	public List<Map<String, Object>> findVisitDataForTable(User user, String startTime, String endTime, String oneDay) {

		List<Map<String, Object>> list = new ArrayList<>();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Project project = (Project) baseDao.loadById(Project.class, user.getParentId());
		if (project == null) {
			return null;
		}

		List<String> dayList = new ArrayList<>();

		if (oneDay != null && !"".equals(oneDay)) {
			dayList.add(oneDay);
		} else {
			// 获取两个时间段内的所有时间段
			dayList = getTwoDateEveryDay(startTime, endTime);
		}

		for (String day : dayList) {
			Map<String, Object> map = new HashMap<>();

			// 到访总数
			Integer totalVisitCount = 0;
			// 有效接访
			Integer effectiveNum = 0;
			// 无效接访
			Integer invalidNum = 0;
			// 新客户通道
			Integer newCustomerNum = 0;
			// 老客户通道接访
			Integer oldCustomerNum = 0;
			// 新客户通道有效接访
			Integer newCustomerEffectiveNum = 0;
			// 老客户通道有效接访
			Integer oldCustomerEffectiveNum = 0;
			// 指定接访量
			Integer appointAgentNum = 0;
			// 新客户通道指定接访
			Integer newCustomerAppointAgentNum = 0;
			// 老客户通道指定接访
			Integer oldCustomerAppointAgentNum = 0;
			// 指定有效接访
			Integer appointAgentEffectiveNum = 0;
			// 总替接数
			Integer allReplaceNum = 0;
			// 按序接访替接
			Integer orderReplaceNum = 0;
			// 确认老客户接访次数
			Integer affirmOldCustomerVisitNum = 0;

			// 新客户接访时长
			long newVisitTime = 0L;
			// 替接接访时长
			long replaceTime = 0L;
			// 老客户接访时长
			long oldVisitTime = 0L;
			// 平均接访时长
			long timeDiff = 0L;
			//
			int totalNum = 0;
			//
			double AverageLongTime = 0.00;
			//
			String strAverageLongTime = "0.00";

			String visitHql = "from VisitRecords where projectId = '" + project.getProjectId()
					+ "' and arriveTime like '%" + day + "%'";

			List<VisitRecords> visitList = baseDao.findByHql(visitHql);

			// 到访总数
			totalVisitCount = visitList.size();
			for (VisitRecords vr : visitList) {

				if (vr.getWriteState() != null && !"".equals(vr.getWriteState())) {

					if (vr.getWriteState() == 1) {
						effectiveNum++;
					} else {
						invalidNum++;
					}
				} else {
					invalidNum++;
				}
				if ((vr.getIsNew() == null || "".equals(vr.getIsNew())) || vr.getIsNew()) {

					newCustomerNum++;
					if (vr.getLeaveTime() != null && !"".equals(vr.getLeaveTime()) && vr.getReceptTime() != null
							&& !"".equals(vr.getReceptTime())) {
						long newLeave = DateUtil.parse(vr.getLeaveTime()).getTime();
						long newRecept = DateUtil.parse(vr.getReceptTime()).getTime();
						// 新客户通道接访时长
						newVisitTime += newLeave - newRecept;
					}/* else {
						if (vr.getArriveTime() != null) {
							newVisitTime += 60 * 60 * 1000;
						}
					}*/
					if (vr.getWriteState() != null && !"".equals(vr.getWriteState())) {

						if (vr.getWriteState() == 1) {
							newCustomerEffectiveNum++;
						}
					}
				} else {
					oldCustomerNum++;
					if (vr.getLeaveTime() != null && !"".equals(vr.getLeaveTime()) && vr.getReceptTime() != null
							&& !"".equals(vr.getReceptTime())) {
						long oldLeave = DateUtil.parse(vr.getLeaveTime()).getTime();
						long oldRecept = DateUtil.parse(vr.getReceptTime()).getTime();
						oldVisitTime += oldLeave - oldRecept;
					}/* else {
						if (vr.getArriveTime() != null) {
							oldVisitTime += 60 * 60 * 1000;
						}
					}*/
					if (vr.getWriteState() != null && !"".equals(vr.getWriteState())) {

						if (vr.getWriteState() == 1) {
							oldCustomerEffectiveNum++;
						}
					}
				}
				// TODO 指定接待只要是指定置业顾问的编码不为空即可
				if (vr.getAppointUserId() != null && !"".equals(vr.getAppointUserId())) {
					// 指定 只要指定接访的置业顾问的id不为空就是指定接待
					if (vr.getAppointUserId().equals(vr.getUserId())) {
						appointAgentNum++;
						if ((vr.getIsNew() == null || "".equals(vr.getIsNew())) || vr.getIsNew()) {
							newCustomerAppointAgentNum++;
						} else {
							oldCustomerAppointAgentNum++;
						}
						if (vr.getWriteState() != null && !"".equals(vr.getWriteState())) {

							if (vr.getWriteState() == 1) {
								appointAgentEffectiveNum++;
							}
						}
						// 替接
					} else {
						allReplaceNum++;
						if (vr.getLeaveTime() != null && !"".equals(vr.getLeaveTime()) && vr.getReceptTime() != null
								&& !"".equals(vr.getReceptTime())) {
							long replaceLeave = DateUtil.parse(vr.getLeaveTime()).getTime();
							long replaceRecept = DateUtil.parse(vr.getReceptTime()).getTime();
							replaceTime += replaceLeave - replaceRecept;
						}/* else {
							if (vr.getArriveTime() != null) {
								oldVisitTime += 60 * 60 * 1000;
							}
						}*/
					}
				}
				if (vr.getLeaveTime() != null && !"".equals(vr.getLeaveTime()) && vr.getReceptTime() != null
						&& !"".equals(vr.getReceptTime())) {
					long leave = DateUtil.parse(vr.getLeaveTime()).getTime();
					long recept = DateUtil.parse(vr.getReceptTime()).getTime();
					timeDiff += leave - recept;
					totalNum++;
				} /*else {
					// 如果没有离开时间，就默认1小时
					if (vr.getArriveTime() != null && !"".equals(vr.getArriveTime())) {
						timeDiff += 60 * 60 * 1000;
						totalNum++;
					}
				}*/
			}
			// 平均接待时间
			if (totalNum > 0) {
				AverageLongTime = timeDiff / totalNum / 1000 / 60;
				strAverageLongTime = SysContent.get2Double(AverageLongTime);
				// 每次平均解放时长
				map.put("averageReceptionTime", strAverageLongTime);
			}

			map.put("allTime", timeDiff / 1000 / 60);
			map.put("newVisitTime", newVisitTime / 1000 / 60);
			map.put("oldVisitTime", oldVisitTime / 1000 / 60);
			map.put("replaceTime", replaceTime / 1000 / 60);
			map.put("totalVisitCount", totalVisitCount);
			map.put("orderReplaceNum", orderReplaceNum);
			/*
			 * int dayNum = DateUtil.getOffsetDays(sdf.parse(endTimeStr),
			 * sdf.parse(startTimeStr)); if (dayNum >0 ){
			 * map.put("everyDayAverageTime", timeDiff/dayNum/1000/60/60+"小时");
			 * }else { map.put("everyDayAverageTime", "暂无"); }
			 */

			map.put("effectiveNum", effectiveNum);
			map.put("affirmOldCustomerVisitNum", affirmOldCustomerVisitNum);
			if (effectiveNum > 0) {

				map.put("effectiveRate", getTwoNumberForValue(effectiveNum, visitList.size()));
			} else {
				map.put("effectiveRate", 0);
			}
			map.put("invalidNum", invalidNum);
			map.put("newCustomerNum", newCustomerNum);
			map.put("oldCustomerNum", oldCustomerNum);
			map.put("newCustomerEffectiveNum", newCustomerEffectiveNum);
			map.put("oldCustomerEffectiveNum", oldCustomerEffectiveNum);
			if (newCustomerNum > 0) {
				map.put("newCustomerEffectiveRate", getTwoNumberForValue(newCustomerEffectiveNum, newCustomerNum));
				map.put("newCustomerAppointAgentRate",
						getTwoNumberForValue(newCustomerAppointAgentNum, newCustomerNum));
			} else {
				map.put("newCustomerAppointAgentRate", 0);
				map.put("newCustomerEffectiveRate", 0);
			}
			if (oldCustomerNum > 0) {
				map.put("oldCustomerEffectiveRate", getTwoNumberForValue(oldCustomerEffectiveNum, oldCustomerNum));
				map.put("oldCustomerAppointAgentRate",
						getTwoNumberForValue(oldCustomerAppointAgentNum, oldCustomerNum));
				map.put("oldCustomerRate", getTwoNumberForValue(oldCustomerNum, visitList.size()));
			} else {
				map.put("oldCustomerAppointAgentRate", 0);
				map.put("oldCustomerEffectiveRate", 0);
				map.put("oldCustomerRate", 0);
			}
			map.put("appointAgentNum", appointAgentNum);
			map.put("newCustomerAppointAgentNum", newCustomerAppointAgentNum);
			map.put("oldCustomerAppointAgentNum", oldCustomerAppointAgentNum);
			map.put("appointAgentEffectiveNum", appointAgentEffectiveNum);
			if (appointAgentNum > 0) {
				map.put("appointAgentEffectiveRate", getTwoNumberForValue(appointAgentEffectiveNum, appointAgentNum));
				map.put("appointAgentRate", getTwoNumberForValue(appointAgentNum, visitList.size()));
			} else {
				map.put("appointAgentEffectiveRate", 0);
				map.put("appointAgentRate", 0);
			}
			map.put("allReplaceNum", allReplaceNum);
			if (allReplaceNum > 0) {
				map.put("allReplaceRate", getTwoNumberForValue(allReplaceNum, visitList.size()));
			} else {
				map.put("allReplaceRate", 0);
			}
			map.put("date", day);

			list.add(map);

		}

		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> findDealStatementForTable(User user, String startTime, String endTime,
			String oneDay) {
		List<Map<String, Object>> list = new ArrayList<>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Project project = (Project) baseDao.loadById(Project.class, user.getParentId());

		if (project == null) {
			return null;
		}

		List<String> dayList = new ArrayList<>();
		if (oneDay != null && !"".equals(oneDay)) {
			dayList.add(oneDay);
		} else {
			// 获取两个时间段内的所有时间段
			dayList = getTwoDateEveryDay(startTime, endTime);
		}

		for (String day : dayList) {
			Map<String, Object> map = new HashMap<>();

			// 认购套数
			int allContractNum = 0;
			Set<Integer> st = new HashSet<>();
			// 认购金额
			double allContractMoney = 0.00;
			// 认购到款金额
			double reachContractMoney = 0.00;
			// 认购锁定房源货值
			double lockHousePrice = 0.00;
			// 已签约数
			int haveDealNum = 0;
			// 放弃签约数
			int abandonDealNum = 0;
			// 待签约数
			int readyToDealNum = 0;
			// 已签约房源货值
			double haveDealHousePrice = 0.00;
			// 放弃签约房源货值
			double abandonDealHousePrice = 0.00;
			// 待签约房源货值
			double readyToDealHousePrice = 0.00;
			// 按揭到款套数
			int haveGetMoneyNum = 0;
			// 新增2次到访
			int newAddTwiceVisitNum = 0;
			// 客户回头数
			int customerReturnBackVisitNum = 0;
			// 储客成交人数
			int reserveCustomerToDealNum = 0;
			//下定数
			int readyToAppointNum = 0;
			Set<String> ss = new HashSet<>();
			Set<String> ss2 = new HashSet<>();
			Set<String> ss3 = new HashSet<>();

			List<VisitRecords> vrList22 = baseDao.findByHql("from VisitRecords where projectId = '" + user.getParentId()
					+ "' and" + " arriveTime like '%" + day + "%'");
			for (VisitRecords visitRecords : vrList22) {
				if (visitRecords.getWriteState() != null && !"".equals(visitRecords.getWriteState())
						&& visitRecords.getWriteState() == 1) {
					ss3.add(visitRecords.getPhone());
				}
			}
			if (ss3.size() > 0) {

				for (String string : ss3) {
					List<VisitRecords> vrList55 = baseDao
							.findByHql("from VisitRecords where projectId = '" + user.getParentId() + "'"
									+ " and arriveTime like '%" + day + "%' and phone = '" + string + "' and writeState = 1");
					if (vrList55.size() >= 2) {
						customerReturnBackVisitNum++;
					} else if (vrList55.size() == 1) {
						List<VisitRecords> vrList66 = baseDao.findByHql("from VisitRecords where projectId = '"
								+ user.getParentId() + "'" + " and arriveTime <= '" + getThisTimeStartTime(day)
								+ "' and phone = '" + string + "' ");
						if (vrList66.size() == 1) {
							customerReturnBackVisitNum++;
						}
					}

					List<VisitRecords> vrList33 = baseDao.findByHql(
							"from VisitRecords where projectId = '" + user.getParentId() + "'" + " and arriveTime <= '"
									+ getThisTimeStartTime(day) + "' and phone = '" + string + "' and writeState = 1");
					if (vrList33.size() > 0) {
						ss.add(string);
						if (vrList33.size() == 1) {
							List<VisitRecords> vrList44 = baseDao
									.findByHql("from VisitRecords where projectId = '" + user.getParentId() + "'"
											+ " and arriveTime like '%" + day + "%' and phone = '" + string + "' and writeState = 1");
							if (vrList44.size() == 1) {
								newAddTwiceVisitNum++;
							}
						}
					} else {
						ss2.add(string);
						List<VisitRecords> vrList44 = baseDao
								.findByHql("from VisitRecords where projectId = '" + user.getParentId() + "'"
										+ " and arriveTime like '%" + day + "%' and phone = '" + string + "' ");
						if (vrList44.size() == 2) {
							newAddTwiceVisitNum++;
						}
					}
				}
				List<ProjectCustomers> pcList = baseDao.findByHql("from ProjectCustomers where projectId = '"
						+ user.getParentId() + "'" + " and lastTime <= '" + getThisTimeLastTime(day) + "' ");
				if (pcList.size() > 0) {
					// 客户回头率
					map.put("customerReturnBackVisitRate",
							getTwoNumberForValue(customerReturnBackVisitNum, pcList.size()));
				} else {

					map.put("customerReturnBackVisitRate", 0);
				}
			} else {

				map.put("customerReturnBackVisitRate", 0);
			}
			map.put("date", day);
			// 新增二次来访客户数
			map.put("newAddTwiceVisitNum", newAddTwiceVisitNum);

			if (ss2.size() > 0) {
				// 新增总储客数
				map.put("newAddMyCustomer", ss2.size());
				for (String string : ss2) {
					List<ContractRecords> crList66 = baseDao
							.findByHql("from ContractRecords where projectId ='" + user.getParentId()
									+ "' and applyTime like '%" + day + "%' and customerPhone = '" + string + "' ");
					if (crList66.size() > 0) {
						for (ContractRecords cr : crList66) {
							if (cr.getRecordStatus() == 4 || cr.getRecordStatus() == 5) {
								reserveCustomerToDealNum++;
							}
						}
					}
				}
				// 储客成交比
				map.put("reserveCustomerToDealRate", getTwoNumberForValue(reserveCustomerToDealNum, ss2.size()));
			} else {
				map.put("newAddMyCustomer", 0);
				map.put("reserveCustomerToDealRate", 0);
			}
			if (ss.size() > 0) {
				// 老客户数
				map.put("oldMyCustomerVisitNum", ss.size());
			} else {
				map.put("oldMyCustomerVisitNum", 0);
			}

			List<House> hList = baseDao.findByHql("from House where projectId = '" + project.getProjectId() + "' ");

			Set<String> ss10 = new HashSet<>();
			List<String> totalCList = new ArrayList<>();
			List<ContractRecords> crList = baseDao.findByHql("from ContractRecords where projectId ='"
					+ project.getProjectId() + "'" + " and applyTime like '%" + day + "%'");
			for (ContractRecords cr : crList) {
				// 总认购客户数(去重)
				ss10.add(cr.getCustomerPhone());
				totalCList.add(cr.getCustomerPhone());
				st.add(cr.getHouseNum());
				if (cr.getBuyPrice() != null && !"".equals(cr.getBuyPrice())) {
					allContractMoney += cr.getBuyPrice();
				}
				if (cr.getRecordStatus() == 4) {
					readyToDealNum++;
					if (cr.getBuyPrice() != null && !"".equals(cr.getBuyPrice())) {
						House h = (House) baseDao.loadObject("from House where houseNum = '" + cr.getHouseNum() + "' ");
						EnterBuy eb = (EnterBuy) baseDao
								.loadObject("from EnterBuy where projectId = '" + cr.getProjectId() + "' ");
						reachContractMoney += eb.getDposit();
						lockHousePrice += cr.getBuyPrice();
						readyToDealHousePrice += cr.getBuyPrice();
					}
					if (cr.getAccountStyle() != null && !"".equals(cr.getAccountStyle())) {

						if (cr.getAccountStyle() == 2) {
							haveGetMoneyNum++;
						}
					}
				}
				//下定数
				if(cr.getRecordStatus() == 4 || cr.getRecordStatus() == 5){
					readyToAppointNum++;
				}
				if (cr.getRecordStatus() == 5) {
					haveDealNum++;
					if (cr.getBuyPrice() != null && !"".equals(cr.getBuyPrice())) {
						// House h = (House) baseDao.loadObject("from House
						// where houseNum = '"+cr.getHouseNum()+"' ");
						haveDealHousePrice += cr.getBuyPrice();
					}
				}
				if (cr.getRecordStatus() == 7) {
					abandonDealNum++;
					if (cr.getBuyPrice() != null && !"".equals(cr.getBuyPrice())) {
						// House h = (House) baseDao.loadObject("from House
						// where houseNum = '"+cr.getHouseNum()+"' ");
						abandonDealHousePrice += cr.getBuyPrice();
					}
				}
			}

			
			Set<String> newCuSet = new HashSet<>();
			Set<String> oldCuSet = new HashSet<>();
			

			for (String string : totalCList) {
				List<VisitRecords> vrList88 = baseDao.findByHql(
						"from VisitRecords where projectId = '" + user.getParentId() + "'" + " and arriveTime <= '"
								+ getThisTimeStartTime(day) + "' and phone = '" + string + "' ");
				if (vrList88.size() > 0) {
					// 老客户认购数
					oldCuSet.add(string);
				} else {
					// 新客户认购数
					newCuSet.add(string);
				}
			}
			// 老客户认购数
			int oldCustomerReadyToBuyNum = oldCuSet.size();
			// 新客户认购数
			int newCustomerReadyToBuyNum = newCuSet.size();

			// 总认购客户数
			if (ss10.size() > 0) {
				map.put("allReadyBuyCustomersNum", ss10.size());
				if (oldCustomerReadyToBuyNum > 0) {
					map.put("oldCustomerReadyToBuyNum", oldCustomerReadyToBuyNum);
					map.put("oldCustomerReadyToBuyRate", getTwoNumberForValue(oldCustomerReadyToBuyNum, ss10.size()));
				} else {
					map.put("oldCustomerReadyToBuyNum", 0);
					map.put("oldCustomerReadyToBuyRate", 0);
				}
				if (newCustomerReadyToBuyNum > 0) {
					map.put("newCustomerReadyToBuyNum", newCustomerReadyToBuyNum);
					map.put("newCustomerReadyToBuyRate", getTwoNumberForValue(newCustomerReadyToBuyNum, ss10.size()));
				} else {
					map.put("newCustomerReadyToBuyNum", 0);
					map.put("newCustomerReadyToBuyRate", 0);
				}
			} else {
				map.put("allReadyBuyCustomersNum", 0);
				map.put("oldCustomerReadyToBuyNum", 0);
				map.put("oldCustomerReadyToBuyRate", 0);
				map.put("newCustomerReadyToBuyNum", 0);
				map.put("newCustomerReadyToBuyRate", 0);
				
			}
			
			if (st.size() > 0) {
				map.put("allContractNum", st.size());
				// 认购套数占比
				// map.put("readyToAllPercent",
				// getTwoNumberForValue(allContractNum, st.size()));
			} else {
				map.put("allContractNum", 0);
				// map.put("readyToAllPercent", 0);
			}
			map.put("allContractMoney", SysContent.get2Double(allContractMoney / 10000));
			map.put("reachContractMoney", SysContent.get2Double(reachContractMoney / 10000));
			map.put("haveDealNum", haveDealNum);
			map.put("lockHousePrice", SysContent.get2Double(lockHousePrice / 10000));
			map.put("abandonDealNum", abandonDealNum);
			map.put("readyToDealNum", readyToDealNum);
			// 下定数
			map.put("readyToAppointNum", readyToAppointNum);
			map.put("haveDealHousePrice", SysContent.get2Double(haveDealHousePrice / 10000));
			map.put("abandonDealHousePrice", SysContent.get2Double(abandonDealHousePrice / 10000));
			map.put("readyToDealHousePrice", SysContent.get2Double(readyToDealHousePrice / 10000));

			String visitDeailSql = "SELECT c.recordNo,c.userId,c.houseNum,c.customerName,c.customerPhone,v.num ,c.recordStatus, v.arriveTime "
					+ "FROM t_contractrecords c, (SELECT customerId,phone,COUNT(phone) num, arriveTime FROM t_visitrecords "
					+ "WHERE writeState = 1 GROUP BY phone)v WHERE c.customerPhone = v.phone and c.recordStatus = 5 and c.projectId = '"
					+ user.getParentId() + "'";// 到访成交
			visitDeailSql += " and v.arriveTime like '%" + day + "%' " + " and c.contractConfirmTime like '%" + day
					+ "%' GROUP BY (c.customerPhone)";
			List list88 = baseDao.queryBySql(visitDeailSql);

			Set<String> ss11 = new HashSet<>();

			List<VisitRecords> vrList = baseDao.findByHql("from VisitRecords where projectId = '" + user.getParentId()
					+ "'" + " and arriveTime like '%" + day + "%'");
			for (VisitRecords visitRecords : vrList) {
				ss11.add(visitRecords.getPhone());
			}

			if (vrList.size() > 0) {
				// 来访成交比
				if (list88.size() > 0) {
					map.put("visitToDealRate", getTwoNumberForValue(list88.size(), ss11.size()));
				} else {
					map.put("visitToDealRate", 0);
				}
			} else {
				map.put("visitToDealRate", 0);
			}
			if (haveDealNum > 0) {
				// 认购签约率
				map.put("readyToDealRate", getTwoNumberForValue(haveDealNum, crList.size()));
			} else {
				map.put("readyToDealRate", 0);
			}
			list.add(map);
		}

		return list;
	}

	@Override
	public List<Map<String, Object>> findOutFieldStatement(User user, String startTime, String endTime, String oneDay) {

		List<Map<String, Object>> list = new ArrayList<>();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Project project = (Project) baseDao.loadById(Project.class, user.getParentId());
		if (project == null) {
			return null;
		}

		List<String> dayList = new ArrayList<>();

		if (oneDay != null && !"".equals(oneDay)) {

			dayList.add(oneDay);

		} else {

			// 获取两个时间段内的所有时间段
			dayList = getTwoDateEveryDay(startTime, endTime);
		}

		for (String day : dayList) {

			Map<String, Object> map = new HashMap<>();

			// 报备到访数
			int grToGetVisitNum = 0;
			// 外场成交数
			int outFieldToDealNum = 0;
			// 内场成交数
			int intFiledToDealNum = 0;

			// 备案客户数
			List<GuideRecords> grList = baseDao.findByHql("from GuideRecords where applyTime like '%" + day
					+ "%' and projectId = '" + user.getParentId() + "' ");
			if (grList.size() > 0) {
				map.put("allGrNum", grList.size());
				for (GuideRecords gr : grList) {
					if (gr.getVisitNo() != null && !"".equals(gr.getVisitNo())) {
						grToGetVisitNum++;
					}
				}
				map.put("grToGetVisitNum", grToGetVisitNum);
				map.put("grToNotGetVisitNum", grList.size() - grToGetVisitNum);
				map.put("grToVisitRate", getTwoNumberForValue(grToGetVisitNum, grList.size()));

				List<VisitRecords> vrList = baseDao.findByHql("from VisitRecords where projectId = '"
						+ user.getParentId() + "' and arriveTime like '%" + day + "%'");
				if (vrList.size() > 0) {
					// 外场导客占比
					map.put("outFieldToVisitRate", getTwoNumberForValue(grToGetVisitNum, vrList.size()));
				} else {
					map.put("outFieldToVisitRate", 0);
				}
				List<String> sList = new ArrayList<>();
				// 带客成交数
				int guideToDealNum = 0;

				List<ContractRecords> crList = baseDao.findByHql("from ContractRecords where projectId = '"
						+ user.getParentId() + "' and applyTime like '%" + day + "%' ");
				if (crList.size() > 0) {
					for (ContractRecords cr : crList) {
						if (cr.getRecordStatus() == 5) {
							String userId = cr.getUserId();
							User u = (User) baseDao.loadObject("from User where userId = '" + cr.getUserId() + "' ");
							Set<Role> sr = u.getRoleId();
							for (Role role : sr) {
								if (role.getRoleId() == 1 || role.getRoleId() == 2) {
									outFieldToDealNum++;
								}
								if (role.getRoleId() == 3) {
									sList.add(cr.getCustomerPhone());
									intFiledToDealNum++;
								}
							}
						}
						map.put("outFieldToDealNum", outFieldToDealNum);
						map.put("outFieldToDealRate",
								getTwoNumberForValue(outFieldToDealNum, outFieldToDealNum + intFiledToDealNum));

					}
					for (String string : sList) {
						List<GuideRecords> grList2 = baseDao
								.findByHql("from GuideRecords where applyTime like '%" + day + "%' and projectId = '"
										+ user.getParentId() + "' and customerPhone = '" + string + "' ");
						if (grList2.size() > 0) {
							guideToDealNum++;
						}
					}
					map.put("guideToDealNum", guideToDealNum);

				} else {
					map.put("outFieldToVisitRate", 0);
					map.put("guideToDealNum", 0);
					map.put("outFieldToVisitNum", 0);
					map.put("outFieldToDealNum", 0);
					map.put("outFieldToDealRate", 0);
				}

			} else {
				map.put("allGrNum", 0);
				map.put("grToGetVisitNum", 0);
				map.put("grToNotGetVisitNum", 0);
				map.put("grToVisitRate", 0);

				List<VisitRecords> vrList = baseDao.findByHql("from VisitRecords where projectId = '"
						+ user.getParentId() + "' and arriveTime like '%" + day + "%' and isNew is true");
				if (vrList.size() > 0) {
					// 外场导客占比
					map.put("outFieldToVisitRate", getTwoNumberForValue(grToGetVisitNum, vrList.size()));
				} else {
					map.put("outFieldToVisitRate", 0);
				}
				List<String> sList = new ArrayList<>();
				// 带客成交数
				int guideToDealNum = 0;

				List<ContractRecords> crList = baseDao.findByHql("from ContractRecords where projectId = '"
						+ user.getParentId() + "' and applyTime like '%" + day + "%' ");
				if (crList.size() > 0) {
					for (ContractRecords cr : crList) {
						if (cr.getRecordStatus() == 5) {
							String userId = cr.getUserId();
							User u = (User) baseDao.loadObject("from User where userId = '" + cr.getUserId() + "' ");
							Set<Role> sr = u.getRoleId();
							for (Role role : sr) {
								if (role.getRoleId() == 1 || role.getRoleId() == 2) {
									outFieldToDealNum++;
								}
								if (role.getRoleId() == 3) {
									sList.add(cr.getCustomerPhone());
									intFiledToDealNum++;
								}
							}
						}
						map.put("outFieldToDealNum", outFieldToDealNum);
						map.put("outFieldToDealRate",
								getTwoNumberForValue(outFieldToDealNum, outFieldToDealNum + intFiledToDealNum));

					}
					for (String string : sList) {
						List<GuideRecords> grList2 = baseDao
								.findByHql("from GuideRecords where applyTime like '%" + day + "%' and projectId = '"
										+ user.getParentId() + "' and customerPhone = '" + string + "' ");
						if (grList2.size() > 0) {
							guideToDealNum++;
						}
					}
					map.put("guideToDealNum", guideToDealNum);
				} else {
					map.put("outFieldToVisitRate", 0);
					map.put("guideToDealNum", 0);
					map.put("outFieldToVisitNum", 0);
					map.put("outFieldToVisitRate", 0);
					map.put("outFieldToDealNum", 0);
					map.put("outFieldToDealRate", 0);
				}
			}
			map.put("date", day);

			list.add(map);
		}

		return list;
	}

	// 获取当前时间的一天的最后时刻
	private String getThisTimeLastTime(String time) {

		Date date = DateUtil.parse(time, DateUtil.PATTERN_CLASSICAL_SIMPLE);
		String start1 = DateUtil.format(date);
		Date s = DateUtil.parse(start1, DateUtil.PATTERN_CLASSICAL);
		Date d = DateUtil.getIntegralEndTime(s);

		return DateUtil.format(d);
	}

	private String getThisTimeStartTime(String time) {

		Date date = DateUtil.parse(time, DateUtil.PATTERN_CLASSICAL_SIMPLE);
		String start1 = DateUtil.format(date);
		Date s = DateUtil.parse(start1, DateUtil.PATTERN_CLASSICAL);
		Date d = DateUtil.getIntegralStartTime(s);

		return DateUtil.format(d);
	}

	@Override
	public Map<String, Object> findVisitDataForLabel(User user, String startTime, String endTime, String oneDay) {

		// TODO
		if (startTime == null || "".equals(startTime)) {
			startTime = DateUtil.format(DateUtil.rollDay(new Date(), -6), DateUtil.PATTERN_CLASSICAL_SIMPLE);
		}
		if (endTime == null || "".equals(endTime)) {
			endTime = DateUtil.format(new Date(), DateUtil.PATTERN_CLASSICAL_SIMPLE);
		}

		Map<String, Object> map = new HashMap<>();

		Date startDate1 = DateUtil.parse(startTime, DateUtil.PATTERN_CLASSICAL_SIMPLE);
		startTime = DateUtil.format(startDate1);

		endTime = getThisTimeLastTime(endTime);

		Project project = (Project) baseDao.loadById(Project.class, user.getParentId());

		// 总接访数
		Integer totalVisitCount = 0;
		// 有效接访
		Integer validVisitCount = 0;
		// 无效接访
		Integer inValidVisitCount = 0;
		// 新客户通道
		Integer newCustomerAccess = 0;
		// 新客户通道流失数
		Integer newStomerAccessLosedCount = 0;
		// 老客户通道
		Integer oldCustomerAccess = 0;
		// 老客户通道流失数
		Integer oldCustomerAccessLosedCount = 0;
		// 新客户通道流失率
		String newCustomerAccessLosed = "0.0%";
		// 老客户通道流失率
		String oldCustomerAccessLosed = "0.0%";
		// 指定接访量
		Integer appointVisitCount = 0;
		// 指定接访流失量
		Integer appointLosedCount = 0;
		// 指定接访流失率
		String appointVisitLosed = "0.0%";
		// 替接数
		Integer replaceVisitCount = 0;
		// 有接访和送别时间的次数
		Integer totalVisitTimeCount = 0;
		// 累计接访时间
		Long totalVisitTime = 0L;

		/*---------累计接访数据-----------*/
		// 总接访
		Integer allVisitCount = 0;
		// 新客户通道
		Integer allNewCustomerAccess = 0;
		// 新客户通道流失
		Integer allNewStomerAccessLosedCount = 0;
		// 老客户通道
		Integer allOldCustomerAccess = 0;
		// 老客户通道流失
		Integer allOldCustomerAccessLosedCount = 0;
		// 指定接访
		Integer allAppointVisitCount = 0;
		// 指定接访流失
		Integer allAppointLosedCount = 0;
		// 替接
		Integer allReplaceVisitCount = 0;
		// 接访总时间
		Long allVisitTime = 0L;
		// 有接访送别时间的次数
		Integer allVisitTimeCount = 0;

		String visitHql = "from VisitRecords where projectId = '" + project.getProjectId() + "' ";

		List<VisitRecords> totalVisitList = baseDao.findByHql(visitHql);

		if (oneDay != null && !"".equals(oneDay)) {

			visitHql += " and arriveTime like '%" + oneDay + "%'";
		} else {
			visitHql += " and arriveTime >= '" + startTime + "'";
			visitHql += " and arriveTime <= '" + endTime + "'";
		}

		List<VisitRecords> visitList = baseDao.findByHql(visitHql);

		totalVisitCount = visitList.size();

		for (VisitRecords vr : visitList) {

			if (vr.getWriteState() != null && !"".equals(vr.getWriteState())) {

				if (vr.getWriteState() == 1) {// 有效接访
					validVisitCount++;
				} else {// 无效接访
					inValidVisitCount++;
				}
			} else {
				inValidVisitCount++;
			}

			if (vr.getIsNew() == null || "".equals(vr.getIsNew()) || vr.getIsNew()) {// 新客户通道

				newCustomerAccess++;
				if (vr.getWriteState() != null && !"".equals(vr.getWriteState())) {

					if (vr.getWriteState() != 1) {
						newStomerAccessLosedCount++;
					}
				} else {
					newStomerAccessLosedCount++;
				}
			} else {// 老客户通道

				oldCustomerAccess++;
				if (vr.getWriteState() != null && !"".equals(vr.getWriteState())) {

					if (vr.getWriteState() != 1) {
						oldCustomerAccessLosedCount++;
					}
				} else {
					oldCustomerAccessLosedCount++;
				}
			}

			if (vr.getAppointUserId() != null && !"".equals(vr.getAppointUserId())) {
				// 指定接访就是指定接访的置业顾问的id不为空
				appointVisitCount++;
				if (vr.getWriteState() != null && !"".equals(vr.getWriteState())) {

					if (vr.getWriteState() != 1) {
						appointLosedCount++;
					}
				} else {
					appointLosedCount++;
				}

				// 替接
				if (!vr.getAppointUserId().equals(vr.getUserId())) {
					replaceVisitCount++;
				}
			}
			// 接访时间
			if (vr.getLeaveTime() != null && !"".equals(vr.getLeaveTime()) && vr.getReceptTime() != null
					&& !"".equals(vr.getReceptTime())) {
				long replaceLeave = DateUtil.parse(vr.getLeaveTime()).getTime();
				long replaceRecept = DateUtil.parse(vr.getReceptTime()).getTime();
				totalVisitTime += replaceLeave - replaceRecept;
				totalVisitTimeCount++;
			} /*
				 * else { totalVisitTime += 60 * 60 * 1000; }
				 */

		}

		// 累计接访数据（历史总记录）
		allVisitCount = totalVisitList.size();
		for (VisitRecords vrs : totalVisitList) {

			if (vrs.getIsNew() == null || "".equals(vrs.getIsNew()) || vrs.getIsNew()) {
				allNewCustomerAccess++;
				if (vrs.getWriteState() != null && !"".equals(vrs.getWriteState())) {

					if (vrs.getWriteState() != 1) {
						allNewStomerAccessLosedCount++;
					}
				} else {
					allNewStomerAccessLosedCount++;
				}
			} else {
				allOldCustomerAccess++;
				if (vrs.getWriteState() != null && !"".equals(vrs.getWriteState())) {

					if (vrs.getWriteState() != 1) {
						allOldCustomerAccessLosedCount++;
					}
				} else {
					allOldCustomerAccessLosedCount++;
				}
			}
			if (vrs.getAppointUserId() != null && !"".equals(vrs.getAppointUserId())) {
				// 总指定接访
				allAppointVisitCount++;
				if (vrs.getWriteState() != null && !"".equals(vrs.getWriteState())) {

					if (vrs.getWriteState() != 1) {
						allAppointLosedCount++;
					}
				} else {
					allAppointLosedCount++;
				}

				// 总替接
				if (!vrs.getAppointUserId().equals(vrs.getUserId())) {
					allReplaceVisitCount++;
				}
			}

			if (vrs.getLeaveTime() != null && !"".equals(vrs.getLeaveTime()) && vrs.getReceptTime() != null
					&& !"".equals(vrs.getReceptTime())) {
				long replaceLeave = DateUtil.parse(vrs.getLeaveTime()).getTime();
				long replaceRecept = DateUtil.parse(vrs.getReceptTime()).getTime();
				allVisitTime += replaceLeave - replaceRecept;
				allVisitTimeCount++;
			} /*
				 * else { allVisitTime += 60 * 60 * 1000; }
				 */

		}

		map.put("totalVisitCount", totalVisitCount);
		map.put("validVisitCount", validVisitCount);
		map.put("inValidVisitCount", inValidVisitCount);
		map.put("newCustomerAccess", newCustomerAccess);
		if (newCustomerAccess > 0) {
			map.put("newCustomerAccessLosed", getTwoNumberForValue(newStomerAccessLosedCount, newCustomerAccess));
		} else {
			map.put("newCustomerAccessLosed", 0);
		}
		map.put("oldCustomerAccess", oldCustomerAccess);
		if (oldCustomerAccess > 0) {
			map.put("oldCustomerAccessLosed", getTwoNumberForValue(oldCustomerAccessLosedCount, oldCustomerAccess));
		} else {
			map.put("oldCustomerAccessLosed", 0);
		}
		map.put("appointVisitCount", appointVisitCount);
		if (appointVisitCount > 0) {
			map.put("appointVisitLosed", getTwoNumberForValue(appointLosedCount, appointVisitCount));
		} else {
			map.put("appointVisitLosed", 0);
		}
		map.put("replaceVisitCount", replaceVisitCount);
		if (totalVisitTime > 0) {
			map.put("averageVisitTime", (totalVisitTime / totalVisitTimeCount / 1000 / 60) + "分钟");
		} else {
			map.put("averageVisitTime", "0小时");
		}

		// 累计接访数据
		map.put("allVisitCount", allVisitCount);
		map.put("allNewCustomerAccess", allNewCustomerAccess);
		if (allNewCustomerAccess > 0) {
			map.put("allNewStomerAccessLosed",
					getTwoNumberForValue(allNewStomerAccessLosedCount, allNewCustomerAccess));
		} else {
			map.put("allNewStomerAccessLosed", 0);
		}
		map.put("allOldCustomerAccess", allOldCustomerAccess);
		if (allOldCustomerAccess > 0) {
			map.put("allOldCustomerAccessLosed",
					getTwoNumberForValue(allOldCustomerAccessLosedCount, allOldCustomerAccess));
		} else {
			map.put("allOldCustomerAccessLosed", 0);
		}
		map.put("allAppointVisitCount", allAppointVisitCount);
		if (allAppointVisitCount > 0) {
			map.put("allAppointLosed", getTwoNumberForValue(allAppointLosedCount, allAppointVisitCount));
		} else {
			map.put("allAppointLosed", 0);
		}
		map.put("allReplaceVisitCount", allReplaceVisitCount);
		if (allVisitTime > 0) {
			map.put("allAverageVisitTime", (allVisitTime / allVisitTimeCount / 1000 / 60) + "分钟");
		} else {
			map.put("allAverageVisitTime", "0小时");
		}

		return map;
	}

	/**
	 * 获取两个数值的百分比
	 * 
	 * @param divisor
	 * @param dividend
	 * @return
	 */
	private String getTwoNumberForValue(Integer divisor, Integer dividend) {

		if (dividend == 0) {
			return "0";
		}

		NumberFormat numberFormat = NumberFormat.getInstance();

		// 设置精确到小数点后2位

		numberFormat.setMaximumFractionDigits(2);

		String result = numberFormat.format((float) divisor / (float) dividend * 100);

		return result;

	}

	// 对到访表的到访时间是否为空进行判断（不可他用）
	private String identifyString(String oneDay, String startTime, String endTime) {

		String result = "";
		if (oneDay != null && !"".equals(oneDay)) {
			result += " and arriveTime like '%" + oneDay + "%'";
		} else {

			if (startTime != null && !"".equals(startTime)) {
				result += " and arriveTime >= '" + startTime + "'";
			}
			if (endTime != null && !"".equals(endTime)) {
				result += " and arriveTime <= '" + endTime + "'";
			}
		}
		return result;
	}

	@Override
	public Map<String, Object> findMomeryCustomerData(User user, String startTime, String endTime, String oneDay) {

		if (startTime == null || "".equals(startTime)) {
			startTime = DateUtil.format(DateUtil.rollDay(new Date(), -6), DateUtil.PATTERN_CLASSICAL_SIMPLE);
		}
		if (endTime == null || "".equals(endTime)) {
			endTime = DateUtil.format(new Date(), DateUtil.PATTERN_CLASSICAL_SIMPLE);
		}

		Map<String, Object> map = new HashMap<>();
		map.put("thisDate", startTime);
		map.put("thisSevenDate", endTime);

		Date startDate1 = DateUtil.parse(startTime, DateUtil.PATTERN_CLASSICAL_SIMPLE);
		startTime = DateUtil.format(startDate1);

		endTime = getThisTimeLastTime(endTime);

		Project project = (Project) baseDao.loadById(Project.class, user.getParentId());

		if (project == null) {
			return null;
		}

		// 新增储客
		int newCustomerCount = 0;
		// 新增二次回头客
		int customerReturnBackVisitNum = 0;
		// 平台导客数
		int platformCustomerCount = 0;
		// 累计新增储客
		int totalNewCustomerCount = 0;
		// 累计新增二次回头客
		int totalCustomerReturnBackVisitNum = 0;
		// 累计平台导客数
		int totalPlatformCustomerCount = 0;

		// 时间段内到访客户
		String cuEndHql = "from ProjectCustomers where projectId = '" + project.getProjectId()
				+ "' and projectCustomerPhone is not null and createTime is not null";
		String cuStartHql = "from ProjectCustomers where projectId = '" + project.getProjectId()
				+ "' and projectCustomerPhone is not null and createTime is not null";
		String startAndEndHql = cuEndHql;
		String totalHql = cuEndHql;
		List<ProjectCustomers> totalPList = baseDao.findByHql(totalHql);
		String visitEndHql = "from VisitRecords where projectId = '" + project.getProjectId()
				+ "' and phone is not null ";
		String visitStartHql = visitEndHql;
		if (oneDay != null && !"".equals(oneDay)) {
			cuEndHql += " and createTime <= '" + getThisTimeLastTime(oneDay) + "' ";
			cuStartHql += " and createTime <= '" + getThisTimeStartTime(oneDay) + "' ";
			visitEndHql += " and arriveTime <= '" + getThisTimeLastTime(oneDay) + "' ";
			visitStartHql += " and arriveTime <= '" + getThisTimeStartTime(oneDay) + "' ";
			startAndEndHql += " and createTime like '%" + oneDay + "%' ";
		} else {
			if (startTime != null && !"".equals(startTime)) {
				cuStartHql += " and createTime <= '" + startTime + "' ";
				visitStartHql += " and arriveTime <= '" + startTime + "' ";
				startAndEndHql += " and createTime >= '" + startTime + "' ";
			}
			if (endTime != null && !"".equals(endTime)) {
				cuEndHql += " and createTime <= '" + endTime + "' ";
				visitEndHql += " and arriveTime <= '" + endTime + "' ";
				startAndEndHql += " and createTime <= '" + endTime + "' ";
			}
		}

		List<ProjectCustomers> listStart = baseDao.findByHql(cuStartHql);
		List<ProjectCustomers> listEnd = baseDao.findByHql(cuEndHql);
		List<VisitRecords> visitEndList = baseDao.findByHql(visitEndHql);
		List<VisitRecords> visitStartList = baseDao.findByHql(visitStartHql);

		newCustomerCount = listEnd.size() - listStart.size();

		int oldStart = 0;
		int oldEnd = 0;

		for (ProjectCustomers pc : listStart) {
			int count1 = 0;
			for (VisitRecords vrs : visitStartList) {
				if (pc.getProjectCustomerPhone().equals(vrs.getPhone())) {
					count1++;
				}
			}
			if (count1 > 1) {
				oldStart++;
			}
		}

		for (ProjectCustomers pc : listEnd) {
			int count2 = 0;
			for (VisitRecords vrs : visitEndList) {
				if (pc.getProjectCustomerPhone().equals(vrs.getPhone())) {
					count2++;
				}
			}
			if (count2 > 1) {
				oldEnd++;
			}
		}

		customerReturnBackVisitNum = oldEnd - oldStart;

		List<ProjectCustomers> startAndEndList = baseDao.findByHql(startAndEndHql);

		for (ProjectCustomers pcs : startAndEndList) {
			// 平台导客
			// 有到访记录和备案记录算平台导客,状态不为2：删除 3：否决
			String platformCuHql = "from GuideRecords where customerPhone = '" + pcs.getProjectCustomerPhone() + "' "
					+ " and applyStatus != 2 and applyStatus != 3";
			List<GuideRecords> platList = baseDao.findByHql(platformCuHql);
			if (platList.size() > 0) {
				platformCustomerCount++;
			}
		}


		// 累计储客数据
		String totalVisitHql = "from VisitRecords where projectId = '" + project.getProjectId() + "'  and phone is not null ";
		List<VisitRecords> list = baseDao.findByHql(totalVisitHql);
		totalNewCustomerCount = totalPList.size();
		for (ProjectCustomers str2 : totalPList) {
			Integer count3 = 0;
			for(VisitRecords vr : list){
				if(str2.getProjectCustomerPhone().equals(vr.getPhone())){
					count3++;
				}
			}
			
			if(count3 > 1){
				totalCustomerReturnBackVisitNum++;
			}

			// 累计平台导客
			String totalPlatformCuHql = "from GuideRecords where customerPhone = '" + str2.getProjectCustomerPhone()
					+ "' " + " and applyStatus != 2 and applyStatus != 3";
			List<GuideRecords> totalPlatList = baseDao.findByHql(totalPlatformCuHql);
			if (totalPlatList.size() > 0) {
				totalPlatformCustomerCount++;
			}
		}
		map.put("newCustomerCount", newCustomerCount);
		map.put("customerReturnBackVisitNum", customerReturnBackVisitNum);
		map.put("platformCustomerCount", platformCustomerCount);
		// 平台到导客率
		map.put("platformCustomerRate", getTwoNumberForValue(platformCustomerCount, newCustomerCount));

		/** 累计储客 ***/
		map.put("totalNewCustomerCount", totalNewCustomerCount);
		map.put("totalCustomerReturnBackVisitNum", totalCustomerReturnBackVisitNum);
		map.put("totalPlatformCustomerCount", totalPlatformCustomerCount);
		map.put("totalPlatformCustomerRate", getTwoNumberForValue(totalPlatformCustomerCount, totalNewCustomerCount));

		return map;
	}

	@Override
	public Map<String, Object> findDealDataForLabel(User user) {

		Map<String, Object> map = new HashMap<>();

		Project project = (Project) baseDao.loadById(Project.class, user.getParentId());

		// 认购总量（成功与不成功）
		Integer enterBuyCount = 0;
		// 下定房源总货值
		Long appointCount = 0L;
		// 总订单数
		Integer totalConNum = 0;
		// 签约数量
		Integer signedCount = 0;
		// 未完成签约数量
		Integer notSignedCount = 0;
		// 已同意的认购
		Integer agreeEnterBuyCount = 0;
		// 来访总数
		Integer totalVisitCount = 0;

		String enterBuyHql = "from ContractRecords where projectId = '" + project.getProjectId() + "'";
		// 所有的认购数据
		List<ContractRecords> totalList = baseDao.findByHql(enterBuyHql);
		totalConNum = totalList.size();
		Set<Integer> conSet = new HashSet<>();
		for (ContractRecords crs : totalList) {

			conSet.add(crs.getHouseNum());

			if (crs.getBuyPrice() != null && !"".equals(crs.getBuyPrice())) {
				appointCount += new Long(crs.getBuyPrice().longValue());
			}

			String remitTime = crs.getRemitConfirmTime();
			if (crs.getRecordStatus() == 5) {
				signedCount++;
			} else if (crs.getRecordStatus() == 1 && (remitTime != null && !"".equals(remitTime))) {
				notSignedCount++;
			} else if (crs.getRecordStatus() == 1) {
				agreeEnterBuyCount++;
			}
		}
		enterBuyCount = totalList.size();

		// 来访总数
		Set<String> visitSet = new HashSet<>();
		String visitHql = "from VisitRecords where projectId = '" + project.getProjectId() + "'";
		List<VisitRecords> visitList = baseDao.findByHql(visitHql);
		for (VisitRecords vs : visitList) {
			if (vs.getPhone() != null && !"".equals(vs.getPhone())) {
				visitSet.add(vs.getPhone());
			}
		}

		totalVisitCount = visitSet.size();

		map.put("enterBuyCount", enterBuyCount);
		map.put("appointCount", SysContent.get2Double((Double) appointCount.doubleValue() / 10000));
		map.put("enterBuyRate", getTwoNumberForValue(agreeEnterBuyCount, enterBuyCount));
		map.put("signedCount", signedCount);
		map.put("visitAndBuyRate", getTwoNumberForValue(signedCount, totalConNum));
		map.put("recordsAndSignedRate", getTwoNumberForValue(signedCount, enterBuyCount));
		map.put("agreeEnterBuyCount", agreeEnterBuyCount);

		return map;
	}

	@Override
	public Map<String, Object> findHouseBuyDataforTable(User user) {

		/**
		 * 成交分析--根据房源类型进行拆分
		 */
		// 已售房源的数量
		List<Integer> soldHouseNum = new ArrayList<>();
		// 库存房源的数量
		List<Integer> inventoryHouseNum = new ArrayList<>();
		// 房源类型的名称
		List<String> houseTypeName = new ArrayList<>();

		Map<String, Object> map = new HashMap<>();
		// 已经售出房源
		List<HouseData> soldHouse = new ArrayList<>();
		// 库存房源
		List<HouseData> inventoryHouse = new ArrayList<>();

		Project project = (Project) baseDao.loadById(Project.class, user.getParentId());
		if (project == null) {
			return null;
		}

		// 查找所有的房源类型不为空的房源并且不是删除状态的
		String houseHql = "from House where projectId = '" + project.getProjectId()
				+ "' and houseTypeId is not null and houseStatus != 2";

		List<House> houseList = baseDao.findByHql(houseHql);

		// 查出房子的房源类型
		String houseTypeHql = "from HouseType where projectId = '" + project.getProjectId() + "' and isUse = 'yes' ";
		List<HouseType> houseTypeList = baseDao.findByHql(houseTypeHql);

		for (House house : houseList) {

			// 查找已经签约的认购，签约代表房子已经售出
			String contractHql = "from ContractRecords where projectId = '" + project.getProjectId() + "'"
					+ " and recordStatus = 5 and houseNum = " + house.getHouseNum() + "";
			ContractRecords cr = (ContractRecords) baseDao.loadObject(contractHql);

			for (HouseType ht : houseTypeList) {

				// 匹配房源类型
				if (ht.getHouseTypeId().equals(house.getHouseTypeId())) {

					HouseData houseData = new HouseData();
					houseData.setHouseTypeId(ht.getHouseTypeId());
					houseData.setHouseTypeName(ht.getCaption());
					houseData.setBuildArea(ht.getArea());
					houseData.setSoldNum(1);
					if (cr == null) {// 库存
						inventoryHouse.add(houseData);
						HouseData hd = new HouseData();
						hd.setHouseTypeId(ht.getHouseTypeId());
						hd.setHouseTypeName(ht.getCaption());
						hd.setSoldNum(0);
						hd.setBuildArea(ht.getArea());
						soldHouse.add(hd);
					} else {// 售出
						soldHouse.add(houseData);
						HouseData hd = new HouseData();
						hd.setHouseTypeId(ht.getHouseTypeId());
						hd.setHouseTypeName(ht.getCaption());
						hd.setSoldNum(0);
						hd.setBuildArea(ht.getArea());
						inventoryHouse.add(hd);
					}
				}

			}

		}
		// 售出房源过滤
		Map<Object, Object> map2 = new HashMap<>();
		// 库存房源过滤
		Map<Object, Object> map3 = new HashMap<>();

		// 售出房源
		for (HouseData sh : soldHouse) {
			if (map2.containsKey(sh.getHouseTypeName())) {
				HouseData hd = new HouseData();
				hd.setHouseTypeName(sh.getHouseTypeName());
				HouseData a = (HouseData) map2.get(sh.getHouseTypeName());
				hd.setSoldNum(a.getSoldNum() + sh.getSoldNum());
				hd.setBuildArea(sh.getBuildArea());
				map2.remove(sh.getHouseTypeName());
				map2.put(hd.getHouseTypeName(), hd);
			} else {
				map2.put(sh.getHouseTypeName(), sh);
			}
		}
		// 库存房源
		for (HouseData ih : inventoryHouse) {
			if (map3.containsKey(ih.getHouseTypeName())) {
				HouseData hd = new HouseData();
				hd.setHouseTypeName(ih.getHouseTypeName());
				HouseData a = (HouseData) map3.get(ih.getHouseTypeName());
				hd.setSoldNum(a.getSoldNum() + ih.getSoldNum());
				hd.setBuildArea(ih.getBuildArea());
				map3.remove(ih.getHouseTypeName());
				map3.put(hd.getHouseTypeName(), hd);
			} else {
				map3.put(ih.getHouseTypeName(), ih);
			}
		}

		// 清空集合
		soldHouse.clear();
		inventoryHouse.clear();

		Iterator i1 = map2.entrySet().iterator();
		Iterator i2 = map3.entrySet().iterator();

		while (i1.hasNext()) {
			Entry entry = (Entry) i1.next();
			Object o = entry.getKey();
			String s = o.toString();
			soldHouse.add((HouseData) map2.get(s));
		}
		while (i2.hasNext()) {
			Entry entry = (Entry) i2.next();
			Object o = entry.getKey();
			String s = o.toString();
			inventoryHouse.add((HouseData) map3.get(s));
		}

		for (HouseData hd : inventoryHouse) {

			for (HouseData h : soldHouse) {

				if (hd.getHouseTypeName().equals(h.getHouseTypeName())) {
					inventoryHouseNum.add(hd.getSoldNum());
					soldHouseNum.add(h.getSoldNum());
					houseTypeName.add(h.getHouseTypeName() + "(" + h.getBuildArea() + "m²)");
				}

			}
		}

		map.put("inventoryHouseNum", inventoryHouseNum);
		map.put("soldHouseNum", soldHouseNum);
		map.put("houseTypeName", houseTypeName);

		return map;

		/*
		
		*//**
			 * 成交分析--根据房源类型进行拆分
			 */
		/*
		 * 
		 * Map<String, Object> map = new HashMap<>(); // 已经售出房源 List<HouseData>
		 * soldHouse = new ArrayList<>(); // 库存房源 List<HouseData> inventoryHouse
		 * = new ArrayList<>();
		 * 
		 * Project project = (Project) baseDao.loadById(Project.class,
		 * user.getParentId()); if (project == null) { return null; }
		 * 
		 * // 查找所有的房源类型不为空的房源 String houseHql = "from House where projectId = '"
		 * + project.getProjectId() + "' and houseTypeId is not null";
		 * 
		 * List<House> houseList = baseDao.findByHql(houseHql);
		 * 
		 * for (House house : houseList) {
		 * 
		 * // 查找已经签约的认购，签约代表房子已经售出 String contractHql =
		 * "from ContractRecords where projectId = '" + project.getProjectId() +
		 * "'" + " and recordStatus = 5 and houseNum = " + house.getHouseNum() +
		 * ""; ContractRecords cr = (ContractRecords)
		 * baseDao.loadObject(contractHql);
		 * 
		 * 
		 * HouseData houseData = new HouseData();
		 * houseData.setBuildArea(house.getBuildArea());
		 * houseData.setSoldNum(1); if (cr == null) {// 库存
		 * inventoryHouse.add(houseData); } else {// 售出
		 * soldHouse.add(houseData); } } // 售出房源过滤 Map<Object, Object> map2 =
		 * new HashMap<>(); // 库存房源过滤 Map<Object, Object> map3 = new
		 * HashMap<>();
		 * 
		 * // 售出房源 for (HouseData sh : soldHouse) { if
		 * (map2.containsKey(sh.getBuildArea())) { HouseData hd = new
		 * HouseData(); hd.setBuildArea(sh.getBuildArea()); Integer a =
		 * (Integer) map2.get(sh.getBuildArea()); hd.setSoldNum(a + 1);
		 * map2.remove(sh.getBuildArea()); map2.put(hd.getBuildArea(),
		 * hd.getSoldNum()); } else { map2.put(sh.getBuildArea(),
		 * sh.getSoldNum()); } } // 库存房源 for (HouseData ih : inventoryHouse) {
		 * if (map3.containsKey(ih.getBuildArea())) { HouseData hd = new
		 * HouseData(); hd.setBuildArea(ih.getBuildArea());
		 * hd.setSoldNum((Integer) (map3.get(ih.getBuildArea())) + 1);
		 * map3.remove(ih.getBuildArea()); map3.put(hd.getBuildArea(),
		 * hd.getSoldNum()); } else { map3.put(ih.getBuildArea(),
		 * ih.getSoldNum()); } }
		 * 
		 * // 清空集合 soldHouse.clear(); inventoryHouse.clear();
		 * 
		 * Iterator i1 = map2.entrySet().iterator(); Iterator i2 =
		 * map3.entrySet().iterator();
		 * 
		 * while (i1.hasNext()) { HouseData house = new HouseData(); Entry entry
		 * = (Entry) i1.next(); Object o = entry.getKey(); String s =
		 * o.toString(); Double d = Double.valueOf(s); house.setBuildArea(d);
		 * house.setSoldNum(Integer.parseInt(map2.get(d).toString()));
		 * soldHouse.add(house); } while (i2.hasNext()) { HouseData house = new
		 * HouseData(); Entry entry = (Entry) i2.next(); Object o =
		 * entry.getKey(); String s = o.toString(); Double d =
		 * Double.valueOf(s); house.setBuildArea(d);
		 * house.setSoldNum(Integer.parseInt(map3.get(d).toString()));
		 * inventoryHouse.add(house); } Collections.sort(soldHouse);
		 * Collections.sort(inventoryHouse); map.put("soldHouse", soldHouse);
		 * map.put("inventoryHouse", inventoryHouse);
		 * 
		 * return map;
		 */}

	@Override
	public List<Map<String, Object>> findPersonImg(User user) {

		List<Map<String, Object>> list = new ArrayList<>();

		Project project = (Project) baseDao.loadById(Project.class, user.getParentId());
		if (project == null) {
			return null;
		}
		Set<User> users = getUserByRole(3);
		for (User u : users) {
			if (project.getProjectId().equals(u.getParentId()) && u.getUserStatus() == 1) {

				Map<String, Object> map = new HashMap<>();
				map.put("userImg", u.getPhoto());
				map.put("userId", u.getUserId());
				map.put("userName", u.getUserCaption());
				map.put("phone", u.getPhone());
				list.add(map);
			}
		}

		return list;
	}

	private Set<User> getUserByRole(Integer roleId) {
		// List<Role> roleList = baseDao.findByHql("from Role where id = " + roleId);
		// Role role = roleList.get(0);
		// Set<User> users = role.getUser();
		// return null;
		String sql = "SELECT * FROM t_users u, user_role ur WHERE  u.userId = ur.u_id and ur.r_id = "+roleId;
		List<User> usersList = baseDao.queryBySql(sql, User.class);
		Set<User> users = new HashSet<>();
		for(User u : usersList){
			users.add(u);
		}
		return users;
	}

	@Override
	public List<Map<String, Object>> findVisitTopAndData(User user, String startTime, String endTime, String oneDay) {

		List<Map<String, Object>> list = new ArrayList<>();

		Project project = (Project) baseDao.loadById(Project.class, user.getParentId());
		if (project == null) {
			return null;
		}

		// 找出所有属于本案场的置业顾问
		Set<User> users = getUserByRole(3);
		for (User u : users) {
			// 案场助理的状态必须为正常的
			if (project.getProjectId().equals(u.getParentId()) && u.getUserStatus() == 1) {

				Map<String, Object> map = new HashMap<>();

				// 接访（次）
				Integer visitCount = 0;
				// 有效接访（次）
				Integer validVisitCount = 0;
				// 接访时长（毫秒）
				Long visitTime = 0L;
				// 储客数量（组）
				Integer momeryCuCount = 0;

				String visitHql = "from VisitRecords where projectId = '" + project.getProjectId() + "'"
						+ " and userId = '" + u.getUserId() + "'";
				if (oneDay != null && !"".equals(oneDay)) {

					visitHql += " and arriveTime like '%" + oneDay + "%'";
				} else {
					if (startTime != null && !"".equals(startTime)) {
						visitHql += " and arriveTime >= '" + getThisTimeStartTime(startTime) + "'";
					}
					if (endTime != null && !"".equals(endTime)) {
						visitHql += " and arriveTime <= '" + getThisTimeLastTime(endTime) + "'";
					}
				}
				List<VisitRecords> visitList = baseDao.findByHql(visitHql);
				// 将客户去重
				Set<String> phoneSet = new HashSet<>();

				for (VisitRecords vrs : visitList) {
					visitCount++;
					if (vrs.getWriteState() != null && !"".equals(vrs.getWriteState()) && vrs.getWriteState() == 1) {
						validVisitCount++;

						phoneSet.add(vrs.getPhone());
						// 这段时间新储客
						/*
						 * String customerHql =
						 * "from VisitRecords where projectId = '" +
						 * project.getProjectId() + "'" + " and phone ='" +
						 * vrs.getPhone() + "'"; if(oneDay != null &&
						 * !"".equals(oneDay)){
						 * 
						 * customerHql += " and arriveTime >= '" +
						 * getThisTimeStartTime(oneDay) + "'"; }else{
						 * 
						 * if(startTime != null && !"".equals(startTime)){
						 * customerHql += " and arriveTime >= '" +
						 * getThisTimeStartTime(startTime) + "'"; } if(endTime
						 * != null && !"".equals(endTime)){ customerHql +=
						 * " and arriveTime <= '" + getThisTimeLastTime(endTime)
						 * + "'"; } } List<VisitRecords> customerList =
						 * baseDao.findByHql(customerHql); String cusHql =
						 * "from VisitRecords where projectId = '" +
						 * project.getProjectId() + "'" + " and phone = '" +
						 * vrs.getPhone() + "'"; if(oneDay != null &&
						 * !"".equals(oneDay)){
						 * 
						 * cusHql += " and arriveTime <= '" +
						 * getThisTimeStartTime(oneDay) + "'"; }else{
						 * 
						 * if(startTime != null && !"".equals(startTime)){
						 * cusHql += " and arriveTime <= '" +
						 * getThisTimeStartTime(startTime) + "'"; } }
						 * List<VisitRecords> cusList =
						 * baseDao.findByHql(cusHql); if(customerList.size() < 2
						 * && cusList.size() == 0){ momeryCuCount++; }
						 */

					}

					if (vrs.getLeaveTime() != null && !"".equals(vrs.getLeaveTime()) && vrs.getReceptTime() != null
							&& !"".equals(vrs.getReceptTime())) {
						long replaceLeave = DateUtil.parse(vrs.getLeaveTime()).getTime();
						;
						long replaceRecept = DateUtil.parse(vrs.getReceptTime()).getTime();
						visitTime += replaceLeave - replaceRecept;
					} else {
						visitTime += 60 * 60 * 1000;
					}

				}
				map.put("userId", u.getUserId());
				map.put("userName", u.getUserCaption());
				map.put("visitCount", visitCount);
				map.put("validVisitCount", validVisitCount);
				map.put("averageVisitTime", visitTime / 1000 / 60 / 60);
				map.put("momeryCuCount", phoneSet.size());
				// 有效接访率
				map.put("validVisitRate", getTwoNumberForValue(validVisitCount, visitCount));
				list.add(map);

			}
		}

		Collections.sort(list, new Comparator<Map<String, Object>>() {

			@Override
			public int compare(Map<String, Object> o1, Map<String, Object> o2) {
				Integer s1 = (Integer) o1.get("visitCount");
				Integer s2 = (Integer) o2.get("visitCount");

				return s2 - s1;
			}

		});

		return list;
	}

	@Override
	public List<Map<String, Object>> findVisitNotRegister(User user, String startTime, String endTime, String oneDay) {

		List<Map<String, Object>> list = new ArrayList<>();
		Project project = (Project) baseDao.loadById(Project.class, user.getParentId());
		if (project == null) {
			return null;
		}

		// 找出所有属于本案场的置业顾问
		Set<User> users = getUserByRole(3);

		for (User u : users) {
			if (project.getProjectId().equals(u.getParentId()) && u.getUserStatus() == 1) {

				Map<String, Object> map = new HashMap<>();
				// 置业顾问头像
				map.put("userImg", u.getPhoto());
				map.put("userName", u.getUserCaption());
				map.put("userId", u.getUserId());
				// 到访未登记
				String visitHql = "from VisitRecords where projectId = '" + project.getProjectId() + "'"
						+ " and userId = '" + u.getUserId() + "' and (writeState = 0 or writeState is null) ";
				if (oneDay != null && !"".equals(oneDay)) {

					visitHql += " and arriveTime like '%" + oneDay + "%'";
				} else {

					if (startTime != null && !"".equals(startTime)) {
						visitHql += " and arriveTime >= '" + getThisTimeStartTime(startTime) + "'";
					}
					if (endTime != null && !"".equals(endTime)) {
						visitHql += " and arriveTime <= '" + getThisTimeLastTime(endTime) + "'";
					}
				}
				List<VisitRecords> visitList = baseDao.findByHql(visitHql);
				map.put("validVisitCount", visitList.size());
				list.add(map);
			}
		}
		Collections.sort(list, new Comparator<Map<String, Object>>() {

			@Override
			public int compare(Map<String, Object> o1, Map<String, Object> o2) {
				Integer s1 = (Integer) o1.get("validVisitCount");
				Integer s2 = (Integer) o2.get("validVisitCount");

				return s2 - s1;
			}

		});

		return list;
	}

	@Override
	public List<Map<String, Object>> findMemoryCuTop(User user, String startTime, String endTime, String oneDay) {

		List<Map<String, Object>> list = new ArrayList<>();
		Project project = (Project) baseDao.loadById(Project.class, user.getParentId());
		if (project == null) {
			return null;
		}

		// 找出所有属于本案场的置业顾问
		Set<User> users = getUserByRole(3);

		for (User u : users) {
			if (project.getProjectId().equals(u.getParentId()) && u.getUserStatus() == 1) {

				Map<String, Object> map = new HashMap<>();

				// 置业顾问的信息
				map.put("userImg", u.getPhoto());
				map.put("userName", u.getUserCaption());
				map.put("userId", u.getUserId());

				String cuHql = "from ProjectCustomers where projectId = '" + "" + project.getProjectId()
						+ "' and createUserId = '" + u.getUserId() + "' ";
				if (startTime != null && !"".equals(startTime)) {
					cuHql += " and createTime >= '" + startTime + "' ";
				}
				if (endTime != null && !"".equals(endTime)) {
					cuHql += " and createTime <= '" + endTime + "' ";
				}

				List<ProjectCustomers> cuList = baseDao.findByHql(cuHql);
				map.put("newCustomerCount", cuList.size());
				list.add(map);
			}
		}

		Collections.sort(list, new Comparator<Map<String, Object>>() {

			@Override
			public int compare(Map<String, Object> o1, Map<String, Object> o2) {
				Integer s1 = (Integer) o1.get("newCustomerCount");
				Integer s2 = (Integer) o2.get("newCustomerCount");

				return s2 - s1;
			}

		});

		return list;
	}

	@Override
	public List<Map<String, Object>> findDealTopAndData(User user, String startTime, String endTime, String oneDay) {

		List<Map<String, Object>> list = new ArrayList<>();
		Project project = (Project) baseDao.loadById(Project.class, user.getParentId());
		if (project == null) {
			return null;
		}
		// 找出所有属于本案场的置业顾问
		Set<User> users = getUserByRole(3);

		for (User u : users) {
			if (project.getProjectId().equals(u.getParentId()) && u.getUserStatus() == 1) {

				Map<String, Object> map = new HashMap<>();

				// 认购数
				Integer recordCount = 0;
				// 签约数
				Integer signCount = 0;
				// 总货值
				Long totalMoney = 0L;

				String buyHql = "from ContractRecords where projectId = '" + project.getProjectId() + "'"
						+ " and userId = '" + u.getUserId() + "'";
				if (oneDay != null && !"".equals(oneDay)) {

					buyHql += " and applyTime like '%" + oneDay + "%'";
				} else {

					if (startTime != null && !"".equals(startTime)) {
						buyHql += " and applyTime >= '" + getThisTimeStartTime(startTime) + "'";
					}
					if (endTime != null && !"".equals(endTime)) {
						buyHql += " and applyTime <= '" + getThisTimeLastTime(endTime) + "'";
					}
				}
				List<ContractRecords> buyList = baseDao.findByHql(buyHql);
				for (ContractRecords vrs : buyList) {
					recordCount++;
					if (vrs.getRecordStatus() == 5 || vrs.getRecordStatus() == 4) {
						signCount++;

						totalMoney += new Long(vrs.getBuyPrice().longValue());
					}

				}
				map.put("userId", u.getUserId());
				map.put("userName", u.getUserCaption());
				map.put("recordCount", recordCount);
				map.put("signCount", signCount);
				map.put("totalMoney", SysContent.get2Double((Double) totalMoney.doubleValue() / 10000));

				list.add(map);
			}
		}

		Collections.sort(list, new Comparator<Map<String, Object>>() {

			@Override
			public int compare(Map<String, Object> o1, Map<String, Object> o2) {
				Integer s1 = (Integer) o1.get("signCount");
				Integer s2 = (Integer) o2.get("signCount");

				return s2 - s1;
			}

		});

		return list;
	}

	@Override
	public List<DataAnalysis> analysisOfData(String startTime, String endTime, String projectId, String oneDay) {
		// 时间为null
		if (startTime == null || "".equals(startTime)) {
			startTime = DateUtil.format(DateUtil.rollDay(new Date(), -6), DateUtil.PATTERN_CLASSICAL_SIMPLE);
		}
		if (endTime == null || "".equals(endTime)) {
			endTime = DateUtil.format(new Date(), DateUtil.PATTERN_CLASSICAL_SIMPLE);
		}
		// 该时间段内的所有到访
		if (oneDay != null && !"".equals(oneDay)) {
			startTime = oneDay;
			endTime = oneDay;
		}
		String ssHql = "from VisitRecords where projectId = '" + projectId + "' and arriveTime >= '"
				+ getThisTimeStartTime(startTime) + "' and arriveTime <='" + getThisTimeLastTime(endTime) + "'";
		List<VisitRecords> ssList = baseDao.findByHql(ssHql);
		// 没有到访
		List<DataAnalysis> dataAnalysiss = new ArrayList<>();
		// 构造出所需的返回结果
		List<String> everyDay = new ArrayList<>();
		if (oneDay != null && !"".equals(oneDay)) {
			everyDay.add(oneDay);
		} else {
			everyDay = getTwoDateEveryDay(startTime, endTime);
		}
		for (String data : everyDay) {
			DataAnalysis dals = new DataAnalysis();
			dals.setDataDate(data);
			dals.setLoseCustomer(0);// 流失
			dals.setLoseCustomerAppoint(0);// 指定流失
			dals.setNewCustomer(0);// 新登
			dals.setNewCustomerAppoint(0);// 指定新登
			dals.setVisitAgain(0);// 在到访

			for (VisitRecords v : ssList) {
				if (v.getArriveTime().startsWith(data)) {
					// 是当天的到访
					if (v.getAppointUserId() != null && !"".equals(v.getAppointUserId())) {// 指定接访
						if (v.getWriteState() == null || "".equals(v.getWriteState()) || v.getWriteState() == 0) {// 无效接访--指定流失
							dals.setLoseCustomerAppoint(dals.getLoseCustomerAppoint() + 1);
						} else {// 指定有效接访
							ProjectCustomers customer = (ProjectCustomers) baseDao.loadObject(
									"from ProjectCustomers where projectCustomerPhone = '" + v.getPhone() + "' and projectId = '" + projectId + "' ");
							if (customer != null) {
								if (customer.getCreateTime() != null && !"".equals(customer.getCreateTime())) {

									Date createTime = DateUtil.parse(customer.getCreateTime(),DateUtil.PATTERN_CLASSICAL);
									Date arrTime = DateUtil.parse(v.getArriveTime(), DateUtil.PATTERN_CLASSICAL);
									if (arrTime.getTime() > createTime.getTime()) {// 在到访
										dals.setVisitAgain(dals.getVisitAgain() + 1);
									} else {
										// 指定新登
										dals.setNewCustomerAppoint(dals.getNewCustomerAppoint() + 1);
									}
								}
							}
						}

					} else {// 不指定接访
						if (v.getWriteState() == null || "".equals(v.getWriteState()) || v.getWriteState() == 0) {// 无效接访--流失
							dals.setLoseCustomer(dals.getLoseCustomer() + 1);
						} else {// 不指定有效接访
							ProjectCustomers customer = (ProjectCustomers) baseDao.loadObject(
									"from ProjectCustomers where projectCustomerPhone = '" + v.getPhone() + "' and projectId = '" + projectId + "' ");
							if (customer != null) {
								if (customer.getCreateTime() != null && !"".equals(customer.getCreateTime())) {
									Date createTime = DateUtil.parse(customer.getCreateTime(), DateUtil.PATTERN_CLASSICAL);
									Date arrTime = DateUtil.parse(v.getArriveTime(), DateUtil.PATTERN_CLASSICAL);
									if (arrTime.getTime() > createTime.getTime()) {// 在到访
										dals.setVisitAgain(dals.getVisitAgain() + 1);
									} else {
										// 新登
										dals.setNewCustomer(dals.getNewCustomer() + 1);
									}
								}
							}
						}
					}

				}
			}
			dataAnalysiss.add(dals);
		}
		return dataAnalysiss;
	}

	@Override
	public Map<String, Object> findMomeryCusListData(User user, String startTime, String endTime, String oneDay) {

		// TODO
		if (startTime == null || "".equals(startTime)) {
			startTime = DateUtil.format(DateUtil.rollDay(new Date(), -6), DateUtil.PATTERN_CLASSICAL_SIMPLE);
		}
		if (endTime == null || "".equals(endTime)) {
			endTime = DateUtil.format(new Date(), DateUtil.PATTERN_CLASSICAL_SIMPLE);
		}

		Map<String, Object> map = new HashMap<>();
		List<StorageCustomerData> list = new ArrayList<>();

		// 获取时间集合
		List<String> dateList = new ArrayList<>();
		List<String> dayList = new ArrayList<>();

		if (oneDay != null && !"".equals(oneDay)) {// 左侧日历选某天数据

			dateList.add(oneDay);
			dayList.add(oneDay);

		} else {
			dateList = getTwoDateEveryDay(startTime, endTime);
			// 获取区间每一天的时间
			dayList = getTwoDateEveryDay(startTime, endTime);
		}

		map.put("date", dateList);

		Project project = (Project) baseDao.loadById(Project.class, user.getParentId());

		if (project == null)
			return null;

		/**
		 * 算法实现思路： 总储客（新 lao） 老客户 总储客：用时间去匹配截止到这一天的累计数据的案场客户表的客户条数
		 * 老客户：这一天的案场客户表中的客户去到访表中查找是否有是老客户
		 */
		for (String s : dayList) {

			StorageCustomerData scd = new StorageCustomerData();

			Integer oldCount = 0;
			// 总储客数（新老）
			Integer newCount = 0;

			// 截止这一天的累计客户数
			String cuHql = "from ProjectCustomers where projectId = '" + project.getProjectId()
					+ "' and createTime <= '" + getThisTimeLastTime(s) + "' and projectCustomerPhone is not null ";
			List<ProjectCustomers> cuList = baseDao.findByHql(cuHql);
			newCount = cuList.size();

			String pcHql = "from VisitRecords where projectId = '" + project.getProjectId() + "' "
					+ " and arriveTime <= '" + getThisTimeLastTime(s) + "' ";
			List<VisitRecords> pcList = baseDao.findByHql(pcHql);

			// 截止到这一天的老客户数 只要是截止这一天之前到访过两次
			for (ProjectCustomers pc : cuList) {
				Integer count = 0;
				for (VisitRecords crs : pcList) {
					if(!StringUtils.isEmpty(pc.getProjectCustomerPhone()) && !StringUtils.isEmpty(crs.getPhone()) ){
						if (crs.getPhone().equals(pc.getProjectCustomerPhone())) {
							count++;
						}
					}
				}

				if (count > 1) {
					oldCount++;
				}
			}
			scd.setNewCustomer(newCount);
			scd.setOldCustomer(oldCount);
			scd.setCustomerCreateDate(s);

			list.add(scd);

		}
		map.put("data", list);
		return map;

	}

}
