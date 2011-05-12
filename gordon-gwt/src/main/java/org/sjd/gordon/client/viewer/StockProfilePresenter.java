package org.sjd.gordon.client.viewer;

import org.sjd.gordon.shared.viewer.StockDetail;

import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealRootContentEvent;

public class StockProfilePresenter extends Presenter<StockProfilePresenter.StockProfileView,
          												   StockProfilePresenter.StockProfileProxy> {
	
	@ProxyStandard
	public interface StockProfileProxy extends Proxy<StockProfilePresenter> { }

	public interface StockProfileView extends View { 
		public void setStock(StockDetail stockDetails);
	}
	
	@Inject
	public StockProfilePresenter(EventBus eventBus, StockProfileView view, StockProfileProxy proxy) {
		super(eventBus,view,proxy);
	}	
	
	@Override
	protected void onBind() { }

	@Override
	protected void onUnbind() { }

	public void setStock(StockDetail stockDetails) {
		getView().setStock(stockDetails);
	}

	@Override
	protected void revealInParent() {
		RevealRootContentEvent.fire(this, this);
	}

}
