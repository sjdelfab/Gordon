package org.sjd.gordon.client.viewer;

import org.sjd.gordon.shared.viewer.StockDetails;

import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.TabPanelEvent;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.google.gwt.user.client.Element;
import com.google.inject.Inject;

public class StockView extends LayoutContainer implements StockDisplay {

	  private TabPanel tabbedPanel;
	  private GeneralInformationPresenter generalInformalPresenter;
	  private TradeHistoryPresenter tradeHistoryPresenter;
	  private StockDetails stockDetails;
	  
	  @Inject
	  public StockView(GeneralInformationPresenter generalInformalPresenter, TradeHistoryPresenter tradeHistoryPresenter) {
		  this.generalInformalPresenter = generalInformalPresenter;
		  this.tradeHistoryPresenter = tradeHistoryPresenter;
	  }
	  
	  @Override  
	  protected void onRender(Element parent, int pos) {  
	    super.onRender(parent, pos);  
	    setLayout(new BorderLayout());
	    tabbedPanel = new TabPanel();  
	    tabbedPanel.setSize(600, 650);  
	    tabbedPanel.setTabScroll(true);  
	    tabbedPanel.setCloseContextMenu(false);  
	  
	    BorderLayoutData centerData = new BorderLayoutData(LayoutRegion.CENTER);
	    add(tabbedPanel,centerData);  
	    
	    TabItem generalTabItem = new TabItem("General");  
	    generalTabItem.add(generalInformalPresenter.getDisplay().asWidget());
	    tabbedPanel.add(generalTabItem);
	    
	    TabItem tradeHistoryTabItem = new TabItem("Trade History");  
	    tradeHistoryTabItem.add(tradeHistoryPresenter.getDisplay().asWidget());
	    tabbedPanel.add(tradeHistoryTabItem);
	    tradeHistoryTabItem.addListener(Events.Select, new Listener<TabPanelEvent>() {
			@Override
			public void handleEvent(TabPanelEvent be) {
				tradeHistoryPresenter.setStock(stockDetails);
			}
	    	
	    });
	  }

	@Override
	public void setStock(StockDetails stockDetails) {
		this.stockDetails = stockDetails;
		generalInformalPresenter.setStock(stockDetails);
	}  	  

}
