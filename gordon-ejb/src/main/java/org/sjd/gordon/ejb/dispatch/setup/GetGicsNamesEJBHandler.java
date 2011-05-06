package org.sjd.gordon.ejb.dispatch.setup;

import java.util.ArrayList;
import java.util.List;

import org.sjd.gordon.ejb.setup.GicsService;
import org.sjd.gordon.model.GicsSector;
import org.sjd.gordon.shared.registry.GetGicsSectors;
import org.sjd.gordon.shared.registry.GicsSectorName;
import org.sjd.gordon.shared.registry.GotGicsSectors;

import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class GetGicsNamesEJBHandler implements ActionHandler<GetGicsSectors, GotGicsSectors> {

	@Inject
	private GicsService gicsService;
	
	@Override
	public GotGicsSectors execute(GetGicsSectors getDetails, ExecutionContext context) throws ActionException {
		List<GicsSector> sectors = gicsService.getSectors();
		ArrayList<GicsSectorName> sectorNames = new ArrayList<GicsSectorName>(sectors.size());
		for(GicsSector sector: sectors) {
			sectorNames.add(GicsSectorName.toSectorName(sector));
		}
		return new GotGicsSectors(sectorNames);
	}

	@Override
	public Class<GetGicsSectors> getActionType() {
		return GetGicsSectors.class;
	}

	@Override
	public void undo(GetGicsSectors action, GotGicsSectors result, ExecutionContext context) throws ActionException { }
}
