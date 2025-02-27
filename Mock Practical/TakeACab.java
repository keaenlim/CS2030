class TakeACab implements Service {
    private static final int flagDownFare = 200;
    private static final int farePerKm = 33;

    @Override
    public int computeFare(int distance, int noOfPassengers, int time) {
        return flagDownFare + farePerKm * distance;
    }

    @Override
    public String toString() {
        return "TakeACab";
    }
}
