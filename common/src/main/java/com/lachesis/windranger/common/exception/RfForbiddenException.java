package com.lachesis.windranger.common.exception;

/*@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "此资源禁止完成此操作")*/
public class RfForbiddenException extends RfException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public RfForbiddenException(String msg)
	{
		super(msg);
	}
	
	public RfForbiddenException(String format, Object... args) {
		
		super(format, args);
	}
}
