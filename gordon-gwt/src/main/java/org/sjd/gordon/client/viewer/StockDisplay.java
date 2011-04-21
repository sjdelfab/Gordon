package org.sjd.gordon.client.viewer;

import org.sjd.gordon.model.StockEntity;

import net.customware.gwt.presenter.client.widget.WidgetDisplay;

public interface StockDisplay extends WidgetDisplay {
	
	public void setStock(StockEntity stock);

}
