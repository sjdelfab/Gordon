package org.sjd.gordon.client.registry;

import org.sjd.gordon.client.AbstractCallback;
import org.sjd.gordon.shared.registry.DeleteRegistryEntryResult;

import com.google.gwt.user.client.rpc.AsyncCallback;

public abstract class DeleteEntryCallback extends AbstractCallback implements AsyncCallback<DeleteRegistryEntryResult> {

	@Override
	public void onSuccess(DeleteRegistryEntryResult deletedEntry) {
		deleted();
	}

	public abstract void deleted();

}
