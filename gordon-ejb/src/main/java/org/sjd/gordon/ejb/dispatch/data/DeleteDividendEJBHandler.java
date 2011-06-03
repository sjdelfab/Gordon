package org.sjd.gordon.ejb.dispatch.data;

import org.sjd.gordon.ejb.StockEntityServiceLocal;
import org.sjd.gordon.ejb.dispatch.AbstractHandler;
import org.sjd.gordon.model.Dividend;
import org.sjd.gordon.shared.exceptions.EntityNotFoundException;
import org.sjd.gordon.shared.viewer.DeleteDividendAction;
import org.sjd.gordon.shared.viewer.DeleteDividendResult;

import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class DeleteDividendEJBHandler extends AbstractHandler implements ActionHandler<DeleteDividendAction,DeleteDividendResult> {

	@Inject
	private StockEntityServiceLocal stockService;

	@Override
	public DeleteDividendResult execute(DeleteDividendAction action, ExecutionContext context) throws ActionException {
		try {
			Dividend dividend = stockService.findDividendById(action.getDividend().getId());
			if (dividend == null) {
				throw new EntityNotFoundException();
			}
			stockService.delete(dividend);
			return new DeleteDividendResult();
		} catch (Throwable cause) {
			if (cause instanceof EntityNotFoundException) {
				throw (EntityNotFoundException)cause;
			}
			throw translateException(cause);
		}
	}

	@Override
	public Class<DeleteDividendAction> getActionType() {
		return DeleteDividendAction.class;
	}

	@Override
	public void undo(DeleteDividendAction action, DeleteDividendResult result, ExecutionContext context) throws ActionException { }

}
