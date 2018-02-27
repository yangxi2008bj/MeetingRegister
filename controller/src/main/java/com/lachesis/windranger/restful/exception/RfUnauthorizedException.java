package com.lachesis.windranger.restful.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "没有操作此资源的权限")
public class RfUnauthorizedException extends RfException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RfUnauthorizedException()
	{
		super();
	}

	public RfUnauthorizedException(String msg)
	{
		super(msg);
	}

	public RfUnauthorizedException(String format, Object... args) {

		super(format, args);
	}

}
