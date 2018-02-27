package com.lachesis.windranger.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


/**
 * 读取配置文件
 * @author xianzhang.rao
 *
 * @version 2017年4月24日上午10:14:40
 */
public class PropertiesUtil {
	
	static Properties prop;
	
	static{
		prop = new Properties();
	}
	
	/**
	 * 根据key获取值
	 * @param fileName
	 * @param key
	 * @return
	 */
	public static String findPropertiesKey(String fileName,String key) {
		try {
			return getProperties(fileName).getProperty(key);
		} catch (Exception e) {
			return "";
		}
	}
	
	/**
	 * 读取属性文件
	 * @param fileName
	 * @return
	 */
	public static Properties getProperties(String fileName){
		InputStream in = null;
		try {
			 in= PropertiesUtil.class.getResourceAsStream(fileName);
			prop.load(in);
		} catch (Exception e) {
			return null;
		}finally{
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return prop;
	}
}
