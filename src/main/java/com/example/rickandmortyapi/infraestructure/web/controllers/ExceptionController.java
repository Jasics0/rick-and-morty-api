package com.example.rickandmortyapi.infraestructure.web.controllers;

import com.example.rickandmortyapi.infraestructure.web.dto.responses.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorDTO> handleException(Exception ex) {

        ErrorDTO errorDTO = ErrorDTO.builder()
                .code("400")
                .message(ex.getMessage())
                .build();

        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }
}