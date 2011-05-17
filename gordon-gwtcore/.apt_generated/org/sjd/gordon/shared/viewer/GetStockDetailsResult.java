package org.sjd.gordon.shared.viewer;

import com.gwtplatform.dispatch.shared.Result;

public class GetStockDetailsResult implements Result { 

  org.sjd.gordon.shared.viewer.StockDetail stockDetails;

  public GetStockDetailsResult(org.sjd.gordon.shared.viewer.StockDetail stockDetails) {
    this.stockDetails = stockDetails;
  }

  protected GetStockDetailsResult() {
    // Possibly for serialization.
  }

  public org.sjd.gordon.shared.viewer.StockDetail getStockDetails() {
    return stockDetails;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    GetStockDetailsResult other = (GetStockDetailsResult) obj;
    if (stockDetails == null) {
      if (other.stockDetails != null)
        return false;
    } else if (!stockDetails.equals(other.stockDetails))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 23;
    hashCode = (hashCode * 37) + (stockDetails == null ? 1 : stockDetails.hashCode());
    return hashCode;
  }

  @Override
  public String toString() {
    return "GetStockDetailsResult["
                 + stockDetails
    + "]";
  }
}
