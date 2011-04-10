package org.sjd.gordon.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;

public class TabularDatasetTest extends AbstractJpaTest {

    @Test 
    public void should_create_a_multi_dimension_dataset_entry() throws Exception { 
    	StockEntity stock = createStock();
    	Exchange exchange = stock.getExchange();
 
    	TabularDatasetDefinition dataSetDefinition = new TabularDatasetDefinition();
    	dataSetDefinition.setDescription("Property description");
    	dataSetDefinition.setName("Prop Name");
    	
    	ColumnDefinition columnDefinition = new ColumnDefinition();
    	columnDefinition.setDescription("Dimensional type");
    	columnDefinition.setName("X column");
    	columnDefinition.setType(DataType.STRING);
    	columnDefinition.setColumnOrder(Integer.valueOf(0));
    	dataSetDefinition.addColumnDefinition(columnDefinition);
    	
    	tx.begin(); 
        em.persist(exchange);
        em.persist(stock);
        em.persist(columnDefinition);
        em.persist(dataSetDefinition); 
        tx.commit(); 
        assertNotNull("ID should not be null", dataSetDefinition.getId()); 
 
        TabularDatasetElement dataSetElement = new TabularDatasetElement();
        dataSetElement.setEntityId(stock.getId());
        dataSetElement.setStringValue("A value");
        dataSetElement.setColumnDefinition(columnDefinition);
        dataSetElement.setRowIndex(Long.valueOf(1));
        
        tx.begin(); 
        em.persist(dataSetElement);
        tx.commit(); 
        assertNotNull("ID should not be null", dataSetElement.getId());
        
        // Retrieves all the entity multi dimensional values from the database
        String getAllValues = "select v from TabularDatasetElement v where v.entityId=" + stock.getId();
        List<TabularDatasetElement> values = em.createQuery(getAllValues,TabularDatasetElement.class).getResultList(); 
        assertEquals(1, values.size()); 
    }
}
