import java.util.List;

class NormalCab implements Driver {
    private final String licensePlate;
    private final int passengerWaitTime; 

    NormalCab(String licensePlate, int passengerWaitTime) {
        this.licensePlate = licensePlate;
        this.passengerWaitTime = passengerWaitTime;
    }
    
    @Override
    public int getWaitTime() {
        return passengerWaitTime;
    }

    @Override
    public List<Service> getServices() {
        return List.of(new JustRide(), new TakeACab());
    }

    @Override
    public String toString() {
        return this.licensePlate + " (" + this.passengerWaitTime
            + " mins away) NormalCab";
    }
}
