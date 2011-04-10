package org.sjd.gordon.client;


import org.sjd.gordon.client.navigation.NavigationPresenter;
import org.sjd.gordon.client.security.LoginEventHandler;
import org.sjd.gordon.client.security.LoginPresenter;
import org.sjd.gordon.client.security.LoginSucessEvent;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.data.BeanModel;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Viewport;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NodeList;
import com.google.gwt.user.client.ui.RootPanel;

public class Gordon implements EntryPoint {

	public static final String STOCKS_STORE = "stocksStore";
	
	private Viewport viewport;
	private LoginPresenter loginPresenter;

	private final GordonGinjector injector = GWT.create(GordonGinjector.class);
	
	public void onModuleLoad() {
		NodeList<Element> elements = RootPanel.getBodyElement().getElementsByTagName("div");
		if (elements.getLength() != 0) {
			RootPanel.getBodyElement().removeChild(elements.getItem(0));
		}
		loginPresenter = injector.getLoginPresenter();
		injector.getEventBus().addHandler(LoginSucessEvent.TYPE, new LoginEventHandler() {
			
			@Override
			public void successfullyLoggedIn(LoginSucessEvent event) {
				login();
			}
		});
		
		viewport = new Viewport();
		final BorderLayout borderLayout = new BorderLayout();
		viewport.setLayout(borderLayout);
		BorderLayoutData centerData = new BorderLayoutData(LayoutRegion.CENTER);
		viewport.add(loginPresenter.getDisplay().asWidget(), centerData);
		viewport.setStyleAttribute("backgroundColor", "#FFFFFF");
		RootPanel.get().add(viewport);
		
		Registry.register(STOCKS_STORE,  new ListStore<BeanModel>());
	}

	private void login() {
		addMenuBar();
		setNavigationPanel();
		setMainPanel();
		viewport.remove(loginPresenter.getDisplay().asWidget());
		viewport.layout();
	}
	
	private void setMainPanel() {
		BorderLayoutData centerData = new BorderLayoutData(LayoutRegion.CENTER);
		centerData.setCollapsible(false);
		ResultsPanel mainPanel = new ResultsPanel();
		viewport.add(new GraphPanel(), centerData);
	}
	
	private void setNavigationPanel() {
		BorderLayoutData westData = new BorderLayoutData(LayoutRegion.WEST, 200, 150, 300);
		westData.setCollapsible(true);
		westData.setSplit(true);
		NavigationPresenter navigationPresenter = injector.getNavigationPresenter();
		viewport.add(navigationPresenter.getDisplay(), westData);
		viewport.layout();
	}
	
	private void addMenuBar() {
		BorderLayoutData northData = new BorderLayoutData(LayoutRegion.NORTH, 33);
		northData.setMargins(new Margins());
		northData.setCollapsible(false);
		northData.setSplit(false);
		
		viewport.add(new TitleStrip(),northData);
	}

}
