package org.sjd.gordon.shared.viewer;

import java.io.Serializable;

import net.customware.gwt.dispatch.shared.Action;

public class GetTradeHistory implements Serializable, Action<GotTradeHistory> {
	
	private static final long serialVersionUID = 9131966215398339304L;
	
	private Long stockId;
	
	public GetTradeHistory() { }
	
	public GetTradeHistory(Long stockId) {
		this.stockId = stockId;
	}

	public Long getStockId() {
		return stockId;
	}
}
