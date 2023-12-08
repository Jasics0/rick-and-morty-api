package com.example.rickandmortyapi.domain.ports.out;

import com.example.rickandmortyapi.domain.model.Request;

public interface RequestRepository {
    void saveRequest(Request request);
}
