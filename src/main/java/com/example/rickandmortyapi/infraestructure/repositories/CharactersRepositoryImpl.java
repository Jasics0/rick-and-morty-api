package com.example.rickandmortyapi.infraestructure.repositories;

import com.example.rickandmortyapi.domain.ports.out.CharactersRepository;
import com.example.rickandmortyapi.gobal.mappers.GenericMapper;
import com.example.rickandmortyapi.infraestructure.api.consumers.ApiConsumer;
import com.example.rickandmortyapi.infraestructure.entities.CharacterEntity;
import com.example.rickandmortyapi.infraestructure.repositories.jpa.CharacterRepositoryJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import com.example.rickandmortyapi.domain.model.Character;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CharactersRepositoryImpl implements CharactersRepository {

    private final CharacterRepositoryJpa characterRepositoryJpa;
    private final ApiConsumer apiConsumer;

    @Override
    public List<Character> getCharacters() {

        List<Character> characters = apiConsumer.getAllCharacters();

        List<CharacterEntity> charactersSaved = characterRepositoryJpa.findAll();
        List<Character> mappedCharacters = GenericMapper.mapList(charactersSaved, Character.class);

        if (!mappedCharacters.isEmpty()) {
            characters.addAll(mappedCharacters);
        }

        return characters;
    }

    @Override
    public Character saveCharacter(Character character) {

        if (existsName(character.getName())) {
            throw new RuntimeException("Character already exists");
        }

        int sizeDB = characterRepositoryJpa.findAll().size() + 1;

        CharacterEntity characterEntity = GenericMapper.map(character, CharacterEntity.class);
        characterEntity.setId(apiConsumer.getCharactersCount() + sizeDB);
        characterEntity = characterRepositoryJpa.save(characterEntity);
        return GenericMapper.map(characterEntity, Character.class);
    }



    private boolean existsName(String name) {

        List<Character> characters = apiConsumer.getCharactersByName(name);
        List<CharacterEntity> charactersSaved = characterRepositoryJpa.findByName(name);

        characters.addAll(GenericMapper.mapList(charactersSaved, Character.class));

        for (Character character : characters) {
            if (character.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }


        return false;
    }

}
