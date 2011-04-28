package org.sjd.gordon.client.security;

import org.sjd.gordon.shared.security.Login;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasValue;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.client.DispatchAsync;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.Place;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealRootContentEvent;

public class LoginPresenter extends Presenter<LoginPresenter.LoginPanelView, LoginPresenter.LoginPanelProxy> {

	@ProxyStandard
	@NameToken(nameToken)
	public interface LoginPanelProxy extends Proxy<LoginPresenter>, Place { }

	public interface LoginPanelView extends View { 
		public HasValue<String> getUserName();
		public HasValue<String> getPassword();
		public HasClickHandlers getLogin();
	}	
	
	public static final String nameToken = "login";
	
	private final DispatchAsync dispatcher;
	
	@Inject
	public LoginPresenter(EventBus eventBus, LoginPanelView view, LoginPanelProxy proxy, DispatchAsync dispatcher) {
		super(eventBus,view,proxy);
		this.dispatcher = dispatcher;
	}
	
	private void doLogin() {
		Log.info("Logging in.");
		Login login = new Login(getView().getUserName().getValue(), getView().getPassword().getValue());
		dispatcher.execute(login, new AuthenticatedCallback() {
			@Override
			public void loggedIn(Boolean success) {
				getEventBus().fireEvent(new LoginSucessEvent());
				if (success) {
					Window.Location.assign("Gordon.html");
				}
			}

			@Override
			public void setDisplayName(String displayName) { 
				getEventBus().fireEvent(new ChangeUserNameEvent(displayName));
			}
		});
	}
	
	@Override
	protected void onBind() {
		getView().getLogin().addClickHandler(new ClickHandler() {
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
	protected void revealInParent() {
		RevealRootContentEvent.fire(this, this);
	}

	

}
