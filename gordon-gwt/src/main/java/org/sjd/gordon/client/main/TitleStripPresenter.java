package org.sjd.gordon.client.main;

import java.util.ArrayList;

import org.sjd.gordon.client.navigation.LoadExchangesCallback;
import org.sjd.gordon.client.navigation.LoadStocksCallback;
import org.sjd.gordon.client.registry.ShowRegistryEvent;
import org.sjd.gordon.client.security.ChangeUserNameEvent;
import org.sjd.gordon.client.security.ChangeUserNameEvent.ChangeUserNameHandler;
import org.sjd.gordon.client.security.ChangeUserPasswordCallback;
import org.sjd.gordon.client.security.EditUserCallback;
import org.sjd.gordon.client.security.LogoutCallback;
import org.sjd.gordon.client.security.ShowUserSetupEvent;
import org.sjd.gordon.model.Exchange;
import org.sjd.gordon.shared.navigation.GetExchangesAction;
import org.sjd.gordon.shared.navigation.GetStocksAction;
import org.sjd.gordon.shared.navigation.StockName;
import org.sjd.gordon.shared.security.ChangeUserPasswordAction;
import org.sjd.gordon.shared.security.EditUser;
import org.sjd.gordon.shared.security.EditUserAction;
import org.sjd.gordon.shared.security.GetCurrentUserAction;
import org.sjd.gordon.shared.security.LogoutAction;
import org.sjd.gordon.shared.security.UserDetail;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.client.DispatchAsync;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealRootContentEvent;

public class TitleStripPresenter extends Presenter<TitleStripPresenter.TitleStripView,TitleStripPresenter.TitleStripProxy> implements TitleStripUIHandler {

	@ProxyStandard
	public interface TitleStripProxy extends Proxy<TitleStripPresenter> { }

	public interface TitleStripView extends View, HasUiHandlers<TitleStripUIHandler> { 
		public HasClickHandlers getLogout();
		public HasClickHandlers addExchange(Exchange exchange);
		public HasClickHandlers getUserSetup();
		public HasClickHandlers getSettings();
		public ChangeUserNameHandler getChangeUserNameEventHandler();
		public void showEditDialog(UserDetail details, EditCurrentUserDialogCallback callback);
		public void logout();
		public void showPasswordSuccessfullyChangedMessage();
	}

	interface EditCurrentUserDialogCallback {
		void commit(UserDetail details);
		void changePassword(Integer userId, String password);
	}	
	
	private final DispatchAsync dispatcher;
	private UserDetail currentUser;
	
	@Inject
	public TitleStripPresenter(EventBus eventBus, TitleStripView view, TitleStripProxy proxy, final DispatchAsync dispatcher) {
		super(eventBus, view, proxy);
		getView().setUiHandlers(this);
		eventBus.addHandler(ChangeUserNameEvent.getType(), view.getChangeUserNameEventHandler());
		this.dispatcher = dispatcher;
		view.getLogout().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				dispatcher.execute(new LogoutAction(), new LogoutCallback() {
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
					public void changePassword(Integer userId, String password) {
						changeUserPassword(userId,password);
					}
				});
			}
		});
	}
	
	private void updateUser(UserDetail details) {
		EditUserAction action = new EditUserAction(details, EditUser.EditType.UPDATE);
		dispatcher.execute(action, new EditUserCallback() {
			@Override
			public void commited(UserDetail user) {
				TitleStripPresenter.this.currentUser = user;
				getEventBus().fireEvent(new ChangeUserNameEvent(user.getFirstName() + " " + user.getLastName()));
			}
		});
	}
	
	private void changeUserPassword(Integer userId, String password) {
		ChangeUserPasswordAction changeUserPassword = new ChangeUserPasswordAction(userId, password);
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
		GetExchangesAction getExchanges = new GetExchangesAction();
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
		GetCurrentUserAction getCurrentUser = new GetCurrentUserAction();
		dispatcher.execute(getCurrentUser, new LoadCurrentUserCallback() {
			@Override
			public void loaded(UserDetail user) {
				TitleStripPresenter.this.currentUser = user;
				getEventBus().fireEvent(new ChangeUserNameEvent(user.getFirstName() + " " + user.getLastName()));
			}
		});
	}

	@Override
	public void getStocks(Exchange exchange, final AsyncCallback<ArrayList<StockName>> callback) {
		GetStocksAction getStocks = new GetStocksAction(exchange.getId());
		dispatcher.execute(getStocks, new LoadStocksCallback() {
			@Override
			public void loaded(ArrayList<StockName> stocks) {
				callback.onSuccess(stocks);
			}
		});
	}
	
}
