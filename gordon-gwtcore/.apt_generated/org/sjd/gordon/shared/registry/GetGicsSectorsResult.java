package org.sjd.gordon.shared.registry;

import com.gwtplatform.dispatch.shared.Result;

public class GetGicsSectorsResult implements Result { 

  java.util.ArrayList<org.sjd.gordon.shared.registry.GicsSectorName> sectorNames;

  public GetGicsSectorsResult(java.util.ArrayList<org.sjd.gordon.shared.registry.GicsSectorName> sectorNames) {
    this.sectorNames = sectorNames;
  }

  protected GetGicsSectorsResult() {
    // Possibly for serialization.
  }

  public java.util.ArrayList<org.sjd.gordon.shared.registry.GicsSectorName> getSectorNames() {
    return sectorNames;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    GetGicsSectorsResult other = (GetGicsSectorsResult) obj;
    if (sectorNames == null) {
      if (other.sectorNames != null)
        return false;
    } else if (!sectorNames.equals(other.sectorNames))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 23;
    hashCode = (hashCode * 37) + (sectorNames == null ? 1 : sectorNames.hashCode());
    return hashCode;
  }

  @Override
  public String toString() {
    return "GetGicsSectorsResult["
                 + sectorNames
    + "]";
  }
}
