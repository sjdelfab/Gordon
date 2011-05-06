package org.sjd.gordon.ejb.dispatch.data;

import org.sjd.gordon.ejb.StockEntityService;
import org.sjd.gordon.ejb.setup.GicsService;
import org.sjd.gordon.model.GicsSector;
import org.sjd.gordon.model.StockDayTradeRecord;
import org.sjd.gordon.model.StockEntity;
import org.sjd.gordon.shared.viewer.GetStockDetails;
import org.sjd.gordon.shared.viewer.GotStockDetails;
import org.sjd.gordon.shared.viewer.StockDetails;

import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class GetStockDetailsEJBHandler implements ActionHandler<GetStockDetails, GotStockDetails> {

	@Inject
	private StockEntityService stockEntityService;
	@Inject
	private GicsService gicsService;
	
	@Override
	public GotStockDetails execute(GetStockDetails getDetails, ExecutionContext context) throws ActionException {
		StockEntity stockEntity = stockEntityService.findStockById(getDetails.getStockId());
		StockDayTradeRecord firstDayTrade = stockEntityService.getFirstTradeDay(getDetails.getStockId());
		StockDetails stockDetails = StockDetails.fromEntity(stockEntity);
		if (stockEntity.getGicsIndustryGroup() != null) {
			Integer industryGrpId = stockEntity.getGicsIndustryGroup().getId();
			GicsSector sector = gicsService.findSectorByIndustryGroupId(industryGrpId);
			stockDetails.setPrimarySectorName(sector.getName());
			stockDetails.setPrimarySectorId(sector.getId());
		}
		stockDetails.setListDate(firstDayTrade.getDate());
		StockDayTradeRecord lastTradeDate = stockEntityService.getLastTradeDay(getDetails.getStockId());
		stockDetails.setLastTradeDate(lastTradeDate.getDate());
		stockDetails.setCurrentPrice(lastTradeDate.getClosePrice());
		return new GotStockDetails(stockDetails);
	}

	@Override
	public Class<GetStockDetails> getActionType() {
		return GetStockDetails.class;
	}

	@Override
	public void undo(GetStockDetails getDetails, GotStockDetails gotDetails, ExecutionContext context) throws ActionException { }

}
