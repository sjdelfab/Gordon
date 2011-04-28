package org.sjd.gordon.client.viewer;

import org.sjd.gordon.client.GordonGinjector;
import org.sjd.gordon.client.navigation.ShowStockEvent;
import org.sjd.gordon.client.navigation.ShowStockEventHandler;
import org.sjd.gordon.shared.viewer.StockDetails;

import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealRootContentEvent;

public class TabbedPanelPresenter extends Presenter<TabbedPanelPresenter.TabbedPanelView, TabbedPanelPresenter.TabbedPanelProxy> {
	
	@ProxyStandard
	public interface TabbedPanelProxy extends Proxy<TabbedPanelPresenter> { }

	public interface TabbedPanelView extends View { 
		public void addStock(StockPresenter stockPresenter, StockDetails stockDetails);
	}

	@Inject
	public TabbedPanelPresenter(EventBus eventBus, TabbedPanelView view, TabbedPanelProxy proxy, final GordonGinjector injector) {
		super(eventBus,view,proxy);
		eventBus.addHandler(ShowStockEvent.TYPE, new ShowStockEventHandler() {
			@Override
			public void show(ShowStockEvent event) {
				StockPresenter stockPresenter = injector.getStockPresenter().get();
				stockPresenter.getView().setStock(event.getStockDetails());
				getView().addStock(stockPresenter, event.getStockDetails());
			}
		});
	}	

	@Override
	protected void revealInParent() {
		RevealRootContentEvent.fire(this, this);
	}

}
