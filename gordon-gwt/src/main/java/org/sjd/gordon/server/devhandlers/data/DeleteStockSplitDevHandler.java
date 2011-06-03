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
		for(Long stockId: Data.splitsMap.keySet()) {
			ArrayList<StockSplit> stockSplits = Data.splitsMap.get(stockId);
			if (stockSplits != null) {
				int index = -1;
				for(int i=0; i < stockSplits.size(); i++) {
					StockSplit stockSplit = stockSplits.get(i);
					if (stockSplit.getId() == action.getStockSplit().getId()) {
						index = -1;
					}
				}
				if (index != -1) {
				    stockSplits.remove(index);
				    break;
				}
			}
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
