import java.util.Optional;

class LeaveEvent extends Event { 
    LeaveEvent(Customer customer, double eventTime) {
        super(customer, eventTime);
    }

    @Override
    protected int priority() {
        return PRIORITY_LEAVE;
    }

    @Override
    Pair<Event, Shop> next(Shop shop) {
        // No next event, so just return 'this'
        return new Pair<>(this, shop);
    }

    @Override
    public String toString() {
        return this.eventTime + " " + customer.toString() + " leaves"; 
    }


}
