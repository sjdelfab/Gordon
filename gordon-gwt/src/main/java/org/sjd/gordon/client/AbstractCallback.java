package org.sjd.gordon.client;

import org.sjd.gordon.shared.exceptions.EntityNotFoundException;
import org.sjd.gordon.shared.exceptions.NonUniqueResultException;
import org.sjd.gordon.shared.exceptions.OptimisticLockException;

import com.allen_sauer.gwt.log.client.Log;
import com.extjs.gxt.ui.client.widget.MessageBox;

public class AbstractCallback {

	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = 
		      "An error occurred while attempting to contact the server. " +
		      "Please check your network connection and try again.";
	
	public void onFailure(Throwable cause) {
		if (cause instanceof OptimisticLockException) {
			MessageBox.alert("Update Error", "Transaction failed because somebody else has modified the data. Please refresh and try again.", null);
		} else if (cause instanceof EntityNotFoundException) {
			MessageBox.alert("Delete Error", "Transaction failed because somebody else has deleted this data. Please refresh and try again.", null);
		} else if (cause instanceof NonUniqueResultException) {
			MessageBox.alert("Insert Error", "Transaction failed because this is duplicate.", null);
		} else {
			Log.error("Handle Failure:", cause);
			MessageBox.alert("Error", SERVER_ERROR, null);
		}
	}
}
