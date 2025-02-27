class Server {
    private final int id;
    private final double time;

    Server(int id) {
        this.id = id;
        this.time = 0.0;
    }

    // Private constructor
    private Server(int id, double time) {
        this.id = id;
        this.time = time;
    }

    Server serve(Customer c, double time) {
        return new Server(this.id, c.serveTill(time));
    }

    boolean canServe(Customer c) {
        return c.canBeServed(this.time);
    }

    public String toString() {
        return "server " + this.id;
    }
 
    public boolean equals(Server s) {
        return this.id == s.id;
    }
}
