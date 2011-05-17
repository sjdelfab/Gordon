package org.sjd.gordon.shared.security;

import com.gwtplatform.dispatch.shared.Action;

public class ChangeUserPasswordAction implements Action<ChangeUserPasswordResult> { 

  java.lang.Integer userId;
  java.lang.String newPassword;

  public ChangeUserPasswordAction(java.lang.Integer userId, java.lang.String newPassword) {
    this.userId = userId;
    this.newPassword = newPassword;
  }

  protected ChangeUserPasswordAction() {
    // Possibly for serialization.
  }

  public java.lang.Integer getUserId() {
    return userId;
  }

  public java.lang.String getNewPassword() {
    return newPassword;
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
    ChangeUserPasswordAction other = (ChangeUserPasswordAction) obj;
    if (userId == null) {
      if (other.userId != null)
        return false;
    } else if (!userId.equals(other.userId))
      return false;
    if (newPassword == null) {
      if (other.newPassword != null)
        return false;
    } else if (!newPassword.equals(other.newPassword))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 23;
    hashCode = (hashCode * 37) + (userId == null ? 1 : userId.hashCode());
    hashCode = (hashCode * 37) + (newPassword == null ? 1 : newPassword.hashCode());
    return hashCode;
  }

  @Override
  public String toString() {
    return "ChangeUserPasswordAction["
                 + userId
                 + ","
                 + newPassword
    + "]";
  }
}
