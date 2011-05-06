package org.sjd.gordon.ejb;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.sjd.gordon.model.Exchange;

public class ExchangeEJBTest extends AbstractEJBTest {
   
    @Test 
    public void should_create_an_exchange() throws Exception {
    	truncateDatabase();
    	ExchangeService exchangeEJB = (ExchangeService) AllEjbTests.ctx.lookup("java:global/classes/ExchangeEJB!org.sjd.gordon.ejb.ExchangeService"); 
 
    	Exchange exchange = new Exchange();
    	exchange.setActive(true);
    	exchange.setCode("ASX");
    	exchange.setName("Australian Exchange");
        exchange = exchangeEJB.createExchange(exchange); 
        assertNotNull("ID should not be null", exchange.getId());
        
        List<Exchange> exchanges = exchangeEJB.getExchanges();
        assertEquals("Must have 1 exchange",1,exchanges.size());
        
        exchange = exchangeEJB.findExchangeById(exchange.getId());
        assertNotNull("Exchange should not be null", exchange);
    }
}
