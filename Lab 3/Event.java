abstract class Event implements Comparable<Event> {
    protected final double eventTime;
    protected final Customer customer;
    // Priority constants to avoid magic numbers
    protected static final int PRIORITY_DONE = 1;
    protected static final int PRIORITY_LEAVE = 2;
    protected static final int PRIORITY_SERVE = 3;
    protected static final int PRIORITY_ARRIVE = 4;
   
    Event(Customer c, double eventTime) { 
        this.eventTime = eventTime;
        this.customer = c;
    }

    protected abstract int priority();

    @Override
    public int compareTo(Event other) {
        int comparison = Double.compare(this.eventTime, other.eventTime);

        if (comparison == 0) {  // if the 2 event timings are equal
            // Compare the events
            int compareEvents = Integer.compare(this.priority(), other.priority());
            // if the events are the same compare the IDs of the customer
            if (compareEvents == 0) {
                return Integer.compare(this.customer.id, other.customer.id);
            }
            return compareEvents;        
        }

        return comparison; 
    }

    public boolean hasNextEvent() {
        // has a future event if the customer has just arrived or been served
        return this.priority() >= PRIORITY_SERVE;
    }

    abstract Pair<Event, Shop> next(Shop shop);
}


    
