package org.sjd.gordon.client.registry;

import org.sjd.gordon.client.AbstractCallback;
import org.sjd.gordon.shared.registry.EditRegistryEntryResult;
import org.sjd.gordon.shared.viewer.StockDetail;

import com.google.gwt.user.client.rpc.AsyncCallback;

public abstract class EditEntryCallback extends AbstractCallback implements AsyncCallback<EditRegistryEntryResult> {

	@Override
	public void onSuccess(EditRegistryEntryResult response) {
		commited(response.getStock());
	}

	public abstract void commited(StockDetail stock);

}
