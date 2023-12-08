package com.example.rickandmortyapi.domain.ports.in;

import com.example.rickandmortyapi.domain.model.Character;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CharacterService {
    List<Character> getCharacters(String name,String gender, String status,String orderBy,int page);

    Character saveCharacter(Character character);
}
