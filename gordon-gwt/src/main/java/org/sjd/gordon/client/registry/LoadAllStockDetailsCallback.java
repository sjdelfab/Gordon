package org.sjd.gordon.client.registry;

import java.util.ArrayList;

import org.sjd.gordon.client.AbstractCallback;
import org.sjd.gordon.shared.registry.GetAllRegistryEntriesResult;
import org.sjd.gordon.shared.viewer.StockDetail;

import com.google.gwt.user.client.rpc.AsyncCallback;

public abstract class LoadAllStockDetailsCallback extends AbstractCallback implements AsyncCallback<GetAllRegistryEntriesResult> {

	@Override
	public void onSuccess(GetAllRegistryEntriesResult allStockDetails) {
		loaded(allStockDetails.getStocks());
	}

	public abstract void loaded(ArrayList<StockDetail> stocks);
}
