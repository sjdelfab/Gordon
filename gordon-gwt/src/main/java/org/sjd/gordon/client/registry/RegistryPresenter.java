package org.sjd.gordon.client.registry;

import java.util.ArrayList;

import org.sjd.gordon.model.Exchange;
import org.sjd.gordon.shared.registry.DeleteRegistryEntry;
import org.sjd.gordon.shared.registry.EditRegistryEntry;
import org.sjd.gordon.shared.registry.GetAllStockDetails;
import org.sjd.gordon.shared.viewer.StockDetails;

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
		public void setStocks(ArrayList<StockDetails> stocks);
		public StockDetails getSelectedItem();
		public void remove(StockDetails stock);
		public HasClickHandlers getDeleteHandler();
		public void confirmDeleteRequest(Listener<MessageBoxEvent> callback);
		public HasClickHandlers getAddHandler();
		public void showEditDialog(EditDialogCallback callback);
		public void add(StockDetails details);
		public HasClickHandlers getUpdateHandler();
		public void showEditDialog(StockDetails details, EditDialogCallback callback);
		public void update(StockDetails details);
	}
	
	interface EditDialogCallback {
		void commit(String code, String name);
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
			public void loaded(ArrayList<StockDetails> stocks) {
				getView().setStocks(stocks);
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
					public void commit(String code, String name) {
						StockDetails details = new StockDetails();
						details.setCode(code);
						details.setName(name);
						editEntry(details);
					}
				});
			}
		});
		getView().getUpdateHandler().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent arg0) {
				final StockDetails selected = getView().getSelectedItem();
				if (selected != null) {
					getView().showEditDialog(selected, new EditDialogCallback() {
						@Override
						public void commit(String code, String name) {
							selected.setCode(code);
							selected.setName(name);
							updateEntry(selected);
						}
					});
				}
			}
		});
	}
	
	private void deleteSelectedEntry() {
		final StockDetails selected = getView().getSelectedItem();
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
	
	private void editEntry(final StockDetails details) {
		EditRegistryEntry action = new EditRegistryEntry(details, exchange.getId(), EditRegistryEntry.EditType.ADD);
		dispatcher.execute(action, new EditEntryCallback() {
			@Override
			public void commited(Long stockId) {
				details.setId(stockId);
				getView().add(details);
			}
		});
	}
	
	private void updateEntry(final StockDetails details) {
		EditRegistryEntry action = new EditRegistryEntry(details, exchange.getId(), EditRegistryEntry.EditType.UPDATE);
		dispatcher.execute(action, new EditEntryCallback() {
			@Override
			public void commited(Long stockId) {
				details.setId(stockId);
				getView().update(details);
			}
		});
	}
	
	private void performDelete(final StockDetails selected) {
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
