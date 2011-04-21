package org.sjd.gordon.server;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.sjd.gordon.model.StockDayTradeRecord;
import org.sjd.gordon.shared.viewer.GetTradeHistory;
import org.sjd.gordon.shared.viewer.GotTradeHistory;

import com.google.inject.Inject;

public class GetTradeHistoryJpaHandler implements ActionHandler<GetTradeHistory, GotTradeHistory> {

	@Inject EntityManager em;
	
	@Override
	public GotTradeHistory execute(GetTradeHistory getTradeHistory, ExecutionContext context) throws DispatchException {
		String getStockTradeHistory = "SELECT s FROM StockDayTradeRecord s WHERE s.stockId = :id ORDER BY s.id";
    	TypedQuery<StockDayTradeRecord> query = em.createQuery(getStockTradeHistory, StockDayTradeRecord.class);
    	query.setParameter("id", getTradeHistory.getStockId());
    	List<StockDayTradeRecord> tradeHistory = query.getResultList();
		return new GotTradeHistory(new ArrayList<StockDayTradeRecord>(tradeHistory));
	}

	@Override
	public Class<GetTradeHistory> getActionType() {
		return GetTradeHistory.class;
	}

	@Override
	public void rollback(GetTradeHistory arg0, GotTradeHistory arg1, ExecutionContext arg2) throws DispatchException {
		
	}

}
