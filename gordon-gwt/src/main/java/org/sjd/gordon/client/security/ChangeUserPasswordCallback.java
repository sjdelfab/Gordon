package org.sjd.gordon.client.security;

import org.sjd.gordon.client.AbstractCallback;
import org.sjd.gordon.shared.security.ChangeUserPasswordResult;

import com.google.gwt.user.client.rpc.AsyncCallback;

public abstract class ChangeUserPasswordCallback extends AbstractCallback implements AsyncCallback<ChangeUserPasswordResult> {

	@Override
	public void onSuccess(ChangeUserPasswordResult changePasswordResponse) {
		passwordSuccessfullyChanged();
	}

	public abstract void passwordSuccessfullyChanged();
}
