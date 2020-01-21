package org.example.converter;

import org.example.converter.generic.JsonConverter;
import org.example.model.Car;

import java.util.List;

public class CarsJsonConverter extends JsonConverter<List<Car>> {
    public CarsJsonConverter(String jsonFilename) {
        super(jsonFilename);
    }
}
