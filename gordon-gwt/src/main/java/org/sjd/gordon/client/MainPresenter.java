package org.sjd.gordon.client;

import net.customware.gwt.presenter.client.EventBus;
import net.customware.gwt.presenter.client.widget.WidgetPresenter;

import com.google.inject.Inject;

public class MainPresenter extends WidgetPresenter<MainDisplay> {
	
	@Inject
	public MainPresenter(final MainDisplay display, final EventBus eventBus) {
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
	

}
