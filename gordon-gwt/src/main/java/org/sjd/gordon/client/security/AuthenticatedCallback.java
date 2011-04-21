package org.sjd.gordon.client.security;

import org.sjd.gordon.client.AbstractCallback;
import org.sjd.gordon.shared.security.LoginResponse;

import com.google.gwt.user.client.rpc.AsyncCallback;

public abstract class AuthenticatedCallback extends AbstractCallback implements AsyncCallback<LoginResponse> {

	public void onSuccess(LoginResponse result) { 
	    loggedIn(result.isSuccessful());
	    if (result.isSuccessful()) {
	    	setDisplayName(result.getDisplayName());
	    }
	}
	
	public abstract void loggedIn(Boolean success);
	public abstract void setDisplayName(String displayName);
	
}
