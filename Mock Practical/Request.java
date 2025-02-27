class Request {
    private final int distance;
    private final int numberOfPassengers;
    private final int time;

    Request(int distance, int numberOfPassengers, int time) {
        this.distance = distance;
        this.numberOfPassengers = numberOfPassengers;
        this.time = time;
    }
    
    public int computeFare(Service s) {
        return s.computeFare(this.distance, this.numberOfPassengers, this.time);
    }

    @Override
    public String toString() {
        return this.distance + "km for " + this.numberOfPassengers
            + "pax @ " + this.time + "hrs";
    }
}
