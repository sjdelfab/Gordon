package org.sjd.gordon.ejb.dispatch.security;

import org.sjd.gordon.ejb.security.UserService;
import org.sjd.gordon.model.User;
import org.sjd.gordon.shared.security.ChangePasswordResponse;
import org.sjd.gordon.shared.security.ChangeUserPassword;
import org.sjd.gordon.util.SHA_256_Util;

import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class ChangeUserPasswordEJBHandler implements ActionHandler<ChangeUserPassword,ChangePasswordResponse> {

	private UserService userService;
	
	@Inject
	public ChangeUserPasswordEJBHandler(UserService userService) {
		this.userService = userService;
	}
	
	@Override
	public ChangePasswordResponse execute(ChangeUserPassword action, ExecutionContext context) throws ActionException {
		Integer userId = action.getUserId();
		User user = userService.findUserById(userId);
		try {
			user.setPassword(SHA_256_Util.hashPassword(new String(action.getNewPassword())));
		} catch (Exception e) {
			throw new ActionException("Unable to encrypt the password",e);
		}
		userService.updateUser(user);
		return new ChangePasswordResponse();
	}

	@Override
	public Class<ChangeUserPassword> getActionType() {
		return ChangeUserPassword.class;
	}

	@Override
	public void undo(ChangeUserPassword action, ChangePasswordResponse response, ExecutionContext context) throws ActionException { }

}
