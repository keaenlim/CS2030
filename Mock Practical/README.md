# 🚖 Cab Booking System

## 📝 Problem Description

This project is a multi-level Java implementation of a cab booking system where different ride services compute fares based on various parameters such as distance, time of service, number of passengers, and service type.

## 📌 Levels of Implementation

### Level 1: JustRide Service
- **Fare Calculation:**
  - Base fare: 22 cents per km
  - No booking fee
  - Peak hour surcharge (600 hrs - 900 hrs): 500 cents
- **Method:** `computeFare(int distance, int pax, int time)`

#### Example Usage:
```sh
jshell> new JustRide().computeFare(20, 3, 1000)
$.. ==> 440

jshell> new JustRide().computeFare(10, 1, 900)
$.. ==> 720
```

### Level 2: Request Class
- Encapsulates distance, number of passengers, and time.
- Computes fare based on the selected service.

#### Example Usage:
```sh
jshell> new Request(20, 3, 1000).computeFare(new JustRide())
$.. ==> 440
```

### Level 3: TakeACab Service
- **Fare Calculation:**
  - Base fare: 33 cents per km
  - Fixed booking fee: 200 cents
  - No peak hour surcharge

#### Example Usage:
```sh
jshell> new TakeACab().computeFare(20, 3, 1000)
$.. ==> 860
```

### Level 4: NormalCab Driver and Booking System
- Introduces **NormalCab** drivers with a license plate number and waiting time.
- NormalCab drivers offer **JustRide** and **TakeACab** services.
- **Booking class** compares fares between services and selects the best option.
- Implements `Comparable<Booking>` to sort bookings based on fare and waiting time.

#### Example Usage:
```sh
jshell> new NormalCab("SHA1234", 5)
$.. ==> SHA1234 (5 mins away) NormalCab

jshell> new Booking(new NormalCab("SHA1234", 5), new Request(20, 3, 1000))
.. ==> $4.40 using SHA1234 (5 mins away) NormalCab (JustRide)
```

### Level 5: ShareARide Service
- **Fare Calculation:**
  - Base fare: 50 cents per km
  - Peak hour surcharge (600 hrs - 900 hrs): 500 cents
  - Fare is divided equally among passengers

- Introduces **PrivateCar** drivers who provide **JustRide** and **ShareARide** services.

#### Example Usage:
```sh
jshell> new Booking(new PrivateCar("SMA7890", 5), new Request(20, 3, 1000))
.. ==> $3.33 using SMA7890 (5 mins away) PrivateCar (ShareARide)
```

### Level 6: Best Booking Selection
- Defines `findBestBooking(Request request, List<Driver> drivers)`
- Sorts bookings by lowest fare, breaking ties by waiting time.

#### Example Usage:
```sh
jshell> findBestBooking(new Request(20, 3, 1000), List.of(new NormalCab("SHA1234", 5), new PrivateCar("SMA7890", 10)))
$3.33 using SMA7890 (10 mins away) PrivateCar (ShareARide)
$4.40 using SHA1234 (5 mins away) NormalCab (JustRide)
```

### Installation
1. Clone the repository:
   ```sh
   git clone https://github.com/keaenlim/CS2030.git
   ```
2. Navigate to the project directory:
   ```sh
   cd CS2030/'Mock Practical'
   ```
3. Compile the files:
   ```sh
   javac --release 21 --enable-preview *.java
   ```
4. Run the Main.java file:
   ```sh
   java Main
   ```

## 🚀 Technologies Used
- **Java** for core logic and implementation
- **JShell** for interactive testing

## 💡 Acknowledgments
- Designed to simulate cab booking logic with multiple services.

## 📬 Contact
For any questions or suggestions, feel free to reach out:
- GitHub: [keaenlim](https://github.com/keaenlim)
- Email: keaen.lim@gmail.com
