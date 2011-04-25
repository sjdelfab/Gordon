package org.sjd.gordon.ejb.dispatch;

import javax.naming.Context;
import javax.naming.NamingException;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.ActionException;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.sjd.gordon.ejb.UserEJB;
import org.sjd.gordon.model.User;
import org.sjd.gordon.shared.security.Login;
import org.sjd.gordon.shared.security.LoginResponse;

import com.google.inject.Inject;

public class AuthenticationEJBHandler implements ActionHandler<Login, LoginResponse> {

	@Inject
	private Context ctx;

	@Override
	public LoginResponse execute(Login login, ExecutionContext context) throws DispatchException {
		UserEJB userEjb = null;
		try {
			userEjb = (UserEJB) ctx.lookup("java:global/gordon-gwt-1.0/UserEJB!org.sjd.gordon.ejb.UserEJB");
		} catch (NamingException e) {
			throw new ActionException(e);
		}
		if (userEjb == null) {
			throw new RuntimeException("EJB not found.");
		}

		User user = userEjb.getUser(login.getUserName());
		LoginResponse response = null;
		if (user == null) {
			response = new LoginResponse(Boolean.FALSE);
		} else if (user.getPassword().equals(login.getPassword())) {
			response = new LoginResponse(Boolean.TRUE);
			response.setDisplayName(user.getFirstName() + " " + user.getLastName());
		} else {
			response = new LoginResponse(Boolean.FALSE);
		}
		return response;
	}

	@Override
	public Class<Login> getActionType() {
		return Login.class;
	}

	@Override
	public void rollback(Login arg0, LoginResponse arg1, ExecutionContext arg2) throws DispatchException {

	}

}
