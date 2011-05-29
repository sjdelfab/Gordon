package org.sjd.gordon.shared.viewer;

import com.gwtplatform.dispatch.shared.Result;

public class EditDividendResult implements Result { 

  org.sjd.gordon.model.Dividend updatedDividend;

  public EditDividendResult(org.sjd.gordon.model.Dividend updatedDividend) {
    this.updatedDividend = updatedDividend;
  }

  protected EditDividendResult() {
    // Possibly for serialization.
  }

  public org.sjd.gordon.model.Dividend getUpdatedDividend() {
    return updatedDividend;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    EditDividendResult other = (EditDividendResult) obj;
    if (updatedDividend == null) {
      if (other.updatedDividend != null)
        return false;
    } else if (!updatedDividend.equals(other.updatedDividend))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 23;
    hashCode = (hashCode * 37) + (updatedDividend == null ? 1 : updatedDividend.hashCode());
    return hashCode;
  }

  @Override
  public String toString() {
    return "EditDividendResult["
                 + updatedDividend
    + "]";
  }
}
