package org.sjd.gordon.server.devhandlers;

import org.sjd.gordon.shared.registry.DeleteRegistryEntryAction;
import org.sjd.gordon.shared.registry.DeleteRegistryEntryResult;

import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class DeleteRegistryEntryDevHandler implements ActionHandler<DeleteRegistryEntryAction,DeleteRegistryEntryResult> {

	@Override
	public DeleteRegistryEntryResult execute(DeleteRegistryEntryAction deleteEntry, ExecutionContext context) throws ActionException {
		Data.detailsMap.remove(deleteEntry.getStockId());
		DeleteRegistryEntryResult response = new DeleteRegistryEntryResult();
		return response;
	}

	@Override
	public Class<DeleteRegistryEntryAction> getActionType() {
		return DeleteRegistryEntryAction.class;
	}

	@Override
	public void undo(DeleteRegistryEntryAction action, DeleteRegistryEntryResult result, ExecutionContext context) throws ActionException {
		// Nothing to do here
	}

}
