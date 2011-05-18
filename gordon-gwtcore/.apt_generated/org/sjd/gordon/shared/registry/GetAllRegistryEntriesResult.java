package org.sjd.gordon.shared.registry;

import com.gwtplatform.dispatch.shared.Result;

public class GetAllRegistryEntriesResult implements Result { 

  java.util.ArrayList<org.sjd.gordon.shared.viewer.StockDetail> stocks;
  java.lang.Integer totalCount;

  public GetAllRegistryEntriesResult(java.util.ArrayList<org.sjd.gordon.shared.viewer.StockDetail> stocks, java.lang.Integer totalCount) {
    this.stocks = stocks;
    this.totalCount = totalCount;
  }

  protected GetAllRegistryEntriesResult() {
    // Possibly for serialization.
  }

  public java.util.ArrayList<org.sjd.gordon.shared.viewer.StockDetail> getStocks() {
    return stocks;
  }

  public java.lang.Integer getTotalCount() {
    return totalCount;
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
    if (totalCount == null) {
      if (other.totalCount != null)
        return false;
    } else if (!totalCount.equals(other.totalCount))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 23;
    hashCode = (hashCode * 37) + (stocks == null ? 1 : stocks.hashCode());
    hashCode = (hashCode * 37) + (totalCount == null ? 1 : totalCount.hashCode());
    return hashCode;
  }

  @Override
  public String toString() {
    return "GetAllRegistryEntriesResult["
                 + stocks
                 + ","
                 + totalCount
    + "]";
  }
}
