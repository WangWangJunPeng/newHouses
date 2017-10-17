package com.sc.tradmaster.service.projectcustomer.impl.dto;

import java.util.Comparator;
import java.util.Date;

import org.apache.poi.poifs.crypt.DataSpaceMapUtils.DataSpaceMapEntry;

import com.sc.tradmaster.utils.DateTime;
import com.sc.tradmaster.utils.DateUtil;

/**
 * 封装客户轨迹单条记录
 * @author maoxy
 *
 */
public class Track implements Comparable<Track>{
	//时间
	private String time;
	private String status;
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public int compareTo(Track o) {
		Date d1 = DateUtil.parse(this.time);
		Date d2 = DateUtil.parse(o.getTime());
		if(d1.before(d2)){
			return -1;
		}else{
			return 1;
		}
	}
	
}
