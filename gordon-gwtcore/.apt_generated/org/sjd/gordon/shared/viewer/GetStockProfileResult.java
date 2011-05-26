package org.sjd.gordon.shared.viewer;

import com.gwtplatform.dispatch.shared.Result;

public class GetStockProfileResult implements Result { 

  org.sjd.gordon.shared.viewer.StockProfile stockProfile;

  public GetStockProfileResult(org.sjd.gordon.shared.viewer.StockProfile stockProfile) {
    this.stockProfile = stockProfile;
  }

  protected GetStockProfileResult() {
    // Possibly for serialization.
  }

  public org.sjd.gordon.shared.viewer.StockProfile getStockProfile() {
    return stockProfile;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    GetStockProfileResult other = (GetStockProfileResult) obj;
    if (stockProfile == null) {
      if (other.stockProfile != null)
        return false;
    } else if (!stockProfile.equals(other.stockProfile))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 23;
    hashCode = (hashCode * 37) + (stockProfile == null ? 1 : stockProfile.hashCode());
    return hashCode;
  }

  @Override
  public String toString() {
    return "GetStockProfileResult["
                 + stockProfile
    + "]";
  }
}
