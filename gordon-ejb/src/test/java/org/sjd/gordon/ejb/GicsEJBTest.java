package org.sjd.gordon.ejb;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.sjd.gordon.ejb.setup.GicsServiceLocal;
import org.sjd.gordon.model.GicsIndustryGroup;
import org.sjd.gordon.model.GicsSector;

public class GicsEJBTest extends AbstractEJBTest {
	
    @Test 
    public void should_create_a_sector_with_industry() throws Exception {
    	truncateDatabase();
    	GicsSector sector = new GicsSector();
    	sector.setActive(true);
    	sector.setCode(10);
    	sector.setName("Energy");
    	GicsIndustryGroup group = new GicsIndustryGroup();
    	group.setActive(true);
    	group.setCode(1010);
    	group.setName("Energy");
    	sector.addIndustryGroup(group);
    	
    	GicsServiceLocal gicsService = (GicsServiceLocal) AllEjbTests.ctx.lookup("java:global/classes/GicsEJB!org.sjd.gordon.ejb.setup.GicsService");
    	sector = gicsService.createSector(sector);
    	assertNotNull("ID should not be null", sector.getId());
    	Integer sectorId = sector.getId();
    	
    	sector = gicsService.findSectorByIndustryGroupId(group.getId());
    	assertEquals(sectorId.intValue(),sector.getId());
    }
}
