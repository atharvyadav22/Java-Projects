public record CarModelClass(String car, double perDayPrice) {

    public double calculateTotalPrice(int rentalDays) {
        return perDayPrice * rentalDays;
    }

}
