package org.sjd.gordon.server;

import javax.persistence.EntityManager;

import org.sjd.gordon.model.BusinessSummary;
import org.sjd.gordon.model.StockEntity;
import org.sjd.gordon.shared.viewer.GetStockProfileAction;
import org.sjd.gordon.shared.viewer.GetStockProfileResult;
import org.sjd.gordon.shared.viewer.StockDetail;
import org.sjd.gordon.shared.viewer.StockProfile;

import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class GetStockProfileJpaHandler implements ActionHandler<GetStockProfileAction, GetStockProfileResult> {

	@Inject EntityManager em;
	
	@Override
	public GetStockProfileResult execute(GetStockProfileAction getProfile, ExecutionContext context) throws ActionException {
		StockEntity stockEntity = em.find(StockEntity.class, getProfile.getStockId());
		StockProfile profile = new StockProfile();
		profile.setDetail(StockDetail.fromEntity(stockEntity));
		BusinessSummary summary = new BusinessSummary();
		summary.setStockId(getProfile.getStockId());
		// TODO: Complete
		summary.setSummary("Business summary blah blah");
		profile.setBusinessSummary(summary);
		return new GetStockProfileResult(profile);
	}

	@Override
	public Class<GetStockProfileAction> getActionType() {
		return GetStockProfileAction.class;
	}

	@Override
	public void undo(GetStockProfileAction getDetails, GetStockProfileResult gotDetails, ExecutionContext context) throws ActionException { }

}
