package org.sjd.gordon.client.viewer;

import net.customware.gwt.dispatch.client.DispatchAsync;
import net.customware.gwt.presenter.client.EventBus;
import net.customware.gwt.presenter.client.widget.WidgetPresenter;

import com.google.inject.Inject;

public class StockPresenter extends WidgetPresenter<StockDisplay> {

	@Inject
	public StockPresenter(final StockDisplay display, final EventBus eventBus, final DispatchAsync dispatcher) {
		super(display,eventBus);
		bind();
	}
	
	@Override
	protected void onBind() { }

	@Override
	protected void onUnbind() { }

	@Override
	protected void onRevealDisplay() { }

}
