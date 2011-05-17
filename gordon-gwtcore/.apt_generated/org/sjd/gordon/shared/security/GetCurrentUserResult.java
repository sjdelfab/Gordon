package org.sjd.gordon.shared.security;

import com.gwtplatform.dispatch.shared.Result;

public class GetCurrentUserResult implements Result { 

  org.sjd.gordon.shared.security.UserDetail user;

  public GetCurrentUserResult(org.sjd.gordon.shared.security.UserDetail user) {
    this.user = user;
  }

  protected GetCurrentUserResult() {
    // Possibly for serialization.
  }

  public org.sjd.gordon.shared.security.UserDetail getUser() {
    return user;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    GetCurrentUserResult other = (GetCurrentUserResult) obj;
    if (user == null) {
      if (other.user != null)
        return false;
    } else if (!user.equals(other.user))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 23;
    hashCode = (hashCode * 37) + (user == null ? 1 : user.hashCode());
    return hashCode;
  }

  @Override
  public String toString() {
    return "GetCurrentUserResult["
                 + user
    + "]";
  }
}
