package org.sjd.gordon.ejb.dispatch.security;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.sjd.gordon.ejb.dispatch.AbstractHandler;
import org.sjd.gordon.ejb.security.UserServiceLocal;
import org.sjd.gordon.model.User;
import org.sjd.gordon.shared.exceptions.UnauthorisedAccessException;
import org.sjd.gordon.shared.security.GetCurrentUserAction;
import org.sjd.gordon.shared.security.GetCurrentUserResult;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class CurrentUserEJBHandler extends AbstractHandler implements ActionHandler<GetCurrentUserAction,GetCurrentUserResult> {

	private Provider<HttpServletRequest> requestProvider;
	private UserServiceLocal userService;

	@Inject
	public CurrentUserEJBHandler(Provider<HttpServletRequest> requestProvider, UserServiceLocal userService) {
		this.requestProvider = requestProvider;
		this.userService = userService;
	}
	
	@Override
	public GetCurrentUserResult execute(GetCurrentUserAction action, ExecutionContext context) throws ActionException {
		HttpServletRequest request = requestProvider.get(); 
		Principal currentUser = request.getUserPrincipal();
		if (currentUser != null) {
			String userName = currentUser.getName();
			User user = userService.getUser(userName);
			if (user == null) {
				throw new UnauthorisedAccessException();
			}
			return new GetCurrentUserResult(GetAllUsersEJBHandler.fromEntity(user));			
		} else {
			throw new UnauthorisedAccessException();
		}
	}

	@Override
	public Class<GetCurrentUserAction> getActionType() {
		return GetCurrentUserAction.class;
	}

	@Override
	public void undo(GetCurrentUserAction action, GetCurrentUserResult response, ExecutionContext context) throws ActionException { }

}
