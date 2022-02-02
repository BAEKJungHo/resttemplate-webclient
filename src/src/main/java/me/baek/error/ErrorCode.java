package me.baek.error;

import org.springframework.http.HttpStatus;

public enum ErrorCode {

    NOT_FOUND(HttpStatus.NOT_FOUND, "-10001", "Resource Not Found"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "-10003", "Internal Server Error"),
    VALIDATION_ERROR(HttpStatus.BAD_REQUEST, "-10004", "Validation Error"),
    HTTP_CLIENT_ERROR(HttpStatus.BAD_REQUEST, "-20001", "Http Client Error"),
    HTTP_SERVER_ERROR(HttpStatus.BAD_REQUEST, "-20002", "Http Server Error"),
    UNKNOWN_HTTP_STATUS_CODE_ERROR(HttpStatus.BAD_REQUEST, "-20003", "Unknown Http Status Code Error"),
    REST_CLIENT_RESPONSE_ERROR(HttpStatus.BAD_REQUEST, "-20004", "Rest Client Response Error"),
    WEB_CLIENT_RESPONSE_EXCEPTION(HttpStatus.BAD_REQUEST, "-20005", "Web Client Response Exception"),
    WEB_CLIENT_REQUEST_EXCEPTION(HttpStatus.BAD_REQUEST, "-20006", "Web Client Request Exception"),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;

    ErrorCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
