package org.sjd.gordon.shared.navigation;

import com.gwtplatform.dispatch.shared.Result;

public class GetStocksResult implements Result { 

  java.util.ArrayList<org.sjd.gordon.shared.navigation.StockName> stocks;

  public GetStocksResult(java.util.ArrayList<org.sjd.gordon.shared.navigation.StockName> stocks) {
    this.stocks = stocks;
  }

  protected GetStocksResult() {
    // Possibly for serialization.
  }

  public java.util.ArrayList<org.sjd.gordon.shared.navigation.StockName> getStocks() {
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
    GetStocksResult other = (GetStocksResult) obj;
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
    return "GetStocksResult["
                 + stocks
    + "]";
  }
}
