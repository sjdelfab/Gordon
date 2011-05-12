package org.sjd.gordon.shared.security;

import java.io.Serializable;
import java.util.ArrayList;

import com.gwtplatform.dispatch.shared.Result;

public class GotAllUserDetails implements Serializable, Result {

	private static final long serialVersionUID = 4278750064103974473L;
	
	private ArrayList<UserDetail> users;
	
	public GotAllUserDetails(ArrayList<UserDetail> users) {
		this.users = users;
	}
	
	public GotAllUserDetails() {
		this(new ArrayList<UserDetail>(0));
	}
	
	public ArrayList<UserDetail> getUsers() {
		return users;
	}
}
