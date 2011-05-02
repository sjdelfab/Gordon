package org.sjd.gordon.ejb.dispatch.data;

import java.util.ArrayList;
import java.util.List;

import org.sjd.gordon.ejb.StockEntityService;
import org.sjd.gordon.model.StockEntity;
import org.sjd.gordon.shared.navigation.GetStocks;
import org.sjd.gordon.shared.navigation.GotStocks;
import org.sjd.gordon.shared.navigation.StockName;

import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class GetStocksEJBHandler implements ActionHandler<GetStocks,GotStocks> {

	@Inject
	private StockEntityService stockEjb;
	
	@Override
	public GotStocks execute(GetStocks getStocks, ExecutionContext context) throws ActionException {
		List<StockEntity> stockEntities = stockEjb.getStocks(getStocks.getExchangeId());
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
	public void undo(GetStocks arg0, GotStocks arg1, ExecutionContext arg2) throws ActionException {
		// Nothing
	}

}
