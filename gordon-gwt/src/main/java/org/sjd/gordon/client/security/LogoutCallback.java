package org.sjd.gordon.client.security;

import org.sjd.gordon.client.AbstractCallback;
import org.sjd.gordon.shared.security.LogoutResponse;

import com.google.gwt.user.client.rpc.AsyncCallback;

public abstract class LogoutCallback extends AbstractCallback implements AsyncCallback<LogoutResponse> {

	@Override
	public void onSuccess(LogoutResponse result) { 
	    logout();
	}
	
	public abstract void logout();
}
