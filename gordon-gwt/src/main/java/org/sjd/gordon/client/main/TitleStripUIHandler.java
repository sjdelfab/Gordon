package org.sjd.gordon.client.main;

import java.util.ArrayList;

import org.sjd.gordon.model.Exchange;
import org.sjd.gordon.shared.navigation.StockName;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.gwtplatform.mvp.client.UiHandlers;

public interface TitleStripUIHandler extends UiHandlers {

	public void getStocks(Exchange exchange, AsyncCallback<ArrayList<StockName>> callback);
}
