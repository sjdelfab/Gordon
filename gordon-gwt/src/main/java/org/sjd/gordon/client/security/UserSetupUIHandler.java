package org.sjd.gordon.client.security;

import org.sjd.gordon.shared.security.UserDetail;

import com.gwtplatform.mvp.client.UiHandlers;

public interface UserSetupUIHandler extends UiHandlers {

	public void add(UserDetail details);
	public void update(UserDetail details);
	public void delete(final UserDetail selected);
	public void changePassword(Integer userId, String password);
}
