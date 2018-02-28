package com.mobiquityinc.validator;

import com.mobiquityinc.exceptions.APIException;

class ValidationResult {

    private boolean valid;
    private String message;

    static ValidationResult ok() {
        return new ValidationResult(true, null);
    }

    static ValidationResult fail(String message) {
        return new ValidationResult(false, message);
    }

    private ValidationResult(boolean valid, String message) {
        this.valid = valid;
        this.message = message;
    }

    private boolean isValid() {
        return valid;
    }

    void throwIfInvalid() {
        if (!isValid()) throw new APIException(getMessage());
    }

    private String getMessage() {
        return message;
    }

}
