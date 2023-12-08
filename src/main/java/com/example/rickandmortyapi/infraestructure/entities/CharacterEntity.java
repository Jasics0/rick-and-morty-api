package com.example.rickandmortyapi.infraestructure.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "characters")
@Getter @Setter
public class CharacterEntity {
    @Id
    private long id;
    private String name;
    private String gender;
    private String status;
    private String image;
}
