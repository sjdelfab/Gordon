package org.sjd.gordon.client.registry;

import java.util.ArrayList;

import org.sjd.gordon.model.Exchange;
import org.sjd.gordon.shared.registry.DeleteRegistryEntryAction;
import org.sjd.gordon.shared.registry.EditRegistryEntry;
import org.sjd.gordon.shared.registry.EditRegistryEntryAction;
import org.sjd.gordon.shared.registry.GetAllRegistryEntriesAction;
import org.sjd.gordon.shared.registry.GetGicsSectorsAction;
import org.sjd.gordon.shared.registry.GicsSectorName;
import org.sjd.gordon.shared.viewer.StockDetail;

import com.extjs.gxt.ui.client.data.BasePagingLoadResult;
import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.client.DispatchAsync;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealRootContentEvent;

public class RegistryPresenter extends Presenter<RegistryPresenter.RegistryPanelView,RegistryPresenter.RegistryPanelProxy> implements RegistryUIHandler {

	@ProxyStandard
	public interface RegistryPanelProxy extends Proxy<RegistryPresenter> { }

	public interface RegistryPanelView extends View, HasUiHandlers<RegistryUIHandler> {
		public void setGicsSectors(ArrayList<GicsSectorName> sectors);
		public void remove(StockDetail selected);
		public void add(StockDetail stock);
		public void update(StockDetail stock);
	}
	
	private final DispatchAsync dispatcher;
	private Exchange exchange;

	@Inject
	public RegistryPresenter(EventBus eventBus, RegistryPanelView view, RegistryPanelProxy proxy, DispatchAsync dispatcher) {
		super(eventBus, view, proxy);
		this.dispatcher = dispatcher;
		getView().setUiHandlers(this);
	}

	@Override
	public void load(final PagingLoadConfig loadConfig, final AsyncCallback<PagingLoadResult<StockDetail>> callback) {
		if (exchange== null) {
			throw new RuntimeException("Exchange must be specified");
		}
		GetAllRegistryEntriesAction getStockDetails = new GetAllRegistryEntriesAction(exchange.getId(),loadConfig.getLimit(),loadConfig.getOffset());
		dispatcher.execute(getStockDetails, new LoadAllStockDetailsCallback() {
			@Override
			public void loaded(ArrayList<StockDetail> stocks, Integer totalCount) {
				callback.onSuccess(new BasePagingLoadResult<StockDetail>(stocks,loadConfig.getOffset(),totalCount));
			}
		});
	}	
	
	private void loadGicsSectors() {
		dispatcher.execute(new GetGicsSectorsAction(), new LoadGicsSectorNamesCallback() {
			@Override
			public void loaded(ArrayList<GicsSectorName> sectors) {
				getView().setGicsSectors(sectors);
			}
		});
	}
	
	@Override
	public void delete(final StockDetail selected) {
		if (selected != null) {
			dispatcher.execute(new DeleteRegistryEntryAction(selected.getId()), new DeleteEntryCallback() {
				@Override
				public void deleted() {
					getView().remove(selected);
				}
			});
		}
	}
	
	@Override
	public void add(final StockDetail details) {
		EditRegistryEntryAction action = new EditRegistryEntryAction(details, exchange.getId(), EditRegistryEntry.EditType.ADD);
		dispatcher.execute(action, new EditEntryCallback() {
			@Override
			public void commited(StockDetail stock) {
				getView().add(stock);
			}
		});
	}
	
	@Override
	public void update(StockDetail details) {
		EditRegistryEntryAction action = new EditRegistryEntryAction(details, exchange.getId(), EditRegistryEntry.EditType.UPDATE);
		dispatcher.execute(action, new EditEntryCallback() {
			@Override
			public void commited(StockDetail stock) {
				getView().update(stock);
			}
		});
	}
	
	@Override
	protected void onBind() {
		super.onBind();
		loadGicsSectors();
	}
	
	@Override
	protected void onUnbind() { }

	@Override
	protected void revealInParent() {
		RevealRootContentEvent.fire(this, this);
	}
	
	public void setExchange(Exchange exchange) {
		this.exchange = exchange;
	}
}
