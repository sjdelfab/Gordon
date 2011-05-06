package org.sjd.gordon.shared.registry;

import java.io.Serializable;

import org.sjd.gordon.model.GicsIndustryGroup;

import com.extjs.gxt.ui.client.data.BaseModel;

public class GicsIndustryGroupName extends BaseModel implements Serializable {
		
	private static final long serialVersionUID = -2833726280272247928L;
	
	public static final String GICS_PRIMARY_INDUSTRY_GROUP_ID = "primary_sector_id";
	public static final String GICS_PRIMARY_INDUSTRY_GROUP_NAME = "primary_sector_name";
	
	public GicsIndustryGroupName() {}
	
	public String getName() {
		return (String)get(GICS_PRIMARY_INDUSTRY_GROUP_NAME);
	}
	
	public Integer getId() {
		return (Integer)get(GICS_PRIMARY_INDUSTRY_GROUP_ID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GicsIndustryGroupName other = (GicsIndustryGroupName) obj;
		if (getName() == null) {
			if (other.getName() != null)
				return false;
		} else if (!getName().equals(other.getName()))
			return false;
		return true;
	}

	public static GicsIndustryGroupName toIndustryGroupName(GicsIndustryGroup industryGroup) {
		GicsIndustryGroupName name = new GicsIndustryGroupName();
		name.set(GICS_PRIMARY_INDUSTRY_GROUP_ID, industryGroup.getId());
		name.set(GICS_PRIMARY_INDUSTRY_GROUP_NAME, industryGroup.getName());
		return name;
	}

}
