import java.util.ArrayList;
import java.util.List;

public class RentalSystem {

    private final List<RentalSystemModelClass> rentalList = new ArrayList<>();

    public List<RentalSystemModelClass> getRentalList() {
        return rentalList;
    }

    public void rentCar(CarModelClass car, CustomerModelClass customer, int days) {
        rentalList.add(new RentalSystemModelClass(car, customer, days));
        System.out.println("Car '" + car.car() + "' rented successfully to " + customer.name() + " for " + days + " days.");
    }

    public void returnCar(CarModelClass car) {
        RentalSystemModelClass rentalToRemove = null;

        for (RentalSystemModelClass rental : rentalList) {
            if (rental.carModelClass().equals(car)) {
                rentalToRemove = rental;
                break;
            }
        }

        if (rentalToRemove != null) {
            rentalList.remove(rentalToRemove);
            System.out.println("Car '" + car.car() + "' returned successfully.");
        } else {
            System.out.println("This car was not rented through the system.");
        }
    }

}
