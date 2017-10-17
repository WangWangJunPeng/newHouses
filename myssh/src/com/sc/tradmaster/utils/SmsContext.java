package com.sc.tradmaster.utils;

public class SmsContext {
	//【九邑房源在线】正在进行修改密码操作，您的验证码是#code#，如非本人操作，请忽略此短信。
	public static String ChangePsd = "【九邑房源在线】正在进行修改密码操作，您的验证码是#code#，如非本人操作，请忽略此短信。";
	//订购申请 提醒案场助理及时审核
	public static String BUY_APPLY = "您收到一笔来自#userName#的购买申请，购买的房源名称为#houseName#，价格为#money#元，请及时审核！";
	//订购申请 同意订购 提醒申请人通知客户
	public static String AGREE_BUY_APPLY = "您申请#projectName#项目，房源名称为#8#，客户姓名：#customerName#，已经被#state#，请及时处理！";
	//订购申请 拒绝订购 提醒申请人通知客户
	public static String REFUSE_BUY_APPLY = "您申请#projectName#项目，房源名称为#8#，客户姓名：#customerName#，已经被#state#，原因为：#reason#";
	//打款确认 提醒案场助理及时确认(ok)
	public static String PLAY_MONEY_ENTER = "您好！来自#userName#的购买申请，房源名称为#houseName#，已经打款，请及时确认！";
	//打款即将到期提醒  提醒申请人通知客户及时大款
	//public static String PLAY_MONEY_WILL_OUT_TIME = "您的申请#projectName#项目，房源名称为#houseName#，客户姓名：#customerName#，已经被#state#，打款即将超时，请及时处理！";
	public static String PLAY_MONEY_WILL_OUT_TIME ="您的申请#projectName#项目，房源名称为#houseName#，客户姓名：#customerName#，已经被#state#，打款即将超时，请及时处理！如已打款，请忽略此短信。";
	//催单
	public static String CALL_ORDER = "您的项目关于#housName#房源的订单，订单号为：#No#，#userRole##userName#请求您审核，请尽快处理！";
	
	//文件上传，路径来源设置
	public static String AD_PIC = "adPic";
	public static String CR_PIC = "recordContractPic";
	public static String HT_PIC = "houseTypePic";
	public static String EF_PIC = "effectPic";
	public static String SHOP_PIC = "shopsPic";
	public static String USERS_PIC = "usersPic";
	public static String WSC_PIC = "willSaleCardPic";
	public static String FB_PIC = "feedBackPic";
	public static String VERSION = "product";
	
	//经理段 订单状态(备案成功,备案失败,确认到访,认购申请,待下定,提交打款,确认到款,签约成功,打款超时)
	public static String ENTERBUY_APPLY= "认购申请";
	public static String ENTERBUY_AGREE= "待下定";
	public static String ENTERBUY_REFUSE= "申请被拒";
	public static String SUBMIT_MONEY= "提交打款";
	public static String ENTER_MONEY= "确认到款";
	public static String SIGN_SUCCESS= "签约成功";
	public static String PAY_MONEY_OUTTIME= "打款超时";
	
	//版本标记字段
	public static String VERTION_DATE = "2017-09-29 22:06:00";
	public static String VERTION_NO = "v1.17.0929.1";
	
	//数据归集
	//是否调用归集方法
	public static final boolean toGuiJiFun = true;
	//url产品
	//public static final String httpClientURL = "http://127.0.0.1:8080/datamerger";
	public static final String httpClientURL = "http://127.0.0.1:8091/datamerger";
	//协议唯一标识符
	public static final String protocolUniqueSymbol = "20170913104500302";
	
	//系统导入agent数据指定时间
	public static String SYSTEM_IMPORT_AGENT_TIME = "1970-01-01 00:00:00";
	
}
