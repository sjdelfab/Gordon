package org.sjd.gordon.server.devhandlers;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.sjd.gordon.shared.security.Login;
import org.sjd.gordon.shared.security.LoginResponse;

public class DevelopmentAuthenticationHandler implements ActionHandler<Login,LoginResponse> {

	@Override
	public LoginResponse execute(Login login, ExecutionContext context) throws DispatchException {
		LoginResponse response = new LoginResponse(Boolean.TRUE);
		response.setDisplayName("John Doe");
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
