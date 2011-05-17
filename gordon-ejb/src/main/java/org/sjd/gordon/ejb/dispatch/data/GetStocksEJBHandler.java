package org.sjd.gordon.ejb.dispatch.data;

import java.util.ArrayList;
import java.util.List;

import org.sjd.gordon.ejb.StockEntityService;
import org.sjd.gordon.model.StockEntity;
import org.sjd.gordon.shared.navigation.GetStocksAction;
import org.sjd.gordon.shared.navigation.GetStocksResult;
import org.sjd.gordon.shared.navigation.StockName;

import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class GetStocksEJBHandler implements ActionHandler<GetStocksAction,GetStocksResult> {

	@Inject
	private StockEntityService stockEjb;
	
	@Override
	public GetStocksResult execute(GetStocksAction getStocks, ExecutionContext context) throws ActionException {
		List<StockEntity> stockEntities = stockEjb.getStocks(getStocks.getExchangeId());
		ArrayList<StockName> stocks = new ArrayList<StockName>(stockEntities.size());
		for(StockEntity entity: stockEntities) {
			stocks.add(StockName.fromEntity(entity));
		}
		return new GetStocksResult(stocks);
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
