package org.sjd.gordon.model;

import java.net.URL;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.eclipse.persistence.internal.jpa.deployment.PersistenceInitializationHelper;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

public abstract class AbstractJpaTest {

	protected static EntityManagerFactory emf; 
	protected static EntityManager em; 
	protected static EntityTransaction tx; 
    
    @BeforeClass 
    public static void initEntityManager() throws Exception {
    	if (!canFindPersistenceXmlFile()) {
    		throw new RuntimeException("Can't find persistence file");
    	}
        emf = Persistence.createEntityManagerFactory("gordonModel"); 
        em = emf.createEntityManager(); 
    } 
 
    @AfterClass 
    public static void closeEntityManager() throws SQLException { 
        em.close(); 
        emf.close(); 
    } 
 
    @Before 
    public void initTransaction() { 
        tx = em.getTransaction(); 
    } 
 
    protected StockEntity createStock() {
    	Exchange exchange = new Exchange();
    	exchange.setActive(true);
    	exchange.setCode("ASX");
    	exchange.setName("Australian Exchange");
    	
    	StockEntity stock = new StockEntity();
    	stock.setCode("BHP");
    	stock.setExchange(exchange);
    	stock.setName("BHP Ltd");
    	return stock;
    }
    
	@SuppressWarnings("rawtypes")
	private static boolean canFindPersistenceXmlFile() throws Exception {
		PersistenceInitializationHelper initializationHelper = new PersistenceInitializationHelper();
		String emName = "gordonModel";
		Map properties = new HashMap();
		ClassLoader classLoader = initializationHelper.getClassLoader(emName, properties);

		Enumeration<URL> resources = classLoader.getResources("META-INF/persistence.xml");
		while (resources.hasMoreElements()) {
			return true;
		}
		return false;
	}
}
