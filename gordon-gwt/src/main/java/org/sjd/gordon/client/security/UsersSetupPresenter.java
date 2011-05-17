package org.sjd.gordon.client.security;

import java.util.ArrayList;

import org.sjd.gordon.shared.security.ChangeUserPasswordAction;
import org.sjd.gordon.shared.security.DeleteUserAction;
import org.sjd.gordon.shared.security.EditUser;
import org.sjd.gordon.shared.security.EditUserAction;
import org.sjd.gordon.shared.security.GetAllUsersAction;
import org.sjd.gordon.shared.security.UserDetail;

import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.client.DispatchAsync;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealRootContentEvent;

public class UsersSetupPresenter extends Presenter<UsersSetupPresenter.UsersSetupView,UsersSetupPresenter.UsersSetupPanelProxy> implements UserSetupUIHandler {

	@ProxyStandard
	public interface UsersSetupPanelProxy extends Proxy<UsersSetupPresenter> { }

	public interface UsersSetupView extends View, HasUiHandlers<UserSetupUIHandler> {
		public void setUsers(ArrayList<UserDetail> users);
		public void remove(UserDetail user);
		public void add(UserDetail details);
		public void update(UserDetail details);
		public void showPasswordSuccessfullyChangedMessage();
	}
	
	interface EditUserDialogCallback {
		void commit(UserDetail details);
	}
	
	private final DispatchAsync dispatcher;
	
	@Inject
	public UsersSetupPresenter(EventBus eventBus, UsersSetupView view, UsersSetupPanelProxy proxy, DispatchAsync dispatcher) {
		super(eventBus, view, proxy);
		this.dispatcher = dispatcher;
		getView().setUiHandlers(this);
	}
	
	@Override
	protected void onBind() {
		super.onBind();
		load();
	}
	
	@Override
	public void add(final UserDetail details) {
		EditUserAction action = new EditUserAction(details, EditUser.EditType.ADD);
		dispatcher.execute(action, new EditUserCallback() {
			@Override
			public void commited(UserDetail user) {
				getView().add(user);
			}
		});
	}
	
	@Override
	public void update(UserDetail details) {
		EditUserAction action = new EditUserAction(details, EditUser.EditType.UPDATE);
		dispatcher.execute(action, new EditUserCallback() {
			@Override
			public void commited(UserDetail user) {
				getView().update(user);
			}
		});
	}
	
	@Override
	public void delete(final UserDetail selected) {
		dispatcher.execute(new DeleteUserAction(selected.getId()), new DeleteUserCallback() {
			@Override
			public void deleted() {
				getView().remove(selected);
			}
		});
	}	
	
	@Override
	public void changePassword(Integer userId, String password) {
		ChangeUserPasswordAction changeUserPassword = new ChangeUserPasswordAction(userId, password);
		dispatcher.execute(changeUserPassword, new ChangeUserPasswordCallback() {
			@Override
			public void passwordSuccessfullyChanged() {
				getView().showPasswordSuccessfullyChangedMessage();
			}
		});
	}
	
	private void load() {
		GetAllUsersAction getUserDetails = new GetAllUsersAction();
		dispatcher.execute(getUserDetails, new LoadAllUserDetailsCallback() {
			@Override
			public void loaded(ArrayList<UserDetail> users) {
				getView().setUsers(users);
			}
		});
	}		
	
	@Override
	protected void onUnbind() { }

	@Override
	protected void revealInParent() {
		RevealRootContentEvent.fire(this, this);
	}	
}
