package org.sjd.gordon.ejb.authorisation;

import java.util.concurrent.Callable;

import javax.annotation.security.RunAs;
import javax.ejb.Stateless;

import org.junit.Test;
import org.sjd.gordon.ejb.AbstractEJBTest;
import org.sjd.gordon.ejb.AllEjbTests;
import org.sjd.gordon.ejb.StockEntityServiceLocal;
import org.sjd.gordon.model.StockEntity;

public class SecurityTest extends AbstractEJBTest {
	
	@Test 
    public void test() throws Exception {
		final StockEntityServiceLocal stockEntityEjb = (StockEntityServiceLocal) AllEjbTests.ctx.lookup("java:global/classes/StockEntityEJB!org.sjd.gordon.ejb.StockEntityService");
		UserCallingBean userCallingBean = (UserCallingBean) AllEjbTests.ctx.lookup("java:global/classes/UserCallingBean");
		userCallingBean.call(new Callable<Object>() {
			@Override
			public Object call() throws Exception {
				StockEntity stock = new StockEntity();
				stockEntityEjb.createStock(stock);
				return null;
			}
			
		});
	}

	public static interface Caller {
        public <V> V call(Callable<V> callable) throws Exception;
    }
	
	@Stateless
    @RunAs("USER")
    public static class UserBean implements Caller {

        public <V> V call(Callable<V> callable) throws Exception {
            return callable.call();
        }

    }
}
