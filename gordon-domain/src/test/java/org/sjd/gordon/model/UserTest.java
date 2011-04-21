package org.sjd.gordon.model;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class UserTest extends AbstractJpaTest {

	   @Test 
	    public void should_create_a_unitary_property_value() throws Exception { 
	    	User user = new User();
	    	user.setFirstName("John");
	    	user.setLastName("Doe");
	    	user.setUsername("jdoe");
	    	user.setPassword("NoSecrets");
	    	
	    	tx.begin(); 
	        em.persist(user);
	        tx.commit(); 
	        assertNotNull("ID should not be null", user.getId()); 
	   }
}
