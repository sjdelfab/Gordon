package org.sjd.gordon.client.security;

import org.sjd.gordon.shared.security.UserDetail;

import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.form.FormPanel.LabelAlign;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.google.gwt.user.client.Element;

public class ChangePasswordEditPanel extends LayoutContainer {

	private TextField<String> newPasswordTextField = new TextField<String>();
	private TextField<String> reenterPasswordTextField = new TextField<String>();
		
	@Override  
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);
		
		setStyleAttribute("backgroundColor", "#FFFFFF");
		setStyleAttribute("paddingLeft", "10px");
		setStyleAttribute("paddingTop", "10px");
		FormLayout layout = new FormLayout();
		layout.setLabelAlign(LabelAlign.LEFT);
		setLayout(layout);

		newPasswordTextField.setAllowBlank(false);
		newPasswordTextField.setPassword(true);
		newPasswordTextField.setFieldLabel("New Password");
		add(newPasswordTextField, new FormData("30%"));

		reenterPasswordTextField.setAllowBlank(false);
		reenterPasswordTextField.setPassword(true);
		reenterPasswordTextField.setFieldLabel("Reenter Password");
		add(reenterPasswordTextField, new FormData("30%"));
	
		setSize(285,80);
	}
	
	public boolean isValid() {
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
		return true;
	}
	
	public String getNewPassword() {
		return newPasswordTextField.getValue();
	}
	
	public static interface ChangePasswordCallback {
		void changePassword(Integer userId, String password);
	}
	
	public static void showChangePasswordDialog(final UserDetail currentDetails, final ChangePasswordCallback callback) {
		final Dialog changePasswordDialog = new Dialog();  
	    changePasswordDialog.setHeading("Change password");  
	    changePasswordDialog.setButtons(Dialog.OKCANCEL);  
	    changePasswordDialog.setBodyStyleName("pad-text");
	    BorderLayoutData data = new BorderLayoutData(LayoutRegion.CENTER);
	    final ChangePasswordEditPanel passwordEditorPanel = new ChangePasswordEditPanel();
	    changePasswordDialog.add(passwordEditorPanel,data);  
	    changePasswordDialog.setScrollMode(Scroll.AUTO);  
	    changePasswordDialog.setHideOnButtonClick(false);
	    changePasswordDialog.getButtonById(Dialog.OK).addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				if (!passwordEditorPanel.isValid()) {
					ce.cancelBubble();
					return;
				}
				Integer userId = currentDetails.getId();
				String newPassword = passwordEditorPanel.getNewPassword();
				changePasswordDialog.setVisible(false);
			    callback.changePassword(userId, newPassword);
			}
		});
	    changePasswordDialog.getButtonById(Dialog.CANCEL).addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				changePasswordDialog.setVisible(false);
			}
	    });
	    changePasswordDialog.setResizable(false);
	    changePasswordDialog.show();
	}
}
