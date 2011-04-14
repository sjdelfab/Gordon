package org.sjd.gordon;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.eclipse.persistence.internal.jpa.deployment.PersistenceInitializationHelper;
import org.sjd.gordon.dao.csv.CSVSecurityHistoryDAO;
import org.sjd.gordon.dao.csv.CSVSecurityRegistryDAO;
import org.sjd.gordon.model.Exchange;
import org.sjd.gordon.model.StockDayTradeRecord;
import org.sjd.gordon.model.StockEntity;

public class BuildDatabase {

    private static File masterListFile, csvDirectory = null;
    private static EntityManager em;
    private static CSVSecurityHistoryDAO tradeHistoryDao;
	
	public static void main(String[] args) throws Exception {
		if (args.length != 2) {
			System.out.println("Usage: BuildDatabase <List CSV file> <Dir with CSVs");
		}
		setMasterListFile(args[0]);
		setCSVDirectory(args[1]);
		if (!canFindPersistenceXmlFile()) {
        	System.out.println("Can't find persistence XMl file.");
        	return;
        }
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gordon"); 
        em = emf.createEntityManager();
        
        build();
	}
	
    private static void build() throws Exception {
    	loadTradeHistory(buildRegistry(addExchange()));
	}

	private static Exchange addExchange() throws Exception {
        Exchange exchange = new Exchange();
        exchange.setActive(true);
        exchange.setCode("ASX");
        exchange.setName("Australian Stock Exchange");
        EntityTransaction tx = em.getTransaction(); 
        tx.begin(); 
        em.persist(exchange); 
        tx.commit();
        return exchange;
    }    	

	private static List<StockEntity> buildRegistry(Exchange exchange) throws Exception {
		CSVSecurityRegistryDAO registryDao = new CSVSecurityRegistryDAO(masterListFile.getAbsolutePath(),exchange);
		List<StockEntity> stocks = registryDao.getStocksSortedByCode(exchange);
		for(int i=0; i < stocks.size(); i++) {
			StockEntity stock = stocks.get(i);
			EntityTransaction tx = em.getTransaction(); 
	        tx.begin(); 
	        em.persist(stock); 
	        tx.commit();
	        stocks.set(i,stock);
		}
		return stocks;
	}
	
	private static void loadTradeHistory(List<StockEntity> stocks) throws Exception {
		for(StockEntity stock: stocks) {
			loadTradeHistory(stock);
		}
	}
	
	private static void loadTradeHistory(StockEntity stock) throws Exception {
		List<StockDayTradeRecord> tradeHistory = tradeHistoryDao.getAllDayTrades(stock);
		EntityTransaction tx = em.getTransaction(); 
        tx.begin();
        StockDayTradeRecord firstRecord = null;
        StockDayTradeRecord record = null;
		for(int i=0; i < tradeHistory.size(); i++) {
			record = tradeHistory.get(i);
			em.persist(record);
			if (firstRecord == null) {
				firstRecord = record;
			}
		}
		stock.setListDate(firstRecord.getDate());
		stock.setLastTradeDate(record.getDate());
		em.merge(stock);
		tx.commit();
	}
	
    private static void setMasterListFile(String filename) throws IOException {
        masterListFile = new File(filename);
        if (!masterListFile.exists()) {
            throw new IOException("File doesn't exist: " + filename);   
        }        
        if (masterListFile.isDirectory()) {
            throw new IOException("Must be a file, not directory");   
        }
        if (!masterListFile.canRead()) {
            throw new IOException("Unable to read: " + filename);   
        }        
    }
    
    private static void setCSVDirectory(String dir) throws IOException {
        csvDirectory = new File(dir);
        if (!csvDirectory.exists()) {
            throw new IOException("Directory doesn't exist: " + dir);   
        }        
        if (!csvDirectory.isDirectory()) {
            throw new IOException("Must be a directory, not file");   
        }
        if (!csvDirectory.canRead()) {
            throw new IOException("Unable to read: " + dir);   
        } 
        tradeHistoryDao = new CSVSecurityHistoryDAO(csvDirectory.getAbsolutePath());
    }
    
	private static boolean canFindPersistenceXmlFile() throws Exception {
	      PersistenceInitializationHelper initializationHelper = new PersistenceInitializationHelper();
	      String emName = "gordon";
	      Map<Object,Object> properties = new HashMap<Object,Object>();
	      ClassLoader classLoader = initializationHelper.getClassLoader(emName, properties);
	      
	      Enumeration<URL> resources = classLoader.getResources("META-INF/persistence.xml");
	      while (resources.hasMoreElements()) {
	          return true;
	      }	
	      return false;
		}    

}
