/**
 * Project Name: windranger-common
 * File Name: PinyinUtil.java
 * Package Name: com.lachesis.windranger.common.util
 * Date: 2017年4月13日下午8:16:06
 * Copyright (c) 2017, Shenzhen Lachesis Mhealth Co., Ltd All Rights Reserved.
 *
 */
package com.lachesis.windranger.common.util;

import net.sourceforge.pinyin4j.PinyinHelper;

/**
 * ClassName: PinyinUtil <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2017年4月13日 下午8:16:06 <br/>
 * @author 胡诗蔚
 * @version
 * @since JDK 1.7.0.45
 * @see
 */
public class PinyinUtil {

	
	/**
	* getPinYinHeadChar: 获取字符串拼音首字母,例：我爱习大大 = WAXDD. <br/>
	*
	* @author 胡诗蔚
	* @param str
	* @return 返回字符串拼音首字符串
	* @since JDK 1.7.0.45
	*/
	public static String getPinYinHeadChar(String str){
		
		String convert="";
		for(int j = 0; j < str.length(); j++){
			
			char word=str.charAt(j);
			//提取汉字的首字母
			String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
			if(pinyinArray != null){
				convert += pinyinArray[0].charAt(0);
			}else{
				convert += word;
			}
		}
		return convert;
	}

}
