package org.sjd.gordon.ejb.dispatch.setup;

import org.sjd.gordon.ejb.StockEntityServiceLocal;
import org.sjd.gordon.ejb.dispatch.AbstractHandler;
import org.sjd.gordon.model.StockEntity;
import org.sjd.gordon.shared.exceptions.EntityNotFoundException;
import org.sjd.gordon.shared.registry.DeleteRegistryEntryAction;
import org.sjd.gordon.shared.registry.DeleteRegistryEntryResult;

import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class DeleteRegistryEntryEJBHandler extends AbstractHandler implements ActionHandler<DeleteRegistryEntryAction,DeleteRegistryEntryResult> {

	private StockEntityServiceLocal stockEntityService;
	
	@Inject
	public DeleteRegistryEntryEJBHandler(StockEntityServiceLocal stockEjb) {
		this.stockEntityService = stockEjb;
	}

	@Override
	public DeleteRegistryEntryResult execute(DeleteRegistryEntryAction deleteEntry, ExecutionContext context) throws ActionException {
		try {
			StockEntity stockEntity = stockEntityService.findStockById(deleteEntry.getStockId());
			if (stockEntity == null) {
				throw new EntityNotFoundException();
			}
			stockEntityService.deleteStock(stockEntity);
			return new DeleteRegistryEntryResult();
		} catch (Throwable cause) {
			if (cause instanceof EntityNotFoundException) {
				throw (EntityNotFoundException)cause;
			}
			throw translateException(cause);
		}
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
