package org.sjd.gordon.shared.navigation;

import com.gwtplatform.dispatch.shared.Action;

public class GetStocksAction implements Action<GetStocksResult> { 

  java.lang.Integer exchangeId;

  public GetStocksAction(java.lang.Integer exchangeId) {
    this.exchangeId = exchangeId;
  }

  protected GetStocksAction() {
    // Possibly for serialization.
  }

  public java.lang.Integer getExchangeId() {
    return exchangeId;
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
    GetStocksAction other = (GetStocksAction) obj;
    if (exchangeId == null) {
      if (other.exchangeId != null)
        return false;
    } else if (!exchangeId.equals(other.exchangeId))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 23;
    hashCode = (hashCode * 37) + (exchangeId == null ? 1 : exchangeId.hashCode());
    return hashCode;
  }

  @Override
  public String toString() {
    return "GetStocksAction["
                 + exchangeId
    + "]";
  }
}
