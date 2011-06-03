package org.sjd.gordon.client.viewer;

import org.sjd.gordon.model.BusinessSummary;
import org.sjd.gordon.shared.navigation.StockName;
import org.sjd.gordon.shared.viewer.GetStockProfileAction;
import org.sjd.gordon.shared.viewer.StockProfile;
import org.sjd.gordon.shared.viewer.UpdateBusinessSummaryAction;

import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.client.DispatchAsync;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealRootContentEvent;

public class StockProfilePresenter extends Presenter<StockProfilePresenter.StockProfileView, StockProfilePresenter.StockProfileProxy> implements StockProfileUIHandler {
	
	@ProxyStandard
	public interface StockProfileProxy extends Proxy<StockProfilePresenter> { }

	public interface StockProfileView extends View, HasUiHandlers<StockProfileUIHandler> { 
		public void setProfile(StockProfile stockProfile);
		public void updateBusinessSummary(BusinessSummary summary);
	}
	
	private final DispatchAsync dispatcher;
	private StockName stockName;
	
	@Inject
	public StockProfilePresenter(EventBus eventBus, StockProfileView view, StockProfileProxy proxy, DispatchAsync dispatcher) {
		super(eventBus,view,proxy);
		this.dispatcher = dispatcher;
		getView().setUiHandlers(this);
	}	
	
	@Override
	protected void onBind() { }

	@Override
	protected void onUnbind() { }

	public void setStock(StockName stockName) {
		this.stockName = stockName;
		GetStockProfileAction getProfile = new GetStockProfileAction(stockName.getId());
		dispatcher.execute(getProfile, new LoadStockDetailsCallback() {
			@Override
			public void loaded(StockProfile stockProfile) {
				getView().setProfile(stockProfile);
			}
		});
	}

	@Override
	protected void revealInParent() {
		RevealRootContentEvent.fire(this, this);
	}

	@Override
	public void updateBusinessSummary(BusinessSummary summary) {
		UpdateBusinessSummaryAction updateBusinessSummary = new UpdateBusinessSummaryAction(summary,stockName.getId());
		dispatcher.execute(updateBusinessSummary, new UpdateBusinessSummaryCallback() {
			@Override
			public void updated(BusinessSummary summary) {
				getView().updateBusinessSummary(summary);
			}
		});
	}

}
