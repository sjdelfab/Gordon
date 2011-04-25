package org.sjd.gordon.ejb.dispatch;

import javax.naming.Context;
import javax.naming.NamingException;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.ActionException;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.sjd.gordon.ejb.StockEntityEJB;
import org.sjd.gordon.model.StockEntity;
import org.sjd.gordon.shared.viewer.GetStockDetails;
import org.sjd.gordon.shared.viewer.GotStockDetails;
import org.sjd.gordon.shared.viewer.StockDetails;

import com.google.inject.Inject;

public class GetStockDetailsEJBHandler implements ActionHandler<GetStockDetails, GotStockDetails> {

	@Inject
	private Context ctx;
	
	@Override
	public GotStockDetails execute(GetStockDetails getDetails, ExecutionContext context) throws DispatchException {
		StockEntityEJB stockEjb = null;
		try {
			stockEjb = (StockEntityEJB) ctx.lookup("java:global/gordon-gwt-1.0/StockEntityEJB!org.sjd.gordon.ejb.StockEntityEJB");
		} catch (NamingException e) {
			throw new ActionException(e);
		}
		if (stockEjb == null) {
			throw new RuntimeException("EJB not found.");
		}
		StockEntity stockEntity = stockEjb.findStockById(getDetails.getStockId());
		return new GotStockDetails(StockDetails.fromEntity(stockEntity));
	}

	@Override
	public Class<GetStockDetails> getActionType() {
		return GetStockDetails.class;
	}

	@Override
	public void rollback(GetStockDetails getDetails, GotStockDetails gotDetails, ExecutionContext context) throws DispatchException { }

}
