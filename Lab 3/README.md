# 🏪 Server and Customer Simulation

## 📝 Problem Description

This project implements a **discrete event simulation** for a server and customer system. Customers arrive at a shop, and based on server availability, they are either served or leave. Events are processed in a priority queue, ensuring an efficient and logical progression of the simulation.

## 📌 Levels of Implementation

### Level 1: Event System and Priority Queue
- Defines the **Event** and **ArriveEvent** classes.
- Uses a **Priority Queue (PQ)** to order and process events chronologically.
- Ensures that each customer arrival is represented as an event in the queue.

#### Example Usage:
```sh
jshell> PQ<Event> pq = new PQ<Event>().add(new ArriveEvent(new Customer(1, 1.0, 1.0), 1.0)).
   ...> add(new ArriveEvent(new Customer(2, 2.0, 1.0), 2.0))
pq ==> [1.0 customer 1 arrives, 2.0 customer 2 arrives]
```

### Level 2: Event Transitions
- Introduces **ServeEvent** and **LeaveEvent** based on server availability.
- Implements the `next()` method to determine whether a customer gets served or leaves.
- Updates the **Shop** and **Server** states accordingly.

#### Example Transitions:
```sh
jshell> new ArriveEvent(new Customer(1, 1.0, 1.0), 1.0).next(new Shop(2)).t()
$.. ==> 1.0 customer 1 serve by server 1

jshell> new ArriveEvent(new Customer(1, 1.0, 1.0), 1.0).next(new Shop(1)).t()
$.. ==> 1.0 customer 1 leaves
```

### Level 3: Processing Events in Sequence
- Defines a **State** class to manage simulation flow.
- Uses `Stream.iterate()` to process each event in order.
- Ensures correct event transitions from arrival to service completion.

#### Example Simulation:
```sh
jshell> Stream.iterate(new State(pq, new Shop(2)), 
   ...> state -> !state.isEmpty(), 
   ...> state -> state.next())
   ...> .map(state -> state.toString())
   ...> .forEach(System.out::println);

1.0 customer 1 arrives
1.0 customer 1 serve by server 1
2.0 customer 1 done
2.0 customer 2 arrives
2.0 customer 2 serve by server 1
3.0 customer 2 done
```

### Level 4: Simulator Class and Main Program
- Implements a **Simulator** class that manages the entire event sequence.
- Reads input from standard input and processes multiple customer requests.
- Outputs the step-by-step event log of the simulation.

#### Example Input & Output:
```sh
$ cat input.txt
3 3
1 0.5 1.0
2 0.6 1.0
3 0.7 1.0

$ cat input.txt | java --enable-preview Main
0.5 customer 1 arrives
0.5 customer 1 serve by server 1
0.6 customer 2 arrives
0.6 customer 2 serve by server 2
0.7 customer 3 arrives
0.7 customer 3 serve by server 3
1.5 customer 1 done
1.6 customer 2 done
1.7 customer 3 done
```
## 🔧 Setting Up
### Prerequisites
Ensure you have the following installed:
- Java Development Kit (JDK 21 or later)
- A terminal or command prompt

### Installation and Running the Simulation
1. **Clone the repository**:
   ```sh
   git clone https://github.com/keaenlim/'Lab 3'.git
   ```
2. **Navigate to the project directory**:
   ```sh
   cd 'Lab 3'
   ```
3. **Compile the Java files**:
   ```sh
   javac --release 21 --enable-preview *.java
   ```
4. **Run the program with an input file**:
   ```sh
   cat input.txt | java --enable-preview Main
   ```
5. **Alternatively, run interactively**:
   ```sh
   java --enable-preview Main
   ```
   Then manually input customer data as prompted.

## 🚀 Technologies Used
- **Java (SE 21)** for core logic and simulation
- **Streams & Functional Programming** for processing event sequences
- **Priority Queue (PQ.java)** for efficient event management

## 📜 License
This project is open-source and available under the MIT License.

## 💡 Acknowledgments
- Inspired by real-world queue management and discrete event simulation.

## 📬 Contact
For any questions or suggestions, feel free to reach out:
- GitHub: [your-username](https://github.com/your-username)
- Email: your-email@example.com
