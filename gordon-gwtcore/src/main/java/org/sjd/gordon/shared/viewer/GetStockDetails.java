package org.sjd.gordon.shared.viewer;

import java.io.Serializable;

import net.customware.gwt.dispatch.shared.Action;

public class GetStockDetails implements Serializable, Action<GotStockDetails> {
	

	private static final long serialVersionUID = 3079572278718211356L;
	
	private Long stockId;
	
	public GetStockDetails() { }
	
	public GetStockDetails(Long stockId) {
		this.stockId = stockId;
	}

	public Long getStockId() {
		return stockId;
	}

}
