import java.util.List;

class PrivateCar implements Driver { 
    private final String licensePlate;
    private final int passengerWaitTime;

    PrivateCar(String licensePlate, int passengerWaitTime) {
        this.licensePlate = licensePlate;
        this.passengerWaitTime = passengerWaitTime;
    }

    @Override
    public int getWaitTime() {
        return passengerWaitTime;
    }

    @Override 
    public List<Service> getServices() {
        return List.of(new JustRide(), new ShareARide());
    }

    @Override
    public String toString() {
        return this.licensePlate + " (" + this.passengerWaitTime + " mins away) PrivateCar";
    }
}
