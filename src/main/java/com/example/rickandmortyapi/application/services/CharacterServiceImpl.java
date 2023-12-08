package com.example.rickandmortyapi.application.services;

import com.example.rickandmortyapi.domain.model.Character;
import com.example.rickandmortyapi.domain.ports.in.CharacterService;
import com.example.rickandmortyapi.domain.ports.out.CharactersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {

    private final CharactersRepository charactersRepository;

    @Override
    public List<Character> getCharacters(String name, String gender, String status, String orderBy, int page) {

        Predicate<Character> filterByName = character -> name == null || character.getName().toLowerCase().contains(name.toLowerCase());
        Predicate<Character> filterByStatus = character -> status == null || character.getStatus().toLowerCase().contains(status.toLowerCase());
        Predicate<Character> filterByGender = character -> gender == null || character.getGender().toLowerCase().contains(gender.toLowerCase());

        List<Character> characters = charactersRepository.getCharacters();
        characters = characters.stream().filter(filterByName).filter(filterByStatus).filter(filterByGender).toList();

        List<Character> result = new ArrayList<>(characters);

        if (orderBy != null) {
            switch (orderBy.toLowerCase()) {
                case "name":
                    result.sort(Comparator.comparing(Character::getName));
                    break;
                case "status":
                    result.sort(Comparator.comparing(Character::getStatus));
                    break;
                case "gender":
                    result.sort(Comparator.comparing(Character::getGender));
                    break;
            }
        }

        result = result.stream().distinct().collect(Collectors.toList());

        int PAGE_SIZE = 10;
        int startIndex = (page - 1) * PAGE_SIZE;
        int endIndex = Math.min(startIndex + PAGE_SIZE, result.size());

        return result.subList(startIndex, endIndex);
    }

    @Override
    public Character saveCharacter(Character character) {
        return charactersRepository.saveCharacter(character);
    }
}
