package org.sjd.gordon.ejb.dispatch.data;

import java.util.ArrayList;

import org.sjd.gordon.ejb.StockEntityServiceLocal;
import org.sjd.gordon.model.StockDayTradeRecord;
import org.sjd.gordon.shared.viewer.GetTradeHistoryAction;
import org.sjd.gordon.shared.viewer.GetTradeHistoryResult;

import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class GetTradeHistoryEJBHandler implements ActionHandler<GetTradeHistoryAction, GetTradeHistoryResult> {

	@Inject
	private StockEntityServiceLocal stockEjb;
	
	@Override
	public GetTradeHistoryResult execute(GetTradeHistoryAction action, ExecutionContext context) throws ActionException {
		return new GetTradeHistoryResult(new ArrayList<StockDayTradeRecord>(stockEjb.getDayTradeData(action.getStockId())));
	}

	@Override
	public Class<GetTradeHistoryAction> getActionType() {
		return GetTradeHistoryAction.class;
	}

	@Override
	public void undo(GetTradeHistoryAction action, GetTradeHistoryResult result, ExecutionContext context) throws ActionException {
	}

}
