package org.sjd.gordon.server.devhandlers.data;

import java.util.ArrayList;

import org.sjd.gordon.model.Dividend;
import org.sjd.gordon.server.devhandlers.Data;
import org.sjd.gordon.shared.viewer.GetDividendHistoryAction;
import org.sjd.gordon.shared.viewer.GetDividendHistoryResult;

import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class GetDividendHistoryDevHandler implements ActionHandler<GetDividendHistoryAction,GetDividendHistoryResult> {

	@Override
	public GetDividendHistoryResult execute(GetDividendHistoryAction action, ExecutionContext context) throws ActionException {
		ArrayList<Dividend> dividendHistory = Data.dividendMap.get(action.getStockId());
		if (dividendHistory == null) {
			dividendHistory = new ArrayList<Dividend>(0);
		}
		return new GetDividendHistoryResult(dividendHistory);
	}

	@Override
	public Class<GetDividendHistoryAction> getActionType() {
		return GetDividendHistoryAction.class;
	}

	@Override
	public void undo(GetDividendHistoryAction action, GetDividendHistoryResult result, ExecutionContext context) throws ActionException { }

}
