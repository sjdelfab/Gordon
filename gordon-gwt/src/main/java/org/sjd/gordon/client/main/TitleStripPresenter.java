package org.sjd.gordon.client.main;

import java.util.ArrayList;

import org.sjd.gordon.client.navigation.LoadExchangesCallback;
import org.sjd.gordon.client.registry.ShowRegistryEvent;
import org.sjd.gordon.client.security.ChangeUserNameEvent;
import org.sjd.gordon.client.security.ChangeUserNameEventHandler;
import org.sjd.gordon.client.security.ChangeUserPasswordCallback;
import org.sjd.gordon.client.security.EditUserCallback;
import org.sjd.gordon.client.security.LogoutCallback;
import org.sjd.gordon.client.security.ShowUserSetupEvent;
import org.sjd.gordon.model.Exchange;
import org.sjd.gordon.shared.navigation.GetExchanges;
import org.sjd.gordon.shared.security.ChangeUserPassword;
import org.sjd.gordon.shared.security.EditUser;
import org.sjd.gordon.shared.security.GetCurrentUser;
import org.sjd.gordon.shared.security.Logout;
import org.sjd.gordon.shared.security.UserDetail;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.EventBus;
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
		public HasClickHandlers addExchange(Exchange exchange);
		public HasClickHandlers getUserSetup();
		public HasClickHandlers getSettings();
		public ChangeUserNameEventHandler getChangeUserNameEventHandler();
		public void showEditDialog(UserDetail details, EditCurrentUserDialogCallback callback);
		public void logout();
		public void showPasswordSuccessfullyChangedMessage();
	}

	interface EditCurrentUserDialogCallback {
		void commit(UserDetail details);
		void changePassword(Integer userId, char[] password);
	}	
	
	private final DispatchAsync dispatcher;
	private UserDetail currentUser;
	
	@Inject
	public TitleStripPresenter(EventBus eventBus, TitleStripView view, TitleStripProxy proxy, final DispatchAsync dispatcher) {
		super(eventBus, view, proxy);
		eventBus.addHandler(ChangeUserNameEvent.TYPE, view.getChangeUserNameEventHandler());
		this.dispatcher = dispatcher;
		view.getLogout().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				dispatcher.execute(new Logout(), new LogoutCallback() {
					@Override
					public void logout() {
						getView().logout();
					}
				});
			}
		});
		view.getUserSetup().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				getEventBus().fireEvent(new ShowUserSetupEvent());
			}
		});
		view.getSettings().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				getView().showEditDialog(currentUser, new EditCurrentUserDialogCallback() {
					@Override
					public void commit(UserDetail details) {
						updateUser(details);
					}
					@Override
					public void changePassword(Integer userId, char[] password) {
						changeUserPassword(userId,password);
					}
				});
			}
		});
	}
	
	private void updateUser(UserDetail details) {
		EditUser action = new EditUser(details, EditUser.EditType.UPDATE);
		dispatcher.execute(action, new EditUserCallback() {
			@Override
			public void commited(UserDetail user) {
				TitleStripPresenter.this.currentUser = user;
				getEventBus().fireEvent(new ChangeUserNameEvent(user.getFirstName() + " " + user.getLastName()));
			}
		});
	}
	
	private void changeUserPassword(Integer userId, char[] password) {
		ChangeUserPassword changeUserPassword = new ChangeUserPassword(userId, password);
		dispatcher.execute(changeUserPassword, new ChangeUserPasswordCallback() {
			@Override
			public void passwordSuccessfullyChanged() {
				getView().showPasswordSuccessfullyChangedMessage();
			}
		});
	}
	
	@Override
	protected void revealInParent() {
		RevealRootContentEvent.fire(this, this);
	}
	
	@Override
	protected void onBind() {
		load();
	}
	
	private void load() {
		GetExchanges getExchanges = new GetExchanges();
		dispatcher.execute(getExchanges, new LoadExchangesCallback() {
			@Override
			public void loaded(ArrayList<Exchange> exchanges) {
				for(final Exchange exchange: exchanges) {
				   getView().addExchange(exchange).addClickHandler(new ClickHandler() {
						@Override
						public void onClick(ClickEvent event) {
							getEventBus().fireEvent(new ShowRegistryEvent(exchange));
						}
					});
				}
			}
		});
		GetCurrentUser getCurrentUser = new GetCurrentUser();
		dispatcher.execute(getCurrentUser, new LoadCurrentUserCallback() {
			@Override
			public void loaded(UserDetail user) {
				TitleStripPresenter.this.currentUser = user;
				getEventBus().fireEvent(new ChangeUserNameEvent(user.getFirstName() + " " + user.getLastName()));
			}
		});
	}
	
}
