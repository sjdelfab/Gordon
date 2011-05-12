package org.sjd.gordon.ejb.authorisation;

import java.util.concurrent.Callable;

import javax.annotation.security.RunAs;
import javax.ejb.Stateless;

@Stateless
@RunAs("USER")
public class UserCallingBean {

	public <V> V call(Callable<V> callable) throws Exception {
        return callable.call();
    }
}
