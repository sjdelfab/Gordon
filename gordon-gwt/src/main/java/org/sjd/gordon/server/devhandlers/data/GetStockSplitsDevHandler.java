package org.sjd.gordon.server.devhandlers.data;

import java.util.ArrayList;

import org.sjd.gordon.model.StockSplit;
import org.sjd.gordon.server.devhandlers.Data;
import org.sjd.gordon.shared.viewer.GetStockSplitsAction;
import org.sjd.gordon.shared.viewer.GetStockSplitsResult;

import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class GetStockSplitsDevHandler implements ActionHandler<GetStockSplitsAction,GetStockSplitsResult> {

	@Override
	public GetStockSplitsResult execute(GetStockSplitsAction action, ExecutionContext context) throws ActionException {
		ArrayList<StockSplit> splits = Data.splitsMap.get(action.getStockId());
		if (splits == null) {
			splits = new ArrayList<StockSplit>(0);
		}
		return new GetStockSplitsResult(splits);
	}

	@Override
	public Class<GetStockSplitsAction> getActionType() {
		return GetStockSplitsAction.class;
	}

	@Override
	public void undo(GetStockSplitsAction action, GetStockSplitsResult result, ExecutionContext context) throws ActionException { }

}
