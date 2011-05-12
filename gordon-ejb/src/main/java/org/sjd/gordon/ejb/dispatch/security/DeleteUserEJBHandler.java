package org.sjd.gordon.ejb.dispatch.security;

import org.sjd.gordon.ejb.dispatch.AbstractHandler;
import org.sjd.gordon.ejb.security.UserService;
import org.sjd.gordon.model.User;
import org.sjd.gordon.shared.exceptions.EntityNotFoundException;
import org.sjd.gordon.shared.security.DeleteUser;
import org.sjd.gordon.shared.security.DeleteUserResponse;

import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class DeleteUserEJBHandler extends AbstractHandler implements ActionHandler<DeleteUser,DeleteUserResponse> {

    private UserService userService;
	
	@Inject
	public DeleteUserEJBHandler(UserService userService) {
		this.userService = userService;
	}
	
	@Override
	public DeleteUserResponse execute(DeleteUser deleteEntry, ExecutionContext context) throws ActionException {
		try {
			User user = userService.findUserById(deleteEntry.getUserId());
			if (user == null) {
				throw new EntityNotFoundException();
			}
			userService.delete(user);
			return new DeleteUserResponse();
		} catch (Throwable cause) {
			if (cause instanceof EntityNotFoundException) {
				throw (EntityNotFoundException)cause;
			}
			throw translateException(cause);
		}
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
