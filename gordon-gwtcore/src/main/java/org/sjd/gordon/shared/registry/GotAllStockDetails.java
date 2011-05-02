package org.sjd.gordon.shared.registry;

import java.io.Serializable;
import java.util.ArrayList;

import org.sjd.gordon.shared.viewer.StockDetails;

import com.gwtplatform.dispatch.shared.Result;

public class GotAllStockDetails implements Serializable, Result {

	private static final long serialVersionUID = 1534375086725683264L;
	
	private ArrayList<StockDetails> stocks;
	
	public GotAllStockDetails(ArrayList<StockDetails> stocks) {
		this.stocks = stocks;
	}
	
	public GotAllStockDetails() {
		this(new ArrayList<StockDetails>(0));
	}
	
	public ArrayList<StockDetails> getStocks() {
		return stocks;
	}
	
}
