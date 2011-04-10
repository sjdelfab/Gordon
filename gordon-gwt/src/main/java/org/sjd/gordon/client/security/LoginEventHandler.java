package org.sjd.gordon.client.security;

import com.google.gwt.event.shared.EventHandler;

public interface LoginEventHandler extends EventHandler {

	public void successfullyLoggedIn(LoginSucessEvent event);
	
}
