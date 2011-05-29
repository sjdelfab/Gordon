package org.sjd.gordon.server.devhandlers.security;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.sjd.gordon.server.devhandlers.Data;
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
		Collections.sort(details, new Comparator<UserDetail>() {
			@Override
			public int compare(UserDetail o1, UserDetail o2) {
				return o1.getLastName().compareTo(o2.getLastName());
			}
		});
		return new GetAllUsersResult(details);
	}

	@Override
	public Class<GetAllUsersAction> getActionType() {
		return GetAllUsersAction.class;
	}

	@Override
	public void undo(GetAllUsersAction action, GetAllUsersResult result, ExecutionContext context) throws ActionException { }

}
