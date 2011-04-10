package org.sjd.gordon.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;

public class UnitaryPropertyTest extends AbstractJpaTest {

    @Test 
    public void should_create_a_unitary_property_value() throws Exception { 
    	StockEntity stock = createStock();
    	Exchange exchange = stock.getExchange();
 
    	UnitaryPropertyDefinition unitaryType = new UnitaryPropertyDefinition();
    	unitaryType.setDescription("Property description");
    	unitaryType.setName("Prop Name");
    	unitaryType.setType(DataType.STRING);
    	
    	tx.begin(); 
        em.persist(exchange);
        em.persist(stock); 
        em.persist(unitaryType); 
        tx.commit(); 
        assertNotNull("ID should not be null", unitaryType.getId()); 
 
        UnitaryPropertyValue unitaryValue = new UnitaryPropertyValue();
        unitaryValue.setEntityId(stock.getId());
        unitaryValue.setStringValue("A value");
        unitaryValue.setDefinition(unitaryType);
        
        tx.begin(); 
        em.persist(unitaryValue);
        tx.commit(); 
        assertNotNull("ID should not be null", unitaryValue.getId());
        
        // Retrieves all the entity unitary values from the database
        String getAllValues = "select v from UnitaryPropertyValue v where v.entityId=" + stock.getId();
        List<UnitaryPropertyValue> values = em.createQuery(getAllValues,UnitaryPropertyValue.class).getResultList(); 
        assertEquals(1, values.size()); 
    }
}
