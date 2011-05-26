package org.sjd.gordon.client;


import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.data.BeanModel;
import com.extjs.gxt.ui.client.store.ListStore;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NodeList;
import com.google.gwt.user.client.ui.RootPanel;
import com.gwtplatform.mvp.client.DelayedBindRegistry;

public class Gordon implements EntryPoint {

	public static final String STOCKS_STORE = "stocksStoreBeanModel";
	public static final String EXCHANGE_STORE = "exchangeStoreBeanModel";
	public static final String STOCK_SPLIT = "stockSplitBeanModel";
	public static final String TREASURY_HELD_STOCK = "treasuryHeldStockBeanModel";
	
	private final GordonGinjector injector = GWT.create(GordonGinjector.class);
	
	public void onModuleLoad() {
		Registry.register(STOCKS_STORE,  new ListStore<BeanModel>());
		Registry.register(EXCHANGE_STORE,  new ListStore<BeanModel>());
		Registry.register(STOCK_SPLIT,  new ListStore<BeanModel>());
		Registry.register(TREASURY_HELD_STOCK,  new ListStore<BeanModel>());
		DelayedBindRegistry.bind(injector);
	    injector.getPlaceManager().revealCurrentPlace();
	    NodeList<Element> elements = RootPanel.getBodyElement().getElementsByTagName("div");
		if (elements.getLength() != 0) {
			RootPanel.getBodyElement().removeChild(elements.getItem(0));
		}
	}

}
