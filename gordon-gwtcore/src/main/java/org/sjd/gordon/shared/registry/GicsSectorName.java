package org.sjd.gordon.shared.registry;

import java.io.Serializable;
import java.util.ArrayList;

import org.sjd.gordon.model.GicsIndustryGroup;
import org.sjd.gordon.model.GicsSector;

import com.extjs.gxt.ui.client.data.BaseModel;

public class GicsSectorName extends BaseModel implements Serializable {
	
	private static final long serialVersionUID = -2101256879452470724L;
	
	public static final String GICS_PRIMARY_SECTOR_ID = "primary_sector_id";
	public static final String GICS_PRIMARY_SECTOR_NAME = "primary_sector_name";
	
	private ArrayList<GicsIndustryGroupName> industryGroups;
	
	public GicsSectorName() {}
	
	public String getName() {
		return (String)get(GICS_PRIMARY_SECTOR_NAME);
	}
	
	public Integer getId() {
		return (Integer)get(GICS_PRIMARY_SECTOR_ID);
	}

	public ArrayList<GicsIndustryGroupName> getIndustryGroups() {
		if (industryGroups == null) {
			return new ArrayList<GicsIndustryGroupName>(0);
		}
		return industryGroups;
	}
	
	public void addGroup(GicsIndustryGroupName group) {
		if (industryGroups == null) {
			industryGroups = new ArrayList<GicsIndustryGroupName>();
		}
		industryGroups.add(group);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GicsSectorName other = (GicsSectorName) obj;
		if (getName() == null) {
			if (other.getName() != null)
				return false;
		} else if (!getName().equals(other.getName()))
			return false;
		return true;
	}

	public static GicsSectorName toSectorName(GicsSector sector) {
		GicsSectorName name = new GicsSectorName();
		name.set(GICS_PRIMARY_SECTOR_ID, sector.getId());
		name.set(GICS_PRIMARY_SECTOR_NAME, sector.getName());
		for(GicsIndustryGroup group: sector.getIndustryGroups()) {
			name.addGroup(GicsIndustryGroupName.toIndustryGroupName(group));
		}
		return name;
	}
	
	
}
