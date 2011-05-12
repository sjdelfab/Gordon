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
import org.sjd.gordon.model.GicsIndustryGroup;
import org.sjd.gordon.model.GicsSector;
import org.sjd.gordon.model.Group;
import org.sjd.gordon.model.StockDayTradeRecord;
import org.sjd.gordon.model.StockEntity;
import org.sjd.gordon.model.User;
import org.sjd.gordon.util.SHA_256_Util;

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
    	createUsers();
    	createGicsSectors();
    	loadTradeHistory(buildRegistry(addExchange()));
	}

    private static HashMap<String,Integer> industryGroupNames = new HashMap<String,Integer>();
    
    private static void createGicsSectors() {
    	createEnergySector();
    	createMaterialsSector();
    	createIndustrialsSector();
    	createConsumerDiscretionarySector();
    	createConsumerStaplesSector();
    	createHealthCareSector();
    	createFinancialsSector();
    	createInformationTechnologySector();
    	createTelecommunicationServicesSector();
    	createUtilitiesSector();
    }
    
    private static GicsSector createSector(String name, Integer code) {
    	GicsSector sector = new GicsSector();
    	sector.setActive(true);
    	sector.setCode(code);
    	sector.setName(name);
    	return sector;
    }
    
    private static void createEnergySector() {
    	EntityTransaction tx = em.getTransaction(); 
    	tx.begin();
    	GicsSector sector = createSector("Energy",10);
    	sector.addIndustryGroup(createIndustry("Energy",1010));
    	em.persist(sector);
    	addGroups(sector);
    	tx.commit();
    }
    
    private static void addGroups(GicsSector sector) {
    	for(GicsIndustryGroup group: sector.getIndustryGroups()) {
    		industryGroupNames.put(group.getName(), group.getId());
    	}
    }
    
    private static void createMaterialsSector() {
    	EntityTransaction tx = em.getTransaction(); 
    	tx.begin();
    	GicsSector sector = createSector("Materials",15);
    	sector.addIndustryGroup(createIndustry("Materials",1510));
    	em.persist(sector);
    	addGroups(sector);
    	tx.commit();
    }
    
    private static void createIndustrialsSector() {
    	EntityTransaction tx = em.getTransaction(); 
    	tx.begin();
    	GicsSector sector = createSector("Industrials",20);
    	sector.addIndustryGroup(createIndustry("Capital Goods",1510));
    	sector.addIndustryGroup(createIndustry("Commercial & Professional Services",2020));
    	sector.addIndustryGroup(createIndustry("Transportation",2030));
    	em.persist(sector);
    	addGroups(sector);
    	tx.commit();
    }
    
    private static void createConsumerDiscretionarySector() {
    	EntityTransaction tx = em.getTransaction(); 
    	tx.begin();
    	GicsSector sector = createSector("Consumer Discretionary",25);
    	sector.addIndustryGroup(createIndustry("Automobiles and Components",2510));
    	sector.addIndustryGroup(createIndustry("Consumer Durables and Apparel",2520));
    	sector.addIndustryGroup(createIndustry("Consumer Services",2530));
    	sector.addIndustryGroup(createIndustry("Media",2540));
    	sector.addIndustryGroup(createIndustry("Retailing",2550));
    	em.persist(sector);
    	addGroups(sector);
    	tx.commit();
    }
    
    private static void createConsumerStaplesSector() {
    	EntityTransaction tx = em.getTransaction(); 
    	tx.begin();
    	GicsSector sector = createSector("Consumer Staples",30);
    	sector.addIndustryGroup(createIndustry("Food & Staples Retailing",3010));
    	sector.addIndustryGroup(createIndustry("Food, Beverage & Tobacco",3020));
    	sector.addIndustryGroup(createIndustry("Household & Personal Products",3030));
    	em.persist(sector);
    	addGroups(sector);
    	tx.commit();
    }
    
    private static void createHealthCareSector() {
    	EntityTransaction tx = em.getTransaction(); 
    	tx.begin();
    	GicsSector sector = createSector("Health Care",35);
    	sector.addIndustryGroup(createIndustry("Health Care Equipment & Services",3510));
    	sector.addIndustryGroup(createIndustry("Pharmaceuticals, Biotechnology & Life Sciences",3520));
    	em.persist(sector);
    	addGroups(sector);
    	tx.commit();
    }
    
    private static void createFinancialsSector() {
    	EntityTransaction tx = em.getTransaction(); 
    	tx.begin();
    	GicsSector sector = createSector("Financials",40);
    	sector.addIndustryGroup(createIndustry("Banks",4010));
    	sector.addIndustryGroup(createIndustry("Diversified Financials",4020));
    	sector.addIndustryGroup(createIndustry("Insurance",4030));
    	sector.addIndustryGroup(createIndustry("Real Estate",4040));
    	em.persist(sector);
    	addGroups(sector);
    	tx.commit();
    }
    
    private static void createInformationTechnologySector() {
    	EntityTransaction tx = em.getTransaction(); 
    	tx.begin();
    	GicsSector sector = createSector("Information Technology",45);
    	sector.addIndustryGroup(createIndustry("Software & Services",4510));
    	sector.addIndustryGroup(createIndustry("Technology Hardware & Equipment",4520));
    	sector.addIndustryGroup(createIndustry("Semiconductors & Semiconductor Equipment",4530));
    	em.persist(sector);
    	addGroups(sector);
    	tx.commit();
    }
    
    private static void createTelecommunicationServicesSector() {
    	EntityTransaction tx = em.getTransaction(); 
    	tx.begin();
    	GicsSector sector = createSector("Telecommunication Services",50);
    	sector.addIndustryGroup(createIndustry("Telecommunication Services",5010));
    	em.persist(sector);
    	addGroups(sector);
    	tx.commit();
    }
    
    private static void createUtilitiesSector() {
    	EntityTransaction tx = em.getTransaction(); 
    	tx.begin();
    	GicsSector sector = createSector("Utilities",55);
    	sector.addIndustryGroup(createIndustry("Utilities",5510));
    	em.persist(sector);
    	addGroups(sector);
    	tx.commit();
    }
    
    private static GicsIndustryGroup createIndustry(String name, Integer code) {
    	GicsIndustryGroup industry = new GicsIndustryGroup();
    	industry.setActive(true);
    	industry.setCode(code);
    	industry.setName(name);
    	return industry;
    }
    
    private static void createUsers() throws Exception {
    	User user = new User();
    	user.setFirstName("Simon");
    	user.setLastName("Doe");
    	user.setUsername("sdoe");
    	user.setActive(Boolean.TRUE);
    	user.setPassword(SHA_256_Util.hashPassword("NoSecrets"));
    	Group adminGroup = new Group();
    	adminGroup.setName("ADMIN");
    	user.addGroup(adminGroup);
    	Group userGroup = new Group();
    	userGroup.setName("USER");
    	user.addGroup(userGroup);
    	
    	EntityTransaction tx = em.getTransaction(); 
    	tx.begin();
    	em.persist(adminGroup);
    	em.persist(userGroup);
        em.persist(user);
        tx.commit(); 
        
        user = new User();
    	user.setFirstName("NOTORIOUS");
    	user.setLastName("S.T.E.W");
    	user.setUsername("nstew");
    	user.setActive(Boolean.TRUE);
    	user.setPassword(SHA_256_Util.hashPassword("LuvYouBaby69"));
    	user.addGroup(userGroup);
    	
    	tx = em.getTransaction(); 
    	tx.begin(); 
        em.persist(user);
        tx.commit(); 
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
		// Ruby or Perl would be better able to do this
		CSVSecurityRegistryDAO registryDao = new CSVSecurityRegistryDAO(masterListFile.getAbsolutePath(),exchange);
		List<StockEntity> stocks = registryDao.getStocksSortedByCode(exchange);
		for(int i=0; i < stocks.size(); i++) {
			StockEntity stock = stocks.get(i);
			EntityTransaction tx = em.getTransaction();
			String gicsGroupName = registryDao.getIndustryGroup(stock.getCode());
			Integer gicsGroupId = industryGroupNames.get(gicsGroupName);
	        tx.begin(); 
	        GicsIndustryGroup group = em.find(GicsIndustryGroup.class, gicsGroupId);
	        if (group == null) {
	        	throw new RuntimeException("Group is null");
	        }
	        stock.setGicsIndustryGroup(group);
	        stock.setActive(true);
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
