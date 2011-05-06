package org.sjd.gordon.ejb.dispatch.data;

import java.util.ArrayList;
import java.util.List;

import org.sjd.gordon.ejb.StockEntityService;
import org.sjd.gordon.ejb.setup.GicsService;
import org.sjd.gordon.model.GicsSector;
import org.sjd.gordon.model.StockDayTradeRecord;
import org.sjd.gordon.model.StockEntity;
import org.sjd.gordon.shared.registry.GetAllStockDetails;
import org.sjd.gordon.shared.registry.GotAllStockDetails;
import org.sjd.gordon.shared.viewer.StockDetails;

import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class GetAllStockDetailsEJBHandler implements ActionHandler<GetAllStockDetails, GotAllStockDetails> {

	@Inject
	private StockEntityService stockEjb;
	@Inject
	private GicsService gicsService;

	@Override
	public GotAllStockDetails execute(GetAllStockDetails getDetails, ExecutionContext context) throws ActionException {
		List<StockEntity> stocks = stockEjb.getStocks(getDetails.getExchangeId());
		ArrayList<StockDetails> details = new ArrayList<StockDetails>(stocks.size());
		for (StockEntity entity : stocks) {
			StockDayTradeRecord firstDayTrade = stockEjb.getFirstTradeDay(entity.getId());
			StockDetails stockDetails = StockDetails.fromEntity(entity);
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
		return new GotAllStockDetails(details);
	}

	@Override
	public Class<GetAllStockDetails> getActionType() {
		return GetAllStockDetails.class;
	}

	@Override
	public void undo(GetAllStockDetails action, GotAllStockDetails result, ExecutionContext context)
			throws ActionException {
	}

}
