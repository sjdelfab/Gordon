package org.sjd.gordon.shared.viewer;

import com.gwtplatform.dispatch.shared.Result;

public class GetDividendHistoryResult implements Result { 

  java.util.ArrayList<org.sjd.gordon.model.Dividend> dividends;

  public GetDividendHistoryResult(java.util.ArrayList<org.sjd.gordon.model.Dividend> dividends) {
    this.dividends = dividends;
  }

  protected GetDividendHistoryResult() {
    // Possibly for serialization.
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
    GetDividendHistoryResult other = (GetDividendHistoryResult) obj;
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
    hashCode = (hashCode * 37) + (dividends == null ? 1 : dividends.hashCode());
    return hashCode;
  }

  @Override
  public String toString() {
    return "GetDividendHistoryResult["
                 + dividends
    + "]";
  }
}
