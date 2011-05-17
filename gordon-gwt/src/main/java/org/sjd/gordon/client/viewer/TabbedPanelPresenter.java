package org.sjd.gordon.client.viewer;

import org.sjd.gordon.client.GordonGinjector;
import org.sjd.gordon.client.navigation.ShowStockEvent;
import org.sjd.gordon.client.navigation.ShowStockEvent.ShowStockHandler;
import org.sjd.gordon.client.registry.RegistryPresenter;
import org.sjd.gordon.client.registry.ShowRegistryEvent;
import org.sjd.gordon.client.registry.ShowRegistryEvent.ShowRegistryHandler;
import org.sjd.gordon.client.security.ShowUserSetupEvent;
import org.sjd.gordon.client.security.ShowUserSetupEvent.ShowUserSetupHandler;
import org.sjd.gordon.client.security.UsersSetupPresenter;
import org.sjd.gordon.model.Exchange;
import org.sjd.gordon.shared.viewer.StockDetail;

import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealRootContentEvent;

public class TabbedPanelPresenter extends Presenter<TabbedPanelPresenter.TabbedPanelView, TabbedPanelPresenter.TabbedPanelProxy> {
	
	@ProxyStandard
	public interface TabbedPanelProxy extends Proxy<TabbedPanelPresenter> { }

	public interface TabbedPanelView extends View { 
		public void addStock(StockPresenter stockPresenter, StockDetail stockDetails);
		public void showRegistryEditor(Exchange exchange, Provider<RegistryPresenter> registryPresenterProvider);
		public void showUserSetupEditor(Provider<UsersSetupPresenter> usersSetupPresenter);
	}

	private GordonGinjector injector;
	
	@Inject
	public TabbedPanelPresenter(EventBus eventBus, TabbedPanelView view, TabbedPanelProxy proxy, final GordonGinjector injector) {
		super(eventBus,view,proxy);
		this.injector = injector;
		eventBus.addHandler(ShowStockEvent.getType(), new ShowStockHandler() {
			@Override
			public void onShowStock(ShowStockEvent event) {
				StockPresenter stockPresenter = injector.getStockPresenter().get();
				stockPresenter.getView().setStock(event.getStockDetails());
				getView().addStock(stockPresenter, event.getStockDetails());
			}
		});
		eventBus.addHandler(ShowRegistryEvent.getType(), new ShowRegistryHandler() {
			@Override
			public void onShowRegistry(ShowRegistryEvent event) {
				TabbedPanelPresenter.this.showRegistryEditor(event.getExchange());
			}
		});
		eventBus.addHandler(ShowUserSetupEvent.getType(), new ShowUserSetupHandler() {
			@Override
			public void onShowUserSetup(ShowUserSetupEvent event) {
				TabbedPanelPresenter.this.showUserSetupEditor();
			}
		});

	}	

	@Override
	protected void revealInParent() {
		RevealRootContentEvent.fire(this, this);
	}

	private void showRegistryEditor(Exchange exchange) {
		getView().showRegistryEditor(exchange, injector.getRegistryPresenter());
	}
	
	private void showUserSetupEditor() {
		getView().showUserSetupEditor(injector.getUsersSetupPresenter());
	}
}
