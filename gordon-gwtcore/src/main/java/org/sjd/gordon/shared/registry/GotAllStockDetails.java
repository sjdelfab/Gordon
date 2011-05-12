package org.sjd.gordon.shared.registry;

import java.io.Serializable;
import java.util.ArrayList;

import org.sjd.gordon.shared.viewer.StockDetail;

import com.gwtplatform.dispatch.shared.Result;

public class GotAllStockDetails implements Serializable, Result {

	private static final long serialVersionUID = 1534375086725683264L;
	
	private ArrayList<StockDetail> stocks;
	
	public GotAllStockDetails(ArrayList<StockDetail> stocks) {
		this.stocks = stocks;
	}
	
	public GotAllStockDetails() {
		this(new ArrayList<StockDetail>(0));
	}
	
	public ArrayList<StockDetail> getStocks() {
		return stocks;
	}
	
}
