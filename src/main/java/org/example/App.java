package org.example;


import org.example.service.CarsService;

public class App
{
    public static void main( String[] args )
    {
        final String JSON_FILENAME = "cars.json";
        CarsService carsService = new CarsService(JSON_FILENAME);

        System.out.println(carsService);

    }
}
