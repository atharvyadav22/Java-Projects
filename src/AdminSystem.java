import java.util.ArrayList;
import java.util.List;

public class AdminSystem {

    private static final List<CarModelClass> carList = new ArrayList<>();

    public List<CarModelClass> carList() {
        return carList;
    }

    public void addCar(CarModelClass car) {
        carList.add(car);
        System.out.println("(Admin Panel) Successfully Added " + car.car());
    }

    public void removeCar(String carName) {
        CarModelClass carModelClass = null;

        for (CarModelClass car: carList){

            if(carName.equalsIgnoreCase(car.car())){
                carModelClass = car;
                break;
            }
        }
        if(carModelClass != null){
            carList.remove(carModelClass);
            System.out.println("(Admin Panel) Successfully Removed Car " + carModelClass.car());
        }
        else System.out.println("(Admin Panel) Car not found in the system: " + carName);

    }

}
