package com.lachesis.windranger.restful.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NO_CONTENT, reason = "没有要删除的资源")
public class RfNoContentException extends RfException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RfNoContentException()
	{
		super();
	}

	public RfNoContentException(String msg)
	{
		super(msg);
	}

	public RfNoContentException(String format, Object... args) {

		super(format, args);
	}
}
