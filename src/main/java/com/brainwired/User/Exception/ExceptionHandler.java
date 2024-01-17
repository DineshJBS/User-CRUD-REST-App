package com.brainwired.User.Exception;

import com.brainwired.User.Config.ResponseHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException exe){
        System.out.println(exe.getMessage());
        ResponseHandler responseHandler = new ResponseHandler();
        responseHandler.setResponseCode(HttpStatus.NOT_FOUND.value());
        responseHandler.setResponseMessage(exe.getMessage());

        return new ResponseEntity<>(responseHandler, HttpStatus.NOT_FOUND);

    }

    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleUMethodArgumentNotValidException(MethodArgumentNotValidException exe){

        BindingResult bindingResult = exe.getBindingResult();

        ResponseHandler responseHandler = new ResponseHandler();
        responseHandler.setResponseCode(HttpStatus.BAD_REQUEST.value());
        responseHandler.setResponseMessage(bindingResult.getFieldError().getDefaultMessage());

        return new ResponseEntity<>(responseHandler, HttpStatus.BAD_REQUEST);

    }
}
