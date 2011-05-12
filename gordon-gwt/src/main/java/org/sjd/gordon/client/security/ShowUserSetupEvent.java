package org.sjd.gordon.client.security;

import com.google.gwt.event.shared.GwtEvent;

public class ShowUserSetupEvent extends GwtEvent<ShowUserSetupEventHandler> {

	public static Type<ShowUserSetupEventHandler> TYPE = new Type<ShowUserSetupEventHandler>();
	
	@Override
	protected void dispatch(ShowUserSetupEventHandler handler) {
		handler.show(this);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<ShowUserSetupEventHandler> getAssociatedType() {
		return TYPE;
	}
}
