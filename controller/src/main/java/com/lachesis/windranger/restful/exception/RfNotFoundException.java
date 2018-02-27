package com.lachesis.windranger.restful.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "找不到对应的资源")
public class RfNotFoundException extends RfException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public RfNotFoundException()
	{
		super();
	}

	public RfNotFoundException(String msg)
	{
		super(msg);
	}

	public RfNotFoundException(String format, Object... args) {

		super(format, args);
	}
}