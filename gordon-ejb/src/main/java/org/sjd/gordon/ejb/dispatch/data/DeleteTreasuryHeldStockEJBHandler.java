package org.sjd.gordon.ejb.dispatch.data;

import org.sjd.gordon.ejb.StockEntityService;
import org.sjd.gordon.ejb.dispatch.AbstractHandler;
import org.sjd.gordon.model.TreasuryHeldStock;
import org.sjd.gordon.shared.exceptions.EntityNotFoundException;
import org.sjd.gordon.shared.viewer.DeleteTreasuryHeldStockAction;
import org.sjd.gordon.shared.viewer.DeleteTreasuryHeldStockResult;

import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class DeleteTreasuryHeldStockEJBHandler extends AbstractHandler implements ActionHandler<DeleteTreasuryHeldStockAction,DeleteTreasuryHeldStockResult> {

	@Inject
	private StockEntityService stockService;

	@Override
	public DeleteTreasuryHeldStockResult execute(DeleteTreasuryHeldStockAction action, ExecutionContext context) throws ActionException {
		try {
			TreasuryHeldStock stockSplit = stockService.findTreasuryHeldStockById(action.getTreasuryHeldStock().getId());
			if (stockSplit == null) {
				throw new EntityNotFoundException();
			}
			stockService.delete(stockSplit);
			return new DeleteTreasuryHeldStockResult();
		} catch (Throwable cause) {
			if (cause instanceof EntityNotFoundException) {
				throw (EntityNotFoundException)cause;
			}
			throw translateException(cause);
		}
	}

	@Override
	public Class<DeleteTreasuryHeldStockAction> getActionType() {
		return DeleteTreasuryHeldStockAction.class;
	}

	@Override
	public void undo(DeleteTreasuryHeldStockAction action, DeleteTreasuryHeldStockResult result, ExecutionContext context) throws ActionException { }

}
