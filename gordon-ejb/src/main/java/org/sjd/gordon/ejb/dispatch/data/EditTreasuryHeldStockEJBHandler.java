package org.sjd.gordon.ejb.dispatch.data;

import org.sjd.gordon.ejb.StockEntityServiceLocal;
import org.sjd.gordon.ejb.dispatch.AbstractHandler;
import org.sjd.gordon.model.TreasuryHeldStock;
import org.sjd.gordon.shared.exceptions.EntityNotFoundException;
import org.sjd.gordon.shared.util.EditType;
import org.sjd.gordon.shared.viewer.EditTreasuryHeldStockAction;
import org.sjd.gordon.shared.viewer.EditTreasuryHeldStockResult;

import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class EditTreasuryHeldStockEJBHandler extends AbstractHandler implements ActionHandler<EditTreasuryHeldStockAction,EditTreasuryHeldStockResult> {

	@Inject
	private StockEntityServiceLocal stockService;
	
	@Override
	public EditTreasuryHeldStockResult execute(EditTreasuryHeldStockAction action, ExecutionContext context) throws ActionException {
		EditType editType = action.getEditType();
		TreasuryHeldStock newTreasuryHeldStock = action.getNewTreasuryHeldStock();
		try {
			if (editType == EditType.ADD) {
				newTreasuryHeldStock = add(action.getStockId(),newTreasuryHeldStock);
			} else {
				newTreasuryHeldStock = update(newTreasuryHeldStock);
			}
		} catch (Throwable cause) {
			throw translateException(cause);
		}
		return new EditTreasuryHeldStockResult(newTreasuryHeldStock);
	}

	private TreasuryHeldStock add(Long stockId, TreasuryHeldStock stockHeld) throws Exception {
		stockHeld = stockService.createTreasuryHeldStock(stockId,stockHeld);
		return stockHeld;
	}

	private TreasuryHeldStock update(TreasuryHeldStock newStockHeld) throws Exception {
		TreasuryHeldStock stockHeld = stockService.findTreasuryHeldStockById(newStockHeld.getId());
		if (stockHeld == null) {
			throw new EntityNotFoundException();
		}
		stockHeld.mergeTo(newStockHeld);
		stockHeld = stockService.updateTreasuryHeldStock(stockHeld);
		return stockHeld;
	}
	
	@Override
	public Class<EditTreasuryHeldStockAction> getActionType() {
		return EditTreasuryHeldStockAction.class;
	}

	@Override
	public void undo(EditTreasuryHeldStockAction action, EditTreasuryHeldStockResult result, ExecutionContext context) throws ActionException { }

}
