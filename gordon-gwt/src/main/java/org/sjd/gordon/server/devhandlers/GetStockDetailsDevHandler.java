package org.sjd.gordon.server.devhandlers;

import org.sjd.gordon.shared.viewer.GetStockDetails;
import org.sjd.gordon.shared.viewer.GotStockDetails;

import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class GetStockDetailsDevHandler implements ActionHandler<GetStockDetails, GotStockDetails> {

	
	@Override
	public GotStockDetails execute(GetStockDetails getDetails, ExecutionContext context) throws ActionException {
		return new GotStockDetails(Data.detailsMap.get(getDetails.getStockId()));
	}

	@Override
	public Class<GetStockDetails> getActionType() {
		return GetStockDetails.class;
	}

	@Override
	public void undo(GetStockDetails action, GotStockDetails result, ExecutionContext context) throws ActionException { }

}
