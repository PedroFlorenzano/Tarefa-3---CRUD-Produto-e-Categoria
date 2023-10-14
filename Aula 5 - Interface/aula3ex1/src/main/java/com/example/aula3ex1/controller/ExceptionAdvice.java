package com.example.aula3ex1.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.aula3ex1.dto.Exceptions.ApiErrorDTO;
import com.example.aula3ex1.exceptions.RegraNegocioException;

import org.springframework.http.HttpStatus;


@RestControllerAdvice
@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class ExceptionAdvice {
    
    @ExceptionHandler(RegraNegocioException.class)
    public ApiErrorDTO handleGeneralException(RegraNegocioException generalException){
        String error = generalException.getMessage();
        return new ApiErrorDTO(error);
    }
}
