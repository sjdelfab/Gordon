package org.sjd.gordon.ejb.dispatch.setup;

import javax.ejb.AccessLocalException;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.sjd.gordon.ejb.dispatch.security.DeleteUserEJBHandler;
import org.sjd.gordon.ejb.security.UserServiceLocal;
import org.sjd.gordon.model.User;
import org.sjd.gordon.shared.exceptions.EntityNotFoundException;
import org.sjd.gordon.shared.exceptions.UnauthorisedAccessException;
import org.sjd.gordon.shared.security.DeleteUserAction;

import com.gwtplatform.dispatch.server.ExecutionContext;

public class DeleteUserHandlerTest {

	private Mockery context;
	@Rule
    public ExpectedException thrown = ExpectedException.none(); 
	
	@Before
    public void beforeTest() {
        context = new Mockery();
    }
    
    @Test
    public void delete_non_existent_user() throws Exception {  
    	final UserServiceLocal service = context.mock(UserServiceLocal.class);
    	final ExecutionContext executionContext = context.mock(ExecutionContext.class);
    	context.checking(new Expectations() {
    		{ allowing(service).findUserById(with(any(Integer.class))); will(returnValue(null)); }
    	});
    	thrown.expect(EntityNotFoundException.class);
    	DeleteUserEJBHandler handler = new DeleteUserEJBHandler(service);
    	DeleteUserAction deleteUser = new DeleteUserAction(Integer.valueOf(1));
    	handler.execute(deleteUser, executionContext);
    }
    
    @Test
    public void delete_not_authorised() throws Exception {  
    	final UserServiceLocal service = context.mock(UserServiceLocal.class);
    	final ExecutionContext executionContext = context.mock(ExecutionContext.class);
    	final AccessLocalException accessException = new AccessLocalException();
    	final User user = new User();
    	context.checking(new Expectations() {
    		{ allowing(service).findUserById(with(any(Integer.class))); will(returnValue(user)); }
    		{ allowing(service).delete(with(any(User.class))); will(throwException(accessException)); }
    	});
    	thrown.expect(UnauthorisedAccessException.class);
    	DeleteUserEJBHandler handler = new DeleteUserEJBHandler(service);
    	DeleteUserAction deleteUser = new DeleteUserAction(Integer.valueOf(1));
    	handler.execute(deleteUser, executionContext);
    }
}
