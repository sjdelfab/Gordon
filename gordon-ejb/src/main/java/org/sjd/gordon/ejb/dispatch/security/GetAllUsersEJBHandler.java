package org.sjd.gordon.ejb.dispatch.security;

import java.util.ArrayList;
import java.util.List;

import org.sjd.gordon.ejb.dispatch.AbstractHandler;
import org.sjd.gordon.ejb.security.UserServiceLocal;
import org.sjd.gordon.model.Group;
import org.sjd.gordon.model.User;
import org.sjd.gordon.shared.security.GetAllUsersAction;
import org.sjd.gordon.shared.security.GetAllUsersResult;
import org.sjd.gordon.shared.security.UserDetail;

import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class GetAllUsersEJBHandler extends AbstractHandler implements ActionHandler<GetAllUsersAction, GetAllUsersResult> {
	
	private UserServiceLocal userService;
	
	@Inject
	public GetAllUsersEJBHandler(UserServiceLocal userService) {
		this.userService = userService;
	}
	
	@Override
	public GetAllUsersResult execute(GetAllUsersAction getDetails, ExecutionContext context) throws ActionException {
		List<User> users = userService.getUsers();
		ArrayList<UserDetail> userDetails = new ArrayList<UserDetail>(users.size());
		for(User user: users) {
			userDetails.add(fromEntity(user));
		}
		return new GetAllUsersResult(userDetails);
	}

	@Override
	public Class<GetAllUsersAction> getActionType() {
		return GetAllUsersAction.class;
	}

	@Override
	public void undo(GetAllUsersAction action, GetAllUsersResult result, ExecutionContext context) throws ActionException { }
	
	public static UserDetail fromEntity(User user) {
		UserDetail userDetail = new UserDetail();
		userDetail.setActive(user.isActive());
		userDetail.setFirstName(user.getFirstName());
		userDetail.setId(user.getId());
		userDetail.setLastName(user.getLastName());
		userDetail.setUID(user.getUsername());
		userDetail.setVersion(user.getVersion());
		StringBuffer roles = new StringBuffer();
		for(int i=0; i < user.getGroups().size(); i++) {
			Group group = user.getGroups().get(i);
			roles.append(group.getName());
			if (i < user.getGroups().size()-1) {
				roles.append(",");
			}
		}
		userDetail.setRoles(roles.toString());
		return userDetail;
	}


}
