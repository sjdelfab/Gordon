package org.sjd.gordon.client.security;

import java.util.ArrayList;

import org.sjd.gordon.client.security.ChangePasswordEditPanel.ChangePasswordCallback;
import org.sjd.gordon.shared.security.ChangeUserPassword;
import org.sjd.gordon.shared.security.DeleteUser;
import org.sjd.gordon.shared.security.EditUser;
import org.sjd.gordon.shared.security.GetAllUserDetails;
import org.sjd.gordon.shared.security.UserDetail;

import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.widget.button.Button;
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

public class UsersSetupPresenter extends Presenter<UsersSetupPresenter.UsersSetupView,UsersSetupPresenter.UsersSetupPanelProxy> {

	@ProxyStandard
	public interface UsersSetupPanelProxy extends Proxy<UsersSetupPresenter> { }

	public interface UsersSetupView extends View {
		public void setUsers(ArrayList<UserDetail> users);
		public UserDetail getSelectedItem();
		public void remove(UserDetail user);
		public HasClickHandlers getDeleteHandler();
		public void confirmDeleteRequest(Listener<MessageBoxEvent> callback);
		public HasClickHandlers getAddHandler();
		public void showEditDialog(EditUserDialogCallback callback);
		public void add(UserDetail details);
		public HasClickHandlers getUpdateHandler();
		public void showEditDialog(UserDetail details, EditUserDialogCallback callback);
		public void update(UserDetail details);
		public HasClickHandlers getChangePasswordHandler();
		public void showChangePasswordDialog(UserDetail details, ChangePasswordCallback callback);
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
	}
	
	@Override
	protected void onBind() {
		super.onBind();
		load();
		getView().getDeleteHandler().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				deleteSelectedUser();
			}
		});
		getView().getAddHandler().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent arg0) {
				getView().showEditDialog(new EditUserDialogCallback() {
					@Override
					public void commit(UserDetail details) {
						addUser(details);
					}
				});
			}
		});
		getView().getUpdateHandler().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent arg0) {
				final UserDetail selected = getView().getSelectedItem();
				if (selected != null) {
					getView().showEditDialog(new UserDetail(selected), new EditUserDialogCallback() {
						@Override
						public void commit(UserDetail details) {
							UserDetail user = new UserDetail(selected);
							user.mergeTo(details);
							updateEntry(user);
						}
					});
				}
			}
		});
		getView().getChangePasswordHandler().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent arg0) {
				final UserDetail selected = getView().getSelectedItem();
				if (selected != null) {
					getView().showChangePasswordDialog(selected, new ChangePasswordCallback() {
						@Override
						public void changePassword(Integer userId, char[] password) {
							doChangePassword(userId,password);
						}
					});
				}	
			}
		});
	}
	
	private void deleteSelectedUser() {
		final UserDetail selected = getView().getSelectedItem();
		if (selected != null) {
			getView().confirmDeleteRequest(new Listener<MessageBoxEvent>() {
				@Override
				public void handleEvent(MessageBoxEvent be) {
					Button buttonClicked = be.getButtonClicked();
					if (buttonClicked.getText().equals(be.getDialog().yesText)) {
						performDelete(selected);
					}
				}
			});
		}
	}
	
	private void addUser(final UserDetail details) {
		EditUser action = new EditUser(details, EditUser.EditType.ADD);
		dispatcher.execute(action, new EditUserCallback() {
			@Override
			public void commited(UserDetail user) {
				getView().add(user);
			}
		});
	}
	
	private void updateEntry(UserDetail details) {
		EditUser action = new EditUser(details, EditUser.EditType.UPDATE);
		dispatcher.execute(action, new EditUserCallback() {
			@Override
			public void commited(UserDetail user) {
				getView().update(user);
			}
		});
	}
	
	private void performDelete(final UserDetail selected) {
		dispatcher.execute(new DeleteUser(selected.getId()), new DeleteUserCallback() {
			@Override
			public void deleted() {
				getView().remove(selected);
			}
		});
	}	
	
	private void doChangePassword(Integer userId, char[] password) {
		ChangeUserPassword changeUserPassword = new ChangeUserPassword(userId, password);
		dispatcher.execute(changeUserPassword, new ChangeUserPasswordCallback() {
			@Override
			public void passwordSuccessfullyChanged() {
				getView().showPasswordSuccessfullyChangedMessage();
			}
		});
	}
	
	private void load() {
		GetAllUserDetails getUserDetails = new GetAllUserDetails();
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
