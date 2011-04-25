package org.sjd.gordon.client.viewer;

import org.sjd.gordon.client.AbstractCallback;
import org.sjd.gordon.shared.viewer.GotStockDetails;
import org.sjd.gordon.shared.viewer.StockDetails;

import com.google.gwt.user.client.rpc.AsyncCallback;

public abstract class LoadStockDetailsCallback extends AbstractCallback implements AsyncCallback<GotStockDetails> {

	@Override
	public void onSuccess(GotStockDetails gotTradeHistory) {
		loaded(gotTradeHistory.getStockDetails());
	}

	public abstract void loaded(StockDetails stockDetails);

}
