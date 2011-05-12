package org.sjd.gordon.client.security;

import java.util.ArrayList;
import java.util.List;

import org.sjd.gordon.client.gxt.Button;
import org.sjd.gordon.client.security.ChangePasswordEditPanel.ChangePasswordCallback;
import org.sjd.gordon.client.security.UsersSetupPresenter.EditUserDialogCallback;
import org.sjd.gordon.client.security.UsersSetupPresenter.UsersSetupView;
import org.sjd.gordon.shared.security.UserDetail;

import com.allen_sauer.gwt.log.client.Log;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.form.FormPanel.LabelAlign;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.gwtplatform.mvp.client.ViewImpl;

public class UserSetupPanelView extends ViewImpl implements UsersSetupView {

	@Inject
	@Named("width")
	private int width;
	@Inject
	@Named("height")
	private int height;	
	
	private ContentPanel contentPanel;
	private ListStore<UserDetail> store;
	private Button addButton, removeButton, updateButton, changePasswordButton;
	private Grid<UserDetail> grid;	
	
	public UserSetupPanelView() {
		store = new ListStore<UserDetail>();
	    List<ColumnConfig> configs = new ArrayList<ColumnConfig>();  
		  
	    ColumnConfig column = new ColumnConfig();  
	    column.setId(UserDetail.FIRST_NAME);  
	    column.setHeader("First Name");  
	    column.setWidth(200);  
	    configs.add(column);  
	  
	    column = new ColumnConfig();  
	    column.setId(UserDetail.LAST_NAME);  
	    column.setHeader("Last Name");  
	    column.setWidth(200);  
	    configs.add(column);  
	  
	    column = new ColumnConfig();  
	    column.setId(UserDetail.UID);  
	    column.setHeader("Username");  
	    column.setWidth(100);  
	    configs.add(column);
	    
	    column = new ColumnConfig();  
	    column.setId(UserDetail.ACTIVE);  
	    column.setHeader("Enabled");  
	    column.setWidth(100);  
	    configs.add(column);
	    
	    column = new ColumnConfig();  
	    column.setId(UserDetail.ROLES);  
	    column.setHeader("Roles"); 
	    column.setWidth(546);  
	    configs.add(column);  
	  	    
	    ColumnModel columnModel = new ColumnModel(configs);  
	  
	    contentPanel = new ContentPanel();
	    ToolBar toolbar = new ToolBar();
	    addButton = new Button();
	    addButton.setIconStyle("add");
	    toolbar.add(addButton);
	    removeButton = new Button();
	    removeButton.setIconStyle("remove");
	    toolbar.add(removeButton);
	    updateButton = new Button();
	    updateButton.setIconStyle("update");
	    toolbar.add(updateButton);
	    changePasswordButton = new Button();
	    changePasswordButton.setIconStyle("personal");
	    toolbar.add(changePasswordButton);
	    contentPanel.setTopComponent(toolbar);
	    contentPanel.setFrame(false);
	    contentPanel.setHeaderVisible(false);
	    contentPanel.setButtonAlign(HorizontalAlignment.CENTER);  
	    contentPanel.setLayout(new FitLayout());  
	    
	    grid = new Grid<UserDetail>(store, columnModel);  
	    grid.setBorders(true);
	    contentPanel.getAriaSupport().setPresentation(true);  
	    contentPanel.add(grid);  
	}
	
	@Override
	public Widget asWidget() {
		contentPanel.setSize(width, height-117);  
		return contentPanel;
	}

	@Override
	public void setUsers(ArrayList<UserDetail> users) {
		store.add(users);
	}

	@Override
	public UserDetail getSelectedItem() {
		return grid.getSelectionModel().getSelectedItem();
	}

	@Override
	public void remove(UserDetail user) {
		if (store.contains(user)) {
			store.remove(user);
			store.commitChanges();
		} else {
			Log.info("Remove: Can't find user: " + user.getUID());
		}
	}

	@Override
	public HasClickHandlers getDeleteHandler() {
		return removeButton;
	}

	@Override
	public void confirmDeleteRequest(Listener<MessageBoxEvent> callback) {
		MessageBox box = new MessageBox();  
        box.setButtons(MessageBox.YESNO);  
        box.setIcon(MessageBox.QUESTION);  
        box.setTitle("Delete");
        box.addCallback(callback);
        box.setMessage("Do you wish to delete this entry?");  
        box.show();
	}

	@Override
	public HasClickHandlers getAddHandler() {
		return addButton;
	}

	@Override
	public void showEditDialog(EditUserDialogCallback callback) {
		showEditDialog(callback, "Add", null);
	}

	@Override
	public void add(UserDetail details) {
		store.add(details);
		store.commitChanges();
	}

	@Override
	public HasClickHandlers getUpdateHandler() {
		return updateButton;
	}

	@Override
	public void showEditDialog(UserDetail details, EditUserDialogCallback callback) {
		showEditDialog(callback, "Update", details);
	}
	
	@Override
	public HasClickHandlers getChangePasswordHandler() {
		return changePasswordButton;
	}
	
	@Override
	public void showChangePasswordDialog(UserDetail userDetails, ChangePasswordCallback callback) {
		ChangePasswordEditPanel.showChangePasswordDialog(userDetails, callback);
	}
	
	@Override
	public void showPasswordSuccessfullyChangedMessage() {
		MessageBox.info("Password Changed", "Password successfully changed", null);
	}

	private void showEditDialog(final EditUserDialogCallback callback, String title, UserDetail details) {
		final Dialog editDialog = new Dialog();  
	    editDialog.setHeading(title);  
	    editDialog.setButtons(Dialog.OKCANCEL);  
	    editDialog.setBodyStyleName("pad-text");
	    BorderLayoutData data = new BorderLayoutData(LayoutRegion.CENTER);
	    final EditorPanel editorPanel = new EditorPanel();
	    if (details != null) {
	    	editorPanel.setDetails(details);
	    }
	    editDialog.add(editorPanel,data);  
	    editDialog.setScrollMode(Scroll.AUTO);  
	    editDialog.setHideOnButtonClick(false);
	    editDialog.getButtonById(Dialog.OK).addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				if (!editorPanel.valid()) {
					ce.cancelBubble();
					return;
				}
				UserDetail details = editorPanel.getUserDetails(); 
				editDialog.setVisible(false);
			    callback.commit(details);
			}
		});
	    editDialog.getButtonById(Dialog.CANCEL).addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				editDialog.setVisible(false);
			}
	    });
	    editDialog.setResizable(false);
	    editDialog.show();
	}

	@Override
	public void update(UserDetail details) {
		if (store.contains(details)) {
			store.update(details);
			store.commitChanges();
		} else {
			Log.info("Update: Can't find user: " + details.getUID());			
		}
	}
	
	private class EditorPanel extends LayoutContainer {
		
		private TextField<String> firstNameTextField = new TextField<String>();
		private TextField<String> lastNameTextField = new TextField<String>();
		private TextField<String> uidTextField = new TextField<String>();
		private TextField<String> rolesTextField = new TextField<String>();
		private TextField<String> newPasswordTextField = new TextField<String>();
		private TextField<String> reenterPasswordTextField = new TextField<String>();
		private CheckBox enabled = new CheckBox();
		
		private UserDetail currentDetails;
		
		@Override  
		protected void onRender(Element parent, int index) {
			super.onRender(parent, index);
			
			setStyleAttribute("backgroundColor", "#FFFFFF");
			setStyleAttribute("paddingLeft", "10px");
			setStyleAttribute("paddingTop", "10px");
			FormLayout layout = new FormLayout();
			layout.setLabelAlign(LabelAlign.LEFT);
			setLayout(layout);

			firstNameTextField.getMessages().setBlankText("Must specify a first name.");
			firstNameTextField.setAllowBlank(false);
			
			firstNameTextField.setFieldLabel("First Name");
			add(firstNameTextField, new FormData("30%"));

			lastNameTextField.getMessages().setBlankText("Must specify a last name.");
			lastNameTextField.setAllowBlank(false);
			lastNameTextField.setFieldLabel("Last Name");
			add(lastNameTextField, new FormData("30%"));
			
			uidTextField.getMessages().setBlankText("Must specify a username.");
			uidTextField.setAllowBlank(false);
			uidTextField.setFieldLabel("Username");
			add(uidTextField, new FormData("30%"));
			
			rolesTextField.getMessages().setBlankText("Comma separated list of roles.");
			rolesTextField.setAllowBlank(false);
			rolesTextField.setFieldLabel("Roles");
			add(rolesTextField, new FormData("30%"));	
			
			if (currentDetails == null) {
				newPasswordTextField.setAllowBlank(false);
				newPasswordTextField.setPassword(true);
				newPasswordTextField.setFieldLabel("New Password");
				add(newPasswordTextField, new FormData("30%"));

				reenterPasswordTextField.setAllowBlank(false);
				reenterPasswordTextField.setPassword(true);
				reenterPasswordTextField.setFieldLabel("Reenter Password");
				add(reenterPasswordTextField, new FormData("30%"));
			}
			
			enabled.setFieldLabel("Enabled");
			add(enabled, new FormData("30%"));	
			
			setSize(285,190);
			
			if (currentDetails != null) {
				firstNameTextField.setValue(currentDetails.getFirstName());
				lastNameTextField.setValue(currentDetails.getLastName());
				uidTextField.setValue(currentDetails.getUID());
				uidTextField.setEnabled(false);
				rolesTextField.setValue(currentDetails.getRoles());
				enabled.setValue(currentDetails.isActive());
			} else {
				// default role
				rolesTextField.setValue(UserDetail.DEFINED_ROLES[0]);
				enabled.setValue(Boolean.TRUE);
			}
		}
		
		UserDetail getUserDetails() {
			UserDetail details = new UserDetail();
			details.setUID(uidTextField.getValue());
			details.setFirstName(firstNameTextField.getValue());
			details.setLastName(lastNameTextField.getValue());
			if (currentDetails != null) {
				details.setId(currentDetails.getId());
				details.setVersion(currentDetails.getVersion());
			} else {
			    details.setPassword(newPasswordTextField.getValue().toCharArray());
			}
			details.setRoles(rolesTextField.getValue().trim().toUpperCase());
			details.setActive(enabled.getValue());
			return details;
		}
		
		void setDetails(UserDetail details) {
			this.currentDetails = details;
		}
		
		boolean valid() {
			lastNameTextField.validate();
			firstNameTextField.validate();
			uidTextField.validate();
			if (lastNameTextField.getValue() == null || lastNameTextField.getValue().equals("")) {
				return false;
			}
			if (firstNameTextField.getValue() == null || firstNameTextField.getValue().equals("")) {
				return false;
			}
			if (uidTextField.getValue() == null || uidTextField.getValue().equals("")) {
				return false;
			}
			String roles = rolesTextField.getValue();
			for(String role: roles.split(",")) {
				if (!isValidRole(role)) {
					return false;
				}
			}
			if (currentDetails == null) {
				reenterPasswordTextField.validate();
				newPasswordTextField.validate();
				if (reenterPasswordTextField.getValue() == null || reenterPasswordTextField.getValue().equals("")) {
					return false;
				}
				if (newPasswordTextField.getValue() == null || newPasswordTextField.getValue().equals("")) {
					return false;
				}
				if (!newPasswordTextField.getValue().equals(reenterPasswordTextField.getValue())) {
					return false;
				}
			}
			return true;
		}

		private boolean isValidRole(String specifiedRole) {
			for(String role: UserDetail.DEFINED_ROLES) {
				if (specifiedRole.trim().equalsIgnoreCase(role)) {
					return true;
				}
			}
			return false;
		}
	}	

}
