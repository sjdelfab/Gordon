package org.sjd.gordon.server.devhandlers;

import org.sjd.gordon.shared.security.Login;
import org.sjd.gordon.shared.security.LoginResponse;

import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class DevelopmentAuthenticationHandler implements ActionHandler<Login,LoginResponse> {

	@Override
	public LoginResponse execute(Login login, ExecutionContext context) throws ActionException {
		LoginResponse response = new LoginResponse(Boolean.TRUE);
		response.setDisplayName("John Doe");
		return response;
	}

	@Override
	public Class<Login> getActionType() {
		return Login.class;
	}

	@Override
	public void undo(Login action, LoginResponse result, ExecutionContext context) throws ActionException {
		// Nothing to do here
	}

}
