package org.sjd.gordon.client;

import org.sjd.gordon.client.viewer.StockDisplay;
import org.sjd.gordon.shared.viewer.StockDetails;

import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.google.gwt.user.client.Element;

public class MainView extends LayoutContainer implements MainDisplay {

	  private TabPanel tabbedPanel;  
	  
	  @Override  
	  protected void onRender(Element parent, int pos) {  
	    super.onRender(parent, pos);  
	    setLayout(new BorderLayout());
	    tabbedPanel = new TabPanel();  
	    tabbedPanel.setResizeTabs(true);  
	    tabbedPanel.setAnimScroll(true);  
	    tabbedPanel.setTabScroll(true);  
	    tabbedPanel.setCloseContextMenu(true);
	    BorderLayoutData centerData = new BorderLayoutData(LayoutRegion.CENTER); 
	    add(tabbedPanel,centerData);  
	  }  
	  
	  public void addStock(StockDisplay stockDisplay, StockDetails stockDetails) {  
	    TabItem item = new TabItem();  
	    item.setText(stockDetails.getCode());  
	    item.addStyleName("pad-text");
	    item.setClosable(true);
	    item.add(stockDisplay.asWidget());
	    tabbedPanel.add(item);
	    layout(true);
	  }  
}
