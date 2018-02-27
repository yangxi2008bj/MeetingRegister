package com.lachesis.windranger.common.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 生成唯一数
 * @author xianzhang.rao
 *
 * @version 2017年2月16日下午1:18:26
 */
public class CodeUtils {
	
	private static DateFormat df = new SimpleDateFormat("YYMMDD");
	private static AtomicInteger atomic = new AtomicInteger(0);
	
	public static String getRoleCode(){
		return df.format(new Date())+getIndex();
	}
	
	public static String getUserCode(){
		return "U"+df.format(new Date())+getIndex();
	}
	
	/**
	 * 获取序号
	 * @return
	 */
	private static int getIndex(){
		
		if(atomic.get()==0){
			//如果为0，那么以当前的时分秒为起始
			atomic.set(getStartIndex());
		}
		if(atomic.get()>=99999){
			atomic.set(1);
		}
		return atomic.getAndIncrement();
	}
	/**
	 * 获取起始的序号
	 * @return
	 */
	private static int getStartIndex(){
		Calendar c = Calendar.getInstance();
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int min = c.get(Calendar.MINUTE);
		int sec = c.get(Calendar.SECOND);
		//时分秒
		return hour*min*sec;
	} 
	
	/**
	 * 系统后台接口调用
	 * @return
	 */
	public static String getSysInvokeId(){
		return df.format(new Date())+getIndex();
	}
}