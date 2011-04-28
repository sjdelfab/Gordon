package org.sjd.gordon.client.viewer;

import org.sjd.gordon.shared.viewer.StockDetails;

import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealRootContentEvent;

public class StockPresenter extends Presenter<StockPresenter.StockPanelView,StockPresenter.StockPanelProxy> {

	@ProxyStandard
	public interface StockPanelProxy extends Proxy<StockPresenter> { }

	public interface StockPanelView extends View { 
		public void setStock(StockDetails stockDetails);
	}
	
	@Inject
	public StockPresenter(EventBus eventBus, StockPanelView view, StockPanelProxy proxy) {
		super(eventBus,view,proxy);
	}
	
	@Override
	protected void onBind() { }

	@Override
	protected void onUnbind() { }

	@Override
	protected void revealInParent() {
		RevealRootContentEvent.fire(this, this);
	}

}
