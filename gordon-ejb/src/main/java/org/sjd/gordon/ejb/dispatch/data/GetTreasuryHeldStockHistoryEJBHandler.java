package org.sjd.gordon.ejb.dispatch.data;

import java.util.ArrayList;
import java.util.List;

import org.sjd.gordon.ejb.StockEntityServiceLocal;
import org.sjd.gordon.model.TreasuryHeldStock;
import org.sjd.gordon.shared.viewer.GetTreasuryHeldStockHistoryAction;
import org.sjd.gordon.shared.viewer.GetTreasuryHeldStockHistoryResult;

import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class GetTreasuryHeldStockHistoryEJBHandler implements ActionHandler<GetTreasuryHeldStockHistoryAction,GetTreasuryHeldStockHistoryResult> {

	@Inject
	private StockEntityServiceLocal stockService;
	
	@Override
	public GetTreasuryHeldStockHistoryResult execute(GetTreasuryHeldStockHistoryAction action, ExecutionContext context) throws ActionException {
		List<TreasuryHeldStock> treasuryHeldStock = stockService.getTreasuryHeldStockHistory(action.getStockId());
		return new GetTreasuryHeldStockHistoryResult(new ArrayList<TreasuryHeldStock>(treasuryHeldStock));
	}

	@Override
	public Class<GetTreasuryHeldStockHistoryAction> getActionType() {
		return GetTreasuryHeldStockHistoryAction.class;
	}

	@Override
	public void undo(GetTreasuryHeldStockHistoryAction action, GetTreasuryHeldStockHistoryResult result, ExecutionContext context) throws ActionException { }

}
