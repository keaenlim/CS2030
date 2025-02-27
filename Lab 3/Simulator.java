import java.util.List;
import java.util.Iterator;
import java.util.stream.Stream;

class Simulator {
    private final int numOfServers;
    private final int numOfCustomers;
    private final List<Pair<Integer,Pair<Double,Double>>> arrivals;

    Simulator(int numOfServers, int numOfCustomers, 
            List<Pair<Integer,Pair<Double,Double>>> arrivals) {
        this.numOfServers = numOfServers;
        this.numOfCustomers = numOfCustomers;
        this.arrivals = arrivals;
    }

    String run() {
        Shop shop = new Shop(numOfServers); // Create the shop with servers
        
        // Convert arrivals into a list of arrival events
        List<ArriveEvent> arrivalEvents = arrivals.stream()
            .map(arrival -> new ArriveEvent(
                new Customer(arrival.t(), arrival.u().t(), arrival.u().u()),
                arrival.u().t()
            ))
            .toList();
        
        // Add arrival events to PQ
        PQ<Event> eventQueue = arrivalEvents.stream()
            .reduce(new PQ<>(), (pq, event) -> pq.add(event), (pq1, pq2) -> pq1);
        
        // Process the event queue using State
        return Stream.iterate(new State(eventQueue, shop), 
                state -> !state.isEmpty(), 
                state -> state.next())
            .map(state -> state.toString())
            .filter(str -> !str.isEmpty()) 
            .reduce("", (x, y) -> x + y + "\n");
    }
}

