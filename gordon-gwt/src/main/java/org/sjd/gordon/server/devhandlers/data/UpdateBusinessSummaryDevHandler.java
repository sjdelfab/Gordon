package org.sjd.gordon.server.devhandlers.data;

import org.sjd.gordon.shared.viewer.UpdateBusinessSummaryAction;
import org.sjd.gordon.shared.viewer.UpdateBusinessSummaryResult;

import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class UpdateBusinessSummaryDevHandler implements ActionHandler<UpdateBusinessSummaryAction,UpdateBusinessSummaryResult> {

	@Override
	public UpdateBusinessSummaryResult execute(UpdateBusinessSummaryAction action, ExecutionContext context)	throws ActionException {
		return new UpdateBusinessSummaryResult(action.getNewBusinessSummary());
	}

	@Override
	public Class<UpdateBusinessSummaryAction> getActionType() {
		return UpdateBusinessSummaryAction.class;
	}

	@Override
	public void undo(UpdateBusinessSummaryAction action, UpdateBusinessSummaryResult result, ExecutionContext context)	throws ActionException { }

}
