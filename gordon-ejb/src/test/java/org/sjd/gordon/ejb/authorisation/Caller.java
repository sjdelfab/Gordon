package org.sjd.gordon.ejb.authorisation;

import java.util.concurrent.Callable;

public interface Caller {
	public <V> V call(Callable<V> callable) throws Exception;
}
