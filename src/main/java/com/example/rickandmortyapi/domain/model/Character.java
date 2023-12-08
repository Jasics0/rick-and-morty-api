package com.example.rickandmortyapi.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Character {
    private long id;
    private String name;
    private String gender;
    private String status;
    private String image;
}
