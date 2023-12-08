package com.example.rickandmortyapi.infraestructure.repositories.jpa;

import com.example.rickandmortyapi.infraestructure.entities.RequestEntity;
import org.springframework.data.repository.CrudRepository;

public interface RequestRepositoryJpa extends CrudRepository<RequestEntity, Long> {
}
