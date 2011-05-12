package org.sjd.gordon.client.security;

import org.sjd.gordon.client.AbstractCallback;
import org.sjd.gordon.shared.security.ChangePasswordResponse;

import com.google.gwt.user.client.rpc.AsyncCallback;

public abstract class ChangeUserPasswordCallback extends AbstractCallback implements AsyncCallback<ChangePasswordResponse> {

	@Override
	public void onSuccess(ChangePasswordResponse changePasswordResponse) {
		passwordSuccessfullyChanged();
	}

	public abstract void passwordSuccessfullyChanged();
}
