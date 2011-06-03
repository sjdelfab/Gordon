package org.sjd.gordon.server.devhandlers.data;

import java.util.ArrayList;

import org.sjd.gordon.model.TreasuryHeldStock;
import org.sjd.gordon.server.devhandlers.Data;
import org.sjd.gordon.shared.util.EditType;
import org.sjd.gordon.shared.viewer.EditTreasuryHeldStockAction;
import org.sjd.gordon.shared.viewer.EditTreasuryHeldStockResult;

import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class EditTreasuryHeldStockDevHandler implements ActionHandler<EditTreasuryHeldStockAction,EditTreasuryHeldStockResult> {

	@Override
	public EditTreasuryHeldStockResult execute(EditTreasuryHeldStockAction action, ExecutionContext context) throws ActionException {
		EditType editType = action.getEditType();
		TreasuryHeldStock newTreasuryHeldStock = action.getNewTreasuryHeldStock();
		if (editType == EditType.ADD) {
			Long newId = Data.treasuryHeldStockCounter++;
			newTreasuryHeldStock.setId(newId);
			ArrayList<TreasuryHeldStock> heldStock = Data.heldInTreasuryMap.get(action.getStockId());
			if (heldStock == null) {
				heldStock = new ArrayList<TreasuryHeldStock>();
				Data.heldInTreasuryMap.put(action.getStockId(),heldStock);
			}
			heldStock.add(newTreasuryHeldStock);
		} else {
			ArrayList<TreasuryHeldStock> heldStock = Data.heldInTreasuryMap.get(action.getStockId());
			TreasuryHeldStock held = heldStock.get(heldStock.indexOf(newTreasuryHeldStock));
			held.mergeTo(newTreasuryHeldStock);
		}
		return new EditTreasuryHeldStockResult(newTreasuryHeldStock);
	}

	@Override
	public Class<EditTreasuryHeldStockAction> getActionType() {
		return EditTreasuryHeldStockAction.class;
	}

	@Override
	public void undo(EditTreasuryHeldStockAction action, EditTreasuryHeldStockResult result, ExecutionContext context) throws ActionException { }

}
