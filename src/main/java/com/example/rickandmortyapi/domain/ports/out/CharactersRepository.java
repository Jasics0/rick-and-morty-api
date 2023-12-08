package com.example.rickandmortyapi.domain.ports.out;

import com.example.rickandmortyapi.domain.model.Character;

import java.util.List;

public interface CharactersRepository {
    List<Character> getCharacters();

    Character saveCharacter(Character character);
}
