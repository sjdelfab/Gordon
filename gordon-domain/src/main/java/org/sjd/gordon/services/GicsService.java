package org.sjd.gordon.services;

import java.util.List;

import org.sjd.gordon.model.GicsIndustryGroup;
import org.sjd.gordon.model.GicsSector;

public interface GicsService {

	public GicsSector findSectorById(Integer id);
	public GicsSector findSectorByIndustryGroupId(Integer industryGroupId);
	public GicsIndustryGroup findIndustryGroupById(Integer id);
	public GicsSector createSector(GicsSector sector);
	public List<GicsSector> getSectors();
	public GicsIndustryGroup findIndustryGroupByName(String industryGroup);
	
}
