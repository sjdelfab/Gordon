package org.sjd.gordon.client.security;

import org.sjd.gordon.client.AbstractCallback;
import org.sjd.gordon.shared.security.LoginResponse;

import com.google.gwt.user.client.rpc.AsyncCallback;

public abstract class AuthenticatedCallback extends AbstractCallback implements AsyncCallback<LoginResponse> {

	public void onSuccess(LoginResponse result) { 
	    loggedIn(result.isSuccessful()); 
	}
	
	public abstract void loggedIn(Boolean success);
	
}
