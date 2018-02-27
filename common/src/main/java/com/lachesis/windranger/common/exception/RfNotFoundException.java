package com.lachesis.windranger.common.exception;

/*@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "鎵句笉鍒板搴旂殑璧勬簮")*/
public class RfNotFoundException extends RfException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public RfNotFoundException(String msg)
	{
		super(msg);
	}

	public RfNotFoundException(String format, Object... args) {

		super(format, args);
	}

	public RfNotFoundException(String entityName, String conditionName, String condition)
	{
		super(String.format("未找到指定的%s(%s:%s)", entityName, conditionName, condition));
	}
}