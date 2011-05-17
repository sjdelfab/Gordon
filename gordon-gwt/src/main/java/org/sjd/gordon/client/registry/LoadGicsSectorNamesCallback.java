package org.sjd.gordon.client.registry;

import java.util.ArrayList;

import org.sjd.gordon.client.AbstractCallback;
import org.sjd.gordon.shared.registry.GetGicsSectorsResult;
import org.sjd.gordon.shared.registry.GicsSectorName;

import com.google.gwt.user.client.rpc.AsyncCallback;

public abstract class LoadGicsSectorNamesCallback extends AbstractCallback implements AsyncCallback<GetGicsSectorsResult> {

	@Override
	public void onSuccess(GetGicsSectorsResult sectors) {
		loaded(sectors.getSectorNames());
	}

	public abstract void loaded(ArrayList<GicsSectorName> sectors);

}
