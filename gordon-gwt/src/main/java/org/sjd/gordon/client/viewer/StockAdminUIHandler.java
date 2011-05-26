package org.sjd.gordon.client.viewer;

import org.sjd.gordon.model.StockSplit;
import org.sjd.gordon.model.TreasuryHeldStock;

import com.gwtplatform.mvp.client.UiHandlers;

public interface StockAdminUIHandler extends UiHandlers {

	public void add(StockSplit newSplit);
	public void update(StockSplit split);
	public void delete(StockSplit splitSelected);
	public void load();
	
	public void add(TreasuryHeldStock heldStock);
	public void update(TreasuryHeldStock heldStock);
	public void delete(TreasuryHeldStock heldStock);

}
