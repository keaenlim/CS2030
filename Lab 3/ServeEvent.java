class ServeEvent extends Event { 
    private final Server server;

    ServeEvent(Customer customer, double eventTime, Server server) {
        super(customer, eventTime);
        this.server = server;
    }

    @Override
    protected int priority() {
        return PRIORITY_SERVE;
    }

    @Override
    Pair<Event, Shop> next(Shop shop) {
        // Determine the time a server is done with service
        double doneTime = eventTime + customer.serviceTime; 

        // Create a DoneEvent
        Event doneEvent = new DoneEvent(customer, doneTime);
        return new Pair<>(doneEvent, shop);
    }

    @Override 
    public String toString() {
        return this.eventTime + " " + customer.toString() + " serve by " + this.server.toString();
    }
}
