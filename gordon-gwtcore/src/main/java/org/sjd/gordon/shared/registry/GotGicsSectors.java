package org.sjd.gordon.shared.registry;

import java.io.Serializable;
import java.util.ArrayList;

import com.gwtplatform.dispatch.shared.Result;

public class GotGicsSectors implements Serializable, Result {

	private static final long serialVersionUID = 5373037948932371202L;
	
	private ArrayList<GicsSectorName> sectorNames;
	
	public GotGicsSectors() {}
	
	public GotGicsSectors(ArrayList<GicsSectorName> sectorNames) {
		this.sectorNames = sectorNames;
	}
	
	public ArrayList<GicsSectorName> getSectorNames() {
		return sectorNames;
	}
	
}
