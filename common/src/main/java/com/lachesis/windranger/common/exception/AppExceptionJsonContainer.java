package com.lachesis.windranger.common.exception;

import java.util.ArrayList;
import java.util.List;

public class AppExceptionJsonContainer {

	private String httpSatusCode;
	private String httpStatusMsg;
	private String errorMsg;
	private List<String> stackTraces;

	public AppExceptionJsonContainer(String code, String statusMsg, String errorMsg) {
		this.setHttpSatusCode(code);
		this.setHttpStatusMsg(statusMsg);
		this.setErrorMsg(errorMsg);
		this.stackTraces = new ArrayList<String>();
	}

	public String getHttpSatusCode() {
		return httpSatusCode;
	}

	public void setHttpSatusCode(String httpSatusCode) {
		this.httpSatusCode = httpSatusCode;
	}

	public String getHttpStatusMsg() {
		return httpStatusMsg;
	}

	public void setHttpStatusMsg(String httpStatusMsg) {
		this.httpStatusMsg = httpStatusMsg;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public List<String> getStackTraces() {
		return stackTraces;
	}

	public void setStackTraces(List<String> stackTraces) {
		this.stackTraces = stackTraces;
	}
}
