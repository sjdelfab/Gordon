package org.sjd.gordon.ejb.dispatch.security;

import org.sjd.gordon.ejb.dispatch.AbstractHandler;
import org.sjd.gordon.ejb.security.UserService;
import org.sjd.gordon.model.User;
import org.sjd.gordon.shared.exceptions.EntityNotFoundException;
import org.sjd.gordon.shared.security.DeleteUserAction;
import org.sjd.gordon.shared.security.DeleteUserResult;

import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class DeleteUserEJBHandler extends AbstractHandler implements ActionHandler<DeleteUserAction,DeleteUserResult> {

    private UserService userService;
	
	@Inject
	public DeleteUserEJBHandler(UserService userService) {
		this.userService = userService;
	}
	
	@Override
	public DeleteUserResult execute(DeleteUserAction deleteEntry, ExecutionContext context) throws ActionException {
		try {
			User user = userService.findUserById(deleteEntry.getUserId());
			if (user == null) {
				throw new EntityNotFoundException();
			}
			userService.delete(user);
			return new DeleteUserResult();
		} catch (Throwable cause) {
			if (cause instanceof EntityNotFoundException) {
				throw (EntityNotFoundException)cause;
			}
			throw translateException(cause);
		}
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
