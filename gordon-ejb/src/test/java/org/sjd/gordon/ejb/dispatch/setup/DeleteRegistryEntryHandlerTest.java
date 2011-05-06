package org.sjd.gordon.ejb.dispatch.setup;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.sjd.gordon.ejb.StockEntityService;
import org.sjd.gordon.shared.exceptions.EntityNotFoundException;
import org.sjd.gordon.shared.registry.DeleteRegistryEntry;

import com.gwtplatform.dispatch.server.ExecutionContext;

public class DeleteRegistryEntryHandlerTest {

	private Mockery context;
	@Rule
    public ExpectedException thrown = ExpectedException.none(); 
	
	@Before
    public void beforeTest() {
        context = new Mockery();
    }
    
    @Test
    public void delete_non_existent_entry() throws Exception {  
    	final StockEntityService service = context.mock(StockEntityService.class);
    	final ExecutionContext executionContext = context.mock(ExecutionContext.class);
    	context.checking(new Expectations() {
    		{ allowing(service).findStockById(with(any(Long.class))); will(returnValue(null)); }
    	});
    	thrown.expect(EntityNotFoundException.class);
    	DeleteRegistryEntryEJBHandler handler = new DeleteRegistryEntryEJBHandler(service);
    	DeleteRegistryEntry deleteEntry = new DeleteRegistryEntry(Long.valueOf(1));
    	handler.execute(deleteEntry, executionContext);
    }
}