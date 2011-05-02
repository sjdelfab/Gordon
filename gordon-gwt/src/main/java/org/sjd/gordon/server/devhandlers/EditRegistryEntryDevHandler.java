package org.sjd.gordon.server.devhandlers;

import org.sjd.gordon.shared.registry.EditRegistryEntry;
import org.sjd.gordon.shared.registry.EditRegistryEntry.EditType;
import org.sjd.gordon.shared.registry.EditRegistryEntryResponse;
import org.sjd.gordon.shared.viewer.StockDetails;

import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class EditRegistryEntryDevHandler implements ActionHandler<EditRegistryEntry,EditRegistryEntryResponse> {

	@Override
	public EditRegistryEntryResponse execute(EditRegistryEntry editEntry, ExecutionContext context) throws ActionException {
		EditRegistryEntry.EditType editType = editEntry.getEditType();
		StockDetails newStockDetails = editEntry.getStockDetails();
		if (editType == EditType.ADD) {
			Long currentMaxId = Data.getMaxId();
			newStockDetails.setId(currentMaxId+1);
			Data.detailsMap.put(currentMaxId + 1, newStockDetails);
		} else {
			StockDetails currentStock = Data.detailsMap.get(newStockDetails.getId());
			currentStock.mergeTo(newStockDetails);
		}
		return new EditRegistryEntryResponse(newStockDetails.getId());
	}

	@Override
	public Class<EditRegistryEntry> getActionType() {
		return EditRegistryEntry.class;
	}

	@Override
	public void undo(EditRegistryEntry action, EditRegistryEntryResponse result, ExecutionContext context) throws ActionException {
		// Nothing to do here
	}

}