package org.sjd.gordon.ejb.dispatch.security;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.xml.crypto.Data;

import org.sjd.gordon.ejb.dispatch.AbstractHandler;
import org.sjd.gordon.ejb.security.UserService;
import org.sjd.gordon.model.User;
import org.sjd.gordon.shared.exceptions.UnauthorisedAccessException;
import org.sjd.gordon.shared.security.GetCurrentUser;
import org.sjd.gordon.shared.security.GotCurrentUserResponse;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class CurrentUserEJBHandler extends AbstractHandler implements ActionHandler<GetCurrentUser,GotCurrentUserResponse> {

	private Provider<HttpServletRequest> requestProvider;
	private UserService userService;

	@Inject
	public CurrentUserEJBHandler(Provider<HttpServletRequest> requestProvider, UserService userService) {
		this.requestProvider = requestProvider;
		this.userService = userService;
	}
	
	@Override
	public GotCurrentUserResponse execute(GetCurrentUser action, ExecutionContext context) throws ActionException {
		HttpServletRequest request = requestProvider.get(); 
		Principal currentUser = request.getUserPrincipal();
		if (currentUser != null) {
			String userName = currentUser.getName();
			User user = userService.getUser(userName);
			if (user == null) {
				throw new UnauthorisedAccessException();
			}
			return new GotCurrentUserResponse(GetAllUsersEJBHandler.fromEntity(user));			
		} else {
			throw new UnauthorisedAccessException();
		}
	}

	@Override
	public Class<GetCurrentUser> getActionType() {
		return GetCurrentUser.class;
	}

	@Override
	public void undo(GetCurrentUser action, GotCurrentUserResponse response, ExecutionContext context) throws ActionException { }

}
