package org.sjd.gordon.client.viewer;

import net.customware.gwt.presenter.client.EventBus;
import net.customware.gwt.presenter.client.widget.WidgetPresenter;

import org.sjd.gordon.shared.viewer.StockDetails;

import com.google.inject.Inject;

public class GeneralInformationPresenter extends WidgetPresenter<GeneralInformationDisplay> {
	
	@Inject
	public GeneralInformationPresenter(final GeneralInformationDisplay display, final EventBus eventBus) {
		super(display,eventBus);
		bind();
	}	
	
	@Override
	protected void onBind() {
		
	}

	@Override
	protected void onUnbind() { }

	@Override
	protected void onRevealDisplay() { }

	public void setStock(StockDetails stockDetails) {
		getDisplay().setStock(stockDetails);
	}

}
