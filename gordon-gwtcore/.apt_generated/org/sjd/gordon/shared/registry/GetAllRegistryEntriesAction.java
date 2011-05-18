package org.sjd.gordon.shared.registry;

import com.gwtplatform.dispatch.shared.Action;

public class GetAllRegistryEntriesAction implements Action<GetAllRegistryEntriesResult> { 

  java.lang.Integer exchangeId;
  java.lang.Integer limit;
  java.lang.Integer offset;

  public GetAllRegistryEntriesAction(java.lang.Integer exchangeId, java.lang.Integer limit, java.lang.Integer offset) {
    this.exchangeId = exchangeId;
    this.limit = limit;
    this.offset = offset;
  }

  protected GetAllRegistryEntriesAction() {
    // Possibly for serialization.
  }

  public java.lang.Integer getExchangeId() {
    return exchangeId;
  }

  public java.lang.Integer getLimit() {
    return limit;
  }

  public java.lang.Integer getOffset() {
    return offset;
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
    if (limit == null) {
      if (other.limit != null)
        return false;
    } else if (!limit.equals(other.limit))
      return false;
    if (offset == null) {
      if (other.offset != null)
        return false;
    } else if (!offset.equals(other.offset))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 23;
    hashCode = (hashCode * 37) + (exchangeId == null ? 1 : exchangeId.hashCode());
    hashCode = (hashCode * 37) + (limit == null ? 1 : limit.hashCode());
    hashCode = (hashCode * 37) + (offset == null ? 1 : offset.hashCode());
    return hashCode;
  }

  @Override
  public String toString() {
    return "GetAllRegistryEntriesAction["
                 + exchangeId
                 + ","
                 + limit
                 + ","
                 + offset
    + "]";
  }
}
