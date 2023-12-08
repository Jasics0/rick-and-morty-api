package com.example.rickandmortyapi.infraestructure.repositories;

import com.example.rickandmortyapi.domain.model.Request;
import com.example.rickandmortyapi.domain.ports.out.RequestRepository;
import com.example.rickandmortyapi.gobal.mappers.GenericMapper;
import com.example.rickandmortyapi.infraestructure.entities.RequestEntity;
import com.example.rickandmortyapi.infraestructure.repositories.jpa.RequestRepositoryJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RequestRepositoryImpl implements RequestRepository {
    private final RequestRepositoryJpa requestRepositoryJpa;
    @Override
    public void saveRequest(Request request) {
        RequestEntity requestEntity = GenericMapper.map(request, RequestEntity.class);
        requestRepositoryJpa.save(requestEntity);
    }
}
