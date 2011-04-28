package org.sjd.gordon.client.main;

import org.sjd.gordon.client.security.LogoutCallback;
import org.sjd.gordon.shared.security.Logout;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.client.DispatchAsync;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealRootContentEvent;

public class TitleStripPresenter extends Presenter<TitleStripPresenter.TitleStripView,TitleStripPresenter.TitleStripProxy> {

	@ProxyStandard
	public interface TitleStripProxy extends Proxy<TitleStripPresenter> { }

	public interface TitleStripView extends View { 
		public HasClickHandlers getLogout();
	}

	@Inject
	public TitleStripPresenter(EventBus eventBus, TitleStripView view, TitleStripProxy proxy, final DispatchAsync dispatcher) {
		super(eventBus, view, proxy);
		view.getLogout().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent arg0) {
				dispatcher.execute(new Logout(), new LogoutCallback() {
					@Override
					public void logout() {
						Window.Location.assign("Login.html");
					}
				});
			}
		});
	}
	
	@Override
	protected void revealInParent() {
		RevealRootContentEvent.fire(this, this);
	}
}