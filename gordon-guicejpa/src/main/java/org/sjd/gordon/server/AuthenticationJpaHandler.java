package org.sjd.gordon.server;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.sjd.gordon.model.User;
import org.sjd.gordon.shared.security.Login;
import org.sjd.gordon.shared.security.LoginResponse;

import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class AuthenticationJpaHandler implements ActionHandler<Login,LoginResponse> {

	@Inject GuiceJPAInitializer initializer;
	@Inject EntityManager em;
	
	@Override
	public LoginResponse execute(Login login, ExecutionContext context) throws ActionException {
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
	public void undo(Login action, LoginResponse result, ExecutionContext context) throws ActionException {
		// Nothing to do here
	}

}
