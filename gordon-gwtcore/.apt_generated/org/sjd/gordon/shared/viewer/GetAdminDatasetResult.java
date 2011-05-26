package org.sjd.gordon.shared.viewer;

import com.gwtplatform.dispatch.shared.Result;

public class GetAdminDatasetResult implements Result { 

  org.sjd.gordon.shared.viewer.AdminDataset adminDataset;

  public GetAdminDatasetResult(org.sjd.gordon.shared.viewer.AdminDataset adminDataset) {
    this.adminDataset = adminDataset;
  }

  protected GetAdminDatasetResult() {
    // Possibly for serialization.
  }

  public org.sjd.gordon.shared.viewer.AdminDataset getAdminDataset() {
    return adminDataset;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    GetAdminDatasetResult other = (GetAdminDatasetResult) obj;
    if (adminDataset == null) {
      if (other.adminDataset != null)
        return false;
    } else if (!adminDataset.equals(other.adminDataset))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 23;
    hashCode = (hashCode * 37) + (adminDataset == null ? 1 : adminDataset.hashCode());
    return hashCode;
  }

  @Override
  public String toString() {
    return "GetAdminDatasetResult["
                 + adminDataset
    + "]";
  }
}
