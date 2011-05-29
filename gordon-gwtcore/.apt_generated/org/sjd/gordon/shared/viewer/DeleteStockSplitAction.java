package org.sjd.gordon.shared.viewer;

import com.gwtplatform.dispatch.shared.Action;

public class DeleteStockSplitAction implements Action<DeleteStockSplitResult> { 

  org.sjd.gordon.model.StockSplit stockSplit;

  public DeleteStockSplitAction(org.sjd.gordon.model.StockSplit stockSplit) {
    this.stockSplit = stockSplit;
  }

  protected DeleteStockSplitAction() {
    // Possibly for serialization.
  }

  public org.sjd.gordon.model.StockSplit getStockSplit() {
    return stockSplit;
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
    DeleteStockSplitAction other = (DeleteStockSplitAction) obj;
    if (stockSplit == null) {
      if (other.stockSplit != null)
        return false;
    } else if (!stockSplit.equals(other.stockSplit))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 23;
    hashCode = (hashCode * 37) + (stockSplit == null ? 1 : stockSplit.hashCode());
    return hashCode;
  }

  @Override
  public String toString() {
    return "DeleteStockSplitAction["
                 + stockSplit
    + "]";
  }
}
