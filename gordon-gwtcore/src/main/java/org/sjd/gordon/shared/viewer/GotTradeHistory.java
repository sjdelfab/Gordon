package org.sjd.gordon.shared.viewer;

import java.io.Serializable;
import java.util.ArrayList;

import org.sjd.gordon.model.StockDayTradeRecord;

import net.customware.gwt.dispatch.shared.Result;

public class GotTradeHistory implements Serializable, Result {
	
	private static final long serialVersionUID = 8286863426826961640L;
	
	private ArrayList<StockDayTradeRecord> history;
	
	public GotTradeHistory() { }

	public GotTradeHistory(ArrayList<StockDayTradeRecord> history) {
		this.history = history;
	}
	
	public ArrayList<StockDayTradeRecord> getHistory() {
		return history;
	}
}
