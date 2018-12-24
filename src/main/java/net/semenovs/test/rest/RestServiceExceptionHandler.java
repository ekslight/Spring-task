package net.semenovs.test.rest;

import net.semenovs.test.exception.ClientException;
import net.semenovs.test.exception.NotEnoughFundsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestServiceExceptionHandler {

    @ExceptionHandler(NotEnoughFundsException.class)
    public ResponseEntity<CommonResponse> handle(NotEnoughFundsException e) {
        CommonResponse response = new CommonResponse(CommonResponse.Status.ERROR, e.getAccount());
        response.setMessage(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ClientException.class)
    public ResponseEntity<CommonResponse> handle(ClientException e) {
        CommonResponse response = new CommonResponse(CommonResponse.Status.ERROR, null);
        response.setMessage(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CommonResponse> handle(Exception e) {
        CommonResponse response = new CommonResponse(CommonResponse.Status.ERROR, null);
        response.setMessage("Unexpected error occurred");
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
