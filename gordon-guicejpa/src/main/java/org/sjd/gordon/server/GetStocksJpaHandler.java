package org.sjd.gordon.server;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.sjd.gordon.model.StockEntity;
import org.sjd.gordon.shared.navigation.GetStocks;
import org.sjd.gordon.shared.navigation.GotStocks;

import com.google.inject.Inject;

public class GetStocksJpaHandler implements ActionHandler<GetStocks,GotStocks> {

	@Inject EntityManager em;
	
	@Override
	public GotStocks execute(GetStocks getStocks, ExecutionContext context) throws DispatchException {
		String getStockByExchange = "SELECT s FROM StockEntity s";
    	TypedQuery<StockEntity> query = em.createQuery(getStockByExchange, StockEntity.class);
    	List<StockEntity> stocks = query.getResultList();
    	System.out.println("Stocks Number: " + stocks.size());
		return new GotStocks(new ArrayList<StockEntity>(stocks));
	}
	
	@Override
	public Class<GetStocks> getActionType() {
		return GetStocks.class;
	}

	@Override
	public void rollback(GetStocks arg0, GotStocks arg1, ExecutionContext arg2) throws DispatchException {
		// Nothing
	}	
}
