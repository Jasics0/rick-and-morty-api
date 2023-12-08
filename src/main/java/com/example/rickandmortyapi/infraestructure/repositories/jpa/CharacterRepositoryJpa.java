package com.example.rickandmortyapi.infraestructure.repositories.jpa;

import com.example.rickandmortyapi.infraestructure.entities.CharacterEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CharacterRepositoryJpa extends CrudRepository<CharacterEntity, Long> {
    List<CharacterEntity> findAll();
    List<CharacterEntity> findByName(String name);
}
