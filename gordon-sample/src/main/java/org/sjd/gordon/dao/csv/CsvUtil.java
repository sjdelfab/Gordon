/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.sjd.gordon.dao.csv;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.StringTokenizer;

import org.sjd.gordon.model.StockDayTradeRecord;

/**
 *
 * @author sjdelfab
 */
public class CsvUtil {

    private static SimpleDateFormat spreadDateFormat = new SimpleDateFormat("dd/MM/yyyy z");
    private static SimpleDateFormat yahooDateFormat = new SimpleDateFormat("yyyy-MM-dd z");
    
    public static StockDayTradeRecord parseSpreadRow(String line, long securityId) throws ParseException {
        StringTokenizer st = new StringTokenizer(line, ",");
        // grab the trade date field in YYYYMMDD form
        String tmp = (st.nextToken()).trim();
        // convert it to dd/mm/yyyy form
        String date = tmp.substring(6) + "/" + tmp.substring(4, 6) + "/" + tmp.substring(0, 4) + " GMT";
        // grab the open price
        tmp = (st.nextToken()).trim();
        BigDecimal open = new BigDecimal(tmp);
        // grab the high price
        tmp = (st.nextToken()).trim();
        BigDecimal high = new BigDecimal(tmp);
        // grab the low price
        tmp = (st.nextToken()).trim();
        BigDecimal low = new BigDecimal(tmp);
        // grab the close price
        tmp = (st.nextToken()).trim();
        BigDecimal close = new BigDecimal(tmp);
        // grab the volume
        tmp = (st.nextToken()).trim();
        double volf = Double.parseDouble(tmp);
        long vol = (long) volf;
        StockDayTradeRecord sdt = new StockDayTradeRecord();
        sdt.setClosePrice(close);
        sdt.setDate(spreadDateFormat.parse(date));
        sdt.setOpenPrice(open);
        sdt.setHighPrice(high);
        sdt.setLowPrice(low);
        sdt.setVolume(vol);
        sdt.setStockId(securityId);
        return sdt;
    }
    
    private static final BigDecimal ONE_HUNDRED = new BigDecimal(100);
    
    public static StockDayTradeRecord parseYahooRow(String line, int securityId) throws ParseException {
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
        return sdt;
    }    
    
}
