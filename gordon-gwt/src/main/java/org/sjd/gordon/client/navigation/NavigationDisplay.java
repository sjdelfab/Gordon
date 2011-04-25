package org.sjd.gordon.client.navigation;

import java.util.ArrayList;

import net.customware.gwt.presenter.client.widget.WidgetDisplay;

import org.sjd.gordon.model.Exchange;
import org.sjd.gordon.shared.navigation.StockName;

import com.extjs.gxt.ui.client.data.BeanModel;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HasValue;

public interface NavigationDisplay extends WidgetDisplay {
	public HasValue<BeanModel> getStock();
	public HasValue<BeanModel> getExchange();
	public HasClickHandlers getExchangeHandler();
	public HasClickHandlers getViewHandler();
	public void setStocks(ArrayList<StockName> stocks);
	public void setExchanges(ArrayList<Exchange> exchanges);

}
