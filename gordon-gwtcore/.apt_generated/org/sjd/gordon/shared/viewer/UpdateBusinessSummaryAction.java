package org.sjd.gordon.shared.viewer;

import com.gwtplatform.dispatch.shared.Action;

public class UpdateBusinessSummaryAction implements Action<UpdateBusinessSummaryResult> { 

  org.sjd.gordon.model.BusinessSummary newBusinessSummary;

  public UpdateBusinessSummaryAction(org.sjd.gordon.model.BusinessSummary newBusinessSummary) {
    this.newBusinessSummary = newBusinessSummary;
  }

  protected UpdateBusinessSummaryAction() {
    // Possibly for serialization.
  }

  public org.sjd.gordon.model.BusinessSummary getNewBusinessSummary() {
    return newBusinessSummary;
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
    UpdateBusinessSummaryAction other = (UpdateBusinessSummaryAction) obj;
    if (newBusinessSummary == null) {
      if (other.newBusinessSummary != null)
        return false;
    } else if (!newBusinessSummary.equals(other.newBusinessSummary))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 23;
    hashCode = (hashCode * 37) + (newBusinessSummary == null ? 1 : newBusinessSummary.hashCode());
    return hashCode;
  }

  @Override
  public String toString() {
    return "UpdateBusinessSummaryAction["
                 + newBusinessSummary
    + "]";
  }
}
