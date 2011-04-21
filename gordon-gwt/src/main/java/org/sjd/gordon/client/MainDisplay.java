package org.sjd.gordon.client;

import net.customware.gwt.presenter.client.widget.WidgetDisplay;

import org.sjd.gordon.client.viewer.StockDisplay;
import org.sjd.gordon.model.StockEntity;

public interface MainDisplay extends WidgetDisplay {

	public void addStock(StockDisplay stockDisplay, StockEntity stock);
}
