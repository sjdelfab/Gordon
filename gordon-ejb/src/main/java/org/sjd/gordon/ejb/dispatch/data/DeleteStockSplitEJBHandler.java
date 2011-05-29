package org.sjd.gordon.ejb.dispatch.data;

import org.sjd.gordon.ejb.StockEntityService;
import org.sjd.gordon.ejb.dispatch.AbstractHandler;
import org.sjd.gordon.model.StockSplit;
import org.sjd.gordon.shared.exceptions.EntityNotFoundException;
import org.sjd.gordon.shared.viewer.DeleteStockSplitAction;
import org.sjd.gordon.shared.viewer.DeleteStockSplitResult;

import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class DeleteStockSplitEJBHandler extends AbstractHandler implements ActionHandler<DeleteStockSplitAction,DeleteStockSplitResult> {

	@Inject
	private StockEntityService stockService;
	
	@Override
	public DeleteStockSplitResult execute(DeleteStockSplitAction action, ExecutionContext context) throws ActionException {
		try {
			StockSplit stockSplit = stockService.findStockSplitById(action.getStockSplit().getId());
			if (stockSplit == null) {
				throw new EntityNotFoundException();
			}
			stockService.delete(stockSplit);
			return new DeleteStockSplitResult();
		} catch (Throwable cause) {
			if (cause instanceof EntityNotFoundException) {
				throw (EntityNotFoundException)cause;
			}
			throw translateException(cause);
		}
	}

	@Override
	public Class<DeleteStockSplitAction> getActionType() {
		return DeleteStockSplitAction.class;
	}

	@Override
	public void undo(DeleteStockSplitAction action, DeleteStockSplitResult result, ExecutionContext context) throws ActionException { }
	
}
