package org.sjd.gordon.shared.security;

import java.io.Serializable;

import com.gwtplatform.dispatch.shared.UnsecuredActionImpl;

public class DeleteUser extends UnsecuredActionImpl<DeleteUserResponse> implements Serializable {
	
	private static final long serialVersionUID = -7423564336083238341L;
	
	private Integer userId;
	
	public DeleteUser() { }
	
	public DeleteUser(Integer userId) {
		this.userId = userId;
	}
	
	public Integer getUserId() {
		return userId;
	}

}
