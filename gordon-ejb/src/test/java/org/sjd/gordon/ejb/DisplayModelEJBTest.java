package org.sjd.gordon.ejb;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.sjd.gordon.model.ColumnDefinition;
import org.sjd.gordon.model.DataType;
import org.sjd.gordon.model.DatasetVisualisationType;
import org.sjd.gordon.model.DisplayGroup;
import org.sjd.gordon.model.Tab;
import org.sjd.gordon.model.TabularDGM;
import org.sjd.gordon.model.TabularDatasetDefinition;
import org.sjd.gordon.model.UnitaryPropertyDGM;
import org.sjd.gordon.model.UnitaryPropertyDefinition;

public class DisplayModelEJBTest extends AbstractEJBTest {

	private static UnitaryPropertyDefinition group1UnitaryDefinition;
	private static TabularDatasetDefinition dataSetDefinition;
	
	@BeforeClass 
	public static void doBeforeAllTests() throws Exception {
		initContainer();
		group1UnitaryDefinition = new UnitaryPropertyDefinition();
		group1UnitaryDefinition.setDescription("Property description");
    	group1UnitaryDefinition.setName("Prop Name");
    	group1UnitaryDefinition.setType(DataType.STRING);
    	
    	UnitaryPropertiesEJB unitaryPropertyEJB = (UnitaryPropertiesEJB) ctx.lookup("java:global/classes/UnitaryPropertiesEJB");
    	group1UnitaryDefinition = unitaryPropertyEJB.addDefinition(group1UnitaryDefinition);
    	
    	dataSetDefinition = new TabularDatasetDefinition();
    	dataSetDefinition.setDescription("Property description");
    	dataSetDefinition.setName("Prop Name");
    	ColumnDefinition columnDefinition = new ColumnDefinition();
    	columnDefinition.setDescription("Dimensional type");
    	columnDefinition.setName("X column");
    	columnDefinition.setType(DataType.STRING);
    	columnDefinition.setColumnOrder(Integer.valueOf(0));
    	dataSetDefinition.addColumnDefinition(columnDefinition);
    	
    	TabularDatasetEJB tabularDatasetEJB = (TabularDatasetEJB) ctx.lookup("java:global/classes/TabularDatasetEJB");
    	dataSetDefinition = tabularDatasetEJB.addDefinition(dataSetDefinition);
	}
	
	@Test 
    public void create_tabs() throws Exception {
    	Tab tab = new Tab();
    	tab.setActive(Boolean.TRUE);
    	tab.setName("Tab 1");
    	tab.setOrder(Integer.valueOf(1));
    	
    	DisplayGroup displayGroup = new DisplayGroup();
    	displayGroup.setActive(Boolean.TRUE);
    	displayGroup.setName("Group 1");
    	
    	UnitaryPropertyDGM unitaryDgm = new UnitaryPropertyDGM();
    	unitaryDgm.setPropertyDefinition(group1UnitaryDefinition);
    	unitaryDgm.setOrder(Integer.valueOf(1));
    	displayGroup.addMember(unitaryDgm);
    	
    	tab.addGroup(displayGroup);

    	DisplayModelEJB displayModelEJB = (DisplayModelEJB) ctx.lookup("java:global/classes/DisplayModelEJB");
    	tab = displayModelEJB.createTab(tab);
    	assertNotNull("ID should not be null", tab.getId());
    	assertEquals("Must have 1 unitary member in group.",1,tab.getGroups().get(0).getUnitaryMembers().size());
    	
    	// update
    	TabularDGM tabularDgm = new TabularDGM();
    	tabularDgm.setDatasetType(dataSetDefinition);
    	tabularDgm.setOrder(Integer.valueOf(1));
    	tabularDgm.setVisualisationType(DatasetVisualisationType.TABLE);
    	tab = displayModelEJB.getTabs().get(0);
    	tab.getGroups().get(0).addMember(tabularDgm);
    	
    	tab = displayModelEJB.updateTab(tab);
    	assertEquals("Must added DGM to display group 1.",1,tab.getGroups().get(0).getDatasetMembers().size());
    	
    	List<Tab> tabs = displayModelEJB.getTabs();
    	assertEquals("Must have 1 tab.",1,tabs.size());
    	assertEquals("Must have 1 dataset memeber in group.",1,tabs.get(0).getGroups().get(0).getDatasetMembers().size());
    	
    	displayModelEJB.removeTab(tab);
    	tabs = displayModelEJB.getTabs();
    	assertEquals("Must have 0 tab.",0,tabs.size());
	}
}
