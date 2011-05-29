package org.sjd.gordon.server.devhandlers.setup;

import java.util.ArrayList;

import org.sjd.gordon.shared.registry.GetGicsSectorsAction;
import org.sjd.gordon.shared.registry.GetGicsSectorsResult;
import org.sjd.gordon.shared.registry.GicsIndustryGroupName;
import org.sjd.gordon.shared.registry.GicsSectorName;

import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class GetGicsNamesDevHandler implements ActionHandler<GetGicsSectorsAction, GetGicsSectorsResult> {

	private static ArrayList<GicsSectorName> names = new ArrayList<GicsSectorName>();
	
	static {
		GicsSectorName name = new GicsSectorName();
		name.set(GicsSectorName.GICS_PRIMARY_SECTOR_NAME, "Energy");
		name.set(GicsSectorName.GICS_PRIMARY_SECTOR_ID, Integer.valueOf(1));
		GicsIndustryGroupName group = new GicsIndustryGroupName();
		group.set(GicsIndustryGroupName.GICS_PRIMARY_INDUSTRY_GROUP_NAME, "Energy");
		group.set(GicsIndustryGroupName.GICS_PRIMARY_INDUSTRY_GROUP_ID, Integer.valueOf(1));
		name.addGroup(group);
		names.add(name);
		
		name = new GicsSectorName();
		name.set(GicsSectorName.GICS_PRIMARY_SECTOR_NAME, "Materials");
		name.set(GicsSectorName.GICS_PRIMARY_SECTOR_ID, Integer.valueOf(2));
		group = new GicsIndustryGroupName();
		group.set(GicsIndustryGroupName.GICS_PRIMARY_INDUSTRY_GROUP_NAME, "Materials");
		group.set(GicsIndustryGroupName.GICS_PRIMARY_INDUSTRY_GROUP_ID, Integer.valueOf(2));
		name.addGroup(group);
		names.add(name);
		
		name = new GicsSectorName();
		name.set(GicsSectorName.GICS_PRIMARY_SECTOR_NAME, "Industrials");
		name.set(GicsSectorName.GICS_PRIMARY_SECTOR_ID, Integer.valueOf(3));
		group = new GicsIndustryGroupName();
		group.set(GicsIndustryGroupName.GICS_PRIMARY_INDUSTRY_GROUP_NAME, "Capital Goods");
		group.set(GicsIndustryGroupName.GICS_PRIMARY_INDUSTRY_GROUP_ID, Integer.valueOf(3));
		name.addGroup(group);
		group = new GicsIndustryGroupName();
		group.set(GicsIndustryGroupName.GICS_PRIMARY_INDUSTRY_GROUP_NAME, "Commercial & Professional Services");
		group.set(GicsIndustryGroupName.GICS_PRIMARY_INDUSTRY_GROUP_ID, Integer.valueOf(4));
		name.addGroup(group);
		names.add(name);
		
		name = new GicsSectorName();
		name.set(GicsSectorName.GICS_PRIMARY_SECTOR_NAME, "Consumer Discretionary");
		name.set(GicsSectorName.GICS_PRIMARY_SECTOR_ID, Integer.valueOf(4));
		names.add(name);
		name = new GicsSectorName();
		name.set(GicsSectorName.GICS_PRIMARY_SECTOR_NAME, "Consumer Staples");
		name.set(GicsSectorName.GICS_PRIMARY_SECTOR_ID, Integer.valueOf(5));
		names.add(name);
		name = new GicsSectorName();
		name.set(GicsSectorName.GICS_PRIMARY_SECTOR_NAME, "Health Care");
		name.set(GicsSectorName.GICS_PRIMARY_SECTOR_ID, Integer.valueOf(6));
		names.add(name);
		name = new GicsSectorName();
		name.set(GicsSectorName.GICS_PRIMARY_SECTOR_NAME, "Financials");
		name.set(GicsSectorName.GICS_PRIMARY_SECTOR_ID, Integer.valueOf(7));
		names.add(name);
		name = new GicsSectorName();
		name.set(GicsSectorName.GICS_PRIMARY_SECTOR_NAME, "Information Technology");
		name.set(GicsSectorName.GICS_PRIMARY_SECTOR_ID, Integer.valueOf(8));
		names.add(name);
		name = new GicsSectorName();
		name.set(GicsSectorName.GICS_PRIMARY_SECTOR_NAME, "Telecommunication Services");
		name.set(GicsSectorName.GICS_PRIMARY_SECTOR_ID, Integer.valueOf(9));
		names.add(name);
		name = new GicsSectorName();
		name.set(GicsSectorName.GICS_PRIMARY_SECTOR_NAME, "Utilities");
		name.set(GicsSectorName.GICS_PRIMARY_SECTOR_ID, Integer.valueOf(10));
		names.add(name);

	}
	
	@Override
	public GetGicsSectorsResult execute(GetGicsSectorsAction getDetails, ExecutionContext context) throws ActionException {
		return new GetGicsSectorsResult(names);
	}

	@Override
	public Class<GetGicsSectorsAction> getActionType() {
		return GetGicsSectorsAction.class;
	}

	@Override
	public void undo(GetGicsSectorsAction action, GetGicsSectorsResult result, ExecutionContext context) throws ActionException { }

}
