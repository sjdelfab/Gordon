package org.sjd.gordon.dao;

public class SecurityHistoryDataException extends java.lang.Exception {
    
	private static final long serialVersionUID = -3231601743120315050L;
	
	private ErrorType errorType;
    
    /**
     * Creates a new instance of <code>StockHistoryDataException</code> without detail message.
     */
    public SecurityHistoryDataException(ErrorType errorType) {
        this.errorType = errorType;
    }
    
    /**
     * Creates a new instance of <code>StockHistoryDataException</code> without detail message.
     */
    public SecurityHistoryDataException(ErrorType errorType, Throwable t) {
        super(t);
        this.errorType = errorType;
    }    
        
    /**
     * Constructs an instance of <code>StockHistoryDataException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public SecurityHistoryDataException(ErrorType errorType, String msg) {
        super(msg);
        this.errorType = errorType;
    }

    public ErrorType getErrorType() {
        return errorType;
    }
    
}
