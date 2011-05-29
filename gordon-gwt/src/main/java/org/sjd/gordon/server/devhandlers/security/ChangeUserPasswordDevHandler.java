package org.sjd.gordon.server.devhandlers.security;

import org.sjd.gordon.shared.security.ChangeUserPasswordAction;
import org.sjd.gordon.shared.security.ChangeUserPasswordResult;

import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class ChangeUserPasswordDevHandler implements ActionHandler<ChangeUserPasswordAction,ChangeUserPasswordResult> {

	@Override
	public ChangeUserPasswordResult execute(ChangeUserPasswordAction action, ExecutionContext context) throws ActionException {
		return new ChangeUserPasswordResult();
	}

	@Override
	public Class<ChangeUserPasswordAction> getActionType() {
		return ChangeUserPasswordAction.class;
	}

	@Override
	public void undo(ChangeUserPasswordAction action, ChangeUserPasswordResult response, ExecutionContext context) throws ActionException { }

}
