package org.sjd.gordon.client.security;

import org.sjd.gordon.client.AbstractCallback;
import org.sjd.gordon.shared.security.EditUserResult;
import org.sjd.gordon.shared.security.UserDetail;

import com.google.gwt.user.client.rpc.AsyncCallback;

public abstract class EditUserCallback extends AbstractCallback implements AsyncCallback<EditUserResult> {

	@Override
	public void onSuccess(EditUserResult response) {
		commited(response.getUpdatedUserDetails());
	}

	public abstract void commited(UserDetail user);

}
