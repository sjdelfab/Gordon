package org.sjd.gordon.ejb.dispatch.security;

import java.util.ArrayList;
import java.util.List;

import org.sjd.gordon.ejb.dispatch.AbstractHandler;
import org.sjd.gordon.ejb.security.UserService;
import org.sjd.gordon.model.Group;
import org.sjd.gordon.model.User;
import org.sjd.gordon.shared.security.EditUser;
import org.sjd.gordon.shared.security.EditUser.EditType;
import org.sjd.gordon.shared.security.EditUserResponse;
import org.sjd.gordon.shared.security.UserDetail;
import org.sjd.gordon.util.SHA_256_Util;

import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class EditUserEJBHandler extends AbstractHandler implements ActionHandler<EditUser,EditUserResponse> {

    private UserService userService;
	
	@Inject
	public EditUserEJBHandler(UserService userService) {
		this.userService = userService;
	}
	
	@Override
	public EditUserResponse execute(EditUser editUser, ExecutionContext context) throws ActionException {
		EditUser.EditType editType = editUser.getEditType();
		UserDetail newUserDetails = editUser.getUserDetail();
		try {
			if (editType == EditType.ADD) {
				 newUserDetails = add(editUser);
			} else {
				newUserDetails = update(editUser);
			}
		} catch (Throwable cause) {
			throw translateException(cause);
		}
		return new EditUserResponse(newUserDetails);
	}

	private UserDetail add(EditUser editUser) throws Exception {
		UserDetail newUserDetail = editUser.getUserDetail();
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

	private UserDetail update(EditUser editUser) {
		UserDetail newUserDetail = editUser.getUserDetail();
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
	public Class<EditUser> getActionType() {
		return EditUser.class;
	}

	@Override
	public void undo(EditUser action, EditUserResponse result, ExecutionContext context) throws ActionException {
		// Nothing to do here
	}


}
