package org.sjd.gordon.client.registry;

import org.sjd.gordon.model.Exchange;

import com.google.gwt.event.shared.GwtEvent;

public class ShowRegistryEvent extends GwtEvent<ShowRegistryEventHandler> {

	public static Type<ShowRegistryEventHandler> TYPE = new Type<ShowRegistryEventHandler>();
		
	private Exchange exchange;
	
	public ShowRegistryEvent(Exchange exchange) {
		this.exchange = exchange;
	}
	
	public Exchange getExchange() {
		return exchange;
	}
	
	@Override
	protected void dispatch(ShowRegistryEventHandler handler) {
		handler.show(this);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<ShowRegistryEventHandler> getAssociatedType() {
		return TYPE;
	}

}
