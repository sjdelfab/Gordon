package org.sjd.gordon.client.security;

import net.customware.gwt.dispatch.client.DispatchAsync;
import net.customware.gwt.presenter.client.EventBus;
import net.customware.gwt.presenter.client.widget.WidgetPresenter;

import org.sjd.gordon.shared.security.Login;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.inject.Inject;

public class LoginPresenter extends WidgetPresenter<LoginView> {

	private final DispatchAsync dispatcher;
	
	@Inject
	public LoginPresenter(final LoginView display, final EventBus eventBus, final DispatchAsync dispatcher) {
		super(display,eventBus);
		this.dispatcher = dispatcher;
		bind();
	}
	
	private void doLogin() {
		Log.info("Logging in.");
		Login login = new Login(display.getUserName().getValue(), display.getPassword().getValue());
		dispatcher.execute(login, new AuthenticatedCallback() {
			@Override
			public void loggedIn(Boolean success) {
				eventBus.fireEvent(new LoginSucessEvent());
			}

			@Override
			public void setDisplayName(String displayName) { 
				eventBus.fireEvent(new ChangeUserNameEvent(displayName));
			}
		});
	}
	
	@Override
	protected void onBind() {
		getDisplay().getLogin().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent arg0) {
				doLogin();
			}
		});
	}

	@Override
	protected void onUnbind() {
		// Add unbind functionality here for more complex presenters.
	}

	@Override
	protected void onRevealDisplay() {
		// Nothing to do. This is more useful in UI which may be buried in a tab bar, tree, etc.
	}

}
