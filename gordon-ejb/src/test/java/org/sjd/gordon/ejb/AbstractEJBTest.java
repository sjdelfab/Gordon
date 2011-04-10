package org.sjd.gordon.ejb;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;

import org.junit.AfterClass;

public abstract class AbstractEJBTest {

	protected static EJBContainer ec; 
	protected static Context ctx; 
 	
	public static void initContainer() throws Exception {
		Map<String, Object> p = new HashMap<String, Object>();
		p.put("org.glassfish.ejb.embedded.glassfish.installation.root", "./src/test/glassfish");
		ec = EJBContainer.createEJBContainer(p);
		ctx = ec.getContext();
	}

    @AfterClass 
    public static void closeContainer() throws Exception { 
        ec.close(); 
    }
}
