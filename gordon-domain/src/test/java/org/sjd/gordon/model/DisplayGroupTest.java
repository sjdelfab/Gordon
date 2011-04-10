package org.sjd.gordon.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;

public class DisplayGroupTest extends AbstractJpaTest {

    @Test 
    public void should_create_a_tab() throws Exception {
    	UnitaryPropertyDefinition unitaryType = new UnitaryPropertyDefinition();
    	unitaryType.setDescription("Property description");
    	unitaryType.setName("Prop Name");
    	unitaryType.setType(DataType.STRING);
    	
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
        em.persist(unitaryType);
        em.persist(dataSetDefinition);
        //em.persist(columnDefinition);
        tx.commit(); 
        assertNotNull("ID should not be null", unitaryType.getId()); 
    	
    	Tab tab = new Tab();
    	tab.setActive(Boolean.TRUE);
    	tab.setName("Tab 1");
    	tab.setOrder(Integer.valueOf(1));
    	
    	DisplayGroup displayGroup = new DisplayGroup();
    	displayGroup.setActive(Boolean.TRUE);
    	displayGroup.setName("Group 1");
    	
    	UnitaryPropertyDGM unitaryDgm = new UnitaryPropertyDGM();
    	unitaryDgm.setDataType(unitaryType);
    	unitaryDgm.setOrder(Integer.valueOf(1));
    	displayGroup.addMember(unitaryDgm);
    	
    	TabularDGM tabularDgm = new TabularDGM();
    	tabularDgm.setDatasetType(dataSetDefinition);
    	tabularDgm.setOrder(Integer.valueOf(1));
    	tabularDgm.setVisualisationType(DatasetVisualisationType.TABLE);
    	displayGroup.addMember(tabularDgm);
    	
    	tab.addGroup(displayGroup);
    	tx.begin(); 
        em.persist(tab);
        //em.persist(displayGroup);
        //em.persist(tabularDgm);
        //em.persist(unitaryDgm);
        tx.commit(); 
        assertNotNull("ID should not be null", tab.getId());
        
        // Retrieves all the tab defined from the database
        String getAllTabs = "select t from Tab t";
        List<Tab> tabs = em.createQuery(getAllTabs,Tab.class).getResultList(); 
        assertEquals(1, tabs.size());
        tab = tabs.get(0);
        assertEquals(1, tab.getGroups().size());
        displayGroup = tab.getGroups().get(0);
        assertEquals(1, displayGroup.getDatasetMembers().size());
        assertEquals(1, displayGroup.getUnitaryMembers().size());
    }

}
