package org.sjd.gordon.client.navigation;

import java.util.ArrayList;

import net.customware.gwt.dispatch.client.DispatchAsync;
import net.customware.gwt.presenter.client.EventBus;
import net.customware.gwt.presenter.client.widget.WidgetDisplay;
import net.customware.gwt.presenter.client.widget.WidgetPresenter;

import org.sjd.gordon.model.StockEntity;
import org.sjd.gordon.shared.navigation.GetStocks;

import com.extjs.gxt.ui.client.data.BeanModel;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HasValue;
import com.google.inject.Inject;

public class NavigationPresenter extends WidgetPresenter<NavigationView> {

	public interface NavigationDisplay extends WidgetDisplay {
		public HasValue<BeanModel> getStock();
		public HasClickHandlers getViewHandler();
	}

	private final DispatchAsync dispatcher;
	
	@Inject
	public NavigationPresenter(final NavigationView display, final EventBus eventBus, final DispatchAsync dispatcher) {
		super(display,eventBus);
		this.dispatcher = dispatcher;
		bind();
	}
	
	@Override
	protected void onBind() {
		load();
	}

	private void load() {
		GetStocks getStocks = new GetStocks();
		dispatcher.execute(getStocks, new LoadStocksCallback() {
			@Override
			public void loaded(ArrayList<StockEntity> stocks) {
				getDisplay().setStocks(stocks);
			}
		});
	}

	@Override
	protected void onUnbind() { }

	@Override
	protected void onRevealDisplay() { }
	
}
