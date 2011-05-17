package org.sjd.gordon.server.devhandlers;

import java.util.ArrayList;

import org.sjd.gordon.shared.security.GetAllUsersAction;
import org.sjd.gordon.shared.security.GetAllUsersResult;
import org.sjd.gordon.shared.security.UserDetail;

import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class GetAllUsersDevHandler implements ActionHandler<GetAllUsersAction, GetAllUsersResult> {
	
	@Override
	public GetAllUsersResult execute(GetAllUsersAction getDetails, ExecutionContext context) throws ActionException {
		ArrayList<UserDetail> details = new ArrayList<UserDetail>(Data.users.values());
		return new GetAllUsersResult(details);
	}

	@Override
	public Class<GetAllUsersAction> getActionType() {
		return GetAllUsersAction.class;
	}

	@Override
	public void undo(GetAllUsersAction action, GetAllUsersResult result, ExecutionContext context) throws ActionException { }

}