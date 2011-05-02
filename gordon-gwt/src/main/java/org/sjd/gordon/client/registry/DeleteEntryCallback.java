package org.sjd.gordon.client.registry;

import org.sjd.gordon.client.AbstractCallback;
import org.sjd.gordon.shared.registry.DeleteRegistryEntryResponse;

import com.google.gwt.user.client.rpc.AsyncCallback;

public abstract class DeleteEntryCallback extends AbstractCallback implements AsyncCallback<DeleteRegistryEntryResponse> {

	@Override
	public void onSuccess(DeleteRegistryEntryResponse deletedEntry) {
		deleted();
	}

	public abstract void deleted();

}
