package org.sjd.gordon.client.security;

import org.sjd.gordon.client.gxt.Button;
import org.sjd.gordon.client.gxt.TextField;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.Viewport;
import com.extjs.gxt.ui.client.widget.form.FormButtonBinding;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.CenterLayout;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;

public class LoginPanel extends ViewImpl implements LoginPresenter.LoginPanelView {

	private FormData formData;
	private TextField<String> userNameField;
	private TextField<String> passwordField;
	private Button loginButton;
	private FormPanel loginPanel;
	
	private LayoutContainer container;
	private Viewport viewport;

	public LoginPanel() {
		container = new LayoutContainer();
		container.setLayout(new CenterLayout());
		container.setBorders(true);
		formData = new FormData("-20");
		container.add(createLoginForm());
		
		viewport = new Viewport();
		final BorderLayout borderLayout = new BorderLayout();
		viewport.setLayout(borderLayout);
		BorderLayoutData centerData = new BorderLayoutData(LayoutRegion.CENTER);
		viewport.add(container, centerData);
		viewport.setStyleAttribute("backgroundColor", "#FFFFFF");
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

	@Override
	public Widget asWidget() {
		return viewport;
	}
	
}
