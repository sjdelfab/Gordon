package org.sjd.gordon.shared.registry;

import com.gwtplatform.dispatch.shared.Action;

public class GetAllRegistryEntriesAction implements Action<GetAllRegistryEntriesResult> { 

  java.lang.Integer exchangeId;

  public GetAllRegistryEntriesAction(java.lang.Integer exchangeId) {
    this.exchangeId = exchangeId;
  }

  protected GetAllRegistryEntriesAction() {
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
    GetAllRegistryEntriesAction other = (GetAllRegistryEntriesAction) obj;
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
    return "GetAllRegistryEntriesAction["
                 + exchangeId
    + "]";
  }
}
