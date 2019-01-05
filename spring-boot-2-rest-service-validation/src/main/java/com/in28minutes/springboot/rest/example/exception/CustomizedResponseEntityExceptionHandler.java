package com.in28minutes.springboot.rest.example.exception;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.oracle.javafx.jmx.json.JSONWriter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.in28minutes.springboot.rest.example.student.StudentNotFoundException;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(StudentNotFoundException.class)
    public final ResponseEntity<Object> handleUserNotFoundException(StudentNotFoundException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, Object> invalidMessage = new HashMap<String, Object>();
        invalidMessage.put("code", -1);

        BindingResult bindingResult = ex.getBindingResult();

        ObjectError error0 = bindingResult.getAllErrors().get(0);

        System.out.println(JSON.toJSONString(error0, true));

        StringBuilder msgBuilder = new StringBuilder();
        if (error0 instanceof FieldError) {
            msgBuilder.append(((FieldError) error0).getField())
                    .append(":")
                    .append(error0.getDefaultMessage());
            invalidMessage.put("message", msgBuilder.toString());
        } else {
            invalidMessage.put("message", error0.getCode() + ":" + error0.getDefaultMessage());
            invalidMessage.put("debug", bindingResult.toString());
        }

        return new ResponseEntity(invalidMessage, HttpStatus.OK);
    }
}