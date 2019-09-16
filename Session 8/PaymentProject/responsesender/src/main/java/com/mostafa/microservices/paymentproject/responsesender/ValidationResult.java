package com.mostafa.microservices.paymentproject.responsesender;

public class ValidationResult {
    private ValidationStatus valid;
    private String message;

    public ValidationResult() {
    }

    public ValidationResult(ValidationStatus valid, String message) {
        this.valid = valid;
        this.message = message;
    }

    public ValidationStatus getValid() {
        return valid;
    }

    public void setValid(ValidationStatus valid) {
        this.valid = valid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean check4Valid() {
        return getValid().equals(ValidationStatus.VALID);
    }
}
