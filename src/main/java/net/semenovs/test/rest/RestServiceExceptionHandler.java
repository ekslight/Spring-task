package net.semenovs.test.rest;

import net.semenovs.test.exception.ClientException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.stream.Collectors;

@ControllerAdvice
public class RestServiceExceptionHandler {

    private static final Logger logger = LogManager.getLogger(RestServiceExceptionHandler.class);

    @ExceptionHandler(ClientException.class)
    public ResponseEntity<CommonResponse> handle(ClientException e) {
        CommonResponse response = new CommonResponse(CommonResponse.Status.ERROR, null);
        response.setMessage(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CommonResponse> handle(Exception e) {
        logger.error(e.getMessage(), e);
        CommonResponse response = new CommonResponse(CommonResponse.Status.ERROR, null);
        response.setMessage("Unexpected error occurred");
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    public ResponseEntity<CommonResponse> handle(MethodArgumentNotValidException e) {
        String message = e.getBindingResult().getFieldErrors().stream()
                .map(this::buildMessage)
                .collect(Collectors.joining("\n"));
        CommonResponse response = new CommonResponse(CommonResponse.Status.ERROR, null);
        response.setMessage(message);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private String buildMessage(FieldError fieldError) {
        return String.format("[%s]: %s", fieldError.getField(), fieldError.getDefaultMessage());
    }
}
