package org.sjd.gordon.ejb;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.sjd.gordon.model.DataType;
import org.sjd.gordon.model.Exchange;
import org.sjd.gordon.model.StockEntity;
import org.sjd.gordon.model.UnitaryPropertyDefinition;
import org.sjd.gordon.model.UnitaryPropertyValue;

public class UnitaryPropertiesEJBTest extends AbstractEJBTest {
		 
		private static Exchange exchange;
		private static StockEntity stock;
		
		@BeforeClass 
		public static void createExchangeAndStock() throws Exception {
			initContainer();
			ExchangeEJB exchangeEJB = (ExchangeEJB) ctx.lookup("java:global/classes/ExchangeEJB"); 
	   	 
	    	exchange = new Exchange();
	    	exchange.setActive(true);
	    	exchange.setCode("ASX");
	    	exchange.setName("Australian Exchange");
	        exchange = exchangeEJB.createExchange(exchange);
	        
	        stock = new StockEntity();
	    	stock.setCode("BHP");
	    	stock.setExchange(exchange);
	    	stock.setLastTradeDate(new Date());
	    	stock.setListDate(new Date());
	    	stock.setName("BHP Ltd");
	    	
	    	StockEntityEJB stockEntityEjb = (StockEntityEJB) ctx.lookup("java:global/classes/StockEntityEJB");
	    	stock = stockEntityEjb.createStock(stock);
		}
		
	    @Test 
	    public void create_a_unitary_property() throws Exception {
	    	UnitaryPropertyDefinition definition = new UnitaryPropertyDefinition();
	    	definition.setDescription("A desc");
	    	definition.setName("A Property");
	    	definition.setType(DataType.STRING);
	    	UnitaryPropertiesEJB unitaryPropertyEJB = (UnitaryPropertiesEJB) ctx.lookup("java:global/classes/UnitaryPropertiesEJB");
	    	definition = unitaryPropertyEJB.addDefinition(definition);
	    	assertNotNull("ID should not be null", definition.getId());
	    	
	    	definition.setDescription("New description");
	    	definition = unitaryPropertyEJB.updateDefinition(definition);
	    	assertEquals("Must have changed description.","New description",definition.getDescription());
	    	
	    	UnitaryPropertyDefinition realNumberDefinition = new UnitaryPropertyDefinition();
	    	realNumberDefinition.setDescription("Real number definition");
	    	realNumberDefinition.setName("FoReal");
	    	realNumberDefinition.setType(DataType.REAL);
	    	realNumberDefinition = unitaryPropertyEJB.addDefinition(realNumberDefinition);
	    	
	    	List<UnitaryPropertyDefinition> definitions = unitaryPropertyEJB.getDefinitions();
	    	assertEquals("Must have 2 definitions.",2,definitions.size());
	    	
	    	unitaryPropertyEJB.deleteDefinition(realNumberDefinition);
	    	definitions = unitaryPropertyEJB.getDefinitions();
	    	assertEquals("Must have 1 definitions.",1,definitions.size());
	    	
	    	UnitaryPropertyValue value = new UnitaryPropertyValue();
	    	value.setDefinition(definition);
	    	value.setEntityId(stock.getId());
	    	value.setStringValue("A value");
	    	value = unitaryPropertyEJB.addValue(value);
	    	assertNotNull("ID should not be null", value.getId());
	    	
	    	value.setStringValue("New value");
	    	value = unitaryPropertyEJB.updateValue(value);
	    	assertEquals("Must have changed value.","New value",value.getStringValue());
	    	
	    	List<UnitaryPropertyValue> values = unitaryPropertyEJB.getValues(stock);
	    	assertEquals("Must have 1 value.",1,values.size());
	    }
}
