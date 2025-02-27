class ShareARide implements Service {
    private static final int timeStart = 600;
    private static final int timeEnd = 900;
    private static final int fareAdd = 500;
    private static final int farePerKm = 50;

    @Override
    public int computeFare(int distance, int noOfPassengers, int timeOfService) {
        int fare = 0;
        if (timeOfService >= timeStart && timeOfService <= timeEnd) {
            fare += fareAdd;
        }
        fare += farePerKm * distance;
        return fare / noOfPassengers;
    }

    @Override
    public String toString() {
        return "ShareARide";
    }
}
