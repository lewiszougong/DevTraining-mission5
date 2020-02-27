package com.DevTraining.mission5.Exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

//ControllerAdvice(annotations = RestController.class)
class PetNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(PetNotFoundException.class)
    String employeeNotFoundHandler(PetNotFoundException ex) {
        return ex.getLocalizedMessage();
    }
}
