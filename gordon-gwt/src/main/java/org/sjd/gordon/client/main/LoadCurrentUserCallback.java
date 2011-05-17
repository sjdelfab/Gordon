package org.sjd.gordon.client.main;

import org.sjd.gordon.client.AbstractCallback;
import org.sjd.gordon.shared.security.GetCurrentUserResult;
import org.sjd.gordon.shared.security.UserDetail;

import com.google.gwt.user.client.rpc.AsyncCallback;

public abstract class LoadCurrentUserCallback extends AbstractCallback implements AsyncCallback<GetCurrentUserResult> {

	@Override
	public void onSuccess(GetCurrentUserResult response) {
		loaded(response.getUser());
	}

	public abstract void loaded(UserDetail user);
}
