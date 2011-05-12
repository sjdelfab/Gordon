package org.sjd.gordon.shared.viewer;

import java.io.Serializable;

import com.gwtplatform.dispatch.shared.Result;

public class GotStockDetails implements Serializable, Result {

	private static final long serialVersionUID = 8265913539728211233L;

	private StockDetail stockDetails;
	
	public GotStockDetails() { }
	
	public GotStockDetails(StockDetail stockDetails) {
		this.stockDetails = stockDetails;
	}
	
	public StockDetail getStockDetails() {
		return stockDetails;
	}
}
