package org.sjd.gordon.shared.navigation;

import com.gwtplatform.dispatch.shared.Action;

public class GetExchangesAction implements Action<GetExchangesResult> { 


  public GetExchangesAction() {
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
    return "GetExchangesAction["
    + "]";
  }
}
