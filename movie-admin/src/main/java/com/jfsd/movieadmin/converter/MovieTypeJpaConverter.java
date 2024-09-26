package com.jfsd.movieadmin.converter;

import com.jfsd.movieadmin.enums.MovieType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class MovieTypeJpaConverter implements AttributeConverter<MovieType, String> {
    @Override
    public String convertToDatabaseColumn(MovieType attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.getType();
    }

    @Override
    public MovieType convertToEntityAttribute(String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        return Stream.of(MovieType.values())
                .filter(c -> c.getType().equals(value))
                .findFirst()
                .orElse(null);
    }
}
