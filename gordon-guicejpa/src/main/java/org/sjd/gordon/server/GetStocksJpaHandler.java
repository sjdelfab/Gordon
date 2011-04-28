package org.sjd.gordon.server;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.sjd.gordon.model.StockEntity;
import org.sjd.gordon.shared.navigation.GetStocks;
import org.sjd.gordon.shared.navigation.GotStocks;
import org.sjd.gordon.shared.navigation.StockName;

import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class GetStocksJpaHandler implements ActionHandler<GetStocks,GotStocks> {

	@Inject EntityManager em;
	
	@Override
	public GotStocks execute(GetStocks getStocks, ExecutionContext context) throws ActionException {
		String getStockByExchange = "SELECT s FROM StockEntity s WHERE s.exchange.id = :id";
    	TypedQuery<StockEntity> query = em.createQuery(getStockByExchange, StockEntity.class);
    	query.setParameter("id", getStocks.getExchangeId());
    	List<StockEntity> stockEntities = query.getResultList();
		ArrayList<StockName> stocks = new ArrayList<StockName>(stockEntities.size());
		for(StockEntity entity: stockEntities) {
			stocks.add(StockName.fromEntity(entity));
		}
		return new GotStocks(stocks);
	}
	
	@Override
	public Class<GetStocks> getActionType() {
		return GetStocks.class;
	}

	@Override
	public void undo(GetStocks action, GotStocks result, ExecutionContext context) throws ActionException {
		// Nothing
	}	
}
