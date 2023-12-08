package com.example.rickandmortyapi.infraestructure.api.consumers;

import com.example.rickandmortyapi.domain.model.Character;
import com.example.rickandmortyapi.infraestructure.api.responses.ApiResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.util.UriUtils;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
@Getter
public class ApiConsumer {

    @Value("${api.characters}")
    private String apiUrl;

    private final RestTemplate restTemplate;
    private Integer charactersCount;

    @Cacheable("characters")
    public List<Character> getAllCharacters() {

        List<Character> characterEntities = new ArrayList<>();

        ApiResponse apiResponse = null;
        int page = 1;

        while (true) {
            try {
                apiResponse = getResponseAPI(page,null);

                if (apiResponse.getCharacters() != null) {
                    characterEntities.addAll(apiResponse.getCharacters());
                }
            } catch (NullPointerException e) {
                log.error("Error: {}", e.getMessage());
            }
            assert apiResponse != null;
            if (apiResponse.getInfo().getNext() == null) {
                break;
            }
            if (charactersCount == null) {
                charactersCount = apiResponse.getInfo().getCount();
            }
            page++;
        }

        return characterEntities;
    }

    public List<Character> getCharactersByName(String name) {

        List<Character> characterEntities = new ArrayList<>();

        ApiResponse apiResponse;
            try {
                apiResponse = getResponseAPI(null,name);
                if (apiResponse.getCharacters() != null) {
                    characterEntities.addAll(apiResponse.getCharacters());
                }
            } catch (Exception e) {
                log.error("Error: {}", e.getMessage());
            }

        return characterEntities;
    }

    private ApiResponse getResponseAPI(Integer page, String name) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(apiUrl + "/character");

        if (page != null) {
            builder.queryParam("page", page);
        }

        if (name != null && !name.isEmpty()) {
            builder.queryParam("name", UriUtils.encode(name, StandardCharsets.UTF_8));
        }

        ResponseEntity<ApiResponse> response = restTemplate.exchange(
                builder.build(true).encode(StandardCharsets.UTF_8).toUri(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );

        return response.getBody();
    }

}