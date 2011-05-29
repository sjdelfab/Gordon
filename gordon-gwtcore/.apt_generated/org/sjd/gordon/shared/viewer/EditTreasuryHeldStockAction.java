package org.sjd.gordon.shared.viewer;

import com.gwtplatform.dispatch.shared.Action;

public class EditTreasuryHeldStockAction implements Action<EditTreasuryHeldStockResult> { 

  org.sjd.gordon.model.TreasuryHeldStock newTreasuryHeldStock;
  org.sjd.gordon.shared.util.EditType editType;

  public EditTreasuryHeldStockAction(org.sjd.gordon.model.TreasuryHeldStock newTreasuryHeldStock, org.sjd.gordon.shared.util.EditType editType) {
    this.newTreasuryHeldStock = newTreasuryHeldStock;
    this.editType = editType;
  }

  protected EditTreasuryHeldStockAction() {
    // Possibly for serialization.
  }

  public org.sjd.gordon.model.TreasuryHeldStock getNewTreasuryHeldStock() {
    return newTreasuryHeldStock;
  }

  public org.sjd.gordon.shared.util.EditType getEditType() {
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
    EditTreasuryHeldStockAction other = (EditTreasuryHeldStockAction) obj;
    if (newTreasuryHeldStock == null) {
      if (other.newTreasuryHeldStock != null)
        return false;
    } else if (!newTreasuryHeldStock.equals(other.newTreasuryHeldStock))
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
    hashCode = (hashCode * 37) + (newTreasuryHeldStock == null ? 1 : newTreasuryHeldStock.hashCode());
    hashCode = (hashCode * 37) + (editType == null ? 1 : editType.hashCode());
    return hashCode;
  }

  @Override
  public String toString() {
    return "EditTreasuryHeldStockAction["
                 + newTreasuryHeldStock
                 + ","
                 + editType
    + "]";
  }
}
