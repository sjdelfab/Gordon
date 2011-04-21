package org.sjd.gordon.client.viewer;

import java.util.ArrayList;

import net.customware.gwt.presenter.client.widget.WidgetDisplay;

import org.sjd.gordon.model.StockDayTradeRecord;

public interface TradeHistoryDisplay extends WidgetDisplay {

	public void setTradeHistory(ArrayList<StockDayTradeRecord> tradeHistory);

}
