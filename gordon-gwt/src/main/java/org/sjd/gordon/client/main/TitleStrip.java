package org.sjd.gordon.client.main;

import org.sjd.gordon.client.gxt.Button;
import org.sjd.gordon.client.security.ChangeUserNameEvent;
import org.sjd.gordon.client.security.ChangeUserNameEventHandler;

import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.util.Padding;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.Text;
import com.extjs.gxt.ui.client.widget.layout.HBoxLayout;
import com.extjs.gxt.ui.client.widget.layout.HBoxLayout.HBoxLayoutAlign;
import com.extjs.gxt.ui.client.widget.layout.HBoxLayoutData;
import com.extjs.gxt.ui.client.widget.menu.Menu;
import com.extjs.gxt.ui.client.widget.menu.MenuBar;
import com.extjs.gxt.ui.client.widget.menu.MenuBarItem;
import com.extjs.gxt.ui.client.widget.menu.MenuItem;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;

public class TitleStrip extends ViewImpl implements ChangeUserNameEventHandler, TitleStripPresenter.TitleStripView {

	private Text displayName;
	private LayoutContainer container;
	private Button logoutButton;
	
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
        
        Menu sessionMenu = new Menu();
		MenuItem logoutMenuItem = new MenuItem("Logout");
		sessionMenu.add(logoutMenuItem);

		Menu setupMenu = new Menu();
		MenuItem setupMenuItem = new MenuItem("Registry");
		setupMenu.add(setupMenuItem);
		MenuItem unitaryDefinitionMenuItem = new MenuItem("Unitary Definitions");
		setupMenu.add(unitaryDefinitionMenuItem);
		MenuItem tabularDefinitionMenuItem = new MenuItem("Tabular Definitions");
		setupMenu.add(tabularDefinitionMenuItem);
		MenuItem layoutMenuItem = new MenuItem("Layout");
		setupMenu.add(layoutMenuItem);

		MenuBar bar = new MenuBar();
		bar.setStyleAttribute("background", "#330099");
		bar.setStyleAttribute("color", "white");
		bar.setBorders(false);
		bar.setStyleAttribute("borderTop", "none");

		MenuBarItem setupMenuBarItem = new MenuBarItem("Setup", setupMenu);
		bar.add(setupMenuBarItem);
		
		container.add(bar, new HBoxLayoutData(new Margins(3)));
		logoutButton = new Button("Logout");
		logoutButton.setStyleAttribute("background", "#330099");
		logoutButton.setStyleAttribute("color", "white");
		logoutButton.setBorders(false);
		container.add(logoutButton, new HBoxLayoutData(new Margins(3)));
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
}
