package org.sjd.gordon.server.devhandlers.data;

import java.util.ArrayList;

import org.sjd.gordon.model.Dividend;
import org.sjd.gordon.server.devhandlers.Data;
import org.sjd.gordon.shared.viewer.DeleteDividendAction;
import org.sjd.gordon.shared.viewer.DeleteDividendResult;

import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class DeleteDividendDevHandler implements ActionHandler<DeleteDividendAction,DeleteDividendResult> {

	@Override
	public DeleteDividendResult execute(DeleteDividendAction action, ExecutionContext context) throws ActionException {
		ArrayList<Dividend> dividends = Data.dividendMap.get(action.getDividend().getStockId());
		if (dividends != null) {
			dividends.remove(action.getDividend());
		}
		return new DeleteDividendResult();
	}

	@Override
	public Class<DeleteDividendAction> getActionType() {
		return DeleteDividendAction.class;
	}

	@Override
	public void undo(DeleteDividendAction action, DeleteDividendResult result, ExecutionContext context) throws ActionException { }

}
