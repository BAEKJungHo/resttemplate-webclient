package me.baek.exception;

import me.baek.error.ErrorCode;
import org.springframework.validation.Errors;

public class ValidationException extends BusinessException {

    private final Errors errors;

    public ValidationException(final Errors errors) {
        super(ErrorCode.VALIDATION_ERROR);
        this.errors = errors;
    }

    public ValidationException(final ErrorCode errorCode) {
        super(errorCode);
        this.errors = null;
    }

    public Errors getErrors() {
        return errors;
    }
}
