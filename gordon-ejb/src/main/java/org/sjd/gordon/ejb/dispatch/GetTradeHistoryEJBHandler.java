package org.sjd.gordon.ejb.dispatch;

import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.NamingException;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.ActionException;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.sjd.gordon.ejb.StockEntityEJB;
import org.sjd.gordon.model.StockDayTradeRecord;
import org.sjd.gordon.shared.viewer.GetTradeHistory;
import org.sjd.gordon.shared.viewer.GotTradeHistory;

import com.google.inject.Inject;

public class GetTradeHistoryEJBHandler implements ActionHandler<GetTradeHistory, GotTradeHistory> {

	@Inject
	private Context ctx;
	
	@Override
	public GotTradeHistory execute(GetTradeHistory arg0, ExecutionContext arg1) throws DispatchException {
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
	public void rollback(GetTradeHistory arg0, GotTradeHistory arg1, ExecutionContext arg2) throws DispatchException {
	}

}
