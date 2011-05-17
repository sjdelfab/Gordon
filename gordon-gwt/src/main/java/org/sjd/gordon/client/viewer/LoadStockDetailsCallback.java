package org.sjd.gordon.client.viewer;

import org.sjd.gordon.client.AbstractCallback;
import org.sjd.gordon.shared.viewer.GetStockDetailsResult;
import org.sjd.gordon.shared.viewer.StockDetail;

import com.google.gwt.user.client.rpc.AsyncCallback;

public abstract class LoadStockDetailsCallback extends AbstractCallback implements AsyncCallback<GetStockDetailsResult> {

	@Override
	public void onSuccess(GetStockDetailsResult gotTradeHistory) {
		loaded(gotTradeHistory.getStockDetails());
	}

	public abstract void loaded(StockDetail stockDetails);

}
