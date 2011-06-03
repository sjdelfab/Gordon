package org.sjd.gordon.server.devhandlers.data;

import java.util.ArrayList;

import org.sjd.gordon.model.TreasuryHeldStock;
import org.sjd.gordon.server.devhandlers.Data;
import org.sjd.gordon.shared.viewer.DeleteTreasuryHeldStockAction;
import org.sjd.gordon.shared.viewer.DeleteTreasuryHeldStockResult;

import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class DeleteTreasuryHeldStockDevHandler implements ActionHandler<DeleteTreasuryHeldStockAction,DeleteTreasuryHeldStockResult> {

	@Override
	public DeleteTreasuryHeldStockResult execute(DeleteTreasuryHeldStockAction action, ExecutionContext context) throws ActionException {
		for(Long stockId: Data.heldInTreasuryMap.keySet()) {
			ArrayList<TreasuryHeldStock> heldStockHistory = Data.heldInTreasuryMap.get(stockId);
			if (heldStockHistory != null) {
				int index = -1;
				for(int i=0; i < heldStockHistory.size(); i++) {
					TreasuryHeldStock heldStock = heldStockHistory.get(i);
					if (heldStock.getId() == action.getTreasuryHeldStock().getId()) {
						index = -1;
					}
				}
				if (index != -1) {
				    heldStockHistory.remove(index);
				    break;
				}
			}
		}
		return new DeleteTreasuryHeldStockResult();
	}

	@Override
	public Class<DeleteTreasuryHeldStockAction> getActionType() {
		return DeleteTreasuryHeldStockAction.class;
	}

	@Override
	public void undo(DeleteTreasuryHeldStockAction action, DeleteTreasuryHeldStockResult result, ExecutionContext context) throws ActionException { }

}
