package org.sjd.gordon.client.main;

import org.sjd.gordon.client.gxt.Button;
import org.sjd.gordon.client.gxt.ClickableLabel;
import org.sjd.gordon.client.gxt.MenuItem;
import org.sjd.gordon.client.main.TitleStripPresenter.EditCurrentUserDialogCallback;
import org.sjd.gordon.client.security.ChangePasswordEditPanel;
import org.sjd.gordon.client.security.ChangePasswordEditPanel.ChangePasswordCallback;
import org.sjd.gordon.client.security.ChangeUserNameEvent;
import org.sjd.gordon.client.security.ChangeUserNameEvent.ChangeUserNameHandler;
import org.sjd.gordon.model.Exchange;
import org.sjd.gordon.shared.security.UserDetail;

import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.DomEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.util.Padding;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.Text;
import com.extjs.gxt.ui.client.widget.form.FormPanel.LabelAlign;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.extjs.gxt.ui.client.widget.layout.HBoxLayout;
import com.extjs.gxt.ui.client.widget.layout.HBoxLayout.HBoxLayoutAlign;
import com.extjs.gxt.ui.client.widget.layout.HBoxLayoutData;
import com.extjs.gxt.ui.client.widget.menu.Menu;
import com.extjs.gxt.ui.client.widget.menu.MenuBar;
import com.extjs.gxt.ui.client.widget.menu.MenuBarItem;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

public class TitleStrip extends ViewWithUiHandlers<TitleStripUIHandler> implements ChangeUserNameHandler, TitleStripPresenter.TitleStripView {

	private Text displayName;
	private LayoutContainer container;
	private Button logoutButton;
	private MenuItem registriesMenuItem;
	private MenuItem userSetupMenuItem;
	private MenuItem stockEquityImportMenuItem;
	private MenuItem csvTradeHistoryImportMenuItem;
	private Menu registriesSubMenu = new Menu();
	private ClickableLabel settingsMenuItem = new ClickableLabel("Settings");
	
	public TitleStrip() {
		container = new LayoutContainer();
		container.setHeight(30);
	    HBoxLayout layout = new HBoxLayout();  
        layout.setPadding(new Padding(0));  
        layout.setHBoxLayoutAlign(HBoxLayoutAlign.TOP);
        container.setLayout(layout);
        container.setStyleAttribute("background", "#330099");
        container.add(new Html("<p style=\"font-size:22px;font-family:arial;color:white\">Gordon</p>"),new HBoxLayoutData(new Margins(2)));
        HBoxLayoutData flex = new HBoxLayoutData(new Margins(0, 0, 0, 0));  
        flex.setFlex(1);  
        container.add(new Text(), flex);
	    
        displayName = new Text();
        displayName.setStyleAttribute("background", "#330099");
        displayName.setStyleAttribute("color", "white");
        displayName.setStyleAttribute("font-size", "12px");
        displayName.setStyleAttribute("font-family", "arial");
        container.add(displayName, new HBoxLayoutData(new Margins(9))); 
        
        MenuBar bar = new MenuBar();
		bar.setStyleAttribute("background", "#330099");
		bar.setStyleAttribute("color", "white");
		bar.setBorders(false);
		bar.setStyleAttribute("borderTop", "none");
        
		Menu setupMenu = new Menu();
		registriesMenuItem = new MenuItem("Registries");
		registriesMenuItem.setSubMenu(registriesSubMenu);
		setupMenu.add(registriesMenuItem);	
		
		userSetupMenuItem = new MenuItem("Users");
		userSetupMenuItem.setIconStyle("users");
		setupMenu.add(userSetupMenuItem);
		
		MenuItem importMenuItem = new MenuItem("Import");
		setupMenu.add(importMenuItem);
		Menu importSubMenu = new Menu();
		importMenuItem.setSubMenu(importSubMenu);
		stockEquityImportMenuItem = new MenuItem("Stock Equities");
		importSubMenu.add(stockEquityImportMenuItem);
		stockEquityImportMenuItem.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				StockEquityImportDialog importDialog = new StockEquityImportDialog();
				importDialog.show();
			}
		});
		csvTradeHistoryImportMenuItem = new MenuItem("CSV Trade History");
		csvTradeHistoryImportMenuItem.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				TradeHistoryImportDialog importDialog = new TradeHistoryImportDialog(getUiHandlers());
				importDialog.show();
			}
		});
		importSubMenu.add(csvTradeHistoryImportMenuItem);
		
//		MenuItem unitaryDefinitionMenuItem = new MenuItem("Unitary Definitions");
//	    setupMenu.add(unitaryDefinitionMenuItem);
//		MenuItem tabularDefinitionMenuItem = new MenuItem("Tabular Definitions");
//		setupMenu.add(tabularDefinitionMenuItem);
//		MenuItem layoutMenuItem = new MenuItem("Layout");
//		setupMenu.add(layoutMenuItem);

		MenuBarItem setupMenuBarItem = new MenuBarItem("Setup", setupMenu);
		bar.add(setupMenuBarItem);
		
		container.add(bar, new HBoxLayoutData(new Margins(3)));
		
        settingsMenuItem.setSize(60, 16);
        settingsMenuItem.setStyleAttribute("vertical-align","middle");
        settingsMenuItem.setStyleAttribute("horizontal-align","middle");
        settingsMenuItem.setStyleAttribute("background", "#330099");
        settingsMenuItem.setStyleAttribute("color", "white");
        settingsMenuItem.setStyleAttribute("font-family","arial");
        settingsMenuItem.setStyleAttribute("font-size", "12px");
        settingsMenuItem.setStyleAttribute("font-type", "bold");
        settingsMenuItem.setStyleAttribute("padding","1px 8px");
        settingsMenuItem.addListener(Events.OnMouseOver, new Listener<DomEvent>() {
			@Override
			public void handleEvent(DomEvent be) {
				settingsMenuItem.setStyleAttribute("background", "#98c5f5");
			}
		});
        settingsMenuItem.addListener(Events.OnMouseOut, new Listener<DomEvent>() {
			@Override
			public void handleEvent(DomEvent be) {
				settingsMenuItem.setStyleAttribute("background", "#330099");
			}
		});

        container.add(settingsMenuItem, new HBoxLayoutData(new Margins(8,12,8,0)));
		
		logoutButton = new Button("Logout");
		logoutButton.setStyleAttribute("background", "#330099");
		logoutButton.setStyleAttribute("color", "white");
		logoutButton.setBorders(false);
		container.add(logoutButton, new HBoxLayoutData(new Margins(4)));
	}

	@Override
	public void onChangeUserName(ChangeUserNameEvent event) {
		displayName.setText(event.getNewName());
		container.layout(true);
	}

	@Override
	public Widget asWidget() {
		return container;
	}

	@Override
	public HasClickHandlers getLogout() {
		return logoutButton;
	}
	
	@Override
	public HasClickHandlers addExchange(Exchange exchange) {
		MenuItem registryMenuItem = new MenuItem(exchange.getCode());
		registryMenuItem.setIconStyle(exchange.getCode().toLowerCase());
		registriesSubMenu.add(registryMenuItem);
		return registryMenuItem;
	}

	@Override
	public HasClickHandlers getUserSetup() {
		return userSetupMenuItem;
	}

	@Override
	public HasClickHandlers getSettings() {
		return settingsMenuItem;
	}

	@Override
	public ChangeUserNameHandler getChangeUserNameEventHandler() {
		return this;
	}
	
	@Override
	public void logout() {
		Window.Location.assign("Login.html");
	}
	
	public void showPasswordSuccessfullyChangedMessage() {
		MessageBox.info("Password Changed", "Password successfully changed", null);
	}
	
	@Override
	public void showEditDialog(UserDetail details, final EditCurrentUserDialogCallback callback) {
		final Dialog editDialog = new Dialog();  
	    editDialog.setHeading("Change your details");  
	    editDialog.setButtons(Dialog.OKCANCEL);  
	    editDialog.setBodyStyleName("pad-text");
	    BorderLayoutData data = new BorderLayoutData(LayoutRegion.CENTER);
	    final EditorUserDetailsPanel editorPanel = new EditorUserDetailsPanel(callback);
    	editorPanel.setDetails(details);
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
	
	private class EditorUserDetailsPanel extends LayoutContainer {
		
		private TextField<String> firstNameTextField = new TextField<String>();
		private TextField<String> lastNameTextField = new TextField<String>();
		
		private UserDetail currentDetails;
		private EditCurrentUserDialogCallback callback;
		
		private EditorUserDetailsPanel(EditCurrentUserDialogCallback callback) {
			this.callback = callback;
		}
		
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

			Button changePasswordButton = new Button("Change Password");
			changePasswordButton.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent arg0) {
					ChangePasswordEditPanel.showChangePasswordDialog(currentDetails, new ChangePasswordCallback() {
						@Override
						public void changePassword(Integer userId, String password) {
							callback.changePassword(userId, password);
						}
					});
				}
			});
			add(changePasswordButton, new FormData("50%"));
			
			setSize(285,105);
			
			if (currentDetails != null) {
				firstNameTextField.setValue(currentDetails.getFirstName());
				lastNameTextField.setValue(currentDetails.getLastName());
			}
		}
		
		UserDetail getUserDetails() {
			UserDetail details = new UserDetail(currentDetails);
			details.setFirstName(firstNameTextField.getValue());
			details.setLastName(lastNameTextField.getValue());
			return details;
		}
		
		void setDetails(UserDetail details) {
			this.currentDetails = details;
		}
		
		boolean valid() {
			lastNameTextField.validate();
			firstNameTextField.validate();
			if (lastNameTextField.getValue() == null || lastNameTextField.getValue().equals("")) {
				return false;
			}
			if (firstNameTextField.getValue() == null || firstNameTextField.getValue().equals("")) {
				return false;
			}
			return true;
		}
		
		@Override
		protected void onHide() {
			super.onHide();
			this.currentDetails = null;
			this.callback = null;
		}
	}
	
}
