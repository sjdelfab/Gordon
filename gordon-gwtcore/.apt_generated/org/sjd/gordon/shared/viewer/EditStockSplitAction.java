package org.sjd.gordon.shared.viewer;

import com.gwtplatform.dispatch.shared.Action;

public class EditStockSplitAction implements Action<EditStockSplitResult> { 

  org.sjd.gordon.model.StockSplit newStockSplit;
  java.lang.Long stockId;
  org.sjd.gordon.shared.util.EditType editType;

  public EditStockSplitAction(org.sjd.gordon.model.StockSplit newStockSplit, java.lang.Long stockId, org.sjd.gordon.shared.util.EditType editType) {
    this.newStockSplit = newStockSplit;
    this.stockId = stockId;
    this.editType = editType;
  }

  protected EditStockSplitAction() {
    // Possibly for serialization.
  }

  public org.sjd.gordon.model.StockSplit getNewStockSplit() {
    return newStockSplit;
  }

  public java.lang.Long getStockId() {
    return stockId;
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
    EditStockSplitAction other = (EditStockSplitAction) obj;
    if (newStockSplit == null) {
      if (other.newStockSplit != null)
        return false;
    } else if (!newStockSplit.equals(other.newStockSplit))
      return false;
    if (stockId == null) {
      if (other.stockId != null)
        return false;
    } else if (!stockId.equals(other.stockId))
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
    hashCode = (hashCode * 37) + (newStockSplit == null ? 1 : newStockSplit.hashCode());
    hashCode = (hashCode * 37) + (stockId == null ? 1 : stockId.hashCode());
    hashCode = (hashCode * 37) + (editType == null ? 1 : editType.hashCode());
    return hashCode;
  }

  @Override
  public String toString() {
    return "EditStockSplitAction["
                 + newStockSplit
                 + ","
                 + stockId
                 + ","
                 + editType
    + "]";
  }
}
