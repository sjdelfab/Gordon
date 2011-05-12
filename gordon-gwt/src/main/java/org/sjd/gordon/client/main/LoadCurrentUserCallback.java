package org.sjd.gordon.client.main;

import org.sjd.gordon.client.AbstractCallback;
import org.sjd.gordon.shared.security.GotCurrentUserResponse;
import org.sjd.gordon.shared.security.UserDetail;

import com.google.gwt.user.client.rpc.AsyncCallback;

public abstract class LoadCurrentUserCallback extends AbstractCallback implements AsyncCallback<GotCurrentUserResponse> {

	@Override
	public void onSuccess(GotCurrentUserResponse response) {
		loaded(response.getUser());
	}

	public abstract void loaded(UserDetail user);
}
