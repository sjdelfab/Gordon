package org.sjd.gordon.client;

import net.customware.gwt.presenter.client.DefaultEventBus;
import net.customware.gwt.presenter.client.EventBus;
import net.customware.gwt.presenter.client.gin.AbstractPresenterModule;

import org.sjd.gordon.client.navigation.NavigationDisplay;
import org.sjd.gordon.client.navigation.NavigationPresenter;
import org.sjd.gordon.client.navigation.NavigationView;
import org.sjd.gordon.client.security.LoginDisplay;
import org.sjd.gordon.client.security.LoginPresenter;
import org.sjd.gordon.client.security.LoginView;
import org.sjd.gordon.client.viewer.GeneralInformationDisplay;
import org.sjd.gordon.client.viewer.GeneralInformationPresenter;
import org.sjd.gordon.client.viewer.GeneralInformationView;
import org.sjd.gordon.client.viewer.StockDisplay;
import org.sjd.gordon.client.viewer.StockPresenter;
import org.sjd.gordon.client.viewer.StockView;
import org.sjd.gordon.client.viewer.TradeHistoryDisplay;
import org.sjd.gordon.client.viewer.TradeHistoryPresenter;
import org.sjd.gordon.client.viewer.TradeHistoryView;

import com.google.inject.Singleton;

public class GordonClientModule extends AbstractPresenterModule {

	@Override
	protected void configure() {
		bind(EventBus.class).to(DefaultEventBus.class).in(Singleton.class);
		bindPresenter(LoginPresenter.class, LoginDisplay.class, LoginView.class);
		bindPresenter(NavigationPresenter.class, NavigationDisplay.class,NavigationView.class);
		bindPresenter(MainPresenter.class, MainDisplay.class,MainView.class);
		bindPresenter(StockPresenter.class, StockDisplay.class,StockView.class);
		bindPresenter(GeneralInformationPresenter.class, GeneralInformationDisplay.class,GeneralInformationView.class);
		bindPresenter(TradeHistoryPresenter.class, TradeHistoryDisplay.class,TradeHistoryView.class);
	}

}
