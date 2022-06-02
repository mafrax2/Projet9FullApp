package com.openclassroom.mediscreenclient.validation;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class CustomRestExceptionHandler  {
//extends ResponseEntityExceptionHandler
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllExceptions(final Exception ex, final WebRequest request) {
        System.out.println("All exceptions Method getting executed!!!!");

        final Map<String, String> details = new HashMap();

        if(ex instanceof ConstraintViolationException) {
            ConstraintViolationException e = (ConstraintViolationException) ex;
            ValidationErrorResponse error = new ValidationErrorResponse();
            ArrayList<Violation> violations = new ArrayList<>();
            int i =1;
            for (ConstraintViolation violation : e.getConstraintViolations()) {
                violations.add(
                        new Violation(violation.getPropertyPath().toString(), violation.getMessage()));
                details.put("error"+i, violation.getMessage());
                i++;
            }
            error.setViolations(violations);
            return new ResponseEntity(details, HttpStatus.BAD_REQUEST);
        }


        details.put("error", ex.getLocalizedMessage());

        return new ResponseEntity(details, HttpStatus.INTERNAL_SERVER_ERROR);
    }

//    @ExceptionHandler(ConstraintViolationException.class)
//    public ResponseEntity<ValidationErrorResponse> onConstraintValidationException(
//            ConstraintViolationException e) {
//        ValidationErrorResponse error = new ValidationErrorResponse();
//        ArrayList<Violation> violations = new ArrayList<>();
//        for (ConstraintViolation violation : e.getConstraintViolations()) {
//            violations.add(
//                    new Violation(violation.getPropertyPath().toString(), violation.getMessage()));
//        }
//        error.setViolations(violations);
//        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
//    }


    @ExceptionHandler(MissingServletRequestParameterException.class)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final Exception ex, final WebRequest request) {
        System.out.println("Validation Error Method getting executed!!!!");
        final List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        return new ResponseEntity("Validation Error", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    ValidationErrorResponse onMethodArgumentNotValidException(
            MethodArgumentNotValidException e) {
        ValidationErrorResponse error = new ValidationErrorResponse();
        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            error.getViolations().add(
                    new Violation(fieldError.getField(), fieldError.getDefaultMessage()));
        }
        return error;
    }

//    @Override
//    protected ResponseEntity<Object> handleMethodArgumentNotValid(
//            MethodArgumentNotValidException ex,
//            HttpHeaders headers,1
//            HttpStatus status,
//            WebRequest request) {
//        List<String> errors = new ArrayList<String>();
//        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
//            errors.add(error.getField() + ": " + error.getDefaultMessage());
//
//        }
//        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
//            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
//
//        }
//
//
//        ApiError apiError =
//                new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
//
//        return handleExceptionInternal(
//                ex, apiError, headers, apiError.getStatus(), request);
//    }

}
