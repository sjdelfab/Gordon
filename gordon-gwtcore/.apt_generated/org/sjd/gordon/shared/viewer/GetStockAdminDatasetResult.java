package org.sjd.gordon.shared.viewer;

import com.gwtplatform.dispatch.shared.Result;

public class GetStockAdminDatasetResult implements Result { 

  java.util.ArrayList<org.sjd.gordon.model.StockSplit> stockSplits;
  java.util.ArrayList<org.sjd.gordon.model.TreasuryHeldStock> treasuryHeldStock;
  java.util.ArrayList<org.sjd.gordon.model.Dividend> dividends;

  public GetStockAdminDatasetResult(java.util.ArrayList<org.sjd.gordon.model.StockSplit> stockSplits, java.util.ArrayList<org.sjd.gordon.model.TreasuryHeldStock> treasuryHeldStock, java.util.ArrayList<org.sjd.gordon.model.Dividend> dividends) {
    this.stockSplits = stockSplits;
    this.treasuryHeldStock = treasuryHeldStock;
    this.dividends = dividends;
  }

  protected GetStockAdminDatasetResult() {
    // Possibly for serialization.
  }

  public java.util.ArrayList<org.sjd.gordon.model.StockSplit> getStockSplits() {
    return stockSplits;
  }

  public java.util.ArrayList<org.sjd.gordon.model.TreasuryHeldStock> getTreasuryHeldStock() {
    return treasuryHeldStock;
  }

  public java.util.ArrayList<org.sjd.gordon.model.Dividend> getDividends() {
    return dividends;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    GetStockAdminDatasetResult other = (GetStockAdminDatasetResult) obj;
    if (stockSplits == null) {
      if (other.stockSplits != null)
        return false;
    } else if (!stockSplits.equals(other.stockSplits))
      return false;
    if (treasuryHeldStock == null) {
      if (other.treasuryHeldStock != null)
        return false;
    } else if (!treasuryHeldStock.equals(other.treasuryHeldStock))
      return false;
    if (dividends == null) {
      if (other.dividends != null)
        return false;
    } else if (!dividends.equals(other.dividends))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 23;
    hashCode = (hashCode * 37) + (stockSplits == null ? 1 : stockSplits.hashCode());
    hashCode = (hashCode * 37) + (treasuryHeldStock == null ? 1 : treasuryHeldStock.hashCode());
    hashCode = (hashCode * 37) + (dividends == null ? 1 : dividends.hashCode());
    return hashCode;
  }

  @Override
  public String toString() {
    return "GetStockAdminDatasetResult["
                 + stockSplits
                 + ","
                 + treasuryHeldStock
                 + ","
                 + dividends
    + "]";
  }
}
