package com.lachesis.windranger.common.vo;

import java.io.Serializable;

public class BaseVo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String code;
	private String msg;

	public BaseVo() {
		super();
	}

	public BaseVo(String ackResult, String msg) {
		super();
		this.code = ackResult;
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String toJsonString(){
		StringBuffer buffer = new StringBuffer();
		buffer.append("{\"code\":").append(code).append(",\"msg\":\"").append(msg).append("\"}");
		return buffer.toString();
	}
}
