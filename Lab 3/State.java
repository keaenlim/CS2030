import java.util.Optional;
import java.util.Iterator;
import java.util.stream.Stream;

class State {
    private final PQ<Event> events;
    protected final Shop shop;
    private final String output;
    private final boolean finished;


    State(PQ<Event> events, Shop shop) {
        this.events = events;
        this.shop = shop;
        this.output = "";
        this.finished = false;
    }

    // Private constructor
    private State(PQ<Event> events, Shop s, String output, boolean finished) {
        this.events = events;
        this.shop = s;
        this.output = output;
        this.finished = finished;
    }

    State next() {
        /*
        // poll for the next event from PQ
        Pair<Optional<Event>, PQ<Event>> polled = events.poll();
        Optional<Event> nextEvent = polled.t();         // Get the next event from the Pair

        if (nextEvent.isEmpty()) {
            return new State(events, shop, Optional.empty(), false); 
        }

        Event currentEvent = nextEvent.get();
        Pair<Event, Shop> result = currentEvent.next(shop);

        // Make the next event become the current one
        PQ<Event> updatedEvents = polled.u();
        if (result.t() != null) {
            updatedEvents = updatedEvents.add(result.t());
        }

        boolean hasMoreEvents = !updatedEvents.isEmpty();

        // Return the updated state
        return new State(updatedEvents, result.u(), Optional.of(currentEvent), hasNextEvent);
        */


        return events.poll().t().map(event -> Optional.of(event.next(shop))
                .filter(pair -> event.hasNextEvent()).map(pair -> new 
                    State(events.poll().u().add(pair.t()), pair.u(),
                        event.toString(), false)).orElseGet(() -> 
                            new State(events.poll().u(), shop, event.toString(),
                                false))).orElse(new State(new PQ<Event>(), this.shop, "",true));
    }

    boolean isEmpty() {
        return events.isEmpty() && this.finished;
    }
        
    public String toString() {
        return output;
    }
}
