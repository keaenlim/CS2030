import java.util.Optional;

class DoneEvent extends Event {
    DoneEvent(Customer customer, double eventTime) {
        super(customer, eventTime);
    }

    @Override
    protected int priority() {
        return PRIORITY_DONE;   // highest priority
    }

    @Override
    Pair<Event, Shop> next(Shop shop) {
        return new Pair<>(this, shop);
    }

    @Override 
    public String toString() { 
        return eventTime + " " + customer.toString() + " done";
    }
}
