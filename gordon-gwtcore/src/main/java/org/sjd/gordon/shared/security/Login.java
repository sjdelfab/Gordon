package org.sjd.gordon.shared.security;

import java.io.Serializable;

import net.customware.gwt.dispatch.shared.Action;

public class Login implements Serializable, Action<LoginResponse> {
	
	private static final long serialVersionUID = -7891652588102783960L;

	private String userName;
	private String password;
	
	public Login() { }
	
	public Login(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public String getPassword() {
		return password;
	}

}
