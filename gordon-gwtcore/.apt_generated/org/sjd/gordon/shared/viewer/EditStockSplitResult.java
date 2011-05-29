package org.sjd.gordon.shared.viewer;

import com.gwtplatform.dispatch.shared.Result;

public class EditStockSplitResult implements Result { 

  org.sjd.gordon.model.StockSplit updatedStockSplit;

  public EditStockSplitResult(org.sjd.gordon.model.StockSplit updatedStockSplit) {
    this.updatedStockSplit = updatedStockSplit;
  }

  protected EditStockSplitResult() {
    // Possibly for serialization.
  }

  public org.sjd.gordon.model.StockSplit getUpdatedStockSplit() {
    return updatedStockSplit;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    EditStockSplitResult other = (EditStockSplitResult) obj;
    if (updatedStockSplit == null) {
      if (other.updatedStockSplit != null)
        return false;
    } else if (!updatedStockSplit.equals(other.updatedStockSplit))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 23;
    hashCode = (hashCode * 37) + (updatedStockSplit == null ? 1 : updatedStockSplit.hashCode());
    return hashCode;
  }

  @Override
  public String toString() {
    return "EditStockSplitResult["
                 + updatedStockSplit
    + "]";
  }
}
