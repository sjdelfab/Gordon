package org.sjd.gordon.ejb.dispatch.data;

import java.util.ArrayList;
import java.util.List;

import org.sjd.gordon.ejb.StockEntityServiceLocal;
import org.sjd.gordon.model.StockSplit;
import org.sjd.gordon.shared.viewer.GetStockSplitsAction;
import org.sjd.gordon.shared.viewer.GetStockSplitsResult;

import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class GetStockSplitsEJBHandler implements ActionHandler<GetStockSplitsAction,GetStockSplitsResult> {

	@Inject
	private StockEntityServiceLocal stockService;
	
	@Override
	public GetStockSplitsResult execute(GetStockSplitsAction action, ExecutionContext context) throws ActionException {
		List<StockSplit> splits = stockService.getStockSplits(action.getStockId());
		return new GetStockSplitsResult(new ArrayList<StockSplit>(splits));
	}

	@Override
	public Class<GetStockSplitsAction> getActionType() {
		return GetStockSplitsAction.class;
	}

	@Override
	public void undo(GetStockSplitsAction action, GetStockSplitsResult result, ExecutionContext context) throws ActionException { }

}
