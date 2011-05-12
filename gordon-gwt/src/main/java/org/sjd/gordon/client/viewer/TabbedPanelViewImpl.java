package org.sjd.gordon.client.viewer;

import org.sjd.gordon.client.registry.RegistryPresenter;
import org.sjd.gordon.client.security.UsersSetupPresenter;
import org.sjd.gordon.model.Exchange;
import org.sjd.gordon.shared.viewer.StockDetail;

import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.name.Named;
import com.gwtplatform.mvp.client.ViewImpl;

public class TabbedPanelViewImpl extends ViewImpl implements TabbedPanelPresenter.TabbedPanelView {

	private final String REGISTRY_LABEL = "Registry";
	private final String USERS_LABEL = "Users";
	
	private TabPanel tabbedPanel;
	
	@Inject
	@Named("width")
	private int width;
	@Inject
	@Named("height")
	private int height;
	
	public TabbedPanelViewImpl() {
		tabbedPanel = new TabPanel();
		tabbedPanel.setResizeTabs(true);
		tabbedPanel.setAnimScroll(true);
		tabbedPanel.setTabScroll(true);
		tabbedPanel.setCloseContextMenu(true);
		TabItem item = new TabItem();
		item.setText("Dashboard");
		item.setItemId("Dashboard");
		item.addStyleName("pad-text");
		item.setClosable(false);
		item.add(new DashBoardView());		
		tabbedPanel.add(item);
	}
	
	public void addStock(StockPresenter stockPresenter, StockDetail stockDetails) {
		TabItem item = tabbedPanel.findItem(stockDetails.getCode(),false);
		if (item != null) {
			tabbedPanel.setSelection(item);
		} else {
			item = new TabItem();
			item.setText(stockDetails.getCode());
			item.setItemId(stockDetails.getCode());
			item.addStyleName("pad-text");
			item.setClosable(true);
			item.add(stockPresenter.getView().asWidget());
			tabbedPanel.add(item);
			tabbedPanel.setSelection(item);
		}
	}

	@Override
	public void showRegistryEditor(Exchange exchange, Provider<RegistryPresenter> registryPresenterProvider) {
		String id = REGISTRY_LABEL + "." + exchange.getCode();
		TabItem item = tabbedPanel.findItem(id, false);
		if (item != null) {
			tabbedPanel.setSelection(item);
		} else {
			RegistryPresenter registryPresenter = registryPresenterProvider.get();
			registryPresenter.setExchange(exchange);
			item = new TabItem();
			item.setText(REGISTRY_LABEL + " (" + exchange.getCode() + ")");
			item.setItemId(id);
			item.addStyleName("pad-text");
			item.setClosable(true);
			item.add(registryPresenter.getView().asWidget());
			tabbedPanel.add(item);
			tabbedPanel.setSelection(item);
		}
	}
	
	
	@Override
	public Widget asWidget() {
		tabbedPanel.setSize(width, height-90);
		return tabbedPanel;
	}

	@Override
	public void showUserSetupEditor(Provider<UsersSetupPresenter> usersSetupPresenter) {
		String id = USERS_LABEL;
		TabItem item = tabbedPanel.findItem(id, false);
		if (item != null) {
			tabbedPanel.setSelection(item);
		} else {
			UsersSetupPresenter userSetupPresenter = usersSetupPresenter.get();
			item = new TabItem();
			item.setText(USERS_LABEL);
			item.setItemId(id);
			item.addStyleName("pad-text");
			item.setClosable(true);
			item.add(userSetupPresenter.getView().asWidget());
			tabbedPanel.add(item);
			tabbedPanel.setSelection(item);
		}
	}
}
