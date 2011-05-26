package org.sjd.gordon.shared.viewer;

import com.gwtplatform.dispatch.shared.Result;

public class UpdateBusinessSummaryResult implements Result { 

  org.sjd.gordon.model.BusinessSummary updatedBusinessSummary;

  public UpdateBusinessSummaryResult(org.sjd.gordon.model.BusinessSummary updatedBusinessSummary) {
    this.updatedBusinessSummary = updatedBusinessSummary;
  }

  protected UpdateBusinessSummaryResult() {
    // Possibly for serialization.
  }

  public org.sjd.gordon.model.BusinessSummary getUpdatedBusinessSummary() {
    return updatedBusinessSummary;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    UpdateBusinessSummaryResult other = (UpdateBusinessSummaryResult) obj;
    if (updatedBusinessSummary == null) {
      if (other.updatedBusinessSummary != null)
        return false;
    } else if (!updatedBusinessSummary.equals(other.updatedBusinessSummary))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 23;
    hashCode = (hashCode * 37) + (updatedBusinessSummary == null ? 1 : updatedBusinessSummary.hashCode());
    return hashCode;
  }

  @Override
  public String toString() {
    return "UpdateBusinessSummaryResult["
                 + updatedBusinessSummary
    + "]";
  }
}
