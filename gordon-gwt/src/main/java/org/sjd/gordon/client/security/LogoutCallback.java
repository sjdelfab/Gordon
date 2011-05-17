package org.sjd.gordon.client.security;

import org.sjd.gordon.client.AbstractCallback;
import org.sjd.gordon.shared.security.LogoutResult;

import com.google.gwt.user.client.rpc.AsyncCallback;

public abstract class LogoutCallback extends AbstractCallback implements AsyncCallback<LogoutResult> {

	@Override
	public void onSuccess(LogoutResult result) { 
	    logout();
	}
	
	public abstract void logout();
}
