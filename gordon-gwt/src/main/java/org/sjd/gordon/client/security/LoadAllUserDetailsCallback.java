package org.sjd.gordon.client.security;

import java.util.ArrayList;

import org.sjd.gordon.client.AbstractCallback;
import org.sjd.gordon.shared.security.GotAllUserDetails;
import org.sjd.gordon.shared.security.UserDetail;

import com.google.gwt.user.client.rpc.AsyncCallback;

public abstract class LoadAllUserDetailsCallback extends AbstractCallback implements AsyncCallback<GotAllUserDetails> {

	@Override
	public void onSuccess(GotAllUserDetails allUserDetails) {
		loaded(allUserDetails.getUsers());
	}

	public abstract void loaded(ArrayList<UserDetail> users);

}
