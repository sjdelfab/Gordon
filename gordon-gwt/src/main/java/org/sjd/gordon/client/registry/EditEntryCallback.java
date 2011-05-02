package org.sjd.gordon.client.registry;

import org.sjd.gordon.client.AbstractCallback;
import org.sjd.gordon.shared.registry.EditRegistryEntryResponse;

import com.google.gwt.user.client.rpc.AsyncCallback;

public abstract class EditEntryCallback extends AbstractCallback implements AsyncCallback<EditRegistryEntryResponse> {

	@Override
	public void onSuccess(EditRegistryEntryResponse response) {
		commited(response.getStockId());
	}

	public abstract void commited(Long stockId);

}
