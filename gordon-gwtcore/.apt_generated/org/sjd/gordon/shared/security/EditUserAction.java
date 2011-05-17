package org.sjd.gordon.shared.security;

import com.gwtplatform.dispatch.shared.Action;

public class EditUserAction implements Action<EditUserResult> { 

  org.sjd.gordon.shared.security.UserDetail newUserDetails;
  org.sjd.gordon.shared.security.EditUser.EditType editType;

  public EditUserAction(org.sjd.gordon.shared.security.UserDetail newUserDetails, org.sjd.gordon.shared.security.EditUser.EditType editType) {
    this.newUserDetails = newUserDetails;
    this.editType = editType;
  }

  protected EditUserAction() {
    // Possibly for serialization.
  }

  public org.sjd.gordon.shared.security.UserDetail getNewUserDetails() {
    return newUserDetails;
  }

  public org.sjd.gordon.shared.security.EditUser.EditType getEditType() {
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
    EditUserAction other = (EditUserAction) obj;
    if (newUserDetails == null) {
      if (other.newUserDetails != null)
        return false;
    } else if (!newUserDetails.equals(other.newUserDetails))
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
    hashCode = (hashCode * 37) + (newUserDetails == null ? 1 : newUserDetails.hashCode());
    hashCode = (hashCode * 37) + (editType == null ? 1 : editType.hashCode());
    return hashCode;
  }

  @Override
  public String toString() {
    return "EditUserAction["
                 + newUserDetails
                 + ","
                 + editType
    + "]";
  }
}
