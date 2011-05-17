package org.sjd.gordon.server.devhandlers;

import java.util.ArrayList;

import org.sjd.gordon.shared.navigation.GetStocksAction;
import org.sjd.gordon.shared.navigation.GetStocksResult;
import org.sjd.gordon.shared.navigation.StockName;

import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class GetStocksDevHandler implements ActionHandler<GetStocksAction,GetStocksResult> {

	@Override
	public GetStocksResult execute(GetStocksAction getStocks, ExecutionContext context) throws ActionException {
		return new GetStocksResult(create());
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
	public Class<GetStocksAction> getActionType() {
		return GetStocksAction.class;
	}

	@Override
	public void undo(GetStocksAction action, GetStocksResult result, ExecutionContext context) throws ActionException {
		// Nothing
	}

}
