package org.sjd.gordon.server.devhandlers;

import org.sjd.gordon.shared.security.EditUser;
import org.sjd.gordon.shared.security.EditUser.EditType;
import org.sjd.gordon.shared.security.EditUserResponse;
import org.sjd.gordon.shared.security.UserDetail;

import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class EditUserDevHandler implements ActionHandler<EditUser,EditUserResponse> {

	@Override
	public EditUserResponse execute(EditUser editEntry, ExecutionContext context) throws ActionException {
		EditUser.EditType editType = editEntry.getEditType();
		UserDetail newUserDetails = editEntry.getUserDetail();
		if (editType == EditType.ADD) {
			Integer currentMaxId = Data.getUserMaxId();
			newUserDetails.setId(currentMaxId+1);
			Data.users.put(currentMaxId + 1, newUserDetails);
		} else {
			UserDetail user = Data.users.get(newUserDetails.getId());
			user.mergeTo(newUserDetails);
		}
		return new EditUserResponse(newUserDetails);
	}

	@Override
	public Class<EditUser> getActionType() {
		return EditUser.class;
	}

	@Override
	public void undo(EditUser action, EditUserResponse result, ExecutionContext context) throws ActionException {
		// Nothing to do here
	}

}
