package com.example.lab4server.exception;

import org.postgresql.util.PSQLException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    private ResponseEntity<String> handleConstraintViolationException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        Map<String, String> errors = bindingResult
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(FieldError::getField,
                        fieldError -> fieldError.getDefaultMessage() == null ?
                                "Ошибка валидации!!" :
                                fieldError.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors.toString());
    }

    @ExceptionHandler(value = PSQLException.class)
    private ResponseEntity<String> handlePSQLException(PSQLException e) {
        String message = "Пользователь с таким ником уже существует!";
        return new ResponseEntity<>(message, HttpStatus.CONFLICT);
    }

}
