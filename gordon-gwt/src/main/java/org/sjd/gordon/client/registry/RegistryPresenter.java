package org.sjd.gordon.client.registry;

import java.util.ArrayList;

import org.sjd.gordon.model.Exchange;
import org.sjd.gordon.shared.registry.DeleteRegistryEntry;
import org.sjd.gordon.shared.registry.EditRegistryEntry;
import org.sjd.gordon.shared.registry.GetAllStockDetails;
import org.sjd.gordon.shared.registry.GetGicsSectors;
import org.sjd.gordon.shared.registry.GicsSectorName;
import org.sjd.gordon.shared.viewer.StockDetail;

import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.client.DispatchAsync;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealRootContentEvent;

public class RegistryPresenter extends Presenter<RegistryPresenter.RegistryPanelView,RegistryPresenter.RegistryPanelProxy> {

	@ProxyStandard
	public interface RegistryPanelProxy extends Proxy<RegistryPresenter> { }

	public interface RegistryPanelView extends View {
		public void setStocks(ArrayList<StockDetail> stocks);
		public void setGicsSectors(ArrayList<GicsSectorName> sectors);
		public StockDetail getSelectedItem();
		public void remove(StockDetail stock);
		public HasClickHandlers getDeleteHandler();
		public void confirmDeleteRequest(Listener<MessageBoxEvent> callback);
		public HasClickHandlers getAddHandler();
		public void showEditDialog(EditDialogCallback callback);
		public void add(StockDetail details);
		public HasClickHandlers getUpdateHandler();
		public void showEditDialog(StockDetail details, EditDialogCallback callback);
		public void update(StockDetail details);
	}
	
	interface EditDialogCallback {
		void commit(StockDetail details);
	}
	
	private final DispatchAsync dispatcher;
	private Exchange exchange;

	@Inject
	public RegistryPresenter(EventBus eventBus, RegistryPanelView view, RegistryPanelProxy proxy, DispatchAsync dispatcher) {
		super(eventBus, view, proxy);
		this.dispatcher = dispatcher;
	}

	private void load() {
		if (exchange== null) {
			throw new RuntimeException("Exchange must be specified");
		}
		GetAllStockDetails getStockDetails = new GetAllStockDetails(exchange.getId());
		dispatcher.execute(getStockDetails, new LoadAllStockDetailsCallback() {
			@Override
			public void loaded(ArrayList<StockDetail> stocks) {
				getView().setStocks(stocks);
			}
		});
		dispatcher.execute(new GetGicsSectors(), new LoadGicsSectorNamesCallback() {
			@Override
			public void loaded(ArrayList<GicsSectorName> sectors) {
				getView().setGicsSectors(sectors);
			}
		});
	}	
	
	@Override
	protected void onBind() {
		super.onBind();
		getView().getDeleteHandler().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				deleteSelectedEntry();
			}
		});
		getView().getAddHandler().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent arg0) {
				getView().showEditDialog(new EditDialogCallback() {
					@Override
					public void commit(StockDetail details) {
						editEntry(details);
					}
				});
			}
		});
		getView().getUpdateHandler().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent arg0) {
				final StockDetail selected = getView().getSelectedItem();
				if (selected != null) {
					getView().showEditDialog(new StockDetail(selected), new EditDialogCallback() {
						@Override
						public void commit(StockDetail details) {
							StockDetail stock = new StockDetail(selected);
							stock.mergeTo(details);
							updateEntry(stock);
						}
					});
				}
			}
		});
	}
	
	private void deleteSelectedEntry() {
		final StockDetail selected = getView().getSelectedItem();
		if (selected != null) {
			getView().confirmDeleteRequest(new Listener<MessageBoxEvent>() {
				@Override
				public void handleEvent(MessageBoxEvent be) {
					Button buttonClicked = be.getButtonClicked();
					if (buttonClicked.getText().equals(be.getDialog().yesText)) {
						performDelete(selected);
					}
				}
			});
		}
	}
	
	private void editEntry(final StockDetail details) {
		EditRegistryEntry action = new EditRegistryEntry(details, exchange.getId(), EditRegistryEntry.EditType.ADD);
		dispatcher.execute(action, new EditEntryCallback() {
			@Override
			public void commited(StockDetail stock) {
				getView().add(stock);
			}
		});
	}
	
	private void updateEntry(StockDetail details) {
		EditRegistryEntry action = new EditRegistryEntry(details, exchange.getId(), EditRegistryEntry.EditType.UPDATE);
		dispatcher.execute(action, new EditEntryCallback() {
			@Override
			public void commited(StockDetail stock) {
				getView().update(stock);
			}
		});
	}
	
	private void performDelete(final StockDetail selected) {
		dispatcher.execute(new DeleteRegistryEntry(selected.getId()), new DeleteEntryCallback() {
			@Override
			public void deleted() {
				getView().remove(selected);
			}
		});
	}
	
	@Override
	protected void onUnbind() { }

	@Override
	protected void revealInParent() {
		RevealRootContentEvent.fire(this, this);
	}
	
	public void setExchange(Exchange exchange) {
		this.exchange = exchange;
		load();
	}
}
