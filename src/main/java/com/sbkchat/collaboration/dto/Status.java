package com.sbkchat.collaboration.dto;

import javax.persistence.Transient;

import org.springframework.stereotype.Component;

@Component
public class Status {

	@Transient
	private String errCode;
	
	@Transient
	private String errMessage;

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public String getErrMessage() {
		return errMessage;
	}

	public void setErrMessage(String errMessage) {
		this.errMessage = errMessage;
	}

		
}
