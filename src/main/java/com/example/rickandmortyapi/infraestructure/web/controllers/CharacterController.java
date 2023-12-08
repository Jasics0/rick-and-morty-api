package com.example.rickandmortyapi.infraestructure.web.controllers;

import com.example.rickandmortyapi.domain.model.Character;
import com.example.rickandmortyapi.domain.model.Request;
import com.example.rickandmortyapi.domain.ports.in.CharacterService;
import com.example.rickandmortyapi.domain.ports.in.RequestService;
import com.example.rickandmortyapi.gobal.mappers.GenericMapper;
import com.example.rickandmortyapi.infraestructure.web.dto.request.CharacterDTO;
import com.example.rickandmortyapi.infraestructure.web.dto.responses.ErrorDTO;
import com.example.rickandmortyapi.infraestructure.web.dto.responses.ResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/characters")
@RequiredArgsConstructor
@Slf4j
public class CharacterController {

    private final CharacterService characterService;
    private final RequestService requestService;

    @GetMapping()
    public ResponseEntity<ResponseDTO<List<CharacterDTO>>> getAllCharacters(@RequestParam(name = "status", required = false) String status, @RequestParam(name = "gender", required = false) String gender, @RequestParam(name = "name", required = false) String name, @RequestParam(name="orderBy", required = false) String orderBy, @RequestParam(name = "page", defaultValue = "1") int page) {

        Request request = Request.builder().method("GET").build();
        try {
            return ResponseEntity.ok(new ResponseDTO<>(GenericMapper.mapList(characterService.getCharacters(name,gender,status,orderBy,page), CharacterDTO.class), "Characters found"));
        } catch (Exception e) {
            request.setStatus("ERROR");
            log.error("Error: " + e.getMessage());
            throw e;
        } finally {
            if (request.getStatus() == null)
                request.setStatus("OK");
            requestService.saveRequest(request);
        }
    }

    @PostMapping()
    public ResponseEntity<ResponseDTO<CharacterDTO>> saveCharacter(@RequestBody CharacterDTO character) {

        Request request = Request.builder().method("POST").body(character.toString()).build();

        try {
            Character characterSaved = characterService.saveCharacter(GenericMapper.map(character, Character.class));
            return ResponseEntity.ok(new ResponseDTO<>(GenericMapper.map(characterSaved, CharacterDTO.class), "Character saved"));
        } catch (Exception e) {
            request.setStatus("ERROR");
            throw e;
        } finally {
            if (request.getStatus() == null)
                request.setStatus("OK");
            requestService.saveRequest(request);
        }
    }


}
