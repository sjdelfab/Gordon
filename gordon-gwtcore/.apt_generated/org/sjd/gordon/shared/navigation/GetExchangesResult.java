package org.sjd.gordon.shared.navigation;

import com.gwtplatform.dispatch.shared.Result;

public class GetExchangesResult implements Result { 

  java.util.ArrayList<org.sjd.gordon.model.Exchange> exchanges;

  public GetExchangesResult(java.util.ArrayList<org.sjd.gordon.model.Exchange> exchanges) {
    this.exchanges = exchanges;
  }

  protected GetExchangesResult() {
    // Possibly for serialization.
  }

  public java.util.ArrayList<org.sjd.gordon.model.Exchange> getExchanges() {
    return exchanges;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    GetExchangesResult other = (GetExchangesResult) obj;
    if (exchanges == null) {
      if (other.exchanges != null)
        return false;
    } else if (!exchanges.equals(other.exchanges))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 23;
    hashCode = (hashCode * 37) + (exchanges == null ? 1 : exchanges.hashCode());
    return hashCode;
  }

  @Override
  public String toString() {
    return "GetExchangesResult["
                 + exchanges
    + "]";
  }
}
