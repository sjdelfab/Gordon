package org.sjd.gordon.shared.navigation;

import java.io.Serializable;
import java.util.ArrayList;

import net.customware.gwt.dispatch.shared.Result;

import org.sjd.gordon.model.StockEntity;

public class GotStocks implements Serializable, Result {

	private static final long serialVersionUID = 5892077069958705597L;
	
	private ArrayList<StockEntity> stocks;
	
	public GotStocks() {
		this(new ArrayList<StockEntity>(0));
	}
	
	public GotStocks(ArrayList<StockEntity> stocks) {
		this.stocks = stocks;
	}
	
	public ArrayList<StockEntity> getStocks() {
		return stocks;
	}

}
