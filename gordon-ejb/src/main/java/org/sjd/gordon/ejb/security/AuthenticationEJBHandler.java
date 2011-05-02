package org.sjd.gordon.ejb.security;

import org.sjd.gordon.model.User;
import org.sjd.gordon.shared.security.Login;
import org.sjd.gordon.shared.security.LoginResponse;

import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class AuthenticationEJBHandler implements ActionHandler<Login, LoginResponse> {

	@Inject
	private UserService userEjb;

	@Override
	public LoginResponse execute(Login login, ExecutionContext context) throws ActionException {
		User user = userEjb.getUser(login.getUserName());
		LoginResponse response = null;
		if (user == null) {
			response = new LoginResponse(Boolean.FALSE);
		} else if (user.getPassword().equals(login.getPassword())) {
			response = new LoginResponse(Boolean.TRUE);
			response.setDisplayName(user.getFirstName() + " " + user.getLastName());
		} else {
			response = new LoginResponse(Boolean.FALSE);
		}
		return response;
	}

	@Override
	public Class<Login> getActionType() {
		return Login.class;
	}

	@Override
	public void undo(Login action, LoginResponse result, ExecutionContext arg2) throws ActionException { }

}
