package org.sjd.gordon.ejb.dispatch.data;

import java.util.ArrayList;
import java.util.List;

import org.sjd.gordon.ejb.StockEntityService;
import org.sjd.gordon.model.StockEntity;
import org.sjd.gordon.shared.registry.GetAllStockDetails;
import org.sjd.gordon.shared.registry.GotAllStockDetails;
import org.sjd.gordon.shared.viewer.StockDetails;

import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class GetAllStockDetailsEJBHandler implements ActionHandler<GetAllStockDetails, GotAllStockDetails> {

	@Inject
	private StockEntityService stockEjb;
	
	@Override
	public GotAllStockDetails execute(GetAllStockDetails getDetails, ExecutionContext context) throws ActionException {
		List<StockEntity> stocks = stockEjb.getStocks(getDetails.getExchangeId());
		ArrayList<StockDetails> details = new ArrayList<StockDetails>(stocks.size());
		for(StockEntity entity: stocks) {
			details.add(StockDetails.fromEntity(entity));
		}
		return new GotAllStockDetails(details);
	}

	@Override
	public Class<GetAllStockDetails> getActionType() {
		return GetAllStockDetails.class;
	}

	@Override
	public void undo(GetAllStockDetails action, GotAllStockDetails result, ExecutionContext context) throws ActionException { }

}
