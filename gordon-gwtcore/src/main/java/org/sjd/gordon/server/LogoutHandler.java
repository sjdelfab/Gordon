package org.sjd.gordon.server;

import javax.servlet.http.HttpServletRequest;

import org.sjd.gordon.shared.security.Logout;
import org.sjd.gordon.shared.security.LogoutResponse;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class LogoutHandler implements ActionHandler<Logout, LogoutResponse> {

	private Provider<HttpServletRequest> requestProvider;

	@Inject
	public LogoutHandler(Provider<HttpServletRequest> requestProvider) {
		this.requestProvider = requestProvider;
	}

	@Override
	public LogoutResponse execute(Logout action, ExecutionContext context) throws ActionException {
		HttpServletRequest request = requestProvider.get(); 
		request.getSession().invalidate();
		return new LogoutResponse();
	}

	@Override
	public Class<Logout> getActionType() {
		return Logout.class;
	}

	@Override
	public void undo(Logout action, LogoutResponse result, ExecutionContext context) throws ActionException { }

}
