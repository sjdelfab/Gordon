package org.sjd.gordon.shared.viewer;

import com.gwtplatform.dispatch.shared.Action;

public class EditDividendAction implements Action<EditDividendResult> { 

  org.sjd.gordon.model.Dividend newDividend;
  org.sjd.gordon.shared.util.EditType editType;

  public EditDividendAction(org.sjd.gordon.model.Dividend newDividend, org.sjd.gordon.shared.util.EditType editType) {
    this.newDividend = newDividend;
    this.editType = editType;
  }

  protected EditDividendAction() {
    // Possibly for serialization.
  }

  public org.sjd.gordon.model.Dividend getNewDividend() {
    return newDividend;
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
    EditDividendAction other = (EditDividendAction) obj;
    if (newDividend == null) {
      if (other.newDividend != null)
        return false;
    } else if (!newDividend.equals(other.newDividend))
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
    hashCode = (hashCode * 37) + (newDividend == null ? 1 : newDividend.hashCode());
    hashCode = (hashCode * 37) + (editType == null ? 1 : editType.hashCode());
    return hashCode;
  }

  @Override
  public String toString() {
    return "EditDividendAction["
                 + newDividend
                 + ","
                 + editType
    + "]";
  }
}
