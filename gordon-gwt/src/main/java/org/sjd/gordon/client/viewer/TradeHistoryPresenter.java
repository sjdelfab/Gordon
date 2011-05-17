package org.sjd.gordon.client.viewer;

import java.util.ArrayList;

import org.sjd.gordon.model.StockDayTradeRecord;
import org.sjd.gordon.shared.viewer.GetTradeHistoryAction;
import org.sjd.gordon.shared.viewer.StockDetail;

import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.client.DispatchAsync;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealRootContentEvent;

public class TradeHistoryPresenter extends Presenter<TradeHistoryPresenter.TradeHistoryView,TradeHistoryPresenter.TradeHistoryProxy> {
	
	@ProxyStandard
	public interface TradeHistoryProxy extends Proxy<TradeHistoryPresenter> { }

	public interface TradeHistoryView extends View { 
		public void setTradeHistory(ArrayList<StockDayTradeRecord> tradeHistory);
	}	
	
	private StockDetail stockDetails;
	private final DispatchAsync dispatcher;
	
	@Inject
	public TradeHistoryPresenter(EventBus eventBus, TradeHistoryView view, TradeHistoryProxy proxy, DispatchAsync dispatcher) {
		super(eventBus,view,proxy);
		this.dispatcher = dispatcher;
		bind();
	}	
	
	@Override
	protected void onBind() { }

	@Override
	protected void onUnbind() { }


	public void setStock(StockDetail stockDetails) {
		if (this.stockDetails == null) {
			this.stockDetails = stockDetails;
			GetTradeHistoryAction getTradeHistoryAction = new GetTradeHistoryAction(this.stockDetails.getId());
			dispatcher.execute(getTradeHistoryAction, new LoadTradeHistoryCallback() {
				@Override
				public void loaded(ArrayList<StockDayTradeRecord> tradeHistory) {
					getView().setTradeHistory(tradeHistory);
				}
			});
		}
	}

	@Override
	protected void revealInParent() {
		RevealRootContentEvent.fire(this, this);
	}

}
