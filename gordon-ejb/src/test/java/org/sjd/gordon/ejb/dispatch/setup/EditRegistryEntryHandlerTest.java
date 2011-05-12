package org.sjd.gordon.ejb.dispatch.setup;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;

import org.eclipse.persistence.exceptions.DatabaseException;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.sjd.gordon.ejb.ExchangeService;
import org.sjd.gordon.ejb.StockEntityService;
import org.sjd.gordon.ejb.setup.GicsService;
import org.sjd.gordon.model.Exchange;
import org.sjd.gordon.model.GicsIndustryGroup;
import org.sjd.gordon.model.StockEntity;
import org.sjd.gordon.shared.exceptions.NonUniqueResultException;
import org.sjd.gordon.shared.exceptions.OptimisticLockException;
import org.sjd.gordon.shared.registry.EditRegistryEntry;
import org.sjd.gordon.shared.registry.EditRegistryEntry.EditType;
import org.sjd.gordon.shared.registry.EditRegistryEntryResponse;
import org.sjd.gordon.shared.viewer.StockDetail;

import com.gwtplatform.dispatch.server.ExecutionContext;

public class EditRegistryEntryHandlerTest {

	private Mockery context;
	private StockDetail newDetails;
	private StockEntityService stockService;
	private ExchangeService exchangeService;
	private GicsService gicsService;
	private ExecutionContext executionContext;
	private EditRegistryEntryEJBHandler handler;
	
	@Rule
    public ExpectedException thrown = ExpectedException.none(); 
	
	@Before
    public void beforeTest() {
        context = new Mockery();
        newDetails = new StockDetail();
    	newDetails.setCode("ABC");
    	newDetails.setName("ABC Ltd");
    	stockService = context.mock(StockEntityService.class);
    	exchangeService = context.mock(ExchangeService.class);
    	executionContext = context.mock(ExecutionContext.class);
    	gicsService = context.mock(GicsService.class);
    	handler = new EditRegistryEntryEJBHandler(stockService,exchangeService,gicsService);
    }
    
    @Test
    public void add_duplicate_entry() throws Exception { 
    	final Exchange exchange = new Exchange();
    	exchange.setId(Integer.valueOf(1));
    	SQLException sqlException = new SQLException("","",23505,null);
    	final DatabaseException dbException = DatabaseException.sqlException(sqlException);
    	dbException.setErrorCode(23505);
    	context.checking(new Expectations() {
    		{ allowing(stockService).createStock(with(any(StockEntity.class))); will(throwException(new RuntimeException(dbException))); }
    		{ allowing(exchangeService).findExchangeById(with(any(Integer.class))); will(returnValue(exchange)); }
    	});
    	EditRegistryEntry editEntry = new EditRegistryEntry(newDetails,Integer.valueOf(1),EditType.ADD);
    	thrown.expect(NonUniqueResultException.class);
    	handler.execute(editEntry, executionContext);
    }
    
    @Test
    public void update_modified_entry() throws Exception {
    	final javax.persistence.OptimisticLockException throwing = new javax.persistence.OptimisticLockException();
    	context.checking(new Expectations() {
    		{ allowing(stockService).findStockById(with(any(Long.class))); will(returnValue(new StockEntity())); }
    		{ allowing(stockService).updateStock(with(any(StockEntity.class))); will(throwException(new RuntimeException(throwing)));}
    	});
    	EditRegistryEntry editEntry = new EditRegistryEntry(newDetails,Integer.valueOf(1),EditType.UPDATE);
    	thrown.expect(OptimisticLockException.class);
    	handler.execute(editEntry, executionContext);
    }
    
    @Test
    public void add() throws Exception {
    	newDetails.setPrimaryIndustryGroupId(Integer.valueOf(1));
    	newDetails.setPrimaryIndustryGroupName("Gas");
    	newDetails.setPrimarySectorId(Integer.valueOf(2));
    	newDetails.setPrimarySectorName("Energy");
    	
    	final Exchange exchange = new Exchange();
    	exchange.setId(Integer.valueOf(1));
    	final ReturnParameter createStockAction = new ReturnParameter(0) {
    		protected Object modify(Object param) {
    			((StockEntity)param).setVersion(Integer.valueOf(1));
    			((StockEntity)param).setId(Long.valueOf(1));
    			return param;
    		};
    	};
    	final GicsIndustryGroup industryGroup = new GicsIndustryGroup();
    	industryGroup.setId(Integer.valueOf(1));
    	industryGroup.setName("Gas");
    	context.checking(new Expectations() {
    		{ allowing(stockService).createStock(with(any(StockEntity.class))); will(createStockAction); }
    		{ allowing(exchangeService).findExchangeById(with(any(Integer.class))); will(returnValue(exchange)); }
    		{ allowing(gicsService).findIndustryGroupById(with(any(Integer.class))); will(returnValue(industryGroup)); }
    	});
    	EditRegistryEntry editEntry = new EditRegistryEntry(newDetails,Integer.valueOf(1),EditType.ADD);
    	EditRegistryEntryResponse response = handler.execute(editEntry, executionContext);
    	StockDetail returnedDetails = response.getStock();
    	assertEquals("ABC",returnedDetails.getCode());
    	assertEquals(Long.valueOf(1),returnedDetails.getId());
    	assertEquals("ABC Ltd",returnedDetails.getName());
    	assertEquals(Integer.valueOf(1),returnedDetails.getPrimaryIndustryGroupId());
    	assertEquals("Gas",returnedDetails.getPrimaryIndustryGroupName());
    	assertEquals(Integer.valueOf(2),returnedDetails.getPrimarySectorId());
    	assertEquals("Energy",returnedDetails.getPrimarySectorName());
    	assertEquals(Integer.valueOf(1),returnedDetails.getVersion());
    }
    
    @Test
    public void update() throws Exception {
    	newDetails.setPrimaryIndustryGroupId(Integer.valueOf(1));
    	newDetails.setPrimaryIndustryGroupName("Gas");
    	newDetails.setPrimarySectorId(Integer.valueOf(2));
    	newDetails.setPrimarySectorName("Energy");
    	newDetails.setCurrentPrice(new BigDecimal("10.0"));
    	newDetails.setVersion(Integer.valueOf(1));
    	Date date = new Date();
    	newDetails.setListDate(date);
    	newDetails.setLastTradeDate(date);
    	
    	final GicsIndustryGroup industryGroup = new GicsIndustryGroup();
    	industryGroup.setId(Integer.valueOf(1));
    	industryGroup.setName("Gas");
    	
    	final StockEntity stockEntity = new StockEntity();
    	stockEntity.setCode("ABC");
    	stockEntity.setName("ABC Ltd");
    	stockEntity.setGicsIndustryGroup(industryGroup);
    	stockEntity.setId(Long.valueOf(1));
    	stockEntity.setVersion(Integer.valueOf(1));
    	
    	final ReturnParameter updateStockAction = new ReturnParameter(0) {
    		protected Object modify(Object param) {
    			StockEntity entity = (StockEntity)param; 
    			entity.setVersion(entity.getVersion()+1);
    			return entity;
    		};
    	};
    	context.checking(new Expectations() {
    		{ allowing(stockService).findStockById(with(any(Long.class))); will(returnValue(stockEntity)); }
    		{ allowing(stockService).updateStock(with(any(StockEntity.class))); will(updateStockAction); }
    		{ allowing(gicsService).findIndustryGroupById(with(any(Integer.class))); will(returnValue(industryGroup)); }
    	});
    	
    	EditRegistryEntry editEntry = new EditRegistryEntry(newDetails,Integer.valueOf(1),EditType.UPDATE);
    	EditRegistryEntryResponse response = handler.execute(editEntry, executionContext);
    	StockDetail returnedDetails = response.getStock();
    	assertEquals("ABC",returnedDetails.getCode());
    	assertEquals(Long.valueOf(1),returnedDetails.getId());
    	assertEquals("ABC Ltd",returnedDetails.getName());
    	assertEquals(Integer.valueOf(1),returnedDetails.getPrimaryIndustryGroupId());
    	assertEquals("Gas",returnedDetails.getPrimaryIndustryGroupName());
    	assertEquals(Integer.valueOf(2),returnedDetails.getPrimarySectorId());
    	assertEquals("Energy",returnedDetails.getPrimarySectorName());
    	assertEquals(Integer.valueOf(2),returnedDetails.getVersion());
    	assertEquals(new BigDecimal("10.0"),returnedDetails.getCurrentPrice());
    	assertEquals(date,returnedDetails.getListDate());
    	assertEquals(date,returnedDetails.getLastTradeDate());    	
    }
    
    @Test
    public void update_change_industry_group() throws Exception {
    	newDetails.setPrimaryIndustryGroupId(Integer.valueOf(1));
    	newDetails.setPrimaryIndustryGroupName("Gas");
    	newDetails.setPrimarySectorId(Integer.valueOf(2));
    	newDetails.setPrimarySectorName("Energy");
    	newDetails.setCurrentPrice(new BigDecimal("10.0"));
    	newDetails.setVersion(Integer.valueOf(1));
    	Date date = new Date();
    	newDetails.setListDate(date);
    	newDetails.setLastTradeDate(date);
    	
    	final GicsIndustryGroup newIndustryGroup = new GicsIndustryGroup();
    	newIndustryGroup.setId(Integer.valueOf(1));
    	newIndustryGroup.setName("Gas");
    	
    	final GicsIndustryGroup industryGroup = new GicsIndustryGroup();
    	industryGroup.setId(Integer.valueOf(2));
    	industryGroup.setName("Oil");
    	
    	final StockEntity stockEntity = new StockEntity();
    	stockEntity.setCode("ABC");
    	stockEntity.setName("ABC Ltd");
    	stockEntity.setGicsIndustryGroup(industryGroup);
    	stockEntity.setId(Long.valueOf(1));
    	stockEntity.setVersion(Integer.valueOf(1));
    	
    	final ReturnParameter updateStockAction = new ReturnParameter(0) {
    		protected Object modify(Object param) {
    			StockEntity entity = (StockEntity)param; 
    			entity.setVersion(entity.getVersion()+1);
    			entity.setGicsIndustryGroup(newIndustryGroup);
    			return entity;
    		};
    	};
    	context.checking(new Expectations() {
    		{ allowing(stockService).findStockById(with(any(Long.class))); will(returnValue(stockEntity)); }
    		{ allowing(stockService).updateStock(with(any(StockEntity.class))); will(updateStockAction); }
    		{ allowing(gicsService).findIndustryGroupById(with(any(Integer.class))); will(returnValue(industryGroup)); }
    	});
    	
    	EditRegistryEntry editEntry = new EditRegistryEntry(newDetails,Integer.valueOf(1),EditType.UPDATE);
    	EditRegistryEntryResponse response = handler.execute(editEntry, executionContext);
    	StockDetail returnedDetails = response.getStock();
    	assertEquals("ABC",returnedDetails.getCode());
    	assertEquals(Long.valueOf(1),returnedDetails.getId());
    	assertEquals("ABC Ltd",returnedDetails.getName());
    	assertEquals(Integer.valueOf(1),returnedDetails.getPrimaryIndustryGroupId());
    	assertEquals("Gas",returnedDetails.getPrimaryIndustryGroupName());
    	assertEquals(Integer.valueOf(2),returnedDetails.getPrimarySectorId());
    	assertEquals("Energy",returnedDetails.getPrimarySectorName());
    	assertEquals(Integer.valueOf(2),returnedDetails.getVersion());
    	assertEquals(new BigDecimal("10.0"),returnedDetails.getCurrentPrice());
    	assertEquals(date,returnedDetails.getListDate());
    	assertEquals(date,returnedDetails.getLastTradeDate());    	
    }
    
}
