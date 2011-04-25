package org.sjd.gordon.server.devhandlers;

import java.util.ArrayList;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.sjd.gordon.shared.navigation.GetStocks;
import org.sjd.gordon.shared.navigation.GotStocks;
import org.sjd.gordon.shared.navigation.StockName;

public class DevelopmentGetStocksHandler implements ActionHandler<GetStocks,GotStocks> {

	@Override
	public GotStocks execute(GetStocks getStocks, ExecutionContext context) throws DispatchException {
		return new GotStocks(create());
	}
	
	private ArrayList<StockName> create() {
		ArrayList<StockName> list = new ArrayList<StockName>();
		StockName stock = new StockName();
		stock.setCode("ABC");
		stock.setName("ABC Ltd");
		list.add(stock);
		stock = new StockName();
		stock.setCode("BHP");
		stock.setName("BHP Ltd");
		list.add(stock);
		return list;
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
