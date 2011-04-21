package org.sjd.gordon.shared.security;

import java.io.Serializable;

import net.customware.gwt.dispatch.shared.Result;

public class LoginResponse implements Serializable, Result {

	private static final long serialVersionUID = -6224442386695100032L;

	private Boolean success;
	private String displayName;
	
	public LoginResponse() { 
		this(Boolean.FALSE);
	}
	
	public LoginResponse(Boolean result) {
		this.success = result;
	}
	
	public Boolean isSuccessful() {
		return success;
	}
	
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	
	public String getDisplayName() {
		return displayName;
	}
}
