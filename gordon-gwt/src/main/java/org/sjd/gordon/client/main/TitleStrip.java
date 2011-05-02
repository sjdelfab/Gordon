package org.sjd.gordon.client.main;

import org.sjd.gordon.client.gxt.Button;
import org.sjd.gordon.client.gxt.MenuItem;
import org.sjd.gordon.client.security.ChangeUserNameEvent;
import org.sjd.gordon.client.security.ChangeUserNameEventHandler;
import org.sjd.gordon.model.Exchange;

import com.extjs.gxt.ui.client.event.DomEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.util.Padding;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.Label;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.Text;
import com.extjs.gxt.ui.client.widget.layout.HBoxLayout;
import com.extjs.gxt.ui.client.widget.layout.HBoxLayout.HBoxLayoutAlign;
import com.extjs.gxt.ui.client.widget.layout.HBoxLayoutData;
import com.extjs.gxt.ui.client.widget.menu.Menu;
import com.extjs.gxt.ui.client.widget.menu.MenuBar;
import com.extjs.gxt.ui.client.widget.menu.MenuBarItem;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;

public class TitleStrip extends ViewImpl implements ChangeUserNameEventHandler, TitleStripPresenter.TitleStripView {

	private Text displayName;
	private LayoutContainer container;
	private Button logoutButton;
	private MenuItem registriesMenuItem;
	private Menu registriesSubMenu = new Menu();
	
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
		
		MenuItem unitaryDefinitionMenuItem = new MenuItem("Unitary Definitions");
		setupMenu.add(unitaryDefinitionMenuItem);
		MenuItem tabularDefinitionMenuItem = new MenuItem("Tabular Definitions");
		setupMenu.add(tabularDefinitionMenuItem);
		MenuItem layoutMenuItem = new MenuItem("Layout");
		setupMenu.add(layoutMenuItem);

		MenuBarItem setupMenuBarItem = new MenuBarItem("Setup", setupMenu);
		bar.add(setupMenuBarItem);
		
		container.add(bar, new HBoxLayoutData(new Margins(3)));
		
        final Label settingsMenuItem = new Label("Settings");
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
	public void changedName(ChangeUserNameEvent event) {
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
		registriesSubMenu.add(registryMenuItem);
		return registryMenuItem;
		
	}
}
