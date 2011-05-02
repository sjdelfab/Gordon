package org.sjd.gordon.client;


import org.sjd.gordon.client.main.MainPagePresenter;
import org.sjd.gordon.client.main.TitleStripPresenter;
import org.sjd.gordon.client.navigation.NavigationPresenter;
import org.sjd.gordon.client.registry.RegistryPresenter;
import org.sjd.gordon.client.viewer.GeneralInformationPresenter;
import org.sjd.gordon.client.viewer.StockPresenter;
import org.sjd.gordon.client.viewer.TabbedPanelPresenter;
import org.sjd.gordon.client.viewer.TradeHistoryPresenter;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.google.inject.Provider;
import com.gwtplatform.dispatch.client.gin.DispatchAsyncModule;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyFailureHandler;

@GinModules({DispatchAsyncModule.class, GordonClientModule.class})
public interface GordonGinjector extends Ginjector {

	EventBus getEventBus();
	Provider<MainPagePresenter> getMainPagePresenter();
	Provider<NavigationPresenter> getNavigationPresenter();
	PlaceManager getPlaceManager();
	ProxyFailureHandler getProxyFailureHandler();
	  
	Provider<TabbedPanelPresenter> getMainPresenter();
	Provider<StockPresenter> getStockPresenter();
	Provider<TradeHistoryPresenter> getTradeHistoryPresenter();
	Provider<GeneralInformationPresenter> getGeneralInformationPresenter();
	Provider<TitleStripPresenter> getTitleStripPresenter();
	Provider<RegistryPresenter> getRegistryPresenter();
}
