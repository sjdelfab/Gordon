package org.sjd.gordon.importing;

public class ImportException extends Exception {

	private static final long serialVersionUID = 690827671559316182L;

	public ImportException(Throwable cause) {
		super(cause);
	}

	public ImportException(String message) {
		super(message);
	}
}
