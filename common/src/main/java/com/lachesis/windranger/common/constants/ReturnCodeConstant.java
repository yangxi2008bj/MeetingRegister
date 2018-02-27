/**   
 * @Title: ErrorCodeConstant.java 
 * @Package com.lachesis.windranger.common.constants 
 * @Description: 返回前端的错误码，正确码，消息。
 * @author Xi Yang xi.yang@lachesis-mh.com   
 * @date 2017年5月3日 上午9:43:25 
 * @version V1.0   
 */
package com.lachesis.windranger.common.constants;

/** 
 * @ClassName: ReturnCodeConstant 
 * @Description: 返回前端的错误码，正确码，消息。常量
 * @author Xi Yang xi.yang@lachesis-mh.com 
 * @date 2017年5月3日 上午9:43:25 
 *  
 */
public class ReturnCodeConstant {
	//返回必填项
	public static final String CODE = "code";
	public static final String MESSAGE = "message";
	//异常code
	public static final String DUPLICATE_BED = "E301";
	//异常Message
	public static final String DUPLICATE_BED_MES = "该床位已绑定设备，请选择其他床位";
	//正确code
	public static final String SUCC_CODE = "S200";
	//正确Message
	public static final String SUCC_MES = "操作成功";
}
