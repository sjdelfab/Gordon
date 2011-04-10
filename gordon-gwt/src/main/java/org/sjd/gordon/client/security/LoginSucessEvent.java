package org.sjd.gordon.client.security;

import com.google.gwt.event.shared.GwtEvent;

public class LoginSucessEvent extends GwtEvent<LoginEventHandler> {

	public static Type<LoginEventHandler> TYPE = new Type<LoginEventHandler>();
	
	@Override
	protected void dispatch(LoginEventHandler handler) {
		handler.successfullyLoggedIn(this);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<LoginEventHandler> getAssociatedType() {
		return TYPE;
	}

}
