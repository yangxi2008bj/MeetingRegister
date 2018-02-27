/**   
 * @Title: CommonConstants.java 
 * @Package com.lachesis.windranger.common.constans 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author Xi   
 * @date 2017年4月6日 上午9:49:23 
 * @version V1.0   
 */
package com.lachesis.windranger.common.constants;

/**
 * @ClassName: CommonConstants
 * @Description: 公用的常量或描述性文字
 * @author Xi xi.yang@lachesis-mh.com
 * @date 2017年4月6日 上午9:49:23
 * 
 */
public class CommonConstant {
	public static final String TOKEN_NAME = "token=";
	public static final String AES_SEED = "LM";
	public static final String TOKEN_USER_NAME = "uid=";
	public static final String TOKEN_ISMOBILE = "isMo=";
	public static final String TOKEN_IP = "ip=";
	public static final String TOKEN_EXPIRE_TIME = "exp=";
	public static final long LIVE_TIME = 3600000;

	public static final String CONTROLLER_ERROR = "Error";
	public static final String CONTROLLER_SUCCESS = "Success";

	public static final String ERROR_NULL_OBJECT = "Null Object";
	public static final String SUCCESS_OPERATION = "Operation Successfully";
	public static final String SYSTEM_PERSON = "System";

	public static final Integer STATUS_YX = 1;// 有效
	public static final Integer STATUS_WX = 9;// 无效

	public static final String PROP_PATH = "/config.properties";
	public static final String TIER_IMAGE_PATH = "tier.image.path";
	public static final String ACTIVITI_FILE_PATH = "activiti.file.path";
	public static final String OPEN_EQUIPMENT = "openEquipment";// 是否开启数据智能硬件

}
