package org.sjd.gordon.client.registry;

import java.util.ArrayList;

import org.sjd.gordon.client.AbstractCallback;
import org.sjd.gordon.shared.registry.GicsSectorName;
import org.sjd.gordon.shared.registry.GotGicsSectors;

import com.google.gwt.user.client.rpc.AsyncCallback;

public abstract class LoadGicsSectorNamesCallback extends AbstractCallback implements AsyncCallback<GotGicsSectors> {

	@Override
	public void onSuccess(GotGicsSectors sectors) {
		loaded(sectors.getSectorNames());
	}

	public abstract void loaded(ArrayList<GicsSectorName> sectors);

}
