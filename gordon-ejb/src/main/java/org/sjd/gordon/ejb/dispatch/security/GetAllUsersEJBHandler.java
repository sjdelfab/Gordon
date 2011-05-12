package org.sjd.gordon.ejb.dispatch.security;

import java.util.ArrayList;
import java.util.List;

import org.sjd.gordon.ejb.dispatch.AbstractHandler;
import org.sjd.gordon.ejb.security.UserService;
import org.sjd.gordon.model.Group;
import org.sjd.gordon.model.User;
import org.sjd.gordon.shared.security.GetAllUserDetails;
import org.sjd.gordon.shared.security.GotAllUserDetails;
import org.sjd.gordon.shared.security.UserDetail;

import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class GetAllUsersEJBHandler extends AbstractHandler implements ActionHandler<GetAllUserDetails, GotAllUserDetails> {
	
	private UserService userService;
	
	@Inject
	public GetAllUsersEJBHandler(UserService userService) {
		this.userService = userService;
	}
	
	@Override
	public GotAllUserDetails execute(GetAllUserDetails getDetails, ExecutionContext context) throws ActionException {
		List<User> users = userService.getUsers();
		ArrayList<UserDetail> userDetails = new ArrayList<UserDetail>(users.size());
		for(User user: users) {
			userDetails.add(fromEntity(user));
		}
		return new GotAllUserDetails(userDetails);
	}

	@Override
	public Class<GetAllUserDetails> getActionType() {
		return GetAllUserDetails.class;
	}

	@Override
	public void undo(GetAllUserDetails action, GotAllUserDetails result, ExecutionContext context) throws ActionException { }
	
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
