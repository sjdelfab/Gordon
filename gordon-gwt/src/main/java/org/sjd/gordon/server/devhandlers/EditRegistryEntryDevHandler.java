package org.sjd.gordon.server.devhandlers;

import org.sjd.gordon.shared.registry.EditRegistryEntry;
import org.sjd.gordon.shared.registry.EditRegistryEntry.EditType;
import org.sjd.gordon.shared.registry.EditRegistryEntryAction;
import org.sjd.gordon.shared.registry.EditRegistryEntryResult;
import org.sjd.gordon.shared.viewer.StockDetail;

import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class EditRegistryEntryDevHandler implements ActionHandler<EditRegistryEntryAction,EditRegistryEntryResult> {

	@Override
	public EditRegistryEntryResult execute(EditRegistryEntryAction editEntry, ExecutionContext context) throws ActionException {
		EditRegistryEntry.EditType editType = editEntry.getEditType();
		StockDetail newStockDetails = editEntry.getStockDetails();
		if (editType == EditType.ADD) {
			Long currentMaxId = Data.getStockMaxId();
			newStockDetails.setId(currentMaxId+1);
			Data.detailsMap.put(currentMaxId + 1, newStockDetails);
		} else {
			StockDetail currentStock = Data.detailsMap.get(newStockDetails.getId());
			currentStock.mergeTo(newStockDetails);
		}
		return new EditRegistryEntryResult(newStockDetails);
	}

	@Override
	public Class<EditRegistryEntryAction> getActionType() {
		return EditRegistryEntryAction.class;
	}

	@Override
	public void undo(EditRegistryEntryAction action, EditRegistryEntryResult result, ExecutionContext context) throws ActionException {
		// Nothing to do here
	}

}
