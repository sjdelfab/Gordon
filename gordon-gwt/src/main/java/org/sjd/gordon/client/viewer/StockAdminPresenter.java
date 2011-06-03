package org.sjd.gordon.client.viewer;

import java.util.ArrayList;

import org.sjd.gordon.client.AbstractCallback;
import org.sjd.gordon.model.Dividend;
import org.sjd.gordon.model.StockSplit;
import org.sjd.gordon.model.TreasuryHeldStock;
import org.sjd.gordon.shared.navigation.StockName;
import org.sjd.gordon.shared.util.EditType;
import org.sjd.gordon.shared.viewer.DeleteDividendAction;
import org.sjd.gordon.shared.viewer.DeleteDividendResult;
import org.sjd.gordon.shared.viewer.DeleteStockSplitAction;
import org.sjd.gordon.shared.viewer.DeleteStockSplitResult;
import org.sjd.gordon.shared.viewer.DeleteTreasuryHeldStockAction;
import org.sjd.gordon.shared.viewer.DeleteTreasuryHeldStockResult;
import org.sjd.gordon.shared.viewer.EditDividendAction;
import org.sjd.gordon.shared.viewer.EditDividendResult;
import org.sjd.gordon.shared.viewer.EditStockSplitAction;
import org.sjd.gordon.shared.viewer.EditStockSplitResult;
import org.sjd.gordon.shared.viewer.EditTreasuryHeldStockAction;
import org.sjd.gordon.shared.viewer.EditTreasuryHeldStockResult;
import org.sjd.gordon.shared.viewer.GetDividendHistoryAction;
import org.sjd.gordon.shared.viewer.GetDividendHistoryResult;
import org.sjd.gordon.shared.viewer.GetStockAdminDatasetAction;
import org.sjd.gordon.shared.viewer.GetStockAdminDatasetResult;
import org.sjd.gordon.shared.viewer.GetStockSplitsAction;
import org.sjd.gordon.shared.viewer.GetStockSplitsResult;
import org.sjd.gordon.shared.viewer.GetTreasuryHeldStockHistoryAction;
import org.sjd.gordon.shared.viewer.GetTreasuryHeldStockHistoryResult;

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
		
		public void setDividendHistory(ArrayList<Dividend> dividendHistory);
		public void remove(Dividend dividend);
		public void add(Dividend dividend);
		public void update(Dividend dividend);
	}
	
	private final DispatchAsync dispatcher;
	private StockName stockName;
	
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
		EditStockSplitAction action = new EditStockSplitAction(newSplit, stockName.getId(), EditType.ADD);
		dispatcher.execute(action, new EditStockSplitCallback() {
			@Override
			public void commited(StockSplit stockSplit) {
				getView().add(stockSplit);
			}
		});
	}

	@Override
	public void update(StockSplit newSplit) {
		EditStockSplitAction action = new EditStockSplitAction(newSplit, stockName.getId(), EditType.UPDATE);
		dispatcher.execute(action, new EditStockSplitCallback() {
			@Override
			public void commited(StockSplit stockSplit) {
				getView().update(stockSplit);
			}
		});
	}

	@Override
	public void delete(final StockSplit splitSelected) {
		DeleteStockSplitAction action = new DeleteStockSplitAction(splitSelected);
		dispatcher.execute(action, new DeleteStockSplitCallback() {
			@Override
			public void deleted() {
				getView().remove(splitSelected);
			}
		});
	}

	public void setStock(StockName stockName) {
		this.stockName = stockName;
		load();
	}
	
	@Override
	public void load() {
		GetStockAdminDatasetAction action = new GetStockAdminDatasetAction(stockName.getId());
		dispatcher.execute(action, new LoadStockAdminCallback() {
			@Override
			public void loaded(ArrayList<StockSplit> splits, ArrayList<TreasuryHeldStock> heldStock, ArrayList<Dividend> dividends) {
				getView().setSplits(splits);
				getView().setTreasuryHeldStock(heldStock);
				getView().setDividendHistory(dividends);
			}
		});
	}

	@Override
	public void loadStockSplits() {
		GetStockSplitsAction action = new GetStockSplitsAction(stockName.getId());
		dispatcher.execute(action, new LoadStockSplitsCallback() {
			@Override
			public void loaded(ArrayList<StockSplit> splits) {
				getView().setSplits(splits);
			}
		});
	}
	
	@Override
	public void loadStockHeldInTreasury() {
		GetTreasuryHeldStockHistoryAction action = new GetTreasuryHeldStockHistoryAction(stockName.getId());
		dispatcher.execute(action, new LoadTreasuryHeldStockHistoryCallback() {
			@Override
			public void loaded(ArrayList<TreasuryHeldStock> heldStockHistory) {
				getView().setTreasuryHeldStock(heldStockHistory);
			}
		});
	}

	@Override
	public void loadDividendHistory() {
		GetDividendHistoryAction action = new GetDividendHistoryAction(stockName.getId());
		dispatcher.execute(action, new LoadDividendHistoryCallback() {
			@Override
			public void loaded(ArrayList<Dividend> dividendHistory) {
				getView().setDividendHistory(dividendHistory);
			}
		});
	}

	@Override
	public void add(TreasuryHeldStock heldStock) {
		EditTreasuryHeldStockAction action = new EditTreasuryHeldStockAction(heldStock, stockName.getId(), EditType.ADD);
		dispatcher.execute(action, new EditTreasuryHeldStockCallback() {
			@Override
			public void commited(TreasuryHeldStock heldStock) {
				getView().add(heldStock);
			}
		});
	}

	@Override
	public void update(TreasuryHeldStock heldStock) {
		EditTreasuryHeldStockAction action = new EditTreasuryHeldStockAction(heldStock, stockName.getId(), EditType.UPDATE);
		dispatcher.execute(action, new EditTreasuryHeldStockCallback() {
			@Override
			public void commited(TreasuryHeldStock heldStock) {
				getView().update(heldStock);
			}
		});
	}

	@Override
	public void delete(final TreasuryHeldStock heldStock) {
		DeleteTreasuryHeldStockAction action = new DeleteTreasuryHeldStockAction(heldStock);
		dispatcher.execute(action, new DeleteTreasuryHeldStockCallback() {
			@Override
			public void deleted() {
				getView().remove(heldStock);
			}
		});
	}

	@Override
	public void add(Dividend newDividend) {
		EditDividendAction action = new EditDividendAction(newDividend, stockName.getId(), EditType.ADD);
		dispatcher.execute(action, new EditDividendCallback() {
			@Override
			public void commited(Dividend dividend) {
				getView().add(dividend);
			}
		});
	}

	@Override
	public void update(Dividend updatedDividend) {
		EditDividendAction action = new EditDividendAction(updatedDividend, stockName.getId(), EditType.UPDATE);
		dispatcher.execute(action, new EditDividendCallback() {
			@Override
			public void commited(Dividend dividend) {
				getView().update(dividend);
			}
		});
	}

	@Override
	public void delete(final Dividend dividend) {
		DeleteDividendAction action = new DeleteDividendAction(dividend);
		dispatcher.execute(action, new DeleteDividendCallback() {
			@Override
			public void deleted() {
				getView().remove(dividend);
			}
		});
	}

	public static abstract class LoadStockAdminCallback extends AbstractCallback implements AsyncCallback<GetStockAdminDatasetResult> {

		@Override
		public void onSuccess(GetStockAdminDatasetResult result) {
			loaded(result.getStockSplits(),result.getTreasuryHeldStock(),result.getDividends());
		}

		public abstract void loaded(ArrayList<StockSplit> splits, ArrayList<TreasuryHeldStock> heldStock, ArrayList<Dividend> dividends);

	}
	
	public static abstract class LoadStockSplitsCallback extends AbstractCallback implements AsyncCallback<GetStockSplitsResult> {

		@Override
		public void onSuccess(GetStockSplitsResult result) {
			loaded(result.getStockSplits());
		}

		public abstract void loaded(ArrayList<StockSplit> splits);

	}
	
	public static abstract class LoadTreasuryHeldStockHistoryCallback extends AbstractCallback implements AsyncCallback<GetTreasuryHeldStockHistoryResult> {

		@Override
		public void onSuccess(GetTreasuryHeldStockHistoryResult result) {
			loaded(result.getTreasuryHeldStockHistory());
		}

		public abstract void loaded(ArrayList<TreasuryHeldStock> heldStockHistory);

	}	

	public static abstract class LoadDividendHistoryCallback extends AbstractCallback implements AsyncCallback<GetDividendHistoryResult> {

		@Override
		public void onSuccess(GetDividendHistoryResult result) {
			loaded(result.getDividends());
		}

		public abstract void loaded(ArrayList<Dividend> dividendHistory);

	}	

	public static abstract class EditStockSplitCallback extends AbstractCallback implements AsyncCallback<EditStockSplitResult> {

		@Override
		public void onSuccess(EditStockSplitResult response) {
			commited(response.getUpdatedStockSplit());
		}

		public abstract void commited(StockSplit stockSplit);

	}

	public static abstract class EditTreasuryHeldStockCallback extends AbstractCallback implements AsyncCallback<EditTreasuryHeldStockResult> {

		@Override
		public void onSuccess(EditTreasuryHeldStockResult response) {
			commited(response.getUpdatedTreasuryHeldStock());
		}

		public abstract void commited(TreasuryHeldStock heldStock);

	}

	public static abstract class EditDividendCallback extends AbstractCallback implements AsyncCallback<EditDividendResult> {

		@Override
		public void onSuccess(EditDividendResult response) {
			commited(response.getUpdatedDividend());
		}

		public abstract void commited(Dividend dividend);

	}

	public static abstract class DeleteStockSplitCallback extends AbstractCallback implements AsyncCallback<DeleteStockSplitResult> {

		@Override
		public void onSuccess(DeleteStockSplitResult deletedEntry) {
			deleted();
		}

		public abstract void deleted();

	}

	public static abstract class DeleteTreasuryHeldStockCallback extends AbstractCallback implements AsyncCallback<DeleteTreasuryHeldStockResult> {

		@Override
		public void onSuccess(DeleteTreasuryHeldStockResult deletedEntry) {
			deleted();
		}

		public abstract void deleted();

	}

	public static abstract class DeleteDividendCallback extends AbstractCallback implements AsyncCallback<DeleteDividendResult> {

		@Override
		public void onSuccess(DeleteDividendResult deletedEntry) {
			deleted();
		}

		public abstract void deleted();

	}

}
