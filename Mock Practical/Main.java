import java.util.List;
import java.util.stream.Stream;

void main() {}

List<Booking> findBestBooking(Request request, List<Driver> drivers) {
    return drivers.stream().flatMap(driver -> driver.getServices()
            .stream().map(service -> new Booking(driver, request, service))).sorted().toList();
}
    
