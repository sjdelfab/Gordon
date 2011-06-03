package org.sjd.gordon.ejb.dispatch.data;

import org.sjd.gordon.ejb.StockEntityServiceLocal;
import org.sjd.gordon.ejb.dispatch.AbstractHandler;
import org.sjd.gordon.model.Dividend;
import org.sjd.gordon.shared.exceptions.EntityNotFoundException;
import org.sjd.gordon.shared.util.EditType;
import org.sjd.gordon.shared.viewer.EditDividendAction;
import org.sjd.gordon.shared.viewer.EditDividendResult;

import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class EditDividendEJBHandler extends AbstractHandler implements ActionHandler<EditDividendAction,EditDividendResult> {

	@Inject
	private StockEntityServiceLocal stockService;
	
	@Override
	public EditDividendResult execute(EditDividendAction action, ExecutionContext context) throws ActionException {
		EditType editType = action.getEditType();
		Dividend newDividend = action.getNewDividend();
		try {
			if (editType == EditType.ADD) {
				newDividend = add(action.getStockId(),newDividend);
			} else {
				newDividend = update(newDividend);
			}
		} catch (Throwable cause) {
			throw translateException(cause);
		}
		return new EditDividendResult(newDividend);
	}

	private Dividend add(Long stockId, Dividend newDividend) throws Exception {
		newDividend = stockService.createDividend(stockId,newDividend);
		return newDividend;
	}

	private Dividend update(Dividend newDividend) throws Exception {
		Dividend dividend = stockService.findDividendById(newDividend.getId());
		if (dividend == null) {
			throw new EntityNotFoundException();
		}
		dividend.mergeTo(newDividend);
		dividend = stockService.updateDividend(dividend);
		return dividend;
	}	
	
	@Override
	public Class<EditDividendAction> getActionType() {
		return EditDividendAction.class;
	}

	@Override
	public void undo(EditDividendAction action, EditDividendResult result, ExecutionContext context) throws ActionException { }

}
