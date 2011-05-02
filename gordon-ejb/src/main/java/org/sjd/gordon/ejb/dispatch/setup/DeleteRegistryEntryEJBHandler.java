package org.sjd.gordon.ejb.dispatch.setup;

import org.sjd.gordon.ejb.StockEntityService;
import org.sjd.gordon.model.StockEntity;
import org.sjd.gordon.shared.registry.DeleteRegistryEntry;
import org.sjd.gordon.shared.registry.DeleteRegistryEntryResponse;

import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class DeleteRegistryEntryEJBHandler implements ActionHandler<DeleteRegistryEntry,DeleteRegistryEntryResponse> {

	@Inject
	private StockEntityService stockEjb;

	@Override
	public DeleteRegistryEntryResponse execute(DeleteRegistryEntry deleteEntry, ExecutionContext context) throws ActionException {
		StockEntity stockEntity = stockEjb.findStockById(deleteEntry.getStockId());
		if (stockEntity == null) {
			// TODO Handle non-existent entity case
		}
		stockEjb.deleteStock(stockEntity);
		return new DeleteRegistryEntryResponse();
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
