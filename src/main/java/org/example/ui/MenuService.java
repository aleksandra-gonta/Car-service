    /*This class provides console menu for car service*/
package org.example.ui;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections.MapUtils;
import org.example.exception.AppException;
import org.example.model.Car;
import org.example.model.data.Statistics;
import org.example.service.CarsService;
import org.example.service.enums.Color;
import org.example.service.enums.OrderBy;
import org.example.service.utils.UserDataService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class MenuService {
    private final CarsService carsService;
    /*Method providing the chosen option*/
    public void mainMenu() {
        while (true) {
            try {
                int option = chooseOption();
                switch (option) {
                    case 1 -> option1();
                    case 2 -> option2();
                    case 3 -> option3();
                    case 4-> option4();
                    case 5 -> option5();
                    case 6 -> option6();
                    case 7 -> option7();
                    case 8 -> option8();
                   case 9 -> option9();
                    case 10-> option10();
                    case 11 -> {
                        UserDataService.close();
                        System.out.println("have a nice day");
                        return;
                    }
                    default -> System.out.println("No option with number " + option);
                }
            } catch (AppException e) {
                System.out.println("----------------------- !!! EXCEPTION !!! --------------------");
                System.out.println(e.getMessage());
                System.out.println("--------------------------------------------------------------");
            }
        }
    }

    private int chooseOption() {

        System.out.println("1 - show cars");
        System.out.println("2 - sort cars");
        System.out.println("3 - higher mileage");
        System.out.println("4 - count cars by the color");
        System.out.println("5 - most expensive car of the model");
        System.out.println("6 - calculate statistics");
        System.out.println("7 - most expensive car");
        System.out.println("8 - sort cars components");
        System.out.println("9 - group cars by components");
        System.out.println("10 - cars within the price range");
        System.out.println("11 - end of app");

        return UserDataService.getInt("Choose option:");
    }

    /*Methods option1-option10 used in the menu, based on all the methods from CarService Class*/
    private void option1() {
        System.out.println(carsService.toString());
    }

    private void option2() {
        OrderBy orderBy = UserDataService.getOrderBy();
        boolean descending = UserDataService.getBoolean("Descending sort?");
        List<Car> sortedCars = carsService.sortBy(orderBy, descending);
        System.out.println(toJson(sortedCars));
    }
    private void option3() {

        double mileageToCompare= UserDataService.getDouble("Enter the mileage:");
        List<Car> carsWithHigherMileage = carsService.getCarsWithHigherMileage(mileageToCompare);
        System.out.println(toJson(carsWithHigherMileage));
    }
    private void option4() {
        Map<Color, Long> carsByTheColor = carsService.countCarsByTheColor();
      convertMap(carsByTheColor);
    }
    private void option5() {
        Map<String, Car> modelWithMostExpensiveCar = carsService.groupByModelWithMostExpensiveCar();
       convertMap(modelWithMostExpensiveCar);
    }
    private void option6() {
        Statistics stats = carsService.calculateCarsStatistics();
        System.out.println(stats);
    }
    private void option7() {
        List<Car> mostExpensiveCars = carsService.findMostExpensiveCars();
        System.out.println(mostExpensiveCars);
    }
    private void option8 (){
        List<Car> carsSortedComponents = carsService.sortCarComponents();
        System.out.println(carsSortedComponents);
    }
    private void option9() {
        Map<String, List<Car>> groupedCarsByComponents = carsService.groupCarsByComponents();
        convertMap(groupedCarsByComponents);
    }
    private void option10() {
        BigDecimal min = UserDataService.getBigDecimal("Enter minimum price:");
        BigDecimal max = UserDataService.getBigDecimal("Enter maximum price:");
        List<Car> carsWithinThePriceRange = carsService.findCarsInThePriceRange(min, max);
        System.out.println(toJson(carsWithinThePriceRange));
    }



    /*Method returning object from JSON file in a pretty printing manner*/
    private static <T> String toJson(T element) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(element);
    }
    /*Method converting map*/
    private void convertMap(Map<?, ?> map) {
         MapUtils.verbosePrint(System.out,"", map);
    }
}
