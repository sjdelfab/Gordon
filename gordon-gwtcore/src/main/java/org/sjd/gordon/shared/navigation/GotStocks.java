package org.sjd.gordon.shared.navigation;

import java.io.Serializable;
import java.util.ArrayList;

import com.gwtplatform.dispatch.shared.Result;

public class GotStocks implements Serializable, Result {

	private static final long serialVersionUID = 5892077069958705597L;
	
	private ArrayList<StockName> stocks;
	
	public GotStocks() {
		this(new ArrayList<StockName>(0));
	}
	
	public GotStocks(ArrayList<StockName> stocks) {
		this.stocks = stocks;
	}
	
	public ArrayList<StockName> getStocks() {
		return stocks;
	}

}
