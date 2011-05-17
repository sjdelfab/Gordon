package org.sjd.gordon.server.devhandlers;

import org.sjd.gordon.shared.security.DeleteUserAction;
import org.sjd.gordon.shared.security.DeleteUserResult;

import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class DeleteUserDevHandler implements ActionHandler<DeleteUserAction,DeleteUserResult> {

	@Override
	public DeleteUserResult execute(DeleteUserAction deleteEntry, ExecutionContext context) throws ActionException {
		Data.users.remove(deleteEntry.getUserId());
		DeleteUserResult response = new DeleteUserResult();
		return response;
	}

	@Override
	public Class<DeleteUserAction> getActionType() {
		return DeleteUserAction.class;
	}

	@Override
	public void undo(DeleteUserAction action, DeleteUserResult result, ExecutionContext context) throws ActionException {
		// Nothing to do here
	}

}
