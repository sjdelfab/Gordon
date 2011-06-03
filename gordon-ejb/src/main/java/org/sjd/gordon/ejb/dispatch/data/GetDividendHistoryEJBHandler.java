package org.sjd.gordon.ejb.dispatch.data;

import java.util.ArrayList;
import java.util.List;

import org.sjd.gordon.ejb.StockEntityServiceLocal;
import org.sjd.gordon.model.Dividend;
import org.sjd.gordon.shared.viewer.GetDividendHistoryAction;
import org.sjd.gordon.shared.viewer.GetDividendHistoryResult;

import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class GetDividendHistoryEJBHandler implements ActionHandler<GetDividendHistoryAction,GetDividendHistoryResult> {

	@Inject
	private StockEntityServiceLocal stockService;
	
	@Override
	public GetDividendHistoryResult execute(GetDividendHistoryAction action, ExecutionContext context) throws ActionException {
		List<Dividend> dividendHistory = stockService.getDividendHistory(action.getStockId());
		return new GetDividendHistoryResult(new ArrayList<Dividend>(dividendHistory));
	}

	@Override
	public Class<GetDividendHistoryAction> getActionType() {
		return GetDividendHistoryAction.class;
	}

	@Override
	public void undo(GetDividendHistoryAction action, GetDividendHistoryResult result, ExecutionContext context) throws ActionException { }

}
