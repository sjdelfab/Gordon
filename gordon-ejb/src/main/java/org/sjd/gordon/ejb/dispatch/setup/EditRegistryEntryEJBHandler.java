package org.sjd.gordon.ejb.dispatch.setup;

import org.sjd.gordon.ejb.ExchangeService;
import org.sjd.gordon.ejb.StockEntityService;
import org.sjd.gordon.model.StockEntity;
import org.sjd.gordon.shared.registry.EditRegistryEntry;
import org.sjd.gordon.shared.registry.EditRegistryEntry.EditType;
import org.sjd.gordon.shared.registry.EditRegistryEntryResponse;
import org.sjd.gordon.shared.viewer.StockDetails;

import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class EditRegistryEntryEJBHandler implements ActionHandler<EditRegistryEntry,EditRegistryEntryResponse> {

	@Inject
	private StockEntityService stockEjb;
	@Inject
	private ExchangeService exchangeEjb;	
	
	@Override
	public EditRegistryEntryResponse execute(EditRegistryEntry editEntry, ExecutionContext context) throws ActionException {
		EditRegistryEntry.EditType editType = editEntry.getEditType();
		StockDetails newStockDetails = editEntry.getStockDetails();
		if (editType == EditType.ADD) {
			StockEntity newStock = new StockEntity();
			newStock.setCode(newStockDetails.getCode());
			newStock.setName(newStockDetails.getName());
			newStock.setExchange(exchangeEjb.findExchangeById(editEntry.getExchangeId()));
			Long newStockId = stockEjb.createStock(newStock).getId();
			newStockDetails.setId(newStockId);
		} else {
			StockEntity stockEntity = stockEjb.findStockById(newStockDetails.getId());
			stockEntity.setCode(newStockDetails.getCode());
			stockEntity.setName(newStockDetails.getName());
			stockEjb.updateStock(stockEntity);
		}
		return new EditRegistryEntryResponse(newStockDetails.getId());
	}

	@Override
	public Class<EditRegistryEntry> getActionType() {
		return EditRegistryEntry.class;
	}

	@Override
	public void undo(EditRegistryEntry action, EditRegistryEntryResponse result, ExecutionContext context) throws ActionException {
		// Nothing to do here
	}

}
