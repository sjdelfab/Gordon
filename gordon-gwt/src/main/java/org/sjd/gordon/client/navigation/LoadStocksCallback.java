package org.sjd.gordon.client.navigation;

import java.util.ArrayList;

import org.sjd.gordon.client.AbstractCallback;
import org.sjd.gordon.shared.navigation.GetStocksResult;
import org.sjd.gordon.shared.navigation.StockName;

import com.google.gwt.user.client.rpc.AsyncCallback;

public abstract class LoadStocksCallback extends AbstractCallback implements AsyncCallback<GetStocksResult> {

	@Override
	public void onSuccess(GetStocksResult stocks) {
		loaded(stocks.getStocks());
	}

	public abstract void loaded(ArrayList<StockName> stocks);
}
