package org.sjd.gordon.client.viewer;

import org.sjd.gordon.client.registry.RegistryPresenter;
import org.sjd.gordon.model.Exchange;
import org.sjd.gordon.shared.viewer.StockDetails;

import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.name.Named;
import com.gwtplatform.mvp.client.ViewImpl;

public class TabbedPanelViewImpl extends ViewImpl implements TabbedPanelPresenter.TabbedPanelView {

	private final String REGISTRY_LABEL = "Registry";
	
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
	}
	
	public void addStock(StockPresenter stockPresenter, StockDetails stockDetails) {
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
		tabbedPanel.setSize(width-200, height-50);
		return tabbedPanel;
	}
}
