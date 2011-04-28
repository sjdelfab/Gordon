package org.sjd.gordon.server;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.sjd.gordon.model.StockDayTradeRecord;
import org.sjd.gordon.shared.viewer.GetTradeHistory;
import org.sjd.gordon.shared.viewer.GotTradeHistory;

import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class GetTradeHistoryJpaHandler implements ActionHandler<GetTradeHistory, GotTradeHistory> {

	@Inject EntityManager em;
	
	@Override
	public GotTradeHistory execute(GetTradeHistory getTradeHistory, ExecutionContext context) throws ActionException {
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
	public void undo(GetTradeHistory action, GotTradeHistory result, ExecutionContext context) throws ActionException {
		
	}

}
