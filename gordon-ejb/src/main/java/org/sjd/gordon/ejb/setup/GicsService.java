package org.sjd.gordon.ejb.setup;

import java.util.List;

import javax.ejb.Local;

import org.sjd.gordon.model.GicsIndustryGroup;
import org.sjd.gordon.model.GicsSector;

@Local
public interface GicsService {

	public GicsSector findSectorById(Integer id);
	public GicsSector findSectorByIndustryGroupId(Integer industryGroupId);
	public GicsIndustryGroup findIndustryGroupById(Integer id);
	public GicsSector createSector(GicsSector sector);
	public List<GicsSector> getSectors();
}
