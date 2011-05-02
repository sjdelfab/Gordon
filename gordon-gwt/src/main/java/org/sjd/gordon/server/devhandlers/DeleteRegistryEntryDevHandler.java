package org.sjd.gordon.server.devhandlers;

import org.sjd.gordon.shared.registry.DeleteRegistryEntry;
import org.sjd.gordon.shared.registry.DeleteRegistryEntryResponse;

import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class DeleteRegistryEntryDevHandler implements ActionHandler<DeleteRegistryEntry,DeleteRegistryEntryResponse> {

	@Override
	public DeleteRegistryEntryResponse execute(DeleteRegistryEntry deleteEntry, ExecutionContext context) throws ActionException {
		Data.detailsMap.remove(deleteEntry.getStockId());
		DeleteRegistryEntryResponse response = new DeleteRegistryEntryResponse();
		return response;
	}

	@Override
	public Class<DeleteRegistryEntry> getActionType() {
		return DeleteRegistryEntry.class;
	}

	@Override
	public void undo(DeleteRegistryEntry action, DeleteRegistryEntryResponse result, ExecutionContext context) throws ActionException {
		// Nothing to do here
	}

}
