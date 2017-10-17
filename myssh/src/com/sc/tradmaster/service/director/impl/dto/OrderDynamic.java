package com.sc.tradmaster.service.director.impl.dto;

import java.text.ParseException;
import java.util.Map;

import com.sc.tradmaster.bean.ContractRecords;
import com.sc.tradmaster.bean.EnterBuy;
import com.sc.tradmaster.utils.SysContent;

public class OrderDynamic {
	public static Map setOrderDynamic(ContractRecords cr,Map map,EnterBuy eb) throws Exception{
		String checkSign = null;
		String payMoney = null;
		String enterPay = null;
		String sign = null;
		String orderOutTime = null;
		String revoca = null;
		if(cr.getRecordStatus()==1 && (cr.getVoucherUploadTime()==null || cr.getVoucherUploadTime().equals(""))){
			checkSign = "已同意";
			map.put("checkTime", cr.getAuditingTime());
		}else if(cr.getRecordStatus()==3){
			checkSign = "订单被拒";
			map.put("checkTime", cr.getAuditingTime());
		}else if(cr.getRecordStatus()==1 && cr.getVoucherUploadTime()!=null && !cr.getVoucherUploadTime().equals("")){
			checkSign = "已同意";
			map.put("checkTime", cr.getAuditingTime());
			payMoney = "已打款";
			map.put("payTime", cr.getVoucherUploadTime());
		}else if(cr.getRecordStatus()==4){
			checkSign = "已同意";
			map.put("checkTime", cr.getAuditingTime());
			payMoney = "已打款";
			map.put("payTime", cr.getVoucherUploadTime());
			enterPay = "待签约";
			map.put("enterPayTime", cr.getRemitConfirmTime());
		}else if(cr.getRecordStatus()==5){
			checkSign = "已同意";
			map.put("checkTime", cr.getAuditingTime());
			payMoney = "已打款";
			map.put("payTime", cr.getVoucherUploadTime());
			enterPay = "待签约";
			map.put("enterPayTime", cr.getRemitConfirmTime());
			sign = "已签约";
			map.put("signTime", cr.getContractConfirmTime());
		}else if(cr.getRecordStatus()==7){
			if(cr.getVoucherUploadTime()!=null && !cr.getVoucherUploadTime().equals("")){
				checkSign = "已同意";
				map.put("checkTime", cr.getAuditingTime());
				payMoney = "已打款";
				map.put("payTime", cr.getVoucherUploadTime());
			}else if((cr.getRemitConfirmTime()!=null && !cr.getRemitConfirmTime().equals("")) || (cr.getRemitConfirmUserId()!=null 
					&& !cr.getRemitConfirmUserId().equals(""))){
				checkSign = "已同意";
				map.put("checkTime", cr.getAuditingTime());
				payMoney = "已打款";
				map.put("payTime", cr.getVoucherUploadTime());
				enterPay = "待签约";
				map.put("enterPayTime", cr.getRemitConfirmTime());
			}else if((cr.getContractconfirmUseerId()!=null && !cr.getContractconfirmUseerId().equals("")) || (cr.getContractConfirmTime()!=null 
					&&     !cr.getContractConfirmTime().equals(""))){
				checkSign = "已同意";
				map.put("checkTime", cr.getAuditingTime());
				payMoney = "已打款";
				map.put("payTime", cr.getVoucherUploadTime());
				enterPay = "待签约";
				map.put("enterPayTime", cr.getRemitConfirmTime());
				sign = "已签约";
				map.put("signTime", cr.getContractConfirmTime());
			}
			revoca = "已撤单";
			map.put("revokeTime",cr.getRevokeTime());
			map.put("revoke", revoca);
		}
		if(cr.getRecordStatus()!=3 && cr.getAuditingTime()!=null && cr.getRecordStatus()!=5){
			orderOutTime = "超时提醒";
			int outTime = Integer.parseInt(eb.getOutOfTime());// 订购规则的超时时间
			String maxPalyMoneyTimeStr = SysContent.addSameHours(cr.getAuditingTime(), outTime);// 最晚打款时间
			map.put("outTime", "订单将于"+maxPalyMoneyTimeStr+"超期");
		}
		map.put("checkSign", checkSign);
		map.put("payMoney", payMoney);
		map.put("enterPay", enterPay);
		map.put("sign", sign);
		map.put("orderOutTime", orderOutTime);
		return map;
	}
}
