package com.example.rickandmortyapi.gobal.mappers;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import java.util.List;
import java.util.stream.Collectors;

public class GenericMapper {
    private static final ModelMapper MAPPER = new ModelMapper();

    public GenericMapper() {
        MAPPER.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    public static  <T> T map(Object source, Class<T> destinationClass) {
        return MAPPER.map(source, destinationClass);
    }
    public static <T> List<T> mapList(List<?> sourceList, Class<T> destinationClass) {
        return sourceList.stream().map(element -> map(element, destinationClass)).collect(Collectors.toList());
    }
}