package org.sjd.gordon.shared.viewer;

import java.io.Serializable;

import com.gwtplatform.dispatch.shared.Result;

public class GotStockDetails implements Serializable, Result {

	private static final long serialVersionUID = 8265913539728211233L;

	private StockDetails stockDetails;
	
	public GotStockDetails() { }
	
	public GotStockDetails(StockDetails stockDetails) {
		this.stockDetails = stockDetails;
	}
	
	public StockDetails getStockDetails() {
		return stockDetails;
	}
}
