package org.sjd.gordon.server.devhandlers;

import org.sjd.gordon.shared.security.ChangePasswordResponse;
import org.sjd.gordon.shared.security.ChangeUserPassword;

import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class ChangeUserPasswordDevHandler implements ActionHandler<ChangeUserPassword,ChangePasswordResponse> {

	@Override
	public ChangePasswordResponse execute(ChangeUserPassword action, ExecutionContext context) throws ActionException {
		return new ChangePasswordResponse();
	}

	@Override
	public Class<ChangeUserPassword> getActionType() {
		return ChangeUserPassword.class;
	}

	@Override
	public void undo(ChangeUserPassword action, ChangePasswordResponse response, ExecutionContext context) throws ActionException { }

}
