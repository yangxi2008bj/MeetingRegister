package com.lachesis.windranger.common.util;

import com.lachesis.windranger.common.exception.RfForbiddenException;

public final class UpdateOperationValidation {

	public static <K> void Validate(K voKey, K updateKey)
	{
		if(updateKey==null){
			throw new RfForbiddenException("传入的模型键值非法");
		}
		if(voKey.equals(updateKey))
			return;
		
		throw new RfForbiddenException("更新的模型键与传入的的键不匹配(key:%s modelKey:%s)", updateKey.toString(), voKey.toString());
	}
	
}
