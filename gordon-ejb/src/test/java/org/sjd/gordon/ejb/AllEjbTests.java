package org.sjd.gordon.ejb;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.sjd.gordon.ejb.authorisation.SecurityTest;

import com.sun.appserv.security.ProgrammaticLogin;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	SecurityTest.class,
	DisplayModelEJBTest.class,
	ExchangeEJBTest.class,
	GicsEJBTest.class,
	StockEntityEJBTest.class,
	TabularDatasetEJBTest.class,
	UnitaryPropertiesEJBTest.class
})
public class AllEjbTests {

	static EJBContainer ec; 
	public static Context ctx; 
 	
	@BeforeClass
	public static void initContainer() throws Exception {
		Map<String, Object> p = new HashMap<String, Object>();
		p.put("org.glassfish.ejb.embedded.glassfish.installation.root", "./src/test/glassfish");
		ec = EJBContainer.createEJBContainer(p);
		ctx = ec.getContext();
		ProgrammaticLogin pl = new ProgrammaticLogin();
		boolean result = pl.login("admin", new char[]{'a','d','m','i','n'}, "file", true);
		if (!result) {
			throw new RuntimeException("Failed login");
		}
	}

    @AfterClass 
    public static void closeContainer() throws Exception {
        ec.close();
        ctx.close();
    }

}
