package org.sjd.gordon.ejb.dispatch.setup;

import java.util.ArrayList;
import java.util.List;

import org.sjd.gordon.ejb.setup.GicsServiceLocal;
import org.sjd.gordon.model.GicsSector;
import org.sjd.gordon.shared.registry.GetGicsSectorsAction;
import org.sjd.gordon.shared.registry.GetGicsSectorsResult;
import org.sjd.gordon.shared.registry.GicsSectorName;

import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class GetGicsNamesEJBHandler implements ActionHandler<GetGicsSectorsAction, GetGicsSectorsResult> {

	@Inject
	private GicsServiceLocal gicsService;
	
	@Override
	public GetGicsSectorsResult execute(GetGicsSectorsAction getDetails, ExecutionContext context) throws ActionException {
		List<GicsSector> sectors = gicsService.getSectors();
		ArrayList<GicsSectorName> sectorNames = new ArrayList<GicsSectorName>(sectors.size());
		for(GicsSector sector: sectors) {
			sectorNames.add(GicsSectorName.toSectorName(sector));
		}
		return new GetGicsSectorsResult(sectorNames);
	}

	@Override
	public Class<GetGicsSectorsAction> getActionType() {
		return GetGicsSectorsAction.class;
	}

	@Override
	public void undo(GetGicsSectorsAction action, GetGicsSectorsResult result, ExecutionContext context) throws ActionException { }
}
