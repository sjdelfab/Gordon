package org.sjd.gordon.shared.security;

import java.io.Serializable;

import com.gwtplatform.dispatch.shared.Result;

public class GotCurrentUserResponse implements Serializable, Result {

	private static final long serialVersionUID = -2068480631220736046L;
	
	private UserDetail user;
	
	public GotCurrentUserResponse() { }
	
	public GotCurrentUserResponse(UserDetail user) {
		this.user = user;
	}
	
	public UserDetail getUser() {
		return user;
	}
}
