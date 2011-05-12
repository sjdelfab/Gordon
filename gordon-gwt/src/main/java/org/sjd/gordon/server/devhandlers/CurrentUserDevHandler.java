package org.sjd.gordon.server.devhandlers;

import org.sjd.gordon.shared.security.GetCurrentUser;
import org.sjd.gordon.shared.security.GotCurrentUserResponse;

import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class CurrentUserDevHandler implements ActionHandler<GetCurrentUser,GotCurrentUserResponse> {

	@Override
	public GotCurrentUserResponse execute(GetCurrentUser action, ExecutionContext context) throws ActionException {
		return new GotCurrentUserResponse(Data.users.get(Integer.valueOf(1)));
	}

	@Override
	public Class<GetCurrentUser> getActionType() {
		return GetCurrentUser.class;
	}

	@Override
	public void undo(GetCurrentUser action, GotCurrentUserResponse response, ExecutionContext context) throws ActionException { }

}
