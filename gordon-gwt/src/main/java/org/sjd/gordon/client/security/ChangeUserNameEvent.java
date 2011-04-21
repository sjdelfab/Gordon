package org.sjd.gordon.client.security;

import com.google.gwt.event.shared.GwtEvent;

public class ChangeUserNameEvent extends GwtEvent<ChangeUserNameEventHandler> {

	public static Type<ChangeUserNameEventHandler> TYPE = new Type<ChangeUserNameEventHandler>();
	
	private String newName;
	
	public ChangeUserNameEvent(String newName) {
		this.newName = newName;
	}
	
	public String getNewName() {
		return newName;
	}
	
	@Override
	protected void dispatch(ChangeUserNameEventHandler handler) {
		handler.changedName(this);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<ChangeUserNameEventHandler> getAssociatedType() {
		return TYPE;
	}
	
}
