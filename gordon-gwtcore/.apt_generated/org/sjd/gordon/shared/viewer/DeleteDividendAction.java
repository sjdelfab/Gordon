package org.sjd.gordon.shared.viewer;

import com.gwtplatform.dispatch.shared.Action;

public class DeleteDividendAction implements Action<DeleteDividendResult> { 

  org.sjd.gordon.model.Dividend dividend;

  public DeleteDividendAction(org.sjd.gordon.model.Dividend dividend) {
    this.dividend = dividend;
  }

  protected DeleteDividendAction() {
    // Possibly for serialization.
  }

  public org.sjd.gordon.model.Dividend getDividend() {
    return dividend;
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
    DeleteDividendAction other = (DeleteDividendAction) obj;
    if (dividend == null) {
      if (other.dividend != null)
        return false;
    } else if (!dividend.equals(other.dividend))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 23;
    hashCode = (hashCode * 37) + (dividend == null ? 1 : dividend.hashCode());
    return hashCode;
  }

  @Override
  public String toString() {
    return "DeleteDividendAction["
                 + dividend
    + "]";
  }
}
