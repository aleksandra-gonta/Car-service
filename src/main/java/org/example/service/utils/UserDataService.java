package org.example.service.utils;

import org.example.exception.AppException;
import org.example.service.enums.OrderBy;


import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;


public final class UserDataService {

    private UserDataService() {}

    private final static Scanner scanner = new Scanner(System.in);

    public static String getString(String message) {
        System.out.println(message);
        return scanner.nextLine();
    }

    public static int getInt(String message) {
        System.out.println(message);

        String valueAsString = scanner.nextLine();
        if (!valueAsString.matches("\\d+")) {
            throw new AppException("int value is not correct");
        }

        return Integer.parseInt(valueAsString);
    }
    public static double getDouble(String message) {
        System.out.println(message);

        String valueAsString = scanner.nextLine();
        if (!valueAsString.matches("\\d+\\.*\\d+")) {
            throw new AppException("double value is not correct");
        }

        return Double.parseDouble(valueAsString);
    }
    public static BigDecimal getBigDecimal(String message) {
        System.out.println(message);

        String valueAsString = scanner.nextLine();
        if (!valueAsString.matches("\\d+\\.*\\d+")) {
            throw new AppException("Big Decimal value is not correct");
        }

        return BigDecimal.valueOf(Long.parseLong(valueAsString));
    }
    public static OrderBy getOrderBy() {

        AtomicInteger counter = new AtomicInteger(1);
        Arrays
                .stream(OrderBy.values())
                .forEach( orderBy -> System.out.println(counter.getAndIncrement() + ". " + orderBy) );
        int option = getInt("Choose order by option:");

        if (option < 1 || option > OrderBy.values().length) {
            throw new AppException("option number is not correct");
        }
        return OrderBy.values()[option - 1];
    }

    public static boolean getBoolean(String message) {
        System.out.println(message + " [y/n]");
        return scanner.nextLine().toLowerCase().equals("y");
    }

    public static void close() {
        if (scanner != null) {
            scanner.close();
        }
    }
}
