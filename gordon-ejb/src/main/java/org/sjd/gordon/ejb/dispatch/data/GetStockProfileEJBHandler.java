package org.sjd.gordon.ejb.dispatch.data;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import org.sjd.gordon.ejb.StockEntityServiceLocal;
import org.sjd.gordon.ejb.StockStatisticsServiceLocal;
import org.sjd.gordon.ejb.setup.GicsServiceLocal;
import org.sjd.gordon.model.BusinessSummary;
import org.sjd.gordon.model.GicsSector;
import org.sjd.gordon.model.StockDayTradeRecord;
import org.sjd.gordon.model.StockEntity;
import org.sjd.gordon.shared.viewer.GetStockProfileAction;
import org.sjd.gordon.shared.viewer.GetStockProfileResult;
import org.sjd.gordon.shared.viewer.StockDetail;
import org.sjd.gordon.shared.viewer.StockProfile;
import org.sjd.gordon.shared.viewer.StockStatistics;
import org.sjd.gordon.shared.viewer.ValuationMeasures;

import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class GetStockProfileEJBHandler implements ActionHandler<GetStockProfileAction, GetStockProfileResult> {

	@Inject
	private StockEntityServiceLocal stockEntityService;
	@Inject
	private GicsServiceLocal gicsService;
	@Inject
	private StockStatisticsServiceLocal stockStatisticsService;
	
	@Override
	public GetStockProfileResult execute(GetStockProfileAction getProfile, ExecutionContext context) throws ActionException {
		StockEntity stockEntity = stockEntityService.findStockById(getProfile.getStockId());
		StockDayTradeRecord firstDayTrade = stockEntityService.getFirstTradeDay(getProfile.getStockId());
		StockDetail stockDetails = StockDetail.fromEntity(stockEntity);
		if (stockEntity.getGicsIndustryGroup() != null) {
			Integer industryGrpId = stockEntity.getGicsIndustryGroup().getId();
			GicsSector sector = gicsService.findSectorByIndustryGroupId(industryGrpId);
			stockDetails.setPrimarySectorName(sector.getName());
			stockDetails.setPrimarySectorId(sector.getId());
		}
		stockDetails.setListDate(firstDayTrade.getDate());
		StockDayTradeRecord lastTradeDate = stockEntityService.getLastTradeDay(getProfile.getStockId());
		stockDetails.setLastTradeDate(lastTradeDate.getDate());
		stockDetails.setCurrentPrice(lastTradeDate.getClosePrice());
		StockProfile profile = new StockProfile();
		profile.setDetail(stockDetails);
		BusinessSummary summary = stockEntity.getBusinessSummary();
		profile.setBusinessSummary(summary);
		
		StockStatistics stockStatistics = new StockStatistics();
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.WEEK_OF_YEAR, -52);
		Date fiftyTwoWeeksAgoDate = cal.getTime();
		Date now = new Date();
		stockStatistics.setAverageVolume(stockStatisticsService.getAverageVolume(getProfile.getStockId(), fiftyTwoWeeksAgoDate, now).intValue());
		stockStatistics.setHigh(stockStatisticsService.getMaxPrice(getProfile.getStockId(), fiftyTwoWeeksAgoDate, now));
		stockStatistics.setLow(stockStatisticsService.getMinPrice(getProfile.getStockId(), fiftyTwoWeeksAgoDate, now));
		cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, -50);
		Date fiftyDaysAgoDate = cal.getTime();
		stockStatistics.setMovingAverage(new BigDecimal(stockStatisticsService.getAveragePrice(getProfile.getStockId(), fiftyDaysAgoDate, now)));
		stockStatistics.setPercentageChange(stockStatisticsService.getPercentageChange(getProfile.getStockId(), fiftyTwoWeeksAgoDate, now));
		profile.setStockStatistics(stockStatistics);
		
		Long sharesOutstanding = stockStatisticsService.calculateSharesOutstanding(stockEntity);
		profile.setSharesOutstanding(sharesOutstanding.intValue());
		
		// TODO
		ValuationMeasures valuationMeasures = new ValuationMeasures();
		BigDecimal marketCapitalisation = lastTradeDate.getClosePrice().multiply(new BigDecimal(sharesOutstanding));
		valuationMeasures.setMarketCapitalisation(marketCapitalisation);
		valuationMeasures.setEnterpriseValue(BigDecimal.ZERO);
		valuationMeasures.setTrailingPE(BigDecimal.ZERO);
		valuationMeasures.setForwardPE(BigDecimal.ZERO);
		valuationMeasures.setPegRatio(BigDecimal.ZERO);
		valuationMeasures.setPriceToSalesRatio(BigDecimal.ZERO);
		valuationMeasures.setPriceToBookRatio(BigDecimal.ZERO);
		valuationMeasures.setEnterpriseValueToRevenueRatio(BigDecimal.ZERO);
		valuationMeasures.setEnterpriseValueToEBITDA(BigDecimal.ZERO);
		profile.setValuationMeasures(valuationMeasures);
		
		return new GetStockProfileResult(profile);
	}

	@Override
	public Class<GetStockProfileAction> getActionType() {
		return GetStockProfileAction.class;
	}

	@Override
	public void undo(GetStockProfileAction action, GetStockProfileResult result, ExecutionContext context) throws ActionException { }

}
