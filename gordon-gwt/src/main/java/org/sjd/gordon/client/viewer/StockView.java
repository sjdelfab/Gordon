package org.sjd.gordon.client.viewer;

import org.sjd.gordon.shared.navigation.StockName;

import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.TabPanelEvent;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.gwtplatform.mvp.client.ViewImpl;

public class StockView extends ViewImpl implements StockPresenter.StockPanelView {

	private TabPanel tabbedPanel;
	private StockProfilePresenter stockProfilePresenter;
	private TradeHistoryPresenter tradeHistoryPresenter;
	private StockName stock;
	@Inject
	@Named("width")
	private int width;
	@Inject
	@Named("height")
	private int height;
	
	@Inject
	public StockView(StockProfilePresenter stockProfilePresenter, TradeHistoryPresenter tradeHistoryPresenter, final StockAdminPresenter stockAdminPresenter) {
		this.stockProfilePresenter = stockProfilePresenter;
		this.tradeHistoryPresenter = tradeHistoryPresenter;

		tabbedPanel = new TabPanel();
		tabbedPanel.setTabScroll(true);
		tabbedPanel.setCloseContextMenu(false);

		TabItem generalTabItem = new TabItem("Profile");
		generalTabItem.add(stockProfilePresenter.getView().asWidget());
		tabbedPanel.add(generalTabItem);

		TabItem tradeHistoryTabItem = new TabItem("Trade History");
		tradeHistoryTabItem.add(tradeHistoryPresenter.getView().asWidget());
		tabbedPanel.add(tradeHistoryTabItem);
		tradeHistoryTabItem.addListener(Events.Select, new Listener<TabPanelEvent>() {
			@Override
			public void handleEvent(TabPanelEvent be) {
				StockView.this.tradeHistoryPresenter.setStock(stock);
			}

		});
		
		TabItem stockAdminTabItem = new TabItem("Administration");
		stockAdminTabItem.add(stockAdminPresenter.getView().asWidget());
		tabbedPanel.add(stockAdminTabItem);
		stockAdminTabItem.addListener(Events.Select, new Listener<TabPanelEvent>() {
			@Override
			public void handleEvent(TabPanelEvent be) {
				stockAdminPresenter.setStock(stock);
			}

		});

	}

	@Override
	public void setStock(StockName stock) {
		this.stock = stock;
		stockProfilePresenter.setStock(stock);
	}

	@Override
	public Widget asWidget() {
		tabbedPanel.setSize(width, height-50);
		return tabbedPanel;
	}

}
