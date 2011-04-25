package org.sjd.gordon.client.viewer;

import java.util.ArrayList;

import net.customware.gwt.dispatch.client.DispatchAsync;
import net.customware.gwt.presenter.client.EventBus;
import net.customware.gwt.presenter.client.widget.WidgetPresenter;

import org.sjd.gordon.model.StockDayTradeRecord;
import org.sjd.gordon.shared.viewer.GetTradeHistory;
import org.sjd.gordon.shared.viewer.StockDetails;

import com.google.inject.Inject;

public class TradeHistoryPresenter extends WidgetPresenter<TradeHistoryDisplay> {
	
	private StockDetails stockDetails;
	private final DispatchAsync dispatcher;
	
	@Inject
	public TradeHistoryPresenter(final TradeHistoryDisplay display, final EventBus eventBus, final DispatchAsync dispatcher) {
		super(display,eventBus);
		this.dispatcher = dispatcher;
		bind();
	}	
	
	@Override
	protected void onBind() { }

	@Override
	protected void onUnbind() { }

	@Override
	protected void onRevealDisplay() { }

	public void setStock(StockDetails stockDetails) {
		if (this.stockDetails == null) {
			this.stockDetails = stockDetails;
			GetTradeHistory getExchanges = new GetTradeHistory(this.stockDetails.getId());
			dispatcher.execute(getExchanges, new LoadTradeHistoryCallback() {
				@Override
				public void loaded(ArrayList<StockDayTradeRecord> tradeHistory) {
					getDisplay().setTradeHistory(tradeHistory);
				}
			});
		}
	}

}
