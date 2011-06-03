package org.sjd.gordon.server.devhandlers.data;

import java.util.ArrayList;

import org.sjd.gordon.model.StockSplit;
import org.sjd.gordon.server.devhandlers.Data;
import org.sjd.gordon.shared.util.EditType;
import org.sjd.gordon.shared.viewer.EditStockSplitAction;
import org.sjd.gordon.shared.viewer.EditStockSplitResult;

import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class EditStockSplitDevHandler implements ActionHandler<EditStockSplitAction,EditStockSplitResult> {

	@Override
	public EditStockSplitResult execute(EditStockSplitAction action, ExecutionContext context) throws ActionException {
		EditType editType = action.getEditType();
		StockSplit newSplit = action.getNewStockSplit();
		if (editType == EditType.ADD) {
			Long newId = Data.stockSplitCounter++;
			newSplit.setId(newId);
			ArrayList<StockSplit> splits = Data.splitsMap.get(action.getStockId());
			if (splits == null) {
				splits = new ArrayList<StockSplit>();
				Data.splitsMap.put(action.getStockId(),splits);
			}
			splits.add(newSplit);
		} else {
			ArrayList<StockSplit> splits = Data.splitsMap.get(action.getStockId());
			StockSplit split = splits.get(splits.indexOf(newSplit));
			split.mergeTo(newSplit);
		}
		return new EditStockSplitResult(newSplit);
	}

	@Override
	public Class<EditStockSplitAction> getActionType() {
		return EditStockSplitAction.class;
	}

	@Override
	public void undo(EditStockSplitAction action, EditStockSplitResult result, ExecutionContext context) throws ActionException { }

}
