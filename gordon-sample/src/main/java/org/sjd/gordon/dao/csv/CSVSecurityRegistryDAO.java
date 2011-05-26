package org.sjd.gordon.dao.csv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.sjd.gordon.dao.SecurityRegistryDAO;
import org.sjd.gordon.dao.SecurityRegistryDAOException;
import org.sjd.gordon.model.Exchange;
import org.sjd.gordon.model.StockEntity;

public class CSVSecurityRegistryDAO implements SecurityRegistryDAO {
    
	// Code > Name
    private HashMap<String,String> stocks = null;
    // Stock Code > Industry group 
    private HashMap<String,String> gicsIndustryGrps = new HashMap<String,String>();
    
    private List<StockEntity> sortedByName, sortedByCode;
    private String masterListFile;
    private Exchange exchange;
    
    public CSVSecurityRegistryDAO(String masterListFile, Exchange exchange) {
        this.masterListFile = masterListFile;
        this.exchange = exchange;
    }

    public int insertRegistryEntry(StockEntity entry) throws SecurityRegistryDAOException {
        throw new UnsupportedOperationException("Not implemented.");
    }

    public List<Exchange> getExchanges() throws SecurityRegistryDAOException {
        throw new UnsupportedOperationException("Not implemented.");
    }
    
    public List<StockEntity> getStocksSortedByCode(Exchange exchange) throws SecurityRegistryDAOException {
        try {
            // make sure loaded...
            getStocks();
            return sortedByCode;
        } catch (IOException ex) {
            throw new SecurityRegistryDAOException(ex);
        }        
    }

    public List<StockEntity> getStocksSortedByName(Exchange exchange) throws SecurityRegistryDAOException {
        try {
            // make sure loaded...
            getStocks();
            return sortedByName;
        } catch (IOException ex) {
            throw new SecurityRegistryDAOException(ex);
        }        
    }

    public boolean exists(String code, Exchange exchange) throws SecurityRegistryDAOException {
        return stocks.get(code) != null;
    }
 
    public String getIndustryGroup(String code) {
    	return gicsIndustryGrps.get(code);
    }

    protected Map<String,String> getStocks() throws IOException {
        if (stocks == null) {
            stocks = new HashMap<String,String>();
            sortedByName = new ArrayList<StockEntity>();
            sortedByCode = new ArrayList<StockEntity>();
            BufferedReader in = new BufferedReader(new FileReader(masterListFile));
            String line;
            int id = 1;
            while ((line=in.readLine()) != null) {
            	String[] tokens = line.split("\"");
            	//System.out.println(Arrays.toString(tokens));
            	// 0 token is empty
            	// 1 is stock name
                String stkName = tokens[1].trim();
                // 3 is code with a , either side
				String stkCode = tokens[2].trim().substring(1, 4);
				//System.out.println(stkCode);
				// 4 is sector group
				String group = tokens[3].trim();
				//System.out.println(group);
				gicsIndustryGrps.put(stkCode, group);
				StockEntity stock = new StockEntity();
				stock.setExchange(exchange);
				stock.setName(stkName);
				stock.setCode(stkCode);
				stocks.put(stkCode, stkName);
				sortedByName.add(stock);
				sortedByCode.add(stock);
				id++;
            }
            Collections.sort(sortedByName,new StockNameComparator());
            Collections.sort(sortedByCode,new StockCodeComparator());
            in.close();
        }
        return stocks;
    }

    public void updateLastTradeDate(StockEntity stock, Date date) throws SecurityRegistryDAOException {
        throw new UnsupportedOperationException("Not implemented.");
    }

    public Date getLastTradeDate(StockEntity stock) throws SecurityRegistryDAOException {
        throw new UnsupportedOperationException("Not implemented.");
    }

    public Date getListDate(StockEntity stock) throws SecurityRegistryDAOException {
        throw new UnsupportedOperationException("Not implemented.");
    }
    
    private class StockNameComparator implements Comparator<StockEntity> {        
        public int compare(StockEntity o1, StockEntity o2) {
            return o1.getName().compareTo(o2.getName());
        }                
    }
    
    private class StockCodeComparator implements Comparator<StockEntity> {
        public int compare(StockEntity o1, StockEntity o2) {
            return o1.getCode().compareTo(o2.getCode());
        }        
    }

    public StockEntity getSecurity(String code, String exchangeCode) throws SecurityRegistryDAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public Exchange getExchange(String code) throws SecurityRegistryDAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public StockEntity insertRegistryEntry(String code, String name, Exchange exchangeCode, 
                                        boolean active, Date listDate, Date lastTradeDate) throws SecurityRegistryDAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void delete(StockEntity entry) throws SecurityRegistryDAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<StockEntity> getRegistrations(Exchange exchange) throws SecurityRegistryDAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void update(StockEntity entry) throws SecurityRegistryDAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
