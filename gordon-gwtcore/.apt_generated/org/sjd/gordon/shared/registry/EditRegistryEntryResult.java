package org.sjd.gordon.shared.registry;

import com.gwtplatform.dispatch.shared.Result;

public class EditRegistryEntryResult implements Result { 

  org.sjd.gordon.shared.viewer.StockDetail stock;

  public EditRegistryEntryResult(org.sjd.gordon.shared.viewer.StockDetail stock) {
    this.stock = stock;
  }

  protected EditRegistryEntryResult() {
    // Possibly for serialization.
  }

  public org.sjd.gordon.shared.viewer.StockDetail getStock() {
    return stock;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    EditRegistryEntryResult other = (EditRegistryEntryResult) obj;
    if (stock == null) {
      if (other.stock != null)
        return false;
    } else if (!stock.equals(other.stock))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 23;
    hashCode = (hashCode * 37) + (stock == null ? 1 : stock.hashCode());
    return hashCode;
  }

  @Override
  public String toString() {
    return "EditRegistryEntryResult["
                 + stock
    + "]";
  }
}
