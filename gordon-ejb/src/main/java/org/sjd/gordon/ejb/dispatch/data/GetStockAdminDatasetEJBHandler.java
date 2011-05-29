package org.sjd.gordon.ejb.dispatch.data;

import java.util.ArrayList;
import java.util.List;

import org.sjd.gordon.ejb.StockEntityService;
import org.sjd.gordon.model.Dividend;
import org.sjd.gordon.model.StockSplit;
import org.sjd.gordon.model.TreasuryHeldStock;
import org.sjd.gordon.shared.viewer.GetStockAdminDatasetAction;
import org.sjd.gordon.shared.viewer.GetStockAdminDatasetResult;

import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class GetStockAdminDatasetEJBHandler implements ActionHandler<GetStockAdminDatasetAction, GetStockAdminDatasetResult> {

	@Inject
	private StockEntityService stockService;
	
	@Override
	public GetStockAdminDatasetResult execute(GetStockAdminDatasetAction action, ExecutionContext context) throws ActionException {
		List<StockSplit> splits = stockService.getStockSplits(action.getStockId());
		List<TreasuryHeldStock> treasuryHeldStock = stockService.getTreasuryHeldStockHistory(action.getStockId());
		List<Dividend> dividendHistory = stockService.getDividendHistory(action.getStockId());
		return new GetStockAdminDatasetResult(new ArrayList<StockSplit>(splits), new ArrayList<TreasuryHeldStock>(treasuryHeldStock), 
				                              new ArrayList<Dividend>(dividendHistory));
	}

	@Override
	public Class<GetStockAdminDatasetAction> getActionType() {
		return GetStockAdminDatasetAction.class;
	}

	@Override
	public void undo(GetStockAdminDatasetAction action, GetStockAdminDatasetResult result, ExecutionContext context) throws ActionException { }

}
