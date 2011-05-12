package org.sjd.gordon.server.devhandlers;

import java.util.ArrayList;

import org.sjd.gordon.shared.registry.GetAllStockDetails;
import org.sjd.gordon.shared.registry.GotAllStockDetails;
import org.sjd.gordon.shared.viewer.StockDetail;

import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class GetAllStockDetailsDevHandler implements ActionHandler<GetAllStockDetails, GotAllStockDetails> {
	
	@Override
	public GotAllStockDetails execute(GetAllStockDetails getDetails, ExecutionContext context) throws ActionException {
		ArrayList<StockDetail> details = new ArrayList<StockDetail>(Data.detailsMap.values());
		return new GotAllStockDetails(details);
	}

	@Override
	public Class<GetAllStockDetails> getActionType() {
		return GetAllStockDetails.class;
	}

	@Override
	public void undo(GetAllStockDetails action, GotAllStockDetails result, ExecutionContext context) throws ActionException { }

}
