package org.sjd.gordon.client.viewer;

import java.util.ArrayList;

import org.sjd.gordon.model.StockSplit;
import org.sjd.gordon.model.TreasuryHeldStock;

import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.client.DispatchAsync;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealRootContentEvent;

public class StockAdminPresenter extends Presenter<StockAdminPresenter.StockAdminView, StockAdminPresenter.StockAdminProxy> implements StockAdminUIHandler {

	@ProxyStandard
	public interface StockAdminProxy extends Proxy<StockAdminPresenter> { }

	public interface StockAdminView extends View, HasUiHandlers<StockAdminUIHandler> {
		public void setSplits(ArrayList<StockSplit> splits);
		public void remove(StockSplit split);
		public void add(StockSplit split);
		public void update(StockSplit split);
		
		public void setTreasuryHeldStock(ArrayList<TreasuryHeldStock> heldStock);
		public void remove(TreasuryHeldStock heldStock);
		public void add(TreasuryHeldStock heldStock);
		public void update(TreasuryHeldStock heldStock);
	}
	
	private final DispatchAsync dispatcher;
	
	@Inject
	public StockAdminPresenter(EventBus eventBus, StockAdminView view, StockAdminProxy proxy, DispatchAsync dispatcher) {
		super(eventBus,view,proxy);
		this.dispatcher = dispatcher;
		getView().setUiHandlers(this);
	}
	
	@Override
	protected void onBind() { }

	@Override
	protected void onUnbind() { }

	@Override
	protected void revealInParent() {
		RevealRootContentEvent.fire(this, this);
	}

	@Override
	public void add(StockSplit newSplit) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(StockSplit split) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(StockSplit splitSelected) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void load() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void add(TreasuryHeldStock heldStock) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(TreasuryHeldStock heldStock) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(TreasuryHeldStock heldStock) {
		// TODO Auto-generated method stub
		
	}

}
