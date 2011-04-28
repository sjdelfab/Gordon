package org.sjd.gordon.client.viewer;

import org.sjd.gordon.shared.viewer.StockDetails;

import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;

public class TabbedPanelViewImpl extends ViewImpl implements TabbedPanelPresenter.TabbedPanelView {

	private TabPanel tabbedPanel;
	
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
	public Widget asWidget() {
		return tabbedPanel;
	}
}
