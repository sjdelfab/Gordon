package org.sjd.gordon.client.security;

import org.sjd.gordon.client.AbstractCallback;
import org.sjd.gordon.shared.security.DeleteUserResponse;

import com.google.gwt.user.client.rpc.AsyncCallback;

public abstract class DeleteUserCallback extends AbstractCallback implements AsyncCallback<DeleteUserResponse> {

	@Override
	public void onSuccess(DeleteUserResponse deletedEntry) {
		deleted();
	}

	public abstract void deleted();

}
