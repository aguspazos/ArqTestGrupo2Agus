package com.cines.helpers;

public class ApiError {
    
    private String error;
    private String errorMessage;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public ApiError(String error, String errorMessage) {
        this.error = error;
        this.errorMessage = errorMessage;
    }  
    
}
