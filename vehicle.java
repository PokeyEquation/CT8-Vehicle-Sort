package vehicleData;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

public class vehicle implements Comparable<vehicle> {
    private String make;
    private String model;
    private double milesPerGallon;

    public vehicle(String make, String model, double milesPerGallon) {
        this.make = make;
        this.model = model;
        this.milesPerGallon = milesPerGallon;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public double getMilesPerGallon() {
        return milesPerGallon;
    }

    @Override
    public int compareTo(vehicle other) {
        return Double.compare(this.milesPerGallon, other.milesPerGallon);
    }

    @Override
    public String toString() {
        return make + " " + model + ": " + milesPerGallon + " mpg";
    }
}
class Main {
    public static void main(String[] args) {
        @SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
        LinkedList<vehicle> vehicles = new LinkedList<>();

        // Prompt user for vehicle data
        while (true) {
            System.out.println("Enter vehicle data (make, model, miles per gallon), or type 'done' to finish:");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("done")) {
                break;
            }

            String[] parts = input.split(",");
            if (parts.length != 3) {
                System.out.println("Invalid input. Please enter make, model, and miles per gallon separated by commas.");
                continue;
            }

            String make = parts[0].trim();
            String model = parts[1].trim();
            double mpg;
            try {
                mpg = Double.parseDouble(parts[2].trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid miles per gallon. Please enter a valid number.");
                continue;
            }

            vehicles.add(new vehicle(make, model, mpg));
        }

        // Sort the vehicles by miles per gallon
        Collections.sort(vehicles);

        // Write sorted vehicles to a text file
        try (FileWriter writer = new FileWriter("sorted_vehicles.txt")) {
            for (vehicle vehicle : vehicles) {
                writer.write(vehicle.toString() + "\n");
            }
            System.out.println("Sorted vehicles written to sorted_vehicles.txt");
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}




