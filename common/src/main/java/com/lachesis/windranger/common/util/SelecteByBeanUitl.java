/**
* Project Name: windranger-common
* File Name: SelecteByBeanUitl.java
* Package Name: com.lachesis.windranger.common.util
* Date: 2017年1月19日上午12:45:01
* Copyright (c) 2017, Shenzhen Lachesis Mhealth Co., Ltd All Rights Reserved.
*
*/
package com.lachesis.windranger.common.util;

import java.util.List;

import com.lachesis.windranger.common.exception.RfNotFoundException;

/**
* ClassName: SelecteByBeanUitl <br/>
* Function: TODO ADD FUNCTION. <br/>
* Reason: TODO ADD REASON. <br/>
* Date: 2017年1月19日 上午12:45:01 <br/>
* @author apoca
* @version
* @since JDK 1.7.0.45
* @see
*/
public final class SelecteByBeanUitl {

	public static <T> T getItemFromSelecteByCode(List<T> queryResult) {
		
		if(queryResult.size() == 0) {
			
			return null;
		}
		else {
			
			return queryResult.get(0);
		}
				
	}
	
}
