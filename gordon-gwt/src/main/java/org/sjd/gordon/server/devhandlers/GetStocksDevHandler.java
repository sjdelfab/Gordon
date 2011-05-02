package org.sjd.gordon.server.devhandlers;

import java.util.ArrayList;

import org.sjd.gordon.shared.navigation.GetStocks;
import org.sjd.gordon.shared.navigation.GotStocks;
import org.sjd.gordon.shared.navigation.StockName;

import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class GetStocksDevHandler implements ActionHandler<GetStocks,GotStocks> {

	@Override
	public GotStocks execute(GetStocks getStocks, ExecutionContext context) throws ActionException {
		return new GotStocks(create());
	}
	
	private ArrayList<StockName> create() {
		ArrayList<StockName> list = new ArrayList<StockName>();
		StockName stock = new StockName(Long.valueOf(1));
		stock.setCode("ABC");
		stock.setName("ABC Ltd");
		list.add(stock);
		stock = new StockName(Long.valueOf(2));
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
	public void undo(GetStocks action, GotStocks result, ExecutionContext context) throws ActionException {
		// Nothing
	}

}
