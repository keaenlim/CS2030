class Booking implements Comparable<Booking> {
    private final Driver driver;
    private final Request request;
    private final Service bestService;
    private final int fare;
    private static final double divisor = 100.0;

    Booking(Driver driver, Request request) {
        this.driver = driver;
        this.request = request;

        // Compute fair for the different services
        this.bestService = driver.getServices().stream().reduce((s1, s2)
                -> request.computeFare(s1) <= request.computeFare(s2)
                ? s1 : s2).orElseThrow();
        
        this.fare = request.computeFare(this.bestService);
    }

    public Booking(Driver driver, Request request, Service service) {
        this.driver = driver;
        this.request = request;
        this.bestService = service;
        this.fare = request.computeFare(service);
    }

    @Override
    public int compareTo(Booking other) {
        // Compare fare of the 2 booking services
        int compare = Integer.compare(this.fare, other.fare);
        // If the fares are same choose the one with the shorter waiting time
        if (compare == 0) {
            return Integer.compare(this.driver.getWaitTime(),
                    other.driver.getWaitTime());
        }
        return compare;
    }

    @Override 
    public String toString() {
        // Display price of cab and include the option you are choosing
        return String.format("$%.2f using %s (%s)", fare / divisor, driver, this.bestService);
    }
}
