package org.sjd.gordon.client.navigation;

import java.util.ArrayList;

import net.customware.gwt.dispatch.client.DispatchAsync;
import net.customware.gwt.presenter.client.EventBus;
import net.customware.gwt.presenter.client.widget.WidgetPresenter;

import org.sjd.gordon.model.Exchange;
import org.sjd.gordon.model.StockEntity;
import org.sjd.gordon.shared.navigation.GetExchanges;
import org.sjd.gordon.shared.navigation.GetStocks;

import com.allen_sauer.gwt.log.client.Log;
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
				StockEntity stock = (StockEntity)getDisplay().getStock().getValue().getBean();
				if (stock != null) {
				    eventBus.fireEvent(new ShowStockEvent(stock));
				}
			}
		});
		getDisplay().getExchange().addValueChangeHandler(new ValueChangeHandler<BeanModel>() {
			@Override
			public void onValueChange(ValueChangeEvent<BeanModel> event) {
				Log.info("Value changed");
				if (event.getValue() != null) {
					Log.info("Getting stocks");
					GetStocks getStocks = new GetStocks((Integer)event.getValue().get("id"));
					dispatcher.execute(getStocks, new LoadStocksCallback() {
						@Override
						public void loaded(ArrayList<StockEntity> stocks) {
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

	@Override
	protected void onUnbind() { }

	@Override
	protected void onRevealDisplay() { }
	
}
