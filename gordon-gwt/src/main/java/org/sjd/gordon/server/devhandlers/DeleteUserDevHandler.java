package org.sjd.gordon.server.devhandlers;

import org.sjd.gordon.shared.security.DeleteUser;
import org.sjd.gordon.shared.security.DeleteUserResponse;

import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class DeleteUserDevHandler implements ActionHandler<DeleteUser,DeleteUserResponse> {

	@Override
	public DeleteUserResponse execute(DeleteUser deleteEntry, ExecutionContext context) throws ActionException {
		Data.users.remove(deleteEntry.getUserId());
		DeleteUserResponse response = new DeleteUserResponse();
		return response;
	}

	@Override
	public Class<DeleteUser> getActionType() {
		return DeleteUser.class;
	}

	@Override
	public void undo(DeleteUser action, DeleteUserResponse result, ExecutionContext context) throws ActionException {
		// Nothing to do here
	}

}
