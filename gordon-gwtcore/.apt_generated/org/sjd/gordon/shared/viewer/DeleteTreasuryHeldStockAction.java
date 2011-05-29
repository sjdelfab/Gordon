package org.sjd.gordon.shared.viewer;

import com.gwtplatform.dispatch.shared.Action;

public class DeleteTreasuryHeldStockAction implements Action<DeleteTreasuryHeldStockResult> { 

  org.sjd.gordon.model.TreasuryHeldStock treasuryHeldStock;

  public DeleteTreasuryHeldStockAction(org.sjd.gordon.model.TreasuryHeldStock treasuryHeldStock) {
    this.treasuryHeldStock = treasuryHeldStock;
  }

  protected DeleteTreasuryHeldStockAction() {
    // Possibly for serialization.
  }

  public org.sjd.gordon.model.TreasuryHeldStock getTreasuryHeldStock() {
    return treasuryHeldStock;
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
    DeleteTreasuryHeldStockAction other = (DeleteTreasuryHeldStockAction) obj;
    if (treasuryHeldStock == null) {
      if (other.treasuryHeldStock != null)
        return false;
    } else if (!treasuryHeldStock.equals(other.treasuryHeldStock))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 23;
    hashCode = (hashCode * 37) + (treasuryHeldStock == null ? 1 : treasuryHeldStock.hashCode());
    return hashCode;
  }

  @Override
  public String toString() {
    return "DeleteTreasuryHeldStockAction["
                 + treasuryHeldStock
    + "]";
  }
}
