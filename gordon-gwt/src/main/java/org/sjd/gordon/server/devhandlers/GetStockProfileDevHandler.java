package org.sjd.gordon.server.devhandlers;

import java.math.BigDecimal;

import org.sjd.gordon.model.BusinessSummary;
import org.sjd.gordon.shared.viewer.GetStockProfileAction;
import org.sjd.gordon.shared.viewer.GetStockProfileResult;
import org.sjd.gordon.shared.viewer.StockProfile;
import org.sjd.gordon.shared.viewer.StockStatistics;
import org.sjd.gordon.shared.viewer.ValuationMeasures;

import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class GetStockProfileDevHandler implements ActionHandler<GetStockProfileAction, GetStockProfileResult> {
	
	@Override
	public GetStockProfileResult execute(GetStockProfileAction getDetails, ExecutionContext context) throws ActionException {
		StockProfile profile = new StockProfile();
		profile.setDetail(Data.detailsMap.get(getDetails.getStockId()));
		BusinessSummary summary = new BusinessSummary();
		summary.setStockId(getDetails.getStockId());
		summary.setSummary("Business summary blah blah");
		profile.setBusinessSummary(summary);
		StockStatistics stockStatistics = new StockStatistics();
		stockStatistics.setAverageVolume(Integer.valueOf(3234345));
		stockStatistics.setHigh(new BigDecimal("22.2"));
		stockStatistics.setLow(new BigDecimal("20.2"));
		stockStatistics.setMovingAverage(new BigDecimal("21.1"));
		stockStatistics.setPercentageChange(new BigDecimal("0.011"));
		profile.setStockStatistics(stockStatistics);
		profile.setSharesOutstanding(Integer.valueOf(1210000000));
		ValuationMeasures valuationMeasures = new ValuationMeasures();
		valuationMeasures.setMarketCapitalisation(new BigDecimal("33020000000"));
		valuationMeasures.setEnterpriseValue(new BigDecimal("35800000000"));
		valuationMeasures.setTrailingPE(new BigDecimal("16.13"));
		valuationMeasures.setForwardPE(new BigDecimal("14.22"));
		valuationMeasures.setPegRatio(new BigDecimal("1.51"));
		valuationMeasures.setPriceToSalesRatio(new BigDecimal("0.63"));
		valuationMeasures.setPriceToBookRatio(new BigDecimal("16.13"));
		valuationMeasures.setEnterpriseValueToRevenueRatio(new BigDecimal("0.68"));
		valuationMeasures.setEnterpriseValueToEBITDA(new BigDecimal("8.91"));
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
