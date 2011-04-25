package org.sjd.gordon.server.devhandlers;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.sjd.gordon.shared.viewer.GetStockDetails;
import org.sjd.gordon.shared.viewer.GotStockDetails;
import org.sjd.gordon.shared.viewer.StockDetails;

public class DevelopmentGetStockDetailsHandler implements ActionHandler<GetStockDetails, GotStockDetails> {

	@Override
	public GotStockDetails execute(GetStockDetails getDetails, ExecutionContext context) throws DispatchException {
		StockDetails stockDetails = new StockDetails();
		stockDetails.setCode("ABC");
		stockDetails.setName("ABC Ltd");
		return new GotStockDetails(stockDetails);
	}

	@Override
	public Class<GetStockDetails> getActionType() {
		return GetStockDetails.class;
	}

	@Override
	public void rollback(GetStockDetails getDetails, GotStockDetails gotDetails, ExecutionContext context) throws DispatchException { }

}
