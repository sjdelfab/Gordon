package org.sjd.gordon.ejb.dispatch;

import org.eclipse.persistence.exceptions.DatabaseException;
import org.sjd.gordon.shared.exceptions.EntityNotFoundException;
import org.sjd.gordon.shared.exceptions.NonUniqueResultException;
import org.sjd.gordon.shared.exceptions.OptimisticLockException;

import com.gwtplatform.dispatch.shared.ActionException;

public abstract class AbstractHandler {
	
	protected ActionException translateException(Throwable thrown) {
		if (causedBy(thrown, javax.persistence.OptimisticLockException.class)) {
			return new OptimisticLockException();
		} else if (causedBy(thrown, javax.persistence.NonUniqueResultException.class)) {
			return new NonUniqueResultException();
		} else if (causedBy(thrown, javax.persistence.EntityNotFoundException.class)) {
			return new EntityNotFoundException();
		} else if (causedBy(thrown, DatabaseException.class)) {
			DatabaseException dbException = (DatabaseException)getCause(thrown, DatabaseException.class);
			if (dbException.getDatabaseErrorCode() == 23505) {
				return new NonUniqueResultException();
			} else {
				return new ActionException("Unknown database error with error code: " + dbException.getDatabaseErrorCode());
			}
		} else {
			thrown.printStackTrace();
			return new ActionException("Unknown error.",thrown);
		}
	}

	private boolean causedBy(Throwable thrown, Class<?> clazz) {
	    if (thrown == null) {
            return false;
        }
	    if (thrown.getClass().equals(clazz)) {
	        return true;
	    }
	    return causedBy(thrown.getCause(), clazz);
	}
	
	private Throwable getCause(Throwable thrown, Class<?> clazz) {
	    if (thrown.getCause() == null) {
            return thrown;
        }
        if (thrown.getCause().getClass().isAssignableFrom(clazz)) {
             return thrown.getCause();
        }
        return getCause(thrown.getCause(), clazz);
	}
}
