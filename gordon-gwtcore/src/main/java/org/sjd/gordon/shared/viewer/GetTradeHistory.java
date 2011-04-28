package org.sjd.gordon.shared.viewer;

import java.io.Serializable;

import com.gwtplatform.dispatch.shared.UnsecuredActionImpl;

public class GetTradeHistory extends UnsecuredActionImpl<GotTradeHistory> implements Serializable {
	
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
