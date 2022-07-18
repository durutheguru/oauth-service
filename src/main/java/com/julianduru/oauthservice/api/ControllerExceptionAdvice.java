package com.julianduru.oauthservice.api;


import com.julianduru.oauthservice.api.ApiErrorResponse;
import com.julianduru.oauthservice.exception.HttpStatusAwareException;
import com.julianduru.oauthservice.exception.RuntimeServiceException;
import com.julianduru.oauthservice.exception.UnprocessableInputException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.data.rest.core.RepositoryConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * created by julian
 */
@Slf4j
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ControllerExceptionAdvice extends ResponseEntityExceptionHandler {


    @ExceptionHandler({Exception.class})
    public ResponseEntity<ApiErrorResponse> handleAllExceptions(Exception e) {
        log.error("Controller Exception: " + e.getMessage(), e);
        return new ResponseEntity<>(new ApiErrorResponse(e), HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<ApiErrorResponse> handleEntityNotFoundException(EntityNotFoundException e) {
        log.error("Controller Exception: " + e.getMessage(), e);
        return new ResponseEntity<>(new ApiErrorResponse(e), HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler({RuntimeServiceException.class})
    public ResponseEntity<ApiErrorResponse> handleRuntimeServiceExceptions(RuntimeServiceException e) {
        log.error("Controller Exception: " + e.getMessage(), e);

        if (e instanceof HttpStatusAwareException) {
            return new ResponseEntity<>(
                new ApiErrorResponse(e), ((HttpStatusAwareException)e).getHttpStatus()
            );
        }

        return new ResponseEntity<>(new ApiErrorResponse(e), HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler({SecurityException.class})
    public ResponseEntity<ApiErrorResponse> handleInvalidLoginCredentialsException(Exception e) {
        log.error("Controller Exception: " + e.getMessage(), e);
        return new ResponseEntity<>(new ApiErrorResponse(e), HttpStatus.UNAUTHORIZED);
    }


    @ExceptionHandler({
        UnprocessableInputException.class,
        IllegalArgumentException.class,
    })
    public ResponseEntity<ApiErrorResponse> handleInvalidInputException(Exception e) {
        log.error("Controller Exception: " + e.getMessage(), e);
        return new ResponseEntity<>(new ApiErrorResponse(e), HttpStatus.UNPROCESSABLE_ENTITY);
    }


    @ExceptionHandler({AccessDeniedException.class})
    public ResponseEntity<ApiErrorResponse> handleAccessDeniedException(Exception e) {
        log.error("Controller Exception: " + e.getMessage(), e);
        return new ResponseEntity<>(new ApiErrorResponse(e), HttpStatus.FORBIDDEN);
    }


    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
        MissingServletRequestParameterException e, HttpHeaders headers, HttpStatus status, WebRequest request
    ) {
        log.error("Controller Exception: " + e.getMessage(), e);
        return new ResponseEntity<>(new ApiErrorResponse(e), HttpStatus.BAD_REQUEST);
    }


    @Override
    protected ResponseEntity<Object> handleHttpMessageNotWritable(
        HttpMessageNotWritableException ex, HttpHeaders headers, HttpStatus status, WebRequest request
    ) {
        log.error(ex.getMessage(), ex);

        return handleExceptionInternal(ex, null, headers, status, request);
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
        MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request
    ) {
        log.error(ex.getMessage(), ex);

        List<FieldError> errorList = ex.getBindingResult().getFieldErrors();
        String error = errorList.get(0).getDefaultMessage();

        return new ResponseEntity<>(new ApiErrorResponse(error), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler({RepositoryConstraintViolationException.class})
    public ResponseEntity<List<String>> handleRepositoryConstraintViolationException(RepositoryConstraintViolationException e) {
        log.error(e.getMessage(), e);

        Errors violations = e.getErrors();
        List<String> messages = violations.getFieldErrors().stream()
            .map(DefaultMessageSourceResolvable::getDefaultMessage)
            .collect(Collectors.toList());

        return new ResponseEntity<>(messages, HttpStatus.BAD_REQUEST);
    }


    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error(ex.getMessage(), ex);

        List<FieldError> errorList = ex.getBindingResult().getFieldErrors();
        String error = errorList.get(0).getDefaultMessage();

        return new ResponseEntity<>(new ApiErrorResponse(error), HttpStatus.BAD_REQUEST);
    }


}




