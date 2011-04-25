package org.sjd.gordon.client.navigation;

import org.sjd.gordon.shared.viewer.StockDetails;

import com.google.gwt.event.shared.GwtEvent;

public class ShowStockEvent extends GwtEvent<ShowStockEventHandler> {

	public static Type<ShowStockEventHandler> TYPE = new Type<ShowStockEventHandler>();
	
	private StockDetails stockDetails;
	
	public ShowStockEvent(StockDetails stockDetails) {
		this.stockDetails = stockDetails;
	}
	
	public StockDetails getStockDetails() {
		return stockDetails;
	}
	
	@Override
	protected void dispatch(ShowStockEventHandler handler) {
		handler.show(this);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<ShowStockEventHandler> getAssociatedType() {
		return TYPE;
	}

}
