package org.sjd.gordon.client;


import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.data.BeanModel;
import com.extjs.gxt.ui.client.store.ListStore;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.gwtplatform.mvp.client.DelayedBindRegistry;

public class Gordon implements EntryPoint {

	public static final String STOCKS_STORE = "stocksStore";
	public static final String EXCHANGE_STORE = "exchangeStore";
	
	private final GordonGinjector injector = GWT.create(GordonGinjector.class);
	
	public void onModuleLoad() {
		Registry.register(STOCKS_STORE,  new ListStore<BeanModel>());
		Registry.register(EXCHANGE_STORE,  new ListStore<BeanModel>());
		DelayedBindRegistry.bind(injector);
	    injector.getPlaceManager().revealCurrentPlace();
	}

}
