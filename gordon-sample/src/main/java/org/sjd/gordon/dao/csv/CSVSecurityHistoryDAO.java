package org.sjd.gordon.dao.csv;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

import org.sjd.gordon.dao.ErrorType;
import org.sjd.gordon.dao.SecurityHistoryDAO;
import org.sjd.gordon.dao.SecurityHistoryDataException;
import org.sjd.gordon.model.StockDayTradeRecord;
import org.sjd.gordon.model.StockEntity;

public class CSVSecurityHistoryDAO implements SecurityHistoryDAO {
    
    private String cvsDirectory;
        
    public CSVSecurityHistoryDAO(String csvDirectory) {
        this.cvsDirectory = csvDirectory;
    }

    public List<StockDayTradeRecord> getAllDayTrades(StockEntity stock) throws SecurityHistoryDataException {
        String cvsFile = cvsDirectory + File.separator + stock.getCode().toUpperCase() + ".csv";
        // now lets create a list of StockDataRecords
        ArrayList<StockDayTradeRecord> data = new ArrayList<StockDayTradeRecord>();
        // open file
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(cvsFile));
            String line;
            while ((line = in.readLine()) != null) {                
                data.add(CsvUtil.parseSpreadRow(line,stock.getId()));
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

    public void appendDayTrades(StockEntity security, List<StockDayTradeRecord> newDayTrades) throws SecurityHistoryDataException {
        String csvFile = cvsDirectory + File.separator + security.getCode().toUpperCase() + ".csv";
        BufferedWriter writer = null;        
        FileOutputStream out = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(csvFile,true)));
            for(StockDayTradeRecord trade: newDayTrades) {
                String str = dateFormat.format(trade.getDate()) + "," + trade.getOpenPrice() + "," + trade.getHighPrice() + "," 
                        + trade.getLowPrice() + "," + trade.getClosePrice() + "," + trade.getVolume() + "\n";
                writer.write(str);
            }
        } catch (FileNotFoundException ex) {
            throw new SecurityHistoryDataException(ErrorType.DATA_ENTITY_NOT_FOUND_ERROR,ex.getMessage());
        } catch(IOException ioex) {
            throw new SecurityHistoryDataException(ErrorType.DATA_CORRUPTION_ERROR,ioex.getMessage());
        } catch (Throwable t) {
            throw new SecurityHistoryDataException(ErrorType.UNKNOWN_ERROR,t.getMessage());           
        } finally {
            if (writer != null) {
                try {
                    // close CSV file
                    writer.close();
                }
                catch (Throwable t) { }
            }
            if (out != null) {
                try {
                    // close CSV file
                    out.close();
                }
                catch (Throwable t) { }
            }
        } 
        
    }
    
    public void createStockHistory(StockEntity security, List<StockDayTradeRecord> data) throws SecurityHistoryDataException {
        appendDayTrades(security,data);
    }

    public List<StockDayTradeRecord> getDayTrades(StockEntity security, Date startDate, Date endDate) throws SecurityHistoryDataException {
        String csvFile = cvsDirectory + File.separator + security.getCode().toUpperCase() + ".csv";
        // now lets create a list of StockDataRecords
        ArrayList<StockDayTradeRecord> data = new ArrayList<StockDayTradeRecord>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy z");
        // open file
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(csvFile));
            String line;
            String date = "";
            while ((line = in.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(line,",");
                // grab the trade date field in YYYYMMDD form
                String tmp = (st.nextToken()).trim();
                // convert it to dd/mm/yyyy form
                date = tmp.substring(6) + "/" + tmp.substring(4,6) + "/" + tmp.substring(0,4) + " GMT";
                Date tradeDate = dateFormat.parse(date);
                if (tradeDate.before(startDate)) {
                    continue;
                }
                if (tradeDate.after(endDate)) {
                    break;
                }
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
                long vol = (long)volf;
                StockDayTradeRecord sdr = new StockDayTradeRecord();
                sdr.setStockId(security.getId());
                sdr.setDate(tradeDate);
                sdr.setOpenPrice(open);
                sdr.setHighPrice(high);
                sdr.setLowPrice(low);
                sdr.setClosePrice(close);
                sdr.setVolume(vol);
                data.add(sdr);
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

    public void delete(StockEntity security) throws SecurityHistoryDataException {
        String csvFile = cvsDirectory + File.separator + security.getCode().toUpperCase() + ".csv";
        try {
            File file = new File(csvFile);
            file.delete();
        } catch (Throwable t) {
            throw new SecurityHistoryDataException(ErrorType.DATA_ENTITY_NOT_FOUND_ERROR,t);
        }
    }
    
    public StockDayTradeRecord getLastDayTrade(StockEntity security) throws SecurityHistoryDataException {
        String csvFile = cvsDirectory + File.separator + security.getCode().toUpperCase() + ".csv";
        // open file
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(csvFile));
            String line;
            String prevLine = null;
            while ((line = in.readLine()) != null) {                
                prevLine = line;
            }
            if (prevLine != null) {
                return CsvUtil.parseSpreadRow(prevLine,security.getId());
            } else {
                return null;
            }
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
}
