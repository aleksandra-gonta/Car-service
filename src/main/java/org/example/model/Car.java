package org.example.model;

import lombok.*;

import org.example.service.enums.Color;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Car {

    private String model;
    private BigDecimal price;
    private Color color;
    private double mileage;
    private List<String> components;

    @Override
    public String toString() {
        return model + " " + price + " " + color + " " + mileage + " " +  components;
    }
}