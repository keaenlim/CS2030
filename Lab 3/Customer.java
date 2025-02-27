class Customer {
    protected final int id;
    protected final double arrivalTime;
    protected final double serviceTime;

    Customer(int id, double arrivalTime) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.serviceTime = 0;
    }

    Customer(int id, double arrivalTime, double serviceTime) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
    }

    boolean canBeServed(double time) {
        return this.arrivalTime >= time;
    }

    double serveTill(double time) {
        return this.arrivalTime + time;
    }

    public String toString() {
        return "customer " + this.id;
    }
}
