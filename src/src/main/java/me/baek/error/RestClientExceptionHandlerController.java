package me.baek.error;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.UnknownHttpStatusCodeException;

import static me.baek.error.ErrorCode.*;

@RestControllerAdvice
public class RestClientExceptionHandlerController {

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<ErrorResponse> httpClient(final HttpClientErrorException e) {
        return ResponseEntity
                .status(HTTP_CLIENT_ERROR.getStatus())
                .body(ErrorResponse.of(HTTP_CLIENT_ERROR));
    }

    @ExceptionHandler(HttpServerErrorException.class)
    public ResponseEntity<ErrorResponse> httpServer(final HttpServerErrorException  e) {
        return ResponseEntity
                .status(HTTP_SERVER_ERROR.getStatus())
                .body(ErrorResponse.of(HTTP_SERVER_ERROR));
    }

    @ExceptionHandler(UnknownHttpStatusCodeException.class)
    public ResponseEntity<ErrorResponse> unKnownHttpStatusCodeByRestTemplate(final UnknownHttpStatusCodeException e) {
        return ResponseEntity
                .status(UNKNOWN_HTTP_STATUS_CODE_ERROR.getStatus())
                .body(ErrorResponse.of(UNKNOWN_HTTP_STATUS_CODE_ERROR));
    }

    @ExceptionHandler(RestClientResponseException.class)
    public ResponseEntity<ErrorResponse> restClientResponse(final RestClientResponseException e) {
        return ResponseEntity
                .status(REST_CLIENT_RESPONSE_ERROR.getStatus())
                .body(ErrorResponse.of(REST_CLIENT_RESPONSE_ERROR));
    }
}
