package org.sjd.gordon.server.devhandlers.data;

import java.util.ArrayList;

import org.sjd.gordon.model.Dividend;
import org.sjd.gordon.server.devhandlers.Data;
import org.sjd.gordon.shared.util.EditType;
import org.sjd.gordon.shared.viewer.EditDividendAction;
import org.sjd.gordon.shared.viewer.EditDividendResult;

import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class EditDividendDevHandler implements ActionHandler<EditDividendAction,EditDividendResult> {

	@Override
	public EditDividendResult execute(EditDividendAction action, ExecutionContext context) throws ActionException {
		EditType editType = action.getEditType();
		Dividend newDividend = action.getNewDividend();
		if (editType == EditType.ADD) {
			Long newId = Data.dividendCounter++;
			newDividend.setId(newId);
			ArrayList<Dividend> dividends = Data.dividendMap.get(action.getStockId());
			if (dividends == null) {
				dividends = new ArrayList<Dividend>();
				Data.dividendMap.put(action.getStockId(),dividends);
			}
			dividends.add(newDividend);
		} else {
			ArrayList<Dividend> dividends = Data.dividendMap.get(action.getStockId());
			Dividend dividend = dividends.get(dividends.indexOf(newDividend));
			dividend.mergeTo(newDividend);
		}
		return new EditDividendResult(newDividend);
	}

	@Override
	public Class<EditDividendAction> getActionType() {
		return EditDividendAction.class;
	}

	@Override
	public void undo(EditDividendAction action, EditDividendResult result, ExecutionContext context) throws ActionException { }

}
