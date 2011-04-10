package org.sjd.gordon.client;

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
		Log.error("Handle Failure:", cause);
		MessageBox.alert("Error", SERVER_ERROR, null);
	}
}
