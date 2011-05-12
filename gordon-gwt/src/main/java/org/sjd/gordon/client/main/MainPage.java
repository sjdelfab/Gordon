package org.sjd.gordon.client.main;

import org.sjd.gordon.client.main.MainPagePresenter.MainPageView;
import org.sjd.gordon.client.navigation.NavigationPresenter;
import org.sjd.gordon.client.viewer.TabbedPanelPresenter;

import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.VBoxLayout;
import com.extjs.gxt.ui.client.widget.layout.VBoxLayoutData;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.gwtplatform.mvp.client.ViewImpl;

public class MainPage extends ViewImpl implements MainPageView {

	private LayoutContainer mainContainer;
	
	private NavigationPresenter navigationPresenter;
	private TabbedPanelPresenter tabbedPanelPresenter;
	@Inject
	@Named("width")
	private int width;
	@Inject
	@Named("height")
	private int height;
	
	@Inject
	public MainPage(NavigationPresenter navigationPresenter, TabbedPanelPresenter tabbedPanelPresenter, 
			                TitleStripPresenter titleStripPresenter) {
		this.navigationPresenter = navigationPresenter;
		this.tabbedPanelPresenter = tabbedPanelPresenter;
		mainContainer = new LayoutContainer();
		VBoxLayout layout = new VBoxLayout();
		mainContainer.setLayout(layout);
		mainContainer.setStyleAttribute("backgroundColor", "#FFFFFF");
		addTitleStrip(titleStripPresenter);
		setNavigationPanel();
		setMainPanel();
	}

	@Override
	public Widget asWidget() {
		mainContainer.setSize(width, height);
		return mainContainer;
	}
	
	private void setMainPanel() {
		mainContainer.add(tabbedPanelPresenter.getView().asWidget(), new VBoxLayoutData(new Margins(0, 0, 0, 0)));
	}
	
	private void setNavigationPanel() {
		mainContainer.add(navigationPresenter.getView().asWidget(), new VBoxLayoutData(new Margins(0, 0, 0, 0)));
		mainContainer.layout();
	}
	
	private void addTitleStrip(TitleStripPresenter titleStripPresenter) {
		mainContainer.add(titleStripPresenter.getView().asWidget(), new VBoxLayoutData(new Margins(0, 0, 0, 0)));
	}
}
