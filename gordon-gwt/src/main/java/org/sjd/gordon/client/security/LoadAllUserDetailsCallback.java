package org.sjd.gordon.client.security;

import java.util.ArrayList;

import org.sjd.gordon.client.AbstractCallback;
import org.sjd.gordon.shared.security.GetAllUsersResult;
import org.sjd.gordon.shared.security.UserDetail;

import com.google.gwt.user.client.rpc.AsyncCallback;

public abstract class LoadAllUserDetailsCallback extends AbstractCallback implements AsyncCallback<GetAllUsersResult> {

	@Override
	public void onSuccess(GetAllUsersResult allUserDetails) {
		loaded(allUserDetails.getUsers());
	}

	public abstract void loaded(ArrayList<UserDetail> users);

}
