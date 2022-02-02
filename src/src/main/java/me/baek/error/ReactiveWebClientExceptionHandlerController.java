package me.baek.error;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.reactive.function.client.UnknownHttpStatusCodeException;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import static me.baek.error.ErrorCode.*;

@RestControllerAdvice
public class ReactiveWebClientExceptionHandlerController {

    @ExceptionHandler(UnknownHttpStatusCodeException.class)
    public ResponseEntity<ErrorResponse> unKnownHttpStatusCodeByRestTemplate(final UnknownHttpStatusCodeException e) {
        return ResponseEntity
                .status(UNKNOWN_HTTP_STATUS_CODE_ERROR.getStatus())
                .body(ErrorResponse.of(UNKNOWN_HTTP_STATUS_CODE_ERROR));
    }

    @ExceptionHandler(WebClientRequestException.class)
    public ResponseEntity<ErrorResponse> webClientRequest(final WebClientRequestException e) {
        return ResponseEntity
                .status(WEB_CLIENT_REQUEST_EXCEPTION.getStatus())
                .body(ErrorResponse.of(WEB_CLIENT_REQUEST_EXCEPTION));
    }

    @ExceptionHandler(WebClientResponseException.class)
    public ResponseEntity<ErrorResponse> webClientResponse(final WebClientResponseException e) {
        return ResponseEntity
                .status(WEB_CLIENT_RESPONSE_EXCEPTION.getStatus())
                .body(ErrorResponse.of(WEB_CLIENT_RESPONSE_EXCEPTION));
    }
}
