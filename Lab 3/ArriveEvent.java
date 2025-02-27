import java.util.Optional;

class ArriveEvent extends Event {
    ArriveEvent(Customer customer, double eventTime) {
        super(customer, eventTime);
    }

    @Override 
    protected int priority() {
        return PRIORITY_ARRIVE;   // lowest priority
    }

    public String toString() {
        return this.eventTime + " " + customer.toString() + " arrives";
    }

    Pair<Event, Shop> next(Shop shop) {
        Optional<Server> server = shop.findServer(this.customer);
        Server serverOptional = server.orElse(new Server(-1));

        if (!serverOptional.equals(new Server(-1))) {
            Server availableServer = serverOptional.serve(customer, customer.serviceTime);
            Shop updatedShop = shop.update(availableServer);      // update changes made           
            
            // Create a new ServeEvent
            Event serveEvent = new ServeEvent(customer, eventTime, serverOptional);
            return new Pair<>(serveEvent, updatedShop);
        } else {
            // Create a new LeaveEvent if there are no available servers
            Event leaveEvent = new LeaveEvent(customer, eventTime);
            return new Pair<>(leaveEvent, shop);
        }
    }    
}

