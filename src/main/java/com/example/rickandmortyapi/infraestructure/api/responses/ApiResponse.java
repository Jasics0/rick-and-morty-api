package com.example.rickandmortyapi.infraestructure.api.responses;

import com.example.rickandmortyapi.domain.model.Character;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {

    private Info info;
    @JsonProperty("results")
    private List<Character> characters;

}
