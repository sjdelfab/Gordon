package org.sjd.gordon.shared.registry;

import com.gwtplatform.dispatch.shared.Action;

public class EditRegistryEntryAction implements Action<EditRegistryEntryResult> { 

  org.sjd.gordon.shared.viewer.StockDetail stockDetails;
  java.lang.Integer exchangeId;
  org.sjd.gordon.shared.registry.EditRegistryEntry.EditType editType;

  public EditRegistryEntryAction(org.sjd.gordon.shared.viewer.StockDetail stockDetails, java.lang.Integer exchangeId, org.sjd.gordon.shared.registry.EditRegistryEntry.EditType editType) {
    this.stockDetails = stockDetails;
    this.exchangeId = exchangeId;
    this.editType = editType;
  }

  protected EditRegistryEntryAction() {
    // Possibly for serialization.
  }

  public org.sjd.gordon.shared.viewer.StockDetail getStockDetails() {
    return stockDetails;
  }

  public java.lang.Integer getExchangeId() {
    return exchangeId;
  }

  public org.sjd.gordon.shared.registry.EditRegistryEntry.EditType getEditType() {
    return editType;
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
    EditRegistryEntryAction other = (EditRegistryEntryAction) obj;
    if (stockDetails == null) {
      if (other.stockDetails != null)
        return false;
    } else if (!stockDetails.equals(other.stockDetails))
      return false;
    if (exchangeId == null) {
      if (other.exchangeId != null)
        return false;
    } else if (!exchangeId.equals(other.exchangeId))
      return false;
    if (editType == null) {
      if (other.editType != null)
        return false;
    } else if (!editType.equals(other.editType))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 23;
    hashCode = (hashCode * 37) + (stockDetails == null ? 1 : stockDetails.hashCode());
    hashCode = (hashCode * 37) + (exchangeId == null ? 1 : exchangeId.hashCode());
    hashCode = (hashCode * 37) + (editType == null ? 1 : editType.hashCode());
    return hashCode;
  }

  @Override
  public String toString() {
    return "EditRegistryEntryAction["
                 + stockDetails
                 + ","
                 + exchangeId
                 + ","
                 + editType
    + "]";
  }
}
