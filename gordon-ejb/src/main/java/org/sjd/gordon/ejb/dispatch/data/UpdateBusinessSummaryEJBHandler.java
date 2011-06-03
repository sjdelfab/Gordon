package org.sjd.gordon.ejb.dispatch.data;

import org.sjd.gordon.ejb.StockEntityServiceLocal;
import org.sjd.gordon.model.BusinessSummary;
import org.sjd.gordon.shared.viewer.UpdateBusinessSummaryAction;
import org.sjd.gordon.shared.viewer.UpdateBusinessSummaryResult;

import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class UpdateBusinessSummaryEJBHandler implements ActionHandler<UpdateBusinessSummaryAction,UpdateBusinessSummaryResult> {

	@Inject
	private StockEntityServiceLocal stockEntityService;
	
	@Override
	public UpdateBusinessSummaryResult execute(UpdateBusinessSummaryAction action, ExecutionContext context) throws ActionException {
		BusinessSummary newBusinessSummary = action.getNewBusinessSummary();
		if (newBusinessSummary.getId() == null) {
			newBusinessSummary = stockEntityService.addBusinessSummary(newBusinessSummary);
		} else {
			newBusinessSummary = stockEntityService.updateBusinessSummary(newBusinessSummary);
		}
		return new UpdateBusinessSummaryResult(newBusinessSummary);
	}

	@Override
	public Class<UpdateBusinessSummaryAction> getActionType() {
		return UpdateBusinessSummaryAction.class;
	}

	@Override
	public void undo(UpdateBusinessSummaryAction action, UpdateBusinessSummaryResult result, ExecutionContext context)	throws ActionException { }

}
