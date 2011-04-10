/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.sjd.gordon.dao.csv;

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
        double open = Double.parseDouble(tmp);
        // grab the high price
        tmp = (st.nextToken()).trim();
        double high = Double.parseDouble(tmp);
        // grab the low price
        tmp = (st.nextToken()).trim();
        double low = Double.parseDouble(tmp);
        // grab the close price
        tmp = (st.nextToken()).trim();
        double close = Double.parseDouble(tmp);
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
        return sdt;
    }
    
    public static StockDayTradeRecord parseYahooRow(String line, int securityId) throws ParseException {
        StringTokenizer st = new StringTokenizer(line, ",");
        // grab the trade date field in YYYY-MM-dd form
        String tmp = (st.nextToken()).trim();
        String date = tmp + " GMT";
        // grab the open price
        tmp = (st.nextToken()).trim();
        double open = Double.parseDouble(tmp)*100.0d;
        // grab the high price
        tmp = (st.nextToken()).trim();
        double high = Double.parseDouble(tmp)*100.0d;
        // grab the low price
        tmp = (st.nextToken()).trim();
        double low = Double.parseDouble(tmp)*100.0d;
        // grab the close price
        tmp = (st.nextToken()).trim();
        double close = Double.parseDouble(tmp)*100.0d;
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
