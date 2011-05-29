package org.sjd.gordon.shared.viewer;

import com.gwtplatform.dispatch.shared.Result;

public class EditTreasuryHeldStockResult implements Result { 

  org.sjd.gordon.model.TreasuryHeldStock updatedTreasuryHeldStock;

  public EditTreasuryHeldStockResult(org.sjd.gordon.model.TreasuryHeldStock updatedTreasuryHeldStock) {
    this.updatedTreasuryHeldStock = updatedTreasuryHeldStock;
  }

  protected EditTreasuryHeldStockResult() {
    // Possibly for serialization.
  }

  public org.sjd.gordon.model.TreasuryHeldStock getUpdatedTreasuryHeldStock() {
    return updatedTreasuryHeldStock;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    EditTreasuryHeldStockResult other = (EditTreasuryHeldStockResult) obj;
    if (updatedTreasuryHeldStock == null) {
      if (other.updatedTreasuryHeldStock != null)
        return false;
    } else if (!updatedTreasuryHeldStock.equals(other.updatedTreasuryHeldStock))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 23;
    hashCode = (hashCode * 37) + (updatedTreasuryHeldStock == null ? 1 : updatedTreasuryHeldStock.hashCode());
    return hashCode;
  }

  @Override
  public String toString() {
    return "EditTreasuryHeldStockResult["
                 + updatedTreasuryHeldStock
    + "]";
  }
}
