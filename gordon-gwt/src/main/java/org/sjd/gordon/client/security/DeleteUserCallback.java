package org.sjd.gordon.client.security;

import org.sjd.gordon.client.AbstractCallback;
import org.sjd.gordon.shared.security.DeleteUserResult;

import com.google.gwt.user.client.rpc.AsyncCallback;

public abstract class DeleteUserCallback extends AbstractCallback implements AsyncCallback<DeleteUserResult> {

	@Override
	public void onSuccess(DeleteUserResult deletedEntry) {
		deleted();
	}

	public abstract void deleted();

}
