import java.util.Scanner;

public class AdminMenu {

    private static final Scanner sc = new Scanner(System.in);
    private static final AdminSystem adminSystem = new AdminSystem();

    public static void AdminMenuOptions() {
        while (true) {
            System.out.println("\n=== Admin Menu ===");
            System.out.println("1. Add a Car");
            System.out.println("2. Remove a Car");
            System.out.println("3. Previous");

            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> addCar();
                case 2 -> removeCar();
                case 3 -> {
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }

            displayCurrentCars();
        }
    }

    private static void addCar() {
        System.out.print("Enter Car Name to Add: ");
        sc.nextLine();
        String carName = sc.nextLine().trim();

        System.out.print("Enter Price Per Day: ");
        double price = sc.nextDouble();

        adminSystem.addCar(new CarModelClass(carName, price));
    }

    private static void removeCar() {
        System.out.print("Enter Car Name to Remove: ");
        sc.nextLine();
        String carToRemove = sc.nextLine().trim();

        adminSystem.removeCar(carToRemove);
    }

    private static void displayCurrentCars() {
        System.out.println("\n=== Current Car List ===");
        if (adminSystem.carList().isEmpty()) {
            System.out.println("No cars available in the system.");
        } else {
            for (CarModelClass car : adminSystem.carList()) {
                System.out.println("- " + car.car() + " | Price: " + car.perDayPrice());
            }
        }
        System.out.println();
    }
}
