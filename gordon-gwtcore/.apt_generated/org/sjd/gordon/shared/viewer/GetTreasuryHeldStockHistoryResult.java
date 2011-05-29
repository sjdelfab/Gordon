package org.sjd.gordon.shared.viewer;

import com.gwtplatform.dispatch.shared.Result;

public class GetTreasuryHeldStockHistoryResult implements Result { 

  java.util.ArrayList<org.sjd.gordon.model.TreasuryHeldStock> treasuryHeldStockHistory;

  public GetTreasuryHeldStockHistoryResult(java.util.ArrayList<org.sjd.gordon.model.TreasuryHeldStock> treasuryHeldStockHistory) {
    this.treasuryHeldStockHistory = treasuryHeldStockHistory;
  }

  protected GetTreasuryHeldStockHistoryResult() {
    // Possibly for serialization.
  }

  public java.util.ArrayList<org.sjd.gordon.model.TreasuryHeldStock> getTreasuryHeldStockHistory() {
    return treasuryHeldStockHistory;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    GetTreasuryHeldStockHistoryResult other = (GetTreasuryHeldStockHistoryResult) obj;
    if (treasuryHeldStockHistory == null) {
      if (other.treasuryHeldStockHistory != null)
        return false;
    } else if (!treasuryHeldStockHistory.equals(other.treasuryHeldStockHistory))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 23;
    hashCode = (hashCode * 37) + (treasuryHeldStockHistory == null ? 1 : treasuryHeldStockHistory.hashCode());
    return hashCode;
  }

  @Override
  public String toString() {
    return "GetTreasuryHeldStockHistoryResult["
                 + treasuryHeldStockHistory
    + "]";
  }
}
