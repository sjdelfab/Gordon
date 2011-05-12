package org.sjd.gordon.shared.security;

import java.io.Serializable;

import com.gwtplatform.dispatch.shared.Result;

public class EditUserResponse implements Serializable, Result {
		
	private static final long serialVersionUID = -6290249568146049655L;
	
	private UserDetail user;
	
	public EditUserResponse() { }
	
	public EditUserResponse(UserDetail user) {
		this.user = user;
	}
	
	public UserDetail getUser() {
		return user;
	}

}
