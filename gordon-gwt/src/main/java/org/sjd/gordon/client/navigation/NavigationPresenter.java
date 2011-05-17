package org.sjd.gordon.client.navigation;

import java.util.ArrayList;

import org.sjd.gordon.client.viewer.LoadStockDetailsCallback;
import org.sjd.gordon.model.Exchange;
import org.sjd.gordon.shared.navigation.GetExchangesAction;
import org.sjd.gordon.shared.navigation.GetStocksAction;
import org.sjd.gordon.shared.navigation.StockName;
import org.sjd.gordon.shared.viewer.GetStockDetailsAction;
import org.sjd.gordon.shared.viewer.StockDetail;

import com.extjs.gxt.ui.client.data.BeanModel;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.HasValue;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.client.DispatchAsync;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealRootContentEvent;

public class NavigationPresenter extends Presenter<NavigationPresenter.NavigationPanelView, NavigationPresenter.NavigationPanelProxy> {

	@ProxyStandard
	public interface NavigationPanelProxy extends Proxy<NavigationPresenter> {
	}

	public interface NavigationPanelView extends View {
		
		public HasValue<BeanModel> getStock();

		public HasValue<BeanModel> getExchange();

		public HasClickHandlers getExchangeHandler();

		public HasClickHandlers getViewHandler();

		public void setStocks(ArrayList<StockName> stocks);

		public void setExchanges(ArrayList<Exchange> exchanges);
	}

	private final DispatchAsync dispatcher;

	@Inject
	public NavigationPresenter(EventBus eventBus, NavigationPanelView view, NavigationPanelProxy proxy, DispatchAsync dispatcher) {
		super(eventBus, view, proxy);
		this.dispatcher = dispatcher;
	}

	@Override
	protected void onBind() {
		load();
		getView().getViewHandler().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent arg0) {
				StockName stock = (StockName) getView().getStock().getValue().getBean();
				if (stock != null) {
					showStock(stock);
				}
			}
		});
		getView().getExchange().addValueChangeHandler(new ValueChangeHandler<BeanModel>() {
			@Override
			public void onValueChange(ValueChangeEvent<BeanModel> event) {
				if (event.getValue() != null) {
					GetStocksAction getStocks = new GetStocksAction((Integer) event.getValue().get("id"));
					dispatcher.execute(getStocks, new LoadStocksCallback() {
						@Override
						public void loaded(ArrayList<StockName> stocks) {
							getView().setStocks(stocks);
						}
					});
				}
			}
		});
	}

	private void load() {
		GetExchangesAction getExchanges = new GetExchangesAction();
		dispatcher.execute(getExchanges, new LoadExchangesCallback() {
			@Override
			public void loaded(ArrayList<Exchange> exchanges) {
				getView().setExchanges(exchanges);
			}
		});
	}

	private void showStock(StockName stockName) {
		GetStockDetailsAction getExchanges = new GetStockDetailsAction(stockName.getId());
		dispatcher.execute(getExchanges, new LoadStockDetailsCallback() {
			@Override
			public void loaded(StockDetail stockDetails) {
				getEventBus().fireEvent(new ShowStockEvent(stockDetails));
			}
		});
	}

	@Override
	protected void onUnbind() {
	}

	@Override
	protected void revealInParent() {
		RevealRootContentEvent.fire(this, this);
	}

}
