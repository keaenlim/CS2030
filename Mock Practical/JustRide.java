class JustRide implements Service {
    private static final int timeStart = 600;
    private static final int timeEnd = 900;
    private static final int fareAdd = 500;
    private static final int farePerKm = 22;

    @Override
    public int computeFare(int distance, int noOfPassengers, int timeOfService) {
        int fare = 0;
        if (timeStart <= timeOfService && timeOfService <= timeEnd) {
            fare += fareAdd;
        }
        fare += distance * farePerKm;
        return fare;
    }

    @Override
    public String toString() {
        return "JustRide";
    }
}
