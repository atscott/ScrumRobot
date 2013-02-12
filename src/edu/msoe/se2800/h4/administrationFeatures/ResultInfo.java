package edu.msoe.se2800.h4.administrationFeatures;

/**
 * User: scottat Date: 1/25/13 Time: 8:15 PM
 * This is used by the database (and could be used elsewhere) to return information about results
 * for a given operation.
 */
public class ResultInfo {
    private String message;
    private boolean success;

    public ResultInfo(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public boolean wasSuccess() {
        return success;
    }
}