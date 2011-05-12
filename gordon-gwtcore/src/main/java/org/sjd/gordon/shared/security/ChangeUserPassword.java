package org.sjd.gordon.shared.security;

import java.io.Serializable;

import com.gwtplatform.dispatch.shared.UnsecuredActionImpl;

public class ChangeUserPassword extends UnsecuredActionImpl<ChangePasswordResponse> implements Serializable {
	
	private static final long serialVersionUID = 5124476449201401744L;
	
	private Integer userId;
	private char[] newPassword;
	
	public ChangeUserPassword() { }
	
	public ChangeUserPassword(Integer userId, char[] newPassword) {
		this.userId = userId;
		this.newPassword = newPassword;
	}
	
	public Integer getUserId() {
		return userId;
	}

	public char[] getNewPassword() {
		return newPassword;
	}
}
