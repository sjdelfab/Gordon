package org.sjd.gordon.shared.security;

import com.gwtplatform.dispatch.shared.Action;

public class DeleteUserAction implements Action<DeleteUserResult> { 

  java.lang.Integer userId;

  public DeleteUserAction(java.lang.Integer userId) {
    this.userId = userId;
  }

  protected DeleteUserAction() {
    // Possibly for serialization.
  }

  public java.lang.Integer getUserId() {
    return userId;
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
    DeleteUserAction other = (DeleteUserAction) obj;
    if (userId == null) {
      if (other.userId != null)
        return false;
    } else if (!userId.equals(other.userId))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 23;
    hashCode = (hashCode * 37) + (userId == null ? 1 : userId.hashCode());
    return hashCode;
  }

  @Override
  public String toString() {
    return "DeleteUserAction["
                 + userId
    + "]";
  }
}
