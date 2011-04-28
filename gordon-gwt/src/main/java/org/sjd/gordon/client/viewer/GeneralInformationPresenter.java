package org.sjd.gordon.client.viewer;

import org.sjd.gordon.shared.viewer.StockDetails;

import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealRootContentEvent;

public class GeneralInformationPresenter extends Presenter<GeneralInformationPresenter.GeneralInformationView,
          												   GeneralInformationPresenter.GeneralInformationProxy> {
	
	@ProxyStandard
	public interface GeneralInformationProxy extends Proxy<GeneralInformationPresenter> { }

	public interface GeneralInformationView extends View { 
		public void setStock(StockDetails stockDetails);
	}
	
	@Inject
	public GeneralInformationPresenter(EventBus eventBus, GeneralInformationView view, GeneralInformationProxy proxy) {
		super(eventBus,view,proxy);
	}	
	
	@Override
	protected void onBind() { }

	@Override
	protected void onUnbind() { }

	public void setStock(StockDetails stockDetails) {
		getView().setStock(stockDetails);
	}

	@Override
	protected void revealInParent() {
		RevealRootContentEvent.fire(this, this);
	}

}
