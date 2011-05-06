package org.sjd.gordon.client.viewer;

import org.sjd.gordon.shared.viewer.StockDetails;

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
	private StockProfilePresenter generalInformalPresenter;
	private TradeHistoryPresenter tradeHistoryPresenter;
	private StockDetails stockDetails;
	@Inject
	@Named("width")
	private int width;
	@Inject
	@Named("height")
	private int height;
	
	@Inject
	public StockView(StockProfilePresenter generalInformalPresenter, final TradeHistoryPresenter tradeHistoryPresenter) {
		this.generalInformalPresenter = generalInformalPresenter;
		this.tradeHistoryPresenter = tradeHistoryPresenter;

		tabbedPanel = new TabPanel();
		tabbedPanel.setTabScroll(true);
		tabbedPanel.setCloseContextMenu(false);

		TabItem generalTabItem = new TabItem("Profile");
		generalTabItem.add(generalInformalPresenter.getView().asWidget());
		tabbedPanel.add(generalTabItem);

		TabItem tradeHistoryTabItem = new TabItem("Trade History");
		tradeHistoryTabItem.add(tradeHistoryPresenter.getView().asWidget());
		tabbedPanel.add(tradeHistoryTabItem);
		tradeHistoryTabItem.addListener(Events.Select, new Listener<TabPanelEvent>() {
			@Override
			public void handleEvent(TabPanelEvent be) {
				StockView.this.tradeHistoryPresenter.setStock(stockDetails);
			}

		});
	}

	@Override
	public void setStock(StockDetails stockDetails) {
		this.stockDetails = stockDetails;
		generalInformalPresenter.setStock(stockDetails);
	}

	@Override
	public Widget asWidget() {
		tabbedPanel.setSize(width-200, height-50);
		return tabbedPanel;
	}

}
