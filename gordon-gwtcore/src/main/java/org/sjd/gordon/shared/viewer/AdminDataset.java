package org.sjd.gordon.shared.viewer;

import java.io.Serializable;
import java.util.List;

import org.sjd.gordon.model.StockSplit;
import org.sjd.gordon.model.TreasuryHeldStock;

public class AdminDataset implements Serializable {
	
	private static final long serialVersionUID = -1702681334767169389L;
	
	private List<StockSplit> stockSplits;
	private List<TreasuryHeldStock> treasuryHeldStock;
	
	public List<StockSplit> getStockSplits() {
		return stockSplits;
	}
	
	public void setStockSplits(List<StockSplit> stockSplits) {
		this.stockSplits = stockSplits;
	}
	
	public List<TreasuryHeldStock> getTreasuryHeldStock() {
		return treasuryHeldStock;
	}
	
	public void setTreasuryHeldStock(List<TreasuryHeldStock> treasuryHeldStock) {
		this.treasuryHeldStock = treasuryHeldStock;
	}

}
