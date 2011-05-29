package org.sjd.gordon.server.devhandlers.data;

import java.util.ArrayList;

import org.sjd.gordon.model.TreasuryHeldStock;
import org.sjd.gordon.server.devhandlers.Data;
import org.sjd.gordon.shared.viewer.GetTreasuryHeldStockHistoryAction;
import org.sjd.gordon.shared.viewer.GetTreasuryHeldStockHistoryResult;

import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class GetTreasuryHeldStockHistoryDevHandler implements ActionHandler<GetTreasuryHeldStockHistoryAction,GetTreasuryHeldStockHistoryResult> {

	@Override
	public GetTreasuryHeldStockHistoryResult execute(GetTreasuryHeldStockHistoryAction action, ExecutionContext context) throws ActionException {
		ArrayList<TreasuryHeldStock> treasuryHeldStock = Data.heldInTreasuryMap.get(action.getStockId());
		if (treasuryHeldStock == null) {
			treasuryHeldStock = new ArrayList<TreasuryHeldStock>(0);
		}
		return new GetTreasuryHeldStockHistoryResult(treasuryHeldStock);
	}

	@Override
	public Class<GetTreasuryHeldStockHistoryAction> getActionType() {
		return GetTreasuryHeldStockHistoryAction.class;
	}

	@Override
	public void undo(GetTreasuryHeldStockHistoryAction action, GetTreasuryHeldStockHistoryResult result, ExecutionContext context) throws ActionException { }

}
