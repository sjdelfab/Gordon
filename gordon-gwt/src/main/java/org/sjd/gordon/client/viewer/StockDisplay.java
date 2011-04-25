package org.sjd.gordon.client.viewer;

import net.customware.gwt.presenter.client.widget.WidgetDisplay;

import org.sjd.gordon.shared.viewer.StockDetails;

public interface StockDisplay extends WidgetDisplay {
	
	public void setStock(StockDetails stockDetails);

}
