package org.sjd.gordon.server.devhandlers.data;

import java.util.ArrayList;

import org.sjd.gordon.model.StockSplit;
import org.sjd.gordon.server.devhandlers.Data;
import org.sjd.gordon.shared.viewer.DeleteStockSplitAction;
import org.sjd.gordon.shared.viewer.DeleteStockSplitResult;

import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class DeleteStockSplitDevHandler implements ActionHandler<DeleteStockSplitAction,DeleteStockSplitResult> {

	@Override
	public DeleteStockSplitResult execute(DeleteStockSplitAction action, ExecutionContext context) throws ActionException {
		ArrayList<StockSplit> splits = Data.splitsMap.get(action.getStockSplit().getStockId());
		if (splits != null) {
			splits.remove(action.getStockSplit());
		}
		return new DeleteStockSplitResult();
	}

	@Override
	public Class<DeleteStockSplitAction> getActionType() {
		return DeleteStockSplitAction.class;
	}

	@Override
	public void undo(DeleteStockSplitAction action, DeleteStockSplitResult result, ExecutionContext context) throws ActionException { }

}
