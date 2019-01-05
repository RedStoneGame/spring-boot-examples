package com.in28minutes.springboot.rest.example.student;

import javax.validation.ConstraintViolation;

public class StoneErrorHandler implements ApiErrorHandler {
    @Override
    public void onError(ConstraintViolation<Object> violation) {
        System.out.println("Sending email to support team: " +
                violation.getPropertyPath() + " " +
                violation.getMessage());
    }
}
