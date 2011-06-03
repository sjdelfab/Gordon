package org.sjd.gordon.importing.tradehistory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.persistence.EntityManager;

import org.sjd.gordon.importing.ImportException;
import org.sjd.gordon.model.StockDayTradeRecord;

public abstract class AbstractCSVTradeHistoryImporter implements CSVTradeHistoryImportService {

	@Override
	public void importTradeHistory(String exchangeName, String symbol, Reader csvFile) throws ImportException {
		try {
			Long stockId = getStockId(exchangeName,symbol);
			List<StockDayTradeRecord> tradeHistory = parse(stockId, csvFile);
			for(StockDayTradeRecord record: tradeHistory) {
				getEntityManager().persist(record);
			}
		} catch (IOException cause) {
			throw new ImportException(cause);
		} catch (ParseException cause) {
			throw new ImportException(cause);
		}
	}
	
	abstract protected EntityManager getEntityManager();
	abstract protected Long getStockId(String exchangeName, String symbol) throws ImportException;
	
	private List<StockDayTradeRecord> parse(Long stockId, Reader csvFile)	throws IOException, ParseException {
        ArrayList<StockDayTradeRecord> data = new ArrayList<StockDayTradeRecord>();
        // open file
        BufferedReader in = null;
        try {
            in = new BufferedReader(csvFile);
            // ignore first line
            in.readLine();
            String line;
            while ((line = in.readLine()) != null) {                
                data.add(0,parse(line,stockId));
            }
            return data;
        } finally {
            if (in != null) {
                try {
                    in.close();
                }
                catch (Throwable t) { }
            }
        }
	}
	
	private static final BigDecimal ONE_HUNDRED = new BigDecimal(100);
	private static SimpleDateFormat yahooDateFormat = new SimpleDateFormat("yyyy-MM-dd z");
	
	private StockDayTradeRecord parse(String line, Long stockId) throws ParseException {
		StringTokenizer st = new StringTokenizer(line, ",");
        // grab the trade date field in YYYY-MM-dd form
        String tmp = (st.nextToken()).trim();
        String date = tmp + " GMT";
        // grab the open price
        tmp = (st.nextToken()).trim();
        
        BigDecimal open = new BigDecimal(tmp).multiply(ONE_HUNDRED);
        // grab the high price
        tmp = (st.nextToken()).trim();
        BigDecimal high = new BigDecimal(tmp).multiply(ONE_HUNDRED);
        // grab the low price
        tmp = (st.nextToken()).trim();
        BigDecimal low = new BigDecimal(tmp).multiply(ONE_HUNDRED);
        // grab the close price
        tmp = (st.nextToken()).trim();
        BigDecimal close = new BigDecimal(tmp).multiply(ONE_HUNDRED);
        // grab the volume
        tmp = (st.nextToken()).trim();
        double volf = Double.parseDouble(tmp);
        long vol = (long) volf;
        StockDayTradeRecord sdt = new StockDayTradeRecord();
        sdt.setClosePrice(close);
        sdt.setDate(yahooDateFormat.parse(date));
        sdt.setOpenPrice(open);
        sdt.setHighPrice(high);
        sdt.setLowPrice(low);
        sdt.setVolume(vol);
        sdt.setStockId(stockId);
        return sdt;
	}
}
