package org.sjd.gordon.shared.security;

import com.gwtplatform.dispatch.shared.Result;

public class GetAllUsersResult implements Result { 

  java.util.ArrayList<org.sjd.gordon.shared.security.UserDetail> users;

  public GetAllUsersResult(java.util.ArrayList<org.sjd.gordon.shared.security.UserDetail> users) {
    this.users = users;
  }

  protected GetAllUsersResult() {
    // Possibly for serialization.
  }

  public java.util.ArrayList<org.sjd.gordon.shared.security.UserDetail> getUsers() {
    return users;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    GetAllUsersResult other = (GetAllUsersResult) obj;
    if (users == null) {
      if (other.users != null)
        return false;
    } else if (!users.equals(other.users))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 23;
    hashCode = (hashCode * 37) + (users == null ? 1 : users.hashCode());
    return hashCode;
  }

  @Override
  public String toString() {
    return "GetAllUsersResult["
                 + users
    + "]";
  }
}
