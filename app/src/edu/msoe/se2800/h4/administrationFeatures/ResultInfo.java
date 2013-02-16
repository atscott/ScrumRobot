package edu.msoe.se2800.h4.administrationFeatures;

/**
 * User: scottat Date: 1/25/13 Time: 8:15 PM
 * This is used by the database (and could be used elsewhere) to return information about results
 * for a given operation.
 */
public class ResultInfo {
	
	/**
	 * String that has the value of a message
	 */
    private String message;
    
    /**
     * Boolean for if successful or not
     */
    private boolean success;

    public ResultInfo(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    /**
     * Returns the message
     * @return String Message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Returns the boolean if successful
     * @return Boolean
     */
    public boolean wasSuccess() {
        return success;
    }
}