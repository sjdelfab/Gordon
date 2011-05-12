package org.sjd.gordon.ejb.dispatch.setup;

import org.sjd.gordon.ejb.StockEntityService;
import org.sjd.gordon.ejb.dispatch.AbstractHandler;
import org.sjd.gordon.model.StockEntity;
import org.sjd.gordon.shared.exceptions.EntityNotFoundException;
import org.sjd.gordon.shared.registry.DeleteRegistryEntry;
import org.sjd.gordon.shared.registry.DeleteRegistryEntryResponse;

import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class DeleteRegistryEntryEJBHandler extends AbstractHandler implements ActionHandler<DeleteRegistryEntry,DeleteRegistryEntryResponse> {

	private StockEntityService stockEntityService;
	
	@Inject
	public DeleteRegistryEntryEJBHandler(StockEntityService stockEjb) {
		this.stockEntityService = stockEjb;
	}

	@Override
	public DeleteRegistryEntryResponse execute(DeleteRegistryEntry deleteEntry, ExecutionContext context) throws ActionException {
		try {
			StockEntity stockEntity = stockEntityService.findStockById(deleteEntry.getStockId());
			if (stockEntity == null) {
				throw new EntityNotFoundException();
			}
			stockEntityService.deleteStock(stockEntity);
			return new DeleteRegistryEntryResponse();
		} catch (Throwable cause) {
			if (cause instanceof EntityNotFoundException) {
				throw (EntityNotFoundException)cause;
			}
			throw translateException(cause);
		}
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
