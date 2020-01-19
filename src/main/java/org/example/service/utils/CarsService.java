package org.example.service.utils;

import org.eclipse.collections.impl.collector.BigDecimalSummaryStatistics;
import org.eclipse.collections.impl.collector.Collectors2;

import org.example.model.Car;

import org.example.model.data.MileageStatistics;
import org.example.model.data.PriceStatistics;
import org.example.model.data.Statistics;
import org.example.service.enums.Color;
import org.example.service.enums.OrderBy;


import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class CarsService {

    private final Set<Car> cars;

    public CarsService(Set<Car> cars) {
        this.cars = cars;
    }

    public List<Car> sortBy(OrderBy orderBy, boolean descending) {
        List<Car> orderedCars = switch (orderBy) {
            case COLOR -> cars
                    .stream()
                    .sorted(Comparator.comparing(Car::getColor))
                    .collect(Collectors.toList());
            case MILEAGE -> cars
                    .stream()
                    .sorted(Comparator.comparing(Car::getMileage))
                    .collect(Collectors.toList());

            case MODEL -> cars
                    .stream()
                    .sorted(Comparator.comparing(Car::getModel))
                    .collect(Collectors.toList());

            default -> cars
                    .stream()
                    .sorted(Comparator.comparing(Car::getPrice))
                    .collect(Collectors.toList());
        };

        if (descending) {
            Collections.reverse(orderedCars);
        }

        return orderedCars;

    }
    public List<Car> getCarsWithHigherMileage(double mileage) {
        return cars
                .stream()
                .filter(c -> c.getMileage() > mileage)
                .collect(Collectors.toList());

    }

    public Map<Color, Long> countCarsByTheColor() {
        return cars
                .stream()
                .collect(Collectors.groupingBy(Car::getColor, Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(e -> e.getKey(),
                        e -> e.getValue(),
                        (v1, v2) -> v1,
                        () -> new LinkedHashMap<>()));


    }

    public Map<String, Car> groupByModelWithMostExpensiveCar() {
        return cars
                .stream()
                .collect(Collectors.groupingBy(Car::getModel, Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparing(c -> c.getPrice())),
                        resultOp -> resultOp.orElseThrow()
                )));


    }


    public Statistics calculateCarsStatistics() {

        DoubleSummaryStatistics mileageStats = cars.stream()
                .collect(Collectors.summarizingDouble(c -> c.getMileage()));



        MileageStatistics mileageStatistics = new MileageStatistics(
                mileageStats.getMin(),
                mileageStats.getMax(),
                mileageStats.getAverage());




        BigDecimalSummaryStatistics priceStats = cars.stream().collect(Collectors2.summarizingBigDecimal(e->e.getPrice()));

        PriceStatistics priceStatistics = new PriceStatistics(
                priceStats.getMin(),
                priceStats.getMax(),
                priceStats.getAverage());


        return new Statistics(mileageStatistics, priceStatistics);

    }

    public List<Car> findMostExpensiveCars() {

        return cars
                .stream()
                .collect(Collectors.groupingBy(Car::getPrice))
                .entrySet().stream()
                .max(Comparator.comparing(Map.Entry::getKey))
                .orElseThrow(() -> new IllegalStateException("...."))
                .getValue();

    }


    public List<Car> sortCarComponents() {
        return cars
                .stream()
                .peek(car -> car.setComponents(car.getComponents().stream().sorted().collect(Collectors.toList())))
                .collect(Collectors.toList());
    }

    public Map<String, List<Car>> groupCarsByComponents() {

        List<String> components = cars
                .stream()
                .flatMap(car -> car.getComponents()
                        .stream())
                .collect(Collectors.toList());

        Map<String, List<Car>> carsContainingComponents = new HashMap<>();
        components
                .forEach(c -> carsContainingComponents
                        .put(c, (cars.stream().filter(e -> e.getComponents().contains(c))
                                .collect(Collectors.toList()))));


        return carsContainingComponents;
    }

    public List<Car> findCarsInThePriceRange(BigDecimal minPrice, BigDecimal maxPrice) {

        return cars
                .stream()
                .filter(c -> c.getPrice().compareTo(minPrice) > 0 && c.getPrice().compareTo(maxPrice) < 0)
                .sorted(Comparator.comparing(Car::getModel))
                .collect(Collectors.toList());


    }

    @Override
    public String toString() {
        return cars
                .stream()
                .map(Car::toString)
                .collect(Collectors.joining("\n"));
    }
}