package org.sjd.gordon.client;

import org.sjd.gordon.client.main.MainPage;
import org.sjd.gordon.client.main.MainPagePresenter;
import org.sjd.gordon.client.main.TitleStrip;
import org.sjd.gordon.client.main.TitleStripPresenter;
import org.sjd.gordon.client.navigation.NavigationPresenter;
import org.sjd.gordon.client.navigation.NavigationView;
import org.sjd.gordon.client.registry.RegistryPresenter;
import org.sjd.gordon.client.registry.RegistryViewImpl;
import org.sjd.gordon.client.viewer.GeneralInformationPresenter;
import org.sjd.gordon.client.viewer.GeneralInformationViewImpl;
import org.sjd.gordon.client.viewer.StockPresenter;
import org.sjd.gordon.client.viewer.StockView;
import org.sjd.gordon.client.viewer.TabbedPanelPresenter;
import org.sjd.gordon.client.viewer.TabbedPanelViewImpl;
import org.sjd.gordon.client.viewer.TradeHistoryPresenter;
import org.sjd.gordon.client.viewer.TradeHistoryViewImpl;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.inject.Singleton;
import com.google.inject.name.Names;
import com.gwtplatform.mvp.client.DefaultProxyFailureHandler;
import com.gwtplatform.mvp.client.RootPresenter;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.gwtplatform.mvp.client.proxy.ParameterTokenFormatter;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyFailureHandler;
import com.gwtplatform.mvp.client.proxy.TokenFormatter;

public class GordonClientModule extends AbstractPresenterModule {

	@Override
	protected void configure() {
		bind(EventBus.class).to(SimpleEventBus.class).in(Singleton.class);
		bind(PlaceManager.class).to(GordonPlaceManager.class).in(Singleton.class);
		bind(TokenFormatter.class).to(ParameterTokenFormatter.class).in(Singleton.class);
		bind(RootPresenter.class).asEagerSingleton();
		bind(ProxyFailureHandler.class).to(DefaultProxyFailureHandler.class).in(Singleton.class);
		
		bindConstant().annotatedWith(Names.named("width")).to(1344);
		bindConstant().annotatedWith(Names.named("height")).to(756);
		
		bindPresenter(NavigationPresenter.class, NavigationPresenter.NavigationPanelView.class, NavigationView.class, 
			      NavigationPresenter.NavigationPanelProxy.class);
		bindPresenter(MainPagePresenter.class, MainPagePresenter.MainPageView.class,
			           MainPage.class, MainPagePresenter.MainPageProxy.class);
		
		bindPresenter(TabbedPanelPresenter.class, TabbedPanelPresenter.TabbedPanelView.class, TabbedPanelViewImpl.class, 
				      TabbedPanelPresenter.TabbedPanelProxy.class);
		bindPresenterWidget(StockPresenter.class, StockPresenter.StockPanelView.class, StockView.class);
		bindPresenterWidget(GeneralInformationPresenter.class, GeneralInformationPresenter.GeneralInformationView.class, 
				GeneralInformationViewImpl.class);
		bindPresenterWidget(TradeHistoryPresenter.class, TradeHistoryPresenter.TradeHistoryView.class, TradeHistoryViewImpl.class);
		
		bindPresenter(TitleStripPresenter.class, TitleStripPresenter.TitleStripView.class, TitleStrip.class, 
				TitleStripPresenter.TitleStripProxy.class);
		bindPresenterWidget(RegistryPresenter.class, RegistryPresenter.RegistryPanelView.class, RegistryViewImpl.class);
		
	}

}
