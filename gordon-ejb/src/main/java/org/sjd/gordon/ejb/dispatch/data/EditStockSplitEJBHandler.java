package org.sjd.gordon.ejb.dispatch.data;

import org.sjd.gordon.ejb.StockEntityServiceLocal;
import org.sjd.gordon.ejb.dispatch.AbstractHandler;
import org.sjd.gordon.model.StockSplit;
import org.sjd.gordon.shared.exceptions.EntityNotFoundException;
import org.sjd.gordon.shared.util.EditType;
import org.sjd.gordon.shared.viewer.EditStockSplitAction;
import org.sjd.gordon.shared.viewer.EditStockSplitResult;

import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class EditStockSplitEJBHandler extends AbstractHandler implements ActionHandler<EditStockSplitAction,EditStockSplitResult> {

	@Inject
	private StockEntityServiceLocal stockService;
	
	@Override
	public EditStockSplitResult execute(EditStockSplitAction action, ExecutionContext context) throws ActionException {
		EditType editType = action.getEditType();
		StockSplit newSplit = action.getNewStockSplit();
		try {
			if (editType == EditType.ADD) {
				newSplit = add(action.getStockId(),newSplit);
			} else {
				newSplit = update(newSplit);
			}
		} catch (Throwable cause) {
			throw translateException(cause);
		}
		return new EditStockSplitResult(newSplit);
	}

	private StockSplit add(Long stockId, StockSplit newSplit) throws Exception {
		newSplit = stockService.createStockSplit(stockId,newSplit);
		return newSplit;
	}

	private StockSplit update(StockSplit newSplit) throws Exception {
		StockSplit stockSplit = stockService.findStockSplitById(newSplit.getId());
		if (stockSplit == null) {
			throw new EntityNotFoundException();
		}
		stockSplit.mergeTo(newSplit);
		stockSplit = stockService.updateStockSplit(stockSplit);
		return stockSplit;
	}	
	
	@Override
	public Class<EditStockSplitAction> getActionType() {
		return EditStockSplitAction.class;
	}

	@Override
	public void undo(EditStockSplitAction action, EditStockSplitResult result, ExecutionContext context) throws ActionException { }

}
