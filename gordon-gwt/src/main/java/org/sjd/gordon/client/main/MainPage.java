package org.sjd.gordon.client.main;

import org.sjd.gordon.client.main.MainPagePresenter.MainPageView;
import org.sjd.gordon.client.navigation.NavigationPresenter;
import org.sjd.gordon.client.viewer.TabbedPanelPresenter;

import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.gwtplatform.mvp.client.ViewImpl;

public class MainPage extends ViewImpl implements MainPageView {

	private LayoutContainer viewport;
	
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
		viewport = new LayoutContainer();
		final BorderLayout borderLayout = new BorderLayout();
		viewport.setLayout(borderLayout);
		viewport.setStyleAttribute("backgroundColor", "#FFFFFF");
		addTitleStrip(titleStripPresenter);
		setNavigationPanel();
		setMainPanel();
	}

	@Override
	public Widget asWidget() {
		viewport.setSize(width, height);
		return viewport;
	}
	
	private void setMainPanel() {
		BorderLayoutData centerData = new BorderLayoutData(LayoutRegion.CENTER);
		centerData.setCollapsible(false);
		viewport.add(tabbedPanelPresenter.getView().asWidget(), centerData);
		BorderLayoutData southData = new BorderLayoutData(LayoutRegion.SOUTH);
		southData.setHidden(true);
		LayoutContainer southContainer = new LayoutContainer();
		southContainer.setSize(0, 0);
		viewport.add(southContainer, southData);
	}
	
	private void setNavigationPanel() {
		BorderLayoutData westData = new BorderLayoutData(LayoutRegion.WEST, 200, 150, 300);
		westData.setCollapsible(false);
		westData.setSplit(false);
		viewport.add(navigationPresenter.getView().asWidget(), westData);
		viewport.layout();
	}
	
	private void addTitleStrip(TitleStripPresenter titleStripPresenter) {
		BorderLayoutData northData = new BorderLayoutData(LayoutRegion.NORTH, 33);
		northData.setMargins(new Margins());
		northData.setCollapsible(false);
		northData.setSplit(false);
		viewport.add(titleStripPresenter.getView().asWidget(),northData);
	}
}
