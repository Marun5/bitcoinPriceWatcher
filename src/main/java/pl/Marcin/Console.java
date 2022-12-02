package pl.Marcin;

import java.util.Scanner;

public class Console {
    private final Price price = new Price();

    Schedule schedule = new Schedule();
    private char option;
    private final Scanner scanner = new Scanner(System.in);



    public void menu() {

        do {
            menuList();
            switch (option) {
                case 'a' -> {
                    System.out.println("To stop watching type 'stop' \n");
                    schedule.launchTimer();
                    String command = scanner.next();
                    while (!command.equals("stop")) {
                        System.out.println("To stop type 'stop'");
                        command = scanner.next();
                    }
                    schedule.timer.cancel();
                }
                case 'b' -> pricesToWatch();
                case 'c' -> setMinMax();
                case 'd' -> {
                    scanner.close();
                    System.out.println("See you soon :)");
                }
                default -> System.out.println("Choose the correct option");
            }
        }
        while (option != 'd');
    }

    private void menuList() {

        System.out.println("\n*!* MENU *!*");
        System.out.println("a) Watch");
        System.out.println("b) Check prices to watch");
        System.out.println("c) Set prices to watch");
        System.out.println("d) Exit");
        System.out.println("\nChoose option:");
        option = scanner.next().charAt(0);
    }

    private void setMinMax() {
        System.out.println("Enter maximum price to watch:");
        System.out.println("(now: "+price.getMax()+"USD)");
        float max = scanner.nextFloat();
        while(max<=price.getMin()){
            System.out.println("Maximum price must be greater than minimum price");
            max = scanner.nextFloat();
        }
        price.setMax(max);
        System.out.println("Enter minimum price to watch:");
        System.out.println("(now: "+price.getMin()+"USD)");
        float min = scanner.nextFloat();
        while(min>=price.getMax()){
            System.out.println("Minimum price must be lower than maximum price");
            min = scanner.nextFloat();
        }
        price.setMin(min);
        pricesToWatch();
    }
    private void pricesToWatch() {
        System.out.println("You set prices to watch:");
        System.out.println("Min: "+price.getMin()+" $");
        System.out.println("Max: "+price.getMax()+" $");
    }
}
