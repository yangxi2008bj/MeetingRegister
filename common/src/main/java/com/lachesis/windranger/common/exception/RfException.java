package com.lachesis.windranger.common.exception;

public class RfException extends AppException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RfException(String msg)
	{
		super(msg);
	}
	
	public RfException(String format, Object... args) {
		
		super(String.format(format, args));
	}
	
}
