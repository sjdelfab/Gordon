package org.sjd.gordon.ejb.dispatch.data;

import org.sjd.gordon.ejb.StockEntityService;
import org.sjd.gordon.model.StockEntity;
import org.sjd.gordon.shared.viewer.GetStockDetails;
import org.sjd.gordon.shared.viewer.GotStockDetails;
import org.sjd.gordon.shared.viewer.StockDetails;

import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class GetStockDetailsEJBHandler implements ActionHandler<GetStockDetails, GotStockDetails> {

	@Inject
	private StockEntityService stockEjb;
	
	@Override
	public GotStockDetails execute(GetStockDetails getDetails, ExecutionContext context) throws ActionException {
		StockEntity stockEntity = stockEjb.findStockById(getDetails.getStockId());
		return new GotStockDetails(StockDetails.fromEntity(stockEntity));
	}

	@Override
	public Class<GetStockDetails> getActionType() {
		return GetStockDetails.class;
	}

	@Override
	public void undo(GetStockDetails getDetails, GotStockDetails gotDetails, ExecutionContext context) throws ActionException { }

}
