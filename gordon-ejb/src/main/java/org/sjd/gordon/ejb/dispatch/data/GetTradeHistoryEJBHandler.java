package org.sjd.gordon.ejb.dispatch.data;

import java.util.ArrayList;

import org.sjd.gordon.ejb.StockEntityService;
import org.sjd.gordon.model.StockDayTradeRecord;
import org.sjd.gordon.shared.viewer.GetTradeHistory;
import org.sjd.gordon.shared.viewer.GotTradeHistory;

import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class GetTradeHistoryEJBHandler implements ActionHandler<GetTradeHistory, GotTradeHistory> {

	@Inject
	private StockEntityService stockEjb;
	
	@Override
	public GotTradeHistory execute(GetTradeHistory arg0, ExecutionContext arg1) throws ActionException {
		return new GotTradeHistory(new ArrayList<StockDayTradeRecord>(stockEjb.getDayTradeData(arg0.getStockId())));
	}

	@Override
	public Class<GetTradeHistory> getActionType() {
		return GetTradeHistory.class;
	}

	@Override
	public void undo(GetTradeHistory action, GotTradeHistory result, ExecutionContext arg2) throws ActionException {
	}

}
