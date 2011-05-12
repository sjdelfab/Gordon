package org.sjd.gordon.shared.security;

import java.io.Serializable;

import com.gwtplatform.dispatch.shared.UnsecuredActionImpl;

public class EditUser extends UnsecuredActionImpl<EditUserResponse> implements Serializable {

	private static final long serialVersionUID = -6265427278304024373L;

	public static enum EditType {ADD, UPDATE};
	
	private UserDetail userDetails;
	private EditType editType;
	
	public EditUser() { }
	
	public EditUser(UserDetail details, EditType editType) { 
		this.userDetails = details;
		this.editType = editType;
	}
	
	public UserDetail getUserDetail() {
		return userDetails;
	}
	
	public EditType getEditType() {
		return editType;
	}

}
