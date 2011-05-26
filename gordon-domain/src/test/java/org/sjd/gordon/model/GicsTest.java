package org.sjd.gordon.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;

public class GicsTest extends AbstractJpaTest {

    @Test 
    public void should_create_a_sector_with_industry() throws Exception { 
    	GicsSector sector = new GicsSector();
    	sector.setActive(true);
    	sector.setCode(10);
    	sector.setName("Energy");
    	GicsIndustryGroup group = new GicsIndustryGroup();
    	group.setActive(true);
    	group.setCode(1010);
    	group.setName("Energy");
    	sector.addIndustryGroup(group);
    	
    	tx.begin(); 
        em.persist(sector); 
        tx.commit(); 
        assertNotNull("ID should not be null", sector.getId()); 
 
        String getAllSectors = "select s from GicsSector s";
        List<GicsSector> sectors = em.createQuery(getAllSectors,GicsSector.class).getResultList(); 
        assertEquals(1, sectors.size());
        group = sectors.get(0).getIndustryGroups().get(0);
        
        // Create a stock with a sector
        StockEntity stock = createStock();
        stock.setGicsIndustryGroup(group);
    	Exchange exchange = stock.getExchange();
    	tx.begin(); 
        em.persist(exchange);
        em.persist(stock); 
        tx.commit();
        
        String getAllStocks = "select s from StockEntity s WHERE s.id = " + stock.getId();
        List<StockEntity> stocks = em.createQuery(getAllStocks,StockEntity.class).getResultList(); 
        assertEquals(1, stocks.size());
        assertNotNull(stocks.get(0).getGicsIndustryGroup());
        assertEquals(1010, stocks.get(0).getGicsIndustryGroup().getCode().intValue());        
    }
    
}
