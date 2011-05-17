package org.sjd.gordon.client.security;

import com.gwtplatform.dispatch.annotation.GenEvent;
import com.gwtplatform.dispatch.annotation.In;

@GenEvent
public class ChangeUserName {

	@In(1) String newName;
}
