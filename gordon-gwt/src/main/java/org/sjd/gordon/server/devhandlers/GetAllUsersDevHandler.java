package org.sjd.gordon.server.devhandlers;

import java.util.ArrayList;

import org.sjd.gordon.shared.security.GetAllUserDetails;
import org.sjd.gordon.shared.security.GotAllUserDetails;
import org.sjd.gordon.shared.security.UserDetail;

import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class GetAllUsersDevHandler implements ActionHandler<GetAllUserDetails, GotAllUserDetails> {
	
	@Override
	public GotAllUserDetails execute(GetAllUserDetails getDetails, ExecutionContext context) throws ActionException {
		ArrayList<UserDetail> details = new ArrayList<UserDetail>(Data.users.values());
		return new GotAllUserDetails(details);
	}

	@Override
	public Class<GetAllUserDetails> getActionType() {
		return GetAllUserDetails.class;
	}

	@Override
	public void undo(GetAllUserDetails action, GotAllUserDetails result, ExecutionContext context) throws ActionException { }

}
