package org.sjd.gordon.server.devhandlers;

import org.sjd.gordon.shared.viewer.GetStockDetailsAction;
import org.sjd.gordon.shared.viewer.GetStockDetailsResult;

import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class GetStockDetailsDevHandler implements ActionHandler<GetStockDetailsAction, GetStockDetailsResult> {
	
	@Override
	public GetStockDetailsResult execute(GetStockDetailsAction getDetails, ExecutionContext context) throws ActionException {
		return new GetStockDetailsResult(Data.detailsMap.get(getDetails.getStockId()));
	}

	@Override
	public Class<GetStockDetailsAction> getActionType() {
		return GetStockDetailsAction.class;
	}

	@Override
	public void undo(GetStockDetailsAction action, GetStockDetailsResult result, ExecutionContext context) throws ActionException { }

}
