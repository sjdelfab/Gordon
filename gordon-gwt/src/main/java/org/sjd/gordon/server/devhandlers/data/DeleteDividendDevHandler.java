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
		for(Long stockId: Data.dividendMap.keySet()) {
			ArrayList<Dividend> dividends = Data.dividendMap.get(stockId);
			if (dividends != null) {
				int index = -1;
				for(int i=0; i < dividends.size(); i++) {
					Dividend dividend = dividends.get(i);
					if (dividend.getId() == action.getDividend().getId()) {
						index = -1;
					}
				}
				if (index != -1) {
				    dividends.remove(index);
				    break;
				}
			}
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
