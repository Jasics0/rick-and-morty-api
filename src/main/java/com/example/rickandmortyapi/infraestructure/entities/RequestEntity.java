package com.example.rickandmortyapi.infraestructure.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "requests")
@Getter
@Setter
public class RequestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String method;
    private LocalDate date;
    private String body;
    private String status;
}
