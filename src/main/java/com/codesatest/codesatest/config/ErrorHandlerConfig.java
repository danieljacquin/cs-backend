package com.codesatest.codesatest.config;

import com.codesatest.codesatest.exceptions.NotFoundServiceException;
import com.codesatest.codesatest.exceptions.ValidateServiceException;
import com.codesatest.codesatest.utils.WrapperResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class ErrorHandlerConfig extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> all(Exception e, WebRequest request) {
        log.error(e.getMessage(), e);

        WrapperResponse<?> response = new WrapperResponse<>(false, "Internal Server Error", null);

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ValidateServiceException.class)
    public ResponseEntity<?> validateServiceException(ValidateServiceException e, WebRequest request) {
        log.info(e.getMessage(), e);

        WrapperResponse<?> response = new WrapperResponse<>(false, e.getMessage(), null);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(NotFoundServiceException.class)
    public ResponseEntity<?> notFoundServiceException(NotFoundServiceException e, WebRequest request) {
        log.info(e.getMessage(), e);

        WrapperResponse<?> response = new WrapperResponse<>(false, e.getMessage(), null);

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> details = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(e -> e.getDefaultMessage())
                .collect(Collectors.toList());
        //ApiErrorDto error = new ApiErrorDto("Validation Failed", details);
        WrapperResponse<?> response = new WrapperResponse<>(false, "Validation Failed", details);
        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }
}
