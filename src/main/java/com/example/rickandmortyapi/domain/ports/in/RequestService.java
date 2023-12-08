package com.example.rickandmortyapi.domain.ports.in;

import com.example.rickandmortyapi.domain.model.Request;
import org.springframework.stereotype.Service;

@Service
public interface RequestService {
    void saveRequest(Request request);
}
