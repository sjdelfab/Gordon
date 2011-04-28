package org.sjd.gordon.ejb.dispatch;

import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.NamingException;

import org.sjd.gordon.ejb.StockEntityEJB;
import org.sjd.gordon.model.StockDayTradeRecord;
import org.sjd.gordon.shared.viewer.GetTradeHistory;
import org.sjd.gordon.shared.viewer.GotTradeHistory;

import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class GetTradeHistoryEJBHandler implements ActionHandler<GetTradeHistory, GotTradeHistory> {

	@Inject
	private Context ctx;
	
	@Override
	public GotTradeHistory execute(GetTradeHistory arg0, ExecutionContext arg1) throws ActionException {
		StockEntityEJB stockEjb = null;
		try {
			stockEjb = (StockEntityEJB) ctx.lookup("java:global/gordon-gwt-1.0/StockEntityEJB!org.sjd.gordon.ejb.StockEntityEJB");
		} catch (NamingException e) {
			throw new ActionException(e);
		}
		if (stockEjb == null) {
			throw new RuntimeException("EJB not found.");
		}
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
