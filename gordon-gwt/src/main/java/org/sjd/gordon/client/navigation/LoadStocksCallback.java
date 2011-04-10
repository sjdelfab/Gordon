package org.sjd.gordon.client.navigation;

import java.util.ArrayList;

import org.sjd.gordon.client.AbstractCallback;
import org.sjd.gordon.model.StockEntity;
import org.sjd.gordon.shared.navigation.GotStocks;

import com.google.gwt.user.client.rpc.AsyncCallback;

public abstract class LoadStocksCallback extends AbstractCallback implements AsyncCallback<GotStocks> {

	@Override
	public void onSuccess(GotStocks stocks) {
		loaded(stocks.getStocks());
	}

	public abstract void loaded(ArrayList<StockEntity> stocks);
}
