package org.sjd.gordon.client.security;

import org.sjd.gordon.client.gxt.Button;
import org.sjd.gordon.client.gxt.TextField;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.form.FormButtonBinding;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.layout.CenterLayout;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.HasValue;

public class LoginView extends LayoutContainer implements LoginDisplay {

	private FormData formData;
	private TextField<String> userNameField;
	private TextField<String> passwordField;
	private Button loginButton;
	private FormPanel loginPanel;

	public LoginView() {
		createLoginForm();
	}
	
	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);
		setLayout(new CenterLayout());
		setBorders(true);
		formData = new FormData("-20");
		add(loginPanel);
	}

	private FormPanel createLoginForm() {
		loginPanel = new FormPanel();
		loginPanel.setHeading("Login");
		loginPanel.setFrame(true);
		loginPanel.setWidth(350);

		userNameField = new TextField<String>();
		userNameField.setFieldLabel("Username");
		userNameField.getMessages().setBlankText("Must specify a username.");
		userNameField.setAllowBlank(false);
		userNameField.getFocusSupport().setPreviousId(loginPanel.getButtonBar().getId());
		loginPanel.add(userNameField, formData);

		passwordField = new TextField<String>();
		passwordField.setFieldLabel("Password");
		passwordField.getMessages().setBlankText("Must specify a password.");
		passwordField.setAllowBlank(false);
		passwordField.setPassword(true);
		loginPanel.add(passwordField, formData);

		loginButton = new Button("Login");
		loginPanel.addButton(loginButton);
		Button clearButton = new Button("Clear");
		clearButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				userNameField.clear();
				passwordField.clear();
			}
		});
		loginPanel.addButton(clearButton);

		loginPanel.setButtonAlign(HorizontalAlignment.CENTER);

		FormButtonBinding binding = new FormButtonBinding(loginPanel);
		binding.addButton(loginButton);

		return loginPanel;
	}
	
	@Override
	public HasValue<String> getUserName() {
		return userNameField;
	}

	@Override
	public HasValue<String> getPassword() {
		return passwordField;
	}

	@Override
	public HasClickHandlers getLogin() {
		return loginButton;
	}
	
}
