package org.sjd.gordon.server.devhandlers.data;

import java.util.ArrayList;

import org.sjd.gordon.model.Dividend;
import org.sjd.gordon.model.StockSplit;
import org.sjd.gordon.model.TreasuryHeldStock;
import org.sjd.gordon.server.devhandlers.Data;
import org.sjd.gordon.shared.viewer.GetStockAdminDatasetAction;
import org.sjd.gordon.shared.viewer.GetStockAdminDatasetResult;

import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class GetStockAdminDatasetDevHandler implements ActionHandler<GetStockAdminDatasetAction, GetStockAdminDatasetResult> {

	@Override
	public GetStockAdminDatasetResult execute(GetStockAdminDatasetAction action, ExecutionContext context) throws ActionException {
		ArrayList<StockSplit> splits = Data.splitsMap.get(action.getStockId());
		if (splits == null) {
			splits = new ArrayList<StockSplit>(0);
		}
		ArrayList<TreasuryHeldStock> treasuryHeldStock = Data.heldInTreasuryMap.get(action.getStockId());
		if (treasuryHeldStock == null) {
			treasuryHeldStock = new ArrayList<TreasuryHeldStock>(0);
		}
		ArrayList<Dividend> dividendHistory = Data.dividendMap.get(action.getStockId());
		if (dividendHistory == null) {
			dividendHistory = new ArrayList<Dividend>(0);
		}

		return new GetStockAdminDatasetResult(splits, treasuryHeldStock, dividendHistory);
	}

	@Override
	public Class<GetStockAdminDatasetAction> getActionType() {
		return GetStockAdminDatasetAction.class;
	}

	@Override
	public void undo(GetStockAdminDatasetAction action, GetStockAdminDatasetResult result, ExecutionContext context) throws ActionException { }

}
