package org.sjd.gordon.server;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.sjd.gordon.model.StockDayTradeRecord;
import org.sjd.gordon.shared.viewer.GetTradeHistoryAction;
import org.sjd.gordon.shared.viewer.GetTradeHistoryResult;

import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class GetTradeHistoryJpaHandler implements ActionHandler<GetTradeHistoryAction, GetTradeHistoryResult> {

	@Inject EntityManager em;
	
	@Override
	public GetTradeHistoryResult execute(GetTradeHistoryAction getTradeHistory, ExecutionContext context) throws ActionException {
		String getStockTradeHistory = "SELECT s FROM StockDayTradeRecord s WHERE s.stockId = :id ORDER BY s.id";
    	TypedQuery<StockDayTradeRecord> query = em.createQuery(getStockTradeHistory, StockDayTradeRecord.class);
    	query.setParameter("id", getTradeHistory.getStockId());
    	List<StockDayTradeRecord> tradeHistory = query.getResultList();
		return new GetTradeHistoryResult(new ArrayList<StockDayTradeRecord>(tradeHistory));
	}

	@Override
	public Class<GetTradeHistoryAction> getActionType() {
		return GetTradeHistoryAction.class;
	}

	@Override
	public void undo(GetTradeHistoryAction action, GetTradeHistoryResult result, ExecutionContext context) throws ActionException {
		
	}

}
