package org.sjd.gordon.ejb.dispatch.security;

import org.sjd.gordon.ejb.security.UserServiceLocal;
import org.sjd.gordon.model.User;
import org.sjd.gordon.shared.security.ChangeUserPasswordAction;
import org.sjd.gordon.shared.security.ChangeUserPasswordResult;
import org.sjd.gordon.util.SHA_256_Util;

import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class ChangeUserPasswordEJBHandler implements ActionHandler<ChangeUserPasswordAction,ChangeUserPasswordResult> {

	private UserServiceLocal userService;
	
	@Inject
	public ChangeUserPasswordEJBHandler(UserServiceLocal userService) {
		this.userService = userService;
	}
	
	@Override
	public ChangeUserPasswordResult execute(ChangeUserPasswordAction action, ExecutionContext context) throws ActionException {
		Integer userId = action.getUserId();
		User user = userService.findUserById(userId);
		try {
			user.setPassword(SHA_256_Util.hashPassword(action.getNewPassword()));
		} catch (Exception e) {
			throw new ActionException("Unable to encrypt the password",e);
		}
		userService.updateUser(user);
		return new ChangeUserPasswordResult();
	}

	@Override
	public Class<ChangeUserPasswordAction> getActionType() {
		return ChangeUserPasswordAction.class;
	}

	@Override
	public void undo(ChangeUserPasswordAction action, ChangeUserPasswordResult response, ExecutionContext context) throws ActionException { }

}
