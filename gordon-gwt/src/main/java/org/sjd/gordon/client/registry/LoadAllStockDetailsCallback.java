package org.sjd.gordon.client.registry;

import java.util.ArrayList;

import org.sjd.gordon.client.AbstractCallback;
import org.sjd.gordon.shared.registry.GotAllStockDetails;
import org.sjd.gordon.shared.viewer.StockDetails;

import com.google.gwt.user.client.rpc.AsyncCallback;

public abstract class LoadAllStockDetailsCallback extends AbstractCallback implements AsyncCallback<GotAllStockDetails> {

	@Override
	public void onSuccess(GotAllStockDetails allStockDetails) {
		loaded(allStockDetails.getStocks());
	}

	public abstract void loaded(ArrayList<StockDetails> stocks);
}
