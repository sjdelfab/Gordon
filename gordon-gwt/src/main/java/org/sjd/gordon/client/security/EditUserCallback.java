package org.sjd.gordon.client.security;

import org.sjd.gordon.client.AbstractCallback;
import org.sjd.gordon.shared.security.EditUserResponse;
import org.sjd.gordon.shared.security.UserDetail;

import com.google.gwt.user.client.rpc.AsyncCallback;

public abstract class EditUserCallback extends AbstractCallback implements AsyncCallback<EditUserResponse> {

	@Override
	public void onSuccess(EditUserResponse response) {
		commited(response.getUser());
	}

	public abstract void commited(UserDetail user);

}
