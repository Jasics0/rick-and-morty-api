package com.example.rickandmortyapi.infraestructure.web.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
@Builder
@AllArgsConstructor
@Data
public class ErrorDTO {
    private String code;
    private String message;
}