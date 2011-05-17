package org.sjd.gordon.client.viewer;

import java.util.ArrayList;

import org.sjd.gordon.client.AbstractCallback;
import org.sjd.gordon.model.StockDayTradeRecord;
import org.sjd.gordon.shared.viewer.GetTradeHistoryResult;

import com.google.gwt.user.client.rpc.AsyncCallback;

public abstract class LoadTradeHistoryCallback extends AbstractCallback implements AsyncCallback<GetTradeHistoryResult> {

	@Override
	public void onSuccess(GetTradeHistoryResult gotTradeHistory) {
		loaded(gotTradeHistory.getHistory());
	}

	public abstract void loaded(ArrayList<StockDayTradeRecord> tradeHistory);

}
