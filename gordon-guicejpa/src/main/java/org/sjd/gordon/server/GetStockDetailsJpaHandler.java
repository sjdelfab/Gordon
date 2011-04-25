package org.sjd.gordon.server;

import javax.persistence.EntityManager;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.sjd.gordon.model.StockEntity;
import org.sjd.gordon.shared.viewer.GetStockDetails;
import org.sjd.gordon.shared.viewer.GotStockDetails;
import org.sjd.gordon.shared.viewer.StockDetails;

import com.google.inject.Inject;

public class GetStockDetailsJpaHandler implements ActionHandler<GetStockDetails, GotStockDetails> {

	@Inject EntityManager em;
	
	@Override
	public GotStockDetails execute(GetStockDetails getDetails, ExecutionContext context) throws DispatchException {
		StockEntity stockEntity = em.find(StockEntity.class, getDetails.getStockId());
		return new GotStockDetails(StockDetails.fromEntity(stockEntity));
	}

	@Override
	public Class<GetStockDetails> getActionType() {
		return GetStockDetails.class;
	}

	@Override
	public void rollback(GetStockDetails getDetails, GotStockDetails gotDetails, ExecutionContext context) throws DispatchException { }

}
