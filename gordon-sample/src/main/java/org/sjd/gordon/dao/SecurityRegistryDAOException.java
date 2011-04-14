package org.sjd.gordon.dao;

public class SecurityRegistryDAOException extends java.lang.Exception {
    
	private static final long serialVersionUID = 6442897371467695225L;

	public SecurityRegistryDAOException(Throwable t) {
        super(t);
    }
    
    
    /**
     * Constructs an instance of <code>StockRegistryDAOException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public SecurityRegistryDAOException(String msg) {
        super(msg);
    }
}
