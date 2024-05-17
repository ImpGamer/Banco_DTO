package com.imp.banca_digital.exceptions.handler;

import com.imp.banca_digital.entity.Error;
import com.imp.banca_digital.exceptions.BankAccountNotFoundException;
import com.imp.banca_digital.exceptions.ClientNotFoundException;
import com.imp.banca_digital.exceptions.InsufficientBalanceException;
import com.imp.banca_digital.exceptions.OperationNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ExceptionsHandler {
    @ExceptionHandler(BankAccountNotFoundException.class)
    public ResponseEntity<Error> handlerBankAccountNotFoundException(BankAccountNotFoundException ex) {
        Error generateError = new Error(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage()
        );
        return new ResponseEntity<>(generateError,null,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<Error> handlerClientNotFoundException(ClientNotFoundException ex) {
        Error generateError = new Error(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage()
        );
        return new ResponseEntity<>(generateError,null,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(InsufficientBalanceException.class)
    public ResponseEntity<Error> handlerInsufficientBalanceException(InsufficientBalanceException ex) {
        Error generateError = new Error(
                HttpStatus.NOT_ACCEPTABLE.value(),
                ex.getMessage()
        );
        return new ResponseEntity<>(generateError,null,HttpStatus.NOT_ACCEPTABLE);
    }
    @ExceptionHandler(OperationNotFoundException.class)
    public ResponseEntity<Error> handlerOperationNotFoundException(OperationNotFoundException ex) {
        Error generateError = new Error(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage()
        );
        return new ResponseEntity<>(generateError,null,HttpStatus.NOT_FOUND);
    }

}
