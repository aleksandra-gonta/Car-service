package org.example;


import org.example.service.CarsService;
import org.example.ui.MenuService;

public class App
{
    public static void main( String[] args )
    {
        final String JSON_FILENAME = "cars.json";
        CarsService carsService = new CarsService(JSON_FILENAME);


        MenuService menuService = new MenuService(carsService);
        menuService.mainMenu();

    }
}
