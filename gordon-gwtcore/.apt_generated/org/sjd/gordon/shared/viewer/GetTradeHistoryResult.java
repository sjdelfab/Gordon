package org.sjd.gordon.shared.viewer;

import com.gwtplatform.dispatch.shared.Result;

public class GetTradeHistoryResult implements Result { 

  java.util.ArrayList<org.sjd.gordon.model.StockDayTradeRecord> history;

  public GetTradeHistoryResult(java.util.ArrayList<org.sjd.gordon.model.StockDayTradeRecord> history) {
    this.history = history;
  }

  protected GetTradeHistoryResult() {
    // Possibly for serialization.
  }

  public java.util.ArrayList<org.sjd.gordon.model.StockDayTradeRecord> getHistory() {
    return history;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    GetTradeHistoryResult other = (GetTradeHistoryResult) obj;
    if (history == null) {
      if (other.history != null)
        return false;
    } else if (!history.equals(other.history))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 23;
    hashCode = (hashCode * 37) + (history == null ? 1 : history.hashCode());
    return hashCode;
  }

  @Override
  public String toString() {
    return "GetTradeHistoryResult["
                 + history
    + "]";
  }
}
