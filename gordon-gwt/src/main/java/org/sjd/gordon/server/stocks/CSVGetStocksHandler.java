package org.sjd.gordon.server.stocks;

import java.util.ArrayList;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.ActionException;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.sjd.gordon.dao.SecurityRegistryDAOException;
import org.sjd.gordon.dao.csv.CSVSecurityRegistryDAO;
import org.sjd.gordon.model.Exchange;
import org.sjd.gordon.model.StockEntity;
import org.sjd.gordon.shared.navigation.GetStocks;
import org.sjd.gordon.shared.navigation.GotStocks;

public class CSVGetStocksHandler implements ActionHandler<GetStocks,GotStocks> {

	@Override
	public GotStocks execute(GetStocks getStocks, ExecutionContext context) throws DispatchException {
		String path = "WEB-INF/csv/catalog.csv";
		Exchange exchange = new Exchange();
    	exchange.setActive(true);
    	exchange.setCode("ASX");
    	exchange.setName("Australian Exchange");
		CSVSecurityRegistryDAO registryDao = new CSVSecurityRegistryDAO(path,exchange);
		ArrayList<StockEntity> stocks;
		try {
			stocks = (ArrayList<StockEntity>)registryDao.getStocksSortedByCode(exchange);
		} catch (SecurityRegistryDAOException e) {
			throw new ActionException(e); 
		}
		return new GotStocks(stocks);
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
