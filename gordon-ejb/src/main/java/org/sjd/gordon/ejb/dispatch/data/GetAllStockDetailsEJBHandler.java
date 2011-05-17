package org.sjd.gordon.ejb.dispatch.data;

import java.util.ArrayList;
import java.util.List;

import org.sjd.gordon.ejb.StockEntityService;
import org.sjd.gordon.ejb.setup.GicsService;
import org.sjd.gordon.model.GicsSector;
import org.sjd.gordon.model.StockDayTradeRecord;
import org.sjd.gordon.model.StockEntity;
import org.sjd.gordon.shared.registry.GetAllRegistryEntriesAction;
import org.sjd.gordon.shared.registry.GetAllRegistryEntriesResult;
import org.sjd.gordon.shared.viewer.StockDetail;

import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class GetAllStockDetailsEJBHandler implements ActionHandler<GetAllRegistryEntriesAction, GetAllRegistryEntriesResult> {

	@Inject
	private StockEntityService stockEjb;
	@Inject
	private GicsService gicsService;

	@Override
	public GetAllRegistryEntriesResult execute(GetAllRegistryEntriesAction getDetails, ExecutionContext context) throws ActionException {
		List<StockEntity> stocks = stockEjb.getStocks(getDetails.getExchangeId());
		ArrayList<StockDetail> details = new ArrayList<StockDetail>(stocks.size());
		for (StockEntity entity : stocks) {
			StockDayTradeRecord firstDayTrade = stockEjb.getFirstTradeDay(entity.getId());
			StockDetail stockDetails = StockDetail.fromEntity(entity);
			if (entity.getGicsIndustryGroup() != null) {
				Integer industryGrpId = entity.getGicsIndustryGroup().getId();
				GicsSector sector = gicsService.findSectorByIndustryGroupId(industryGrpId);
				stockDetails.setPrimarySectorName(sector.getName());
				stockDetails.setPrimarySectorId(sector.getId());
			}
			if (firstDayTrade != null) {
				stockDetails.setListDate(firstDayTrade.getDate());
			}
			StockDayTradeRecord lastTradeDate = stockEjb.getLastTradeDay(entity.getId());
			if (lastTradeDate != null) {
				stockDetails.setLastTradeDate(lastTradeDate.getDate());
				stockDetails.setCurrentPrice(lastTradeDate.getClosePrice());
			}
			details.add(stockDetails);
		}
		return new GetAllRegistryEntriesResult(details);
	}

	@Override
	public Class<GetAllRegistryEntriesAction> getActionType() {
		return GetAllRegistryEntriesAction.class;
	}

	@Override
	public void undo(GetAllRegistryEntriesAction action, GetAllRegistryEntriesResult result, ExecutionContext context)
			throws ActionException {
	}

}
