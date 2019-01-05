package com.in28minutes.springboot.rest.example.student;

import javax.validation.ConstraintViolation;
import javax.validation.Payload;

public interface ApiErrorHandler extends Payload {

    void onError (ConstraintViolation<Object> violation);

}
