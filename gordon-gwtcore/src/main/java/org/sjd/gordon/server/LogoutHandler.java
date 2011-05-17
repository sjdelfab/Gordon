package org.sjd.gordon.server;

import javax.servlet.http.HttpServletRequest;

import org.sjd.gordon.shared.security.LogoutAction;
import org.sjd.gordon.shared.security.LogoutResult;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class LogoutHandler implements ActionHandler<LogoutAction, LogoutResult> {

	private Provider<HttpServletRequest> requestProvider;

	@Inject
	public LogoutHandler(Provider<HttpServletRequest> requestProvider) {
		this.requestProvider = requestProvider;
	}

	@Override
	public LogoutResult execute(LogoutAction action, ExecutionContext context) throws ActionException {
		HttpServletRequest request = requestProvider.get(); 
		request.getSession().invalidate();
		return new LogoutResult();
	}

	@Override
	public Class<LogoutAction> getActionType() {
		return LogoutAction.class;
	}

	@Override
	public void undo(LogoutAction action, LogoutResult result, ExecutionContext context) throws ActionException { }

}
