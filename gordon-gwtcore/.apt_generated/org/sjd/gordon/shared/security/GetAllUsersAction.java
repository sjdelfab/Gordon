package org.sjd.gordon.shared.security;

import com.gwtplatform.dispatch.shared.Action;

public class GetAllUsersAction implements Action<GetAllUsersResult> { 


  public GetAllUsersAction() {
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
    return super.equals(obj);
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }

  @Override
  public String toString() {
    return "GetAllUsersAction["
    + "]";
  }
}
