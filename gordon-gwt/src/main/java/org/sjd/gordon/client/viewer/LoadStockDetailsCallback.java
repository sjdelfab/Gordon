package org.sjd.gordon.client.viewer;

import org.sjd.gordon.client.AbstractCallback;
import org.sjd.gordon.shared.viewer.GetStockProfileResult;
import org.sjd.gordon.shared.viewer.StockProfile;

import com.google.gwt.user.client.rpc.AsyncCallback;

public abstract class LoadStockDetailsCallback extends AbstractCallback implements AsyncCallback<GetStockProfileResult> {

	@Override
	public void onSuccess(GetStockProfileResult result) {
		loaded(result.getStockProfile());
	}

	public abstract void loaded(StockProfile profile);

}
