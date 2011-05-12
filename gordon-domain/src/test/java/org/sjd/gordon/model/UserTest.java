package org.sjd.gordon.model;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class UserTest extends AbstractJpaTest {

	   @Test 
	    public void should_create_a_user() throws Exception { 
	    	User user = new User();
	    	user.setFirstName("John");
	    	user.setLastName("Doe");
	    	user.setUsername("jdoe");
	    	user.setPassword("NoSecrets");
	    	user.setActive(Boolean.TRUE);
	    	Group group = new Group();
	    	group.setName("ADMIN");
	    	user.addGroup(group);
	    	
	    	tx.begin();
	    	em.persist(group);
	        em.persist(user);
	        tx.commit(); 
	        assertNotNull("ID should not be null", user.getId()); 
	   }
}
