package com.example.rickandmortyapi.infraestructure.web.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CharacterDTO {
    private long id;
    private String name;
    private String gender;
    private String status;
    private String image;
}
