package org.sjd.gordon.server.devhandlers;

import org.sjd.gordon.shared.security.GetCurrentUserAction;
import org.sjd.gordon.shared.security.GetCurrentUserResult;

import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class CurrentUserDevHandler implements ActionHandler<GetCurrentUserAction,GetCurrentUserResult> {

	@Override
	public GetCurrentUserResult execute(GetCurrentUserAction action, ExecutionContext context) throws ActionException {
		return new GetCurrentUserResult(Data.users.get(Integer.valueOf(1)));
	}

	@Override
	public Class<GetCurrentUserAction> getActionType() {
		return GetCurrentUserAction.class;
	}

	@Override
	public void undo(GetCurrentUserAction action, GetCurrentUserResult response, ExecutionContext context) throws ActionException { }

}
