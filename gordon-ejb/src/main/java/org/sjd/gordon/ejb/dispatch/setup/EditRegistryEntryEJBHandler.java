package org.sjd.gordon.ejb.dispatch.setup;

import java.math.BigDecimal;
import java.util.Date;

import org.sjd.gordon.ejb.ExchangeServiceLocal;
import org.sjd.gordon.ejb.StockEntityServiceLocal;
import org.sjd.gordon.ejb.dispatch.AbstractHandler;
import org.sjd.gordon.ejb.setup.GicsServiceLocal;
import org.sjd.gordon.model.GicsIndustryGroup;
import org.sjd.gordon.model.StockEntity;
import org.sjd.gordon.shared.registry.EditRegistryEntry;
import org.sjd.gordon.shared.registry.EditRegistryEntry.EditType;
import org.sjd.gordon.shared.registry.EditRegistryEntryAction;
import org.sjd.gordon.shared.registry.EditRegistryEntryResult;
import org.sjd.gordon.shared.viewer.StockDetail;

import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class EditRegistryEntryEJBHandler extends AbstractHandler implements ActionHandler<EditRegistryEntryAction,EditRegistryEntryResult> {

	private StockEntityServiceLocal stockService;
	private ExchangeServiceLocal exchangeService;
	private GicsServiceLocal gicsService;
	
	@Inject
	public EditRegistryEntryEJBHandler(StockEntityServiceLocal stockService, ExchangeServiceLocal exchangeService, GicsServiceLocal gicsService) {
		this.stockService = stockService;
		this.exchangeService = exchangeService;
		this.gicsService = gicsService;
	}
	
	@Override
	public EditRegistryEntryResult execute(EditRegistryEntryAction editEntry, ExecutionContext context) throws ActionException {
		EditRegistryEntry.EditType editType = editEntry.getEditType();
		StockDetail newStockDetails = editEntry.getStockDetails();
		try {
			if (editType == EditType.ADD) {
				 newStockDetails = add(editEntry);
			} else {
				newStockDetails = update(editEntry);
			}
		} catch (Throwable cause) {
			throw translateException(cause);
		}
		return new EditRegistryEntryResult(newStockDetails);
	}

	private StockDetail add(EditRegistryEntryAction editEntry) throws Exception {
		StockDetail newStockDetails = editEntry.getStockDetails();
		StockEntity newStock = new StockEntity();
		newStock.setCode(newStockDetails.getCode());
		newStock.setName(newStockDetails.getName());
		if (newStockDetails.getPrimaryIndustryGroupId() != null) {
			GicsIndustryGroup industryGroup = gicsService.findIndustryGroupById(newStockDetails.getPrimaryIndustryGroupId());
			newStock.setGicsIndustryGroup(industryGroup);
		}
		newStock.setExchange(exchangeService.findExchangeById(editEntry.getExchangeId()));
		newStock = stockService.createStock(newStock);
		newStockDetails = StockDetail.fromEntity(newStock);
		// lost the sector info, so re-add
		if (newStockDetails.getPrimaryIndustryGroupId() != null) {
			newStockDetails.setPrimarySectorId(editEntry.getStockDetails().getPrimarySectorId());
			newStockDetails.setPrimarySectorName(editEntry.getStockDetails().getPrimarySectorName());
		}
		return newStockDetails;
	}
	
	private StockDetail update(EditRegistryEntryAction editEntry) throws Exception {
		StockDetail newStockDetails = editEntry.getStockDetails();
		StockEntity stockEntity = stockService.findStockById(newStockDetails.getId());
		stockEntity.setVersion(newStockDetails.getVersion());
		stockEntity.setCode(newStockDetails.getCode());
		stockEntity.setName(newStockDetails.getName());
		Integer newPrimaryIndustryGroupId = newStockDetails.getPrimaryIndustryGroupId();
		Integer currentIndustryGroupId = null;
		if (stockEntity.getGicsIndustryGroup() != null) {
			currentIndustryGroupId = stockEntity.getGicsIndustryGroup().getId();
		}
		if (newPrimaryIndustryGroupId != null) {
			if (!newPrimaryIndustryGroupId.equals(currentIndustryGroupId)) {
				GicsIndustryGroup industryGroup = gicsService.findIndustryGroupById(newPrimaryIndustryGroupId);
				stockEntity.setGicsIndustryGroup(industryGroup);
			}
		}
		Date listDate = newStockDetails.getListDate();
		Date lastTradeDate = newStockDetails.getLastTradeDate();
		BigDecimal currentPrice = newStockDetails.getCurrentPrice();
		newStockDetails = StockDetail.fromEntity(stockService.updateStock(stockEntity));
		newStockDetails.setListDate(listDate);
		newStockDetails.setLastTradeDate(lastTradeDate);
		newStockDetails.setCurrentPrice(currentPrice);
		if (newStockDetails.getPrimaryIndustryGroupId() != null) {
			newStockDetails.setPrimarySectorId(editEntry.getStockDetails().getPrimarySectorId());
			newStockDetails.setPrimarySectorName(editEntry.getStockDetails().getPrimarySectorName());
		}
		return newStockDetails;
	}
	
	@Override
	public Class<EditRegistryEntryAction> getActionType() {
		return EditRegistryEntryAction.class;
	}

	@Override
	public void undo(EditRegistryEntryAction action, EditRegistryEntryResult result, ExecutionContext context) throws ActionException {
		// Nothing to do here
	}
	

}
