package com.example.privmall.controller;

import com.example.privmall.dto.response.ExceptionResponse;
import com.example.privmall.exception.CustomRootException;
import com.example.privmall.exception.DuplicateEmailException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;


@ControllerAdvice
public class GlobalExceptionController {

    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ExceptionResponse methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException exception) {
        Map<String, String> fieldMessage = !exception.hasFieldErrors() ? Map.of() : exception
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        Objects.requireNonNull(DefaultMessageSourceResolvable::getDefaultMessage)));
        return ExceptionResponse.of(Objects.toString(HttpStatus.BAD_REQUEST.value()), "Invalid Request", fieldMessage);
    }

    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    public ExceptionResponse methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception) {
        return ExceptionResponse.of(
                Objects.toString(HttpStatus.BAD_REQUEST.value()),
                exception.getErrorCode()
        );
    }

    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = DuplicateEmailException.class)
    public ExceptionResponse duplicateEmailException(DuplicateEmailException exception) {
        return ExceptionResponse.of(Objects.toString(HttpStatus.BAD_REQUEST.value()), exception.getMessage());
    }

    @ExceptionHandler(value = CustomRootException.class)
    public ResponseEntity<ExceptionResponse> customRootExceptionHandler(CustomRootException customRootException) {
        Map<String, String> validation = customRootException.getValidation();
        String statusCode = customRootException.getStatusCode();
        return ResponseEntity
                .status(Integer.parseInt(statusCode))
                .body(ExceptionResponse.of(statusCode, customRootException.getMessage(), validation));
    }



}

