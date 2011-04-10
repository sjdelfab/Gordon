package org.sjd.gordon.server.security;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.sjd.gordon.shared.security.Login;
import org.sjd.gordon.shared.security.LoginResponse;

public class AuthenticationHandler implements ActionHandler<Login,LoginResponse> {

	@Override
	public LoginResponse execute(Login login, ExecutionContext context) throws DispatchException {
		LoginResponse response = new LoginResponse(Boolean.TRUE);
		return response;
	}

	@Override
	public Class<Login> getActionType() {
		return Login.class;
	}

	@Override
	public void rollback(Login login, LoginResponse loginResponse, ExecutionContext context) throws DispatchException {
		// Nothing to do here
	}

}
