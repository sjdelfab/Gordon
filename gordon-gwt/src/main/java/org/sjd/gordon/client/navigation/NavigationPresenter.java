package org.sjd.gordon.client.navigation;

import java.util.ArrayList;

import net.customware.gwt.dispatch.client.DispatchAsync;
import net.customware.gwt.presenter.client.EventBus;
import net.customware.gwt.presenter.client.widget.WidgetPresenter;

import org.sjd.gordon.client.viewer.LoadStockDetailsCallback;
import org.sjd.gordon.model.Exchange;
import org.sjd.gordon.shared.navigation.GetExchanges;
import org.sjd.gordon.shared.navigation.GetStocks;
import org.sjd.gordon.shared.navigation.StockName;
import org.sjd.gordon.shared.viewer.GetStockDetails;
import org.sjd.gordon.shared.viewer.StockDetails;

import com.extjs.gxt.ui.client.data.BeanModel;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.inject.Inject;

public class NavigationPresenter extends WidgetPresenter<NavigationDisplay> {

	private final DispatchAsync dispatcher;
	
	@Inject
	public NavigationPresenter(final NavigationDisplay display, final EventBus eventBus, final DispatchAsync dispatcher) {
		super(display,eventBus);
		this.dispatcher = dispatcher;
		bind();
	}
	
	@Override
	protected void onBind() {
		load();
		getDisplay().getViewHandler().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent arg0) {
				StockName stock = (StockName)getDisplay().getStock().getValue().getBean();
				if (stock != null) {
					showStock(stock);
				}
			}
		});
		getDisplay().getExchange().addValueChangeHandler(new ValueChangeHandler<BeanModel>() {
			@Override
			public void onValueChange(ValueChangeEvent<BeanModel> event) {
				if (event.getValue() != null) {
					GetStocks getStocks = new GetStocks((Integer)event.getValue().get("id"));
					dispatcher.execute(getStocks, new LoadStocksCallback() {
						@Override
						public void loaded(ArrayList<StockName> stocks) {
							getDisplay().setStocks(stocks);
						}
					});
				}
			}
		});
	}

	private void load() {
		GetExchanges getExchanges = new GetExchanges();
		dispatcher.execute(getExchanges, new LoadExchangesCallback() {
			@Override
			public void loaded(ArrayList<Exchange> exchanges) {
				getDisplay().setExchanges(exchanges);
			}
		});
	}
	
	private void showStock(StockName stockName) {
		GetStockDetails getExchanges = new GetStockDetails(stockName.getId());
		dispatcher.execute(getExchanges, new LoadStockDetailsCallback() {
			@Override
			public void loaded(StockDetails stockDetails) {
				eventBus.fireEvent(new ShowStockEvent(stockDetails));
			}
		});
	}

	@Override
	protected void onUnbind() { }

	@Override
	protected void onRevealDisplay() { }
	
}
