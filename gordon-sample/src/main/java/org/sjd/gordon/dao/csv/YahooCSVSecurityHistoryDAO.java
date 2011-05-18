package org.sjd.gordon.dao.csv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import org.sjd.gordon.dao.ErrorType;
import org.sjd.gordon.dao.SecurityHistoryDAO;
import org.sjd.gordon.dao.SecurityHistoryDataException;
import org.sjd.gordon.model.StockDayTradeRecord;
import org.sjd.gordon.model.StockEntity;

public class YahooCSVSecurityHistoryDAO implements SecurityHistoryDAO {

	private String cvsDirectory;

	public YahooCSVSecurityHistoryDAO(String csvDirectory) {
		this.cvsDirectory = csvDirectory;
	}

	@Override
	public List<StockDayTradeRecord> getAllDayTrades(StockEntity stock)	throws SecurityHistoryDataException {
        String cvsFile = cvsDirectory + File.separator + stock.getCode().toUpperCase() + ".csv";
        // now lets create a list of StockDataRecords
        ArrayList<StockDayTradeRecord> data = new ArrayList<StockDayTradeRecord>();
        // open file
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(cvsFile));
            // ignore first line
            in.readLine();
            String line;
            while ((line = in.readLine()) != null) {                
                data.add(0,CsvUtil.parseYahooRow(line,stock.getId()));
            }
            return data;
        }
        catch (FileNotFoundException e) {            
            throw new SecurityHistoryDataException(ErrorType.DATA_ENTITY_NOT_FOUND_ERROR,e.getMessage());
        } catch (ParseException e) {
            throw new SecurityHistoryDataException(ErrorType.DATA_CORRUPTION_ERROR,e.getMessage());
        } catch (IOException e) {
            throw new SecurityHistoryDataException(ErrorType.DATA_CORRUPTION_ERROR,e.getMessage());
        } catch (NumberFormatException e) {
            throw new SecurityHistoryDataException(ErrorType.DATA_CORRUPTION_ERROR,e.getMessage());
        } catch (NoSuchElementException e) {
            throw new SecurityHistoryDataException(ErrorType.DATA_CORRUPTION_ERROR,e.getMessage());
        } catch (Throwable t) {
            throw new SecurityHistoryDataException(ErrorType.UNKNOWN_ERROR,t.getMessage());
        }
        finally {
            if (in != null) {
                try {
                    // close CSV file
                    in.close();
                }
                catch (Throwable t) { }
            }
        }        

	}

	@Override
	public List<StockDayTradeRecord> getDayTrades(StockEntity security, Date startDate, Date endDate) throws SecurityHistoryDataException {
		throw new UnsupportedOperationException();
	}

	@Override
	public StockDayTradeRecord getLastDayTrade(StockEntity security) throws SecurityHistoryDataException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void appendDayTrades(StockEntity security, List<StockDayTradeRecord> newDayTrades) throws SecurityHistoryDataException {  }

	@Override
	public void createStockHistory(StockEntity security, List<StockDayTradeRecord> data) throws SecurityHistoryDataException { }

	@Override
	public void delete(StockEntity security) throws SecurityHistoryDataException { }
}
