package org.sjd.gordon.ejb.dispatch.security;

import java.util.ArrayList;
import java.util.List;

import org.sjd.gordon.ejb.dispatch.AbstractHandler;
import org.sjd.gordon.ejb.security.UserServiceLocal;
import org.sjd.gordon.model.Group;
import org.sjd.gordon.model.User;
import org.sjd.gordon.shared.security.EditUser;
import org.sjd.gordon.shared.security.EditUser.EditType;
import org.sjd.gordon.shared.security.EditUserAction;
import org.sjd.gordon.shared.security.EditUserResult;
import org.sjd.gordon.shared.security.UserDetail;
import org.sjd.gordon.util.SHA_256_Util;

import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class EditUserEJBHandler extends AbstractHandler implements ActionHandler<EditUserAction,EditUserResult> {

    private UserServiceLocal userService;
	
	@Inject
	public EditUserEJBHandler(UserServiceLocal userService) {
		this.userService = userService;
	}
	
	@Override
	public EditUserResult execute(EditUserAction editUser, ExecutionContext context) throws ActionException {
		EditUser.EditType editType = editUser.getEditType();
		UserDetail newUserDetails = editUser.getNewUserDetails();
		try {
			if (editType == EditType.ADD) {
				 newUserDetails = add(editUser);
			} else {
				newUserDetails = update(editUser);
			}
		} catch (Throwable cause) {
			throw translateException(cause);
		}
		return new EditUserResult(newUserDetails);
	}

	private UserDetail add(EditUserAction editUser) throws Exception {
		UserDetail newUserDetail = editUser.getNewUserDetails();
		User newUser = new User();
		newUser.setFirstName(newUserDetail.getFirstName());
		newUser.setLastName(newUserDetail.getLastName());
		newUser.setUsername(newUserDetail.getUID());
		newUser.setActive(newUserDetail.isActive());
		newUser.setPassword(SHA_256_Util.hashPassword(new String(newUserDetail.getPassword())));
		String[] roles = newUserDetail.getRoles().split(",");
		if (roles.length > 0) {
			List<Group> groups = new ArrayList<Group>(roles.length);
		    for(String role: roles) {
		    	groups.add(userService.findGroupByName(role.trim()));
		    }
		    newUser.setGroups(groups);
		}
		newUser = userService.createUser(newUser);
		newUserDetail = GetAllUsersEJBHandler.fromEntity(newUser);
		return newUserDetail;
	}

	private UserDetail update(EditUserAction editUser) {
		UserDetail newUserDetail = editUser.getNewUserDetails();
		User user = userService.findUserById(newUserDetail.getId());
		user.setVersion(newUserDetail.getVersion());
		user.setFirstName(newUserDetail.getFirstName());
		user.setLastName(newUserDetail.getLastName());
		user.setActive(newUserDetail.isActive());
		user.getGroups().clear();
		String[] roles = newUserDetail.getRoles().split(",");
		if (roles.length > 0) {
			List<Group> groups = new ArrayList<Group>(roles.length);
		    for(String role: roles) {
		    	groups.add(userService.findGroupByName(role.trim()));
		    }
		    user.setGroups(groups);
		}
		newUserDetail = GetAllUsersEJBHandler.fromEntity(userService.updateUser(user));
		return newUserDetail;
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
