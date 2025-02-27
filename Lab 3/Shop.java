import java.util.Optional;
import java.util.List;
import java.util.stream.IntStream;

class Shop {
    private final List<Server> servers;

    Shop(int numOfServers) {
        if (numOfServers <= 0) {
            this.servers = List.of();
        } else {
            this.servers = IntStream.rangeClosed(1, numOfServers)
                                    .mapToObj(s -> new Server(s))
                                    .toList();
        }
    }

    Optional<Server> findServer(Customer c) {
        return this.servers.stream()
                           .filter(s -> s.canServe(c))
                           .findFirst();
    }

    private Shop(List<Server> servers) {
        this.servers = servers;
    }

    Shop update(Server s) {
        List<Server> updatedServers = this.servers.stream()
                                                 .map(server -> server.equals(s) ? s : server)
                                                 .toList();
        return new Shop(updatedServers);
    }

    public String toString() {
        return this.servers.stream()
                           .map(s -> s.toString())
                           .toList()
                           .toString();
    }
}
