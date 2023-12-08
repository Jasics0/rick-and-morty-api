package com.example.rickandmortyapi.application.services;

import com.example.rickandmortyapi.domain.model.Request;
import com.example.rickandmortyapi.domain.ports.in.RequestService;
import com.example.rickandmortyapi.domain.ports.out.RequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class RequestServiceImpl implements RequestService {

    private final RequestRepository requestRepository;

    @Override
    public void saveRequest(Request request) {
        request.setDate(LocalDate.now());
        requestRepository.saveRequest(request);
    }
}
