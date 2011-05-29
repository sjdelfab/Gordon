package org.sjd.gordon.shared.viewer;

import com.gwtplatform.dispatch.shared.Result;

public class GetStockSplitsResult implements Result { 

  java.util.ArrayList<org.sjd.gordon.model.StockSplit> stockSplits;

  public GetStockSplitsResult(java.util.ArrayList<org.sjd.gordon.model.StockSplit> stockSplits) {
    this.stockSplits = stockSplits;
  }

  protected GetStockSplitsResult() {
    // Possibly for serialization.
  }

  public java.util.ArrayList<org.sjd.gordon.model.StockSplit> getStockSplits() {
    return stockSplits;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    GetStockSplitsResult other = (GetStockSplitsResult) obj;
    if (stockSplits == null) {
      if (other.stockSplits != null)
        return false;
    } else if (!stockSplits.equals(other.stockSplits))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 23;
    hashCode = (hashCode * 37) + (stockSplits == null ? 1 : stockSplits.hashCode());
    return hashCode;
  }

  @Override
  public String toString() {
    return "GetStockSplitsResult["
                 + stockSplits
    + "]";
  }
}
