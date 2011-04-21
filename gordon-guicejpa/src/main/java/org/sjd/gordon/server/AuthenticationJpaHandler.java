package org.sjd.gordon.server;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.sjd.gordon.model.User;
import org.sjd.gordon.shared.security.Login;
import org.sjd.gordon.shared.security.LoginResponse;

import com.google.inject.Inject;

public class AuthenticationJpaHandler implements ActionHandler<Login,LoginResponse> {

	@Inject GuiceJPAInitializer initializer;
	@Inject EntityManager em;
	
	@Override
	public LoginResponse execute(Login login, ExecutionContext context) throws DispatchException {
		String getUser = "SELECT u FROM User u WHERE u.username = :uid";
    	TypedQuery<User> query = em.createQuery(getUser, User.class);
    	query.setParameter("uid", login.getUserName());
    	User user = query.getSingleResult();
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
	public void rollback(Login login, LoginResponse loginResponse, ExecutionContext context) throws DispatchException {
		// Nothing to do here
	}

}
