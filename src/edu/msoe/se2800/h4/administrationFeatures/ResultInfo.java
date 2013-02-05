package edu.msoe.se2800.h4.administrationFeatures;

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