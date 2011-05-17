package org.sjd.gordon.shared.registry;

import com.gwtplatform.dispatch.shared.Result;

public class GetAllRegistryEntriesResult implements Result { 

  java.util.ArrayList<org.sjd.gordon.shared.viewer.StockDetail> stocks;

  public GetAllRegistryEntriesResult(java.util.ArrayList<org.sjd.gordon.shared.viewer.StockDetail> stocks) {
    this.stocks = stocks;
  }

  protected GetAllRegistryEntriesResult() {
    // Possibly for serialization.
  }

  public java.util.ArrayList<org.sjd.gordon.shared.viewer.StockDetail> getStocks() {
    return stocks;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    GetAllRegistryEntriesResult other = (GetAllRegistryEntriesResult) obj;
    if (stocks == null) {
      if (other.stocks != null)
        return false;
    } else if (!stocks.equals(other.stocks))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 23;
    hashCode = (hashCode * 37) + (stocks == null ? 1 : stocks.hashCode());
    return hashCode;
  }

  @Override
  public String toString() {
    return "GetAllRegistryEntriesResult["
                 + stocks
    + "]";
  }
}
