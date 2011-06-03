package org.sjd.gordon.ejb;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.sjd.gordon.ejb.setup.TabularDatasetEJB;
import org.sjd.gordon.model.ColumnDefinition;
import org.sjd.gordon.model.DataType;
import org.sjd.gordon.model.Exchange;
import org.sjd.gordon.model.StockEntity;
import org.sjd.gordon.model.TabularDatasetDefinition;
import org.sjd.gordon.model.TabularDatasetElement;
import org.sjd.gordon.model.TabularDatasetRow;

public class TabularDatasetEJBTest extends AbstractEJBTest {
		 
		private static Exchange exchange;
		private static StockEntity stock;
		
		@BeforeClass 
		public static void createExchangeAndStock() throws Exception {
			truncateDatabase();
			ExchangeServiceLocal exchangeEJB = (ExchangeServiceLocal) AllEjbTests.ctx.lookup("java:global/classes/ExchangeEJB!org.sjd.gordon.ejb.ExchangeService"); 
	   	 
	    	exchange = new Exchange();
	    	exchange.setActive(true);
	    	exchange.setCode("ASX");
	    	exchange.setName("Australian Exchange");
	        exchange = exchangeEJB.createExchange(exchange);
	        
	        stock = new StockEntity();
	    	stock.setCode("BHP");
	    	stock.setExchange(exchange);
	    	stock.setName("BHP Ltd");
	    	
	    	StockEntityServiceLocal stockEntityEjb = (StockEntityServiceLocal) AllEjbTests.ctx.lookup("java:global/classes/StockEntityEJB!org.sjd.gordon.ejb.StockEntityService");
	    	stock = stockEntityEjb.createStock(stock);
		}
		
	    @Test 
	    public void create_tabular_dataset() throws Exception {
	    	TabularDatasetDefinition definition = new TabularDatasetDefinition();
	    	definition.setName("A table");
	    	definition.setDescription("Tabular dataset");
	    	ColumnDefinition columnDefinition = new ColumnDefinition();
	    	columnDefinition.setColumnOrder(Integer.valueOf(0));
	    	columnDefinition.setDescription("Column 0 description");
	    	columnDefinition.setName("Col 0");
	    	columnDefinition.setType(DataType.STRING);
	    	definition.addColumnDefinition(columnDefinition);
	    	
	    	TabularDatasetEJB tabularDatasetEJB = (TabularDatasetEJB) AllEjbTests.ctx.lookup("java:global/classes/TabularDatasetEJB");
	    	definition = tabularDatasetEJB.addDefinition(definition);
	    	assertNotNull("ID should not be null", definition.getId());
	    	
	    	columnDefinition = new ColumnDefinition();
	    	columnDefinition.setColumnOrder(Integer.valueOf(1));
	    	columnDefinition.setDescription("Column 1 description");
	    	columnDefinition.setName("Col 1");
	    	columnDefinition.setType(DataType.REAL);
	    	definition.addColumnDefinition(columnDefinition);
	    	definition = tabularDatasetEJB.updateDefinition(definition);
	    	assertEquals("Must have 2 column definitions.",2,definition.getColumnDefinitions().size());
	    	
	    	TabularDatasetDefinition newDefinition = new TabularDatasetDefinition();
	    	newDefinition.setName("New table");
	    	newDefinition.setDescription("New Tabular dataset");
	    	ColumnDefinition newColumnDefinition = new ColumnDefinition();
	    	newColumnDefinition.setColumnOrder(Integer.valueOf(0));
	    	newColumnDefinition.setDescription("New Column 0 description");
	    	newColumnDefinition.setName("New Col 0");
	    	newColumnDefinition.setType(DataType.DATE);
	    	newDefinition.addColumnDefinition(newColumnDefinition);
	    	newDefinition = tabularDatasetEJB.addDefinition(newDefinition);
	    	
	    	List<TabularDatasetDefinition> definitions = tabularDatasetEJB.getDefinitions();
	    	assertEquals("Must have 2 definitions.",2,definitions.size());
	    	
	    	tabularDatasetEJB.deleteDefinition(newDefinition);
	    	definitions = tabularDatasetEJB.getDefinitions();
	    	assertEquals("Must have 1 definition.",1,definitions.size());
	    	
	    	TabularDatasetRow row0 = new TabularDatasetRow(definition.getColumnDefinitions().size());
	    	TabularDatasetElement element0_0 = new TabularDatasetElement();
	    	element0_0.setColumnDefinition(definition.getColumnDefinitions().get(0));
	    	element0_0.setEntityId(stock.getId());
	    	element0_0.setStringValue("Value 0");
	    	element0_0.setRowIndex(0l);
	    	row0.set(0, element0_0);
	    	TabularDatasetElement element0_1 = new TabularDatasetElement();
	    	element0_1 = new TabularDatasetElement();
	    	element0_1.setColumnDefinition(definition.getColumnDefinitions().get(1));
	    	element0_1.setEntityId(stock.getId());
	    	element0_1.setRealNumberValue(new BigDecimal("3.14"));
	    	element0_1.setRowIndex(0l);
	    	row0.set(1, element0_1);
	    	
	    	row0 = tabularDatasetEJB.addRow(row0);
	    	
	    	List<TabularDatasetRow> data = tabularDatasetEJB.getData(stock, definition);
	    	assertEquals("Must have 1 row.",1,data.size());
	    	
	    	TabularDatasetElement tableElement = row0.get(0);
	    	tableElement.setStringValue("New value");
	    	tabularDatasetEJB.updateElement(tableElement);
	    	data = tabularDatasetEJB.getData(stock, definition);
	    	assertEquals("Must have changed element value.","New value",data.get(0).get(0).getStringValue());
	    	
	    	tabularDatasetEJB.removeRow(tableElement.getRowIndex());
	    	data = tabularDatasetEJB.getData(stock, definition);
	    	assertEquals("Must have 0 rows.",0,data.size());
	    }
}
