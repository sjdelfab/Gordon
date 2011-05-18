package org.sjd.gordon.server.devhandlers;

import java.util.ArrayList;

import org.sjd.gordon.shared.registry.GetAllRegistryEntriesAction;
import org.sjd.gordon.shared.registry.GetAllRegistryEntriesResult;
import org.sjd.gordon.shared.viewer.StockDetail;

import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class GetAllStockDetailsDevHandler implements ActionHandler<GetAllRegistryEntriesAction, GetAllRegistryEntriesResult> {
	
	@Override
	public GetAllRegistryEntriesResult execute(GetAllRegistryEntriesAction getDetails, ExecutionContext context) throws ActionException {
		int limit = getDetails.getLimit();
		int offset = getDetails.getOffset();
		ArrayList<StockDetail> details = new ArrayList<StockDetail>(limit);
		for(int i=offset; i < (offset + limit); i++) {
			StockDetail detail = Data.detailsMap.get(Long.valueOf(i));
			if (detail != null) {
				details.add(detail);
			}
		}
		return new GetAllRegistryEntriesResult(details,Data.detailsMap.values().size());
	}

	@Override
	public Class<GetAllRegistryEntriesAction> getActionType() {
		return GetAllRegistryEntriesAction.class;
	}

	@Override
	public void undo(GetAllRegistryEntriesAction action, GetAllRegistryEntriesResult result, ExecutionContext context) throws ActionException { }

}
