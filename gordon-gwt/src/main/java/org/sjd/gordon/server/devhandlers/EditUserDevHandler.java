package org.sjd.gordon.server.devhandlers;

import org.sjd.gordon.shared.security.EditUser;
import org.sjd.gordon.shared.security.EditUser.EditType;
import org.sjd.gordon.shared.security.EditUserAction;
import org.sjd.gordon.shared.security.EditUserResult;
import org.sjd.gordon.shared.security.UserDetail;

import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class EditUserDevHandler implements ActionHandler<EditUserAction,EditUserResult> {

	@Override
	public EditUserResult execute(EditUserAction editEntry, ExecutionContext context) throws ActionException {
		EditUser.EditType editType = editEntry.getEditType();
		UserDetail newUserDetails = editEntry.getNewUserDetails();
		if (editType == EditType.ADD) {
			Integer currentMaxId = Data.getUserMaxId();
			newUserDetails.setId(currentMaxId+1);
			Data.users.put(currentMaxId + 1, newUserDetails);
		} else {
			UserDetail user = Data.users.get(newUserDetails.getId());
			user.mergeTo(newUserDetails);
		}
		return new EditUserResult(newUserDetails);
	}

	@Override
	public Class<EditUserAction> getActionType() {
		return EditUserAction.class;
	}

	@Override
	public void undo(EditUserAction action, EditUserResult result, ExecutionContext context) throws ActionException {
		// Nothing to do here
	}

}
