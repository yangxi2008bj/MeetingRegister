package com.lachesis.windranger.common.exception;

public abstract class AppException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AppException(String msg)
	{
		super(msg);
	}
	
	public AppException(String format, Object... args) {
		super(String.format(format, args));
	}
	
	
	public static String getExceptionJsonString(Exception ex, String statusName, int statusCode) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("{\"code\":").append(statusCode).append(",\"message\":\"").append(ex.getMessage()).append("\"}");
		return buffer.toString();
	} 
	
}
