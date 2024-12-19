import java.util.Scanner;
import java.util.UUID;

public class UserMenu {

    private static final Scanner sc = new Scanner(System.in);
    private static final AdminSystem adminSystem = new AdminSystem();
    private static final RentalSystem rentalSystem = new RentalSystem();

    public static void UserMenuOptions() {
        while (true) {
            System.out.println("=== Option Menu ===");
            System.out.println("1. Rent a Car");
            System.out.println("2. Return a Car");
            System.out.println("3. Add a Car (Admin)");
            System.out.println("4. Exit");

            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> {
                    displayAvailableCars();
                    rentCar();
                }
                case 2 -> returnCar();
                case 3 -> AdminMenu.AdminMenuOptions();
                case 4 -> {
                    System.out.println("Thank you for choosing Car Rental System! Goodbye.");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void displayAvailableCars() {
        System.out.println("\n=== Current Car List ===");
        if (adminSystem.carList().isEmpty()) {
            System.out.println("No cars available.");
        } else {
            for (CarModelClass car : adminSystem.carList()) {
                System.out.println("- " + car.car() + " | Price: " + car.perDayPrice());
            }
        }
        System.out.println();
    }

    public static void rentCar() {
        System.out.print("Enter Car to Rent: ");
        sc.nextLine();
        String carName = sc.nextLine().trim();

        System.out.print("Enter Number of Days to Rent: ");
        int days = sc.nextInt();

        System.out.print("Enter Your Name: ");
        sc.nextLine();
        String name = sc.nextLine().trim();

        CustomerModelClass customer = new CustomerModelClass(name, UUID.randomUUID().toString());

        CarModelClass carToRent = null;
        for (CarModelClass car : adminSystem.carList()) {
            if (carName.equalsIgnoreCase(car.car())) {
                carToRent = car;
                break;
            }
        }


        if (carToRent != null) {
            adminSystem.removeCar(carToRent.car());
            rentalSystem.rentCar(carToRent, customer, days);
        } else {
            System.out.println("Car not found in the system. Please try again.");
        }
    }


    private static void returnCar() {
        System.out.print("Enter Car Name to Return: ");
        sc.nextLine();
        String carName = sc.nextLine().trim();

        RentalSystemModelClass rentalToReturn = null;

        for (RentalSystemModelClass rental : rentalSystem.getRentalList()) {
            if (carName.equalsIgnoreCase(rental.carModelClass().car())) {
                rentalToReturn = rental;
                break;
            }
        }

        if (rentalToReturn != null) {
            rentalSystem.returnCar(rentalToReturn.carModelClass());
            adminSystem.addCar(rentalToReturn.carModelClass());
            double totalBill = rentalToReturn.carModelClass().calculateTotalPrice(rentalToReturn.days());
            System.out.println("Car returned successfully.");
            System.out.println("Dear " + rentalToReturn.customerModelClass().name() + ", your total bill is: " + totalBill);
        } else {
            System.out.println("Invalid car name or car not found in the rental system.");
        }
    }
}
