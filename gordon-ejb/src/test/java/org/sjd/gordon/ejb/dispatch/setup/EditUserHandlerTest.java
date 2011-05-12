package org.sjd.gordon.ejb.dispatch.setup;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import org.eclipse.persistence.exceptions.DatabaseException;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.sjd.gordon.ejb.dispatch.security.EditUserEJBHandler;
import org.sjd.gordon.ejb.security.UserService;
import org.sjd.gordon.model.Group;
import org.sjd.gordon.model.User;
import org.sjd.gordon.shared.exceptions.NonUniqueResultException;
import org.sjd.gordon.shared.exceptions.OptimisticLockException;
import org.sjd.gordon.shared.security.EditUser;
import org.sjd.gordon.shared.security.EditUser.EditType;
import org.sjd.gordon.shared.security.EditUserResponse;
import org.sjd.gordon.shared.security.UserDetail;

import com.gwtplatform.dispatch.server.ExecutionContext;

public class EditUserHandlerTest {

	private Mockery context;
	private UserDetail userDetail;
	private UserService userService;
	private ExecutionContext executionContext;
	private EditUserEJBHandler handler;
	
	@Rule
    public ExpectedException thrown = ExpectedException.none(); 
	
	@Before
    public void beforeTest() {
        context = new Mockery();
        userDetail = new UserDetail();
        userDetail.setFirstName("Simon");
        userDetail.setLastName("Smith");
        userDetail.setUID("ssmith");
        userDetail.setActive(Boolean.TRUE);
  
    	userService = context.mock(UserService.class);
    	executionContext = context.mock(ExecutionContext.class);
    	handler = new EditUserEJBHandler(userService);
    }
    
    @Test
    public void add_duplicate_user() throws Exception {
       	userDetail.setPassword("password".toCharArray());
       	userDetail.setVersion(Integer.valueOf(1));
       	userDetail.setRoles("ADMIN");
       	
    	final Group adminGroup = new Group();
    	adminGroup.setName("ADMIN");
    	
    	SQLException sqlException = new SQLException("","",23505,null);
    	final DatabaseException dbException = DatabaseException.sqlException(sqlException);
    	dbException.setErrorCode(23505);
    	context.checking(new Expectations() {
    		{ allowing(userService).createUser(with(any(User.class))); will(throwException(new RuntimeException(dbException))); }
    		{ allowing(userService).findGroupByName(with("ADMIN")); will(returnValue(adminGroup)); }
    	});
    	EditUser editUser = new EditUser(userDetail,EditType.ADD);
    	thrown.expect(NonUniqueResultException.class);
    	handler.execute(editUser, executionContext);
    }
    
    @Test
    public void update_modified_user() throws Exception {
    	userDetail.setRoles("USER");
    	final javax.persistence.OptimisticLockException throwing = new javax.persistence.OptimisticLockException();
    	final Group userGroup = new Group();
    	userGroup.setName("USER"); 
    	final User user = new User();
    	user.addGroup(userGroup);
    	context.checking(new Expectations() {
    		{ allowing(userService).findUserById(with(any(Integer.class))); will(returnValue(user)); }
    		{ allowing(userService).updateUser(with(any(User.class))); will(throwException(new RuntimeException(throwing)));}
    		{ allowing(userService).findGroupByName(with("USER")); will(returnValue(userGroup)); }
    	});
    	EditUser editUser = new EditUser(userDetail,EditType.UPDATE);
    	thrown.expect(OptimisticLockException.class);
    	handler.execute(editUser, executionContext);
    }
    
    @Test
    public void add_with_1_role() throws Exception {
       	userDetail.setPassword("password".toCharArray());
       	userDetail.setVersion(Integer.valueOf(1));

    	userDetail.setRoles("ADMIN");
    	final ReturnParameter createUserAction = new ReturnParameter(0) {
    		protected Object modify(Object param) {
    			((User)param).setVersion(Integer.valueOf(1));
    			((User)param).setId(Integer.valueOf(1));
    			return param;
    		};
    	};
    	final Group adminGroup = new Group();
    	adminGroup.setName("ADMIN");
    	context.checking(new Expectations() {
    		{ allowing(userService).createUser(with(any(User.class))); will(createUserAction); }
    		{ allowing(userService).findGroupByName(with("ADMIN")); will(returnValue(adminGroup)); }
    	});
    	EditUser editUser = new EditUser(userDetail,EditType.ADD);
    	EditUserResponse response = handler.execute(editUser, executionContext);
    	UserDetail returnedDetails = response.getUser();
    	assertEquals("Simon",returnedDetails.getFirstName());
    	assertEquals(Integer.valueOf(1),returnedDetails.getId());
    	assertEquals("Smith",returnedDetails.getLastName());
    	assertEquals(Integer.valueOf(1),returnedDetails.getVersion());
    	assertEquals("ADMIN",returnedDetails.getRoles());
    	assertEquals("ssmith",returnedDetails.getUID());
    }

    @Test
    public void add_with_2_role() throws Exception {
    	userDetail.setRoles("ADMIN, USER");
    	userDetail.setPassword("password".toCharArray());
    	final ReturnParameter createUserAction = new ReturnParameter(0) {
    		protected Object modify(Object param) {
    			((User)param).setVersion(Integer.valueOf(1));
    			((User)param).setId(Integer.valueOf(1));
    			return param;
    		};
    	};
    	final Group adminGroup = new Group();
    	adminGroup.setName("ADMIN");
    	final Group userGroup = new Group();
    	userGroup.setName("USER");
    	context.checking(new Expectations() {
    		{ allowing(userService).createUser(with(any(User.class))); will(createUserAction); }
    		{ allowing(userService).findGroupByName(with("ADMIN")); will(returnValue(adminGroup)); }
    		{ allowing(userService).findGroupByName(with("USER")); will(returnValue(userGroup)); }
    	});
    	EditUser editUser = new EditUser(userDetail,EditType.ADD);
    	EditUserResponse response = handler.execute(editUser, executionContext);
    	UserDetail returnedDetails = response.getUser();
    	assertEquals("Simon",returnedDetails.getFirstName());
    	assertEquals(Integer.valueOf(1),returnedDetails.getId());
    	assertEquals("Smith",returnedDetails.getLastName());
    	assertEquals(Integer.valueOf(1),returnedDetails.getVersion());
    	assertEquals("ADMIN,USER",returnedDetails.getRoles());
    	assertEquals("ssmith",returnedDetails.getUID());
    	assertEquals(Boolean.TRUE,returnedDetails.isActive());
    }

    @Test
    public void update() throws Exception {
        userDetail.setFirstName("Simon");
        userDetail.setLastName("Smith");
        userDetail.setActive(Boolean.TRUE);    	
    	userDetail.setRoles("USER");
       	userDetail.setVersion(Integer.valueOf(1));
 	    	
    	final Group adminGroup = new Group();
    	adminGroup.setName("ADMIN");
    	final Group userGroup = new Group();
    	userGroup.setName("USER");    	
    	
    	final User user = new User();
    	user.setFirstName("Bob");
    	user.setLastName("Roberts");
    	user.setActive(Boolean.FALSE);
    	user.setId(Integer.valueOf(1));
    	user.setUsername("broberts");
    	user.setVersion(Integer.valueOf(1));
    	user.addGroup(adminGroup);
    	
    	final ReturnParameter updateUserAction = new ReturnParameter(0) {
    		protected Object modify(Object param) {
    			User entity = (User)param; 
    			entity.setVersion(entity.getVersion()+1);
    			return entity;
    		};
    	};
    	context.checking(new Expectations() {
    		{ allowing(userService).findUserById(with(any(Integer.class))); will(returnValue(user)); }
    		{ allowing(userService).updateUser(with(any(User.class))); will(updateUserAction); }
    		{ allowing(userService).findGroupByName(with("ADMIN")); will(returnValue(adminGroup)); }
    		{ allowing(userService).findGroupByName(with("USER")); will(returnValue(userGroup)); }
    	});
    	
    	EditUser editUser = new EditUser(userDetail,EditType.UPDATE);
    	EditUserResponse response = handler.execute(editUser, executionContext);
    	UserDetail returnedDetails = response.getUser();
    	assertEquals("Simon",returnedDetails.getFirstName());
    	assertEquals(Integer.valueOf(1),returnedDetails.getId());
    	assertEquals("Smith",returnedDetails.getLastName());
    	assertEquals(Integer.valueOf(2),returnedDetails.getVersion());
    	assertEquals("USER",returnedDetails.getRoles());
    	assertEquals("broberts",returnedDetails.getUID());
    	assertEquals(Boolean.TRUE,returnedDetails.isActive());  	
    }
    
}
