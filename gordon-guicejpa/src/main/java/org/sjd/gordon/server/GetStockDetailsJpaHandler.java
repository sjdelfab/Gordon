package org.sjd.gordon.server;

import javax.persistence.EntityManager;

import org.sjd.gordon.model.StockEntity;
import org.sjd.gordon.shared.viewer.GetStockDetailsAction;
import org.sjd.gordon.shared.viewer.GetStockDetailsResult;
import org.sjd.gordon.shared.viewer.StockDetail;

import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class GetStockDetailsJpaHandler implements ActionHandler<GetStockDetailsAction, GetStockDetailsResult> {

	@Inject EntityManager em;
	
	@Override
	public GetStockDetailsResult execute(GetStockDetailsAction getDetails, ExecutionContext context) throws ActionException {
		StockEntity stockEntity = em.find(StockEntity.class, getDetails.getStockId());
		return new GetStockDetailsResult(StockDetail.fromEntity(stockEntity));
	}

	@Override
	public Class<GetStockDetailsAction> getActionType() {
		return GetStockDetailsAction.class;
	}

	@Override
	public void undo(GetStockDetailsAction getDetails, GetStockDetailsResult gotDetails, ExecutionContext context) throws ActionException { }

}
