package org.sjd.gordon.client;

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
import com.google.gwt.user.client.Element;

public class TitleStrip extends LayoutContainer {

	@Override  
	protected void onRender(Element parent, int index) {  
	    super.onRender(parent, index);
	    setHeight(30);
	    HBoxLayout layout = new HBoxLayout();  
        layout.setPadding(new Padding(0));  
        layout.setHBoxLayoutAlign(HBoxLayoutAlign.TOP);
        setLayout(layout);
        setStyleAttribute("background", "#330099");
        add(new Html("<p style=\"font-size:22px;font-family:arial;color:white\">Gordon</p>"),new HBoxLayoutData(new Margins(2)));
        HBoxLayoutData flex = new HBoxLayoutData(new Margins(0, 0, 0, 0));  
        flex.setFlex(1);  
        add(new Text(), flex);
	    
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
		bar.add(new MenuBarItem("Session", sessionMenu));
		add(bar, new HBoxLayoutData(new Margins(2)));  
	}
}
