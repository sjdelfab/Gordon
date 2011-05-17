package org.sjd.gordon.shared.security;

import com.gwtplatform.dispatch.shared.Result;

public class EditUserResult implements Result { 

  org.sjd.gordon.shared.security.UserDetail updatedUserDetails;

  public EditUserResult(org.sjd.gordon.shared.security.UserDetail updatedUserDetails) {
    this.updatedUserDetails = updatedUserDetails;
  }

  protected EditUserResult() {
    // Possibly for serialization.
  }

  public org.sjd.gordon.shared.security.UserDetail getUpdatedUserDetails() {
    return updatedUserDetails;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    EditUserResult other = (EditUserResult) obj;
    if (updatedUserDetails == null) {
      if (other.updatedUserDetails != null)
        return false;
    } else if (!updatedUserDetails.equals(other.updatedUserDetails))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 23;
    hashCode = (hashCode * 37) + (updatedUserDetails == null ? 1 : updatedUserDetails.hashCode());
    return hashCode;
  }

  @Override
  public String toString() {
    return "EditUserResult["
                 + updatedUserDetails
    + "]";
  }
}
