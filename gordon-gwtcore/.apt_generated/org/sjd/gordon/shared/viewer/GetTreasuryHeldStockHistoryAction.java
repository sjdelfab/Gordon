package org.sjd.gordon.shared.viewer;

import com.gwtplatform.dispatch.shared.Action;

public class GetTreasuryHeldStockHistoryAction implements Action<GetTreasuryHeldStockHistoryResult> { 

  java.lang.Long stockId;

  public GetTreasuryHeldStockHistoryAction(java.lang.Long stockId) {
    this.stockId = stockId;
  }

  protected GetTreasuryHeldStockHistoryAction() {
    // Possibly for serialization.
  }

  public java.lang.Long getStockId() {
    return stockId;
  }

  @Override
  public String getServiceName() {
    return "dispatch/";
  }

  @Override
  public boolean isSecured() {
    return false;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    GetTreasuryHeldStockHistoryAction other = (GetTreasuryHeldStockHistoryAction) obj;
    if (stockId == null) {
      if (other.stockId != null)
        return false;
    } else if (!stockId.equals(other.stockId))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 23;
    hashCode = (hashCode * 37) + (stockId == null ? 1 : stockId.hashCode());
    return hashCode;
  }

  @Override
  public String toString() {
    return "GetTreasuryHeldStockHistoryAction["
                 + stockId
    + "]";
  }
}
