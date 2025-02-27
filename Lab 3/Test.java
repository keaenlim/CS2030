import java.util.Scanner;
import java.util.stream.Stream;

public class Test {

    
    public static void main(String[] args) {
        // Test 1
        PQ<Event> test1 = new PQ<Event>()
                .add(new ArriveEvent(new Customer(1, 0.5, 1.0), 0.5))
                .add(new ArriveEvent(new Customer(2, 0.6, 1.0), 0.6))
                .add(new ArriveEvent(new Customer(3, 0.7, 1.0), 0.7));
        Shop shop1 = new Shop(3);
        // Test 2
        PQ<Event> test2 = new PQ<Event>()
                .add(new ArriveEvent(new Customer(1, 0.5, 1.0), 0.5))
                .add(new ArriveEvent(new Customer(2, 0.6, 1.0), 0.6))
                .add(new ArriveEvent(new Customer(3, 0.7, 1.0), 0.7))
                .add(new ArriveEvent(new Customer(4, 1.5, 1.0), 1.5))
                .add(new ArriveEvent(new Customer(5, 1.6, 2.0), 1.6))
                .add(new ArriveEvent(new Customer(6, 1.7, 3.0), 1.7));
        Shop shop2 = new Shop(3);
        // Test 3
        PQ<Event> test3 = new PQ<Event>()
                .add(new ArriveEvent(new Customer(1, 0.5, 1.0), 0.5))
                .add(new ArriveEvent(new Customer(2, 0.6, 1.0), 0.6))
                .add(new ArriveEvent(new Customer(3, 0.7, 1.0), 0.7));
        Shop shop3 = new Shop(2);
        // Test 4
        PQ<Event> test4 = new PQ<Event>()
                .add(new ArriveEvent(new Customer(1, 0.5, 1.0), 0.5))
                .add(new ArriveEvent(new Customer(2, 0.6, 1.0), 0.6))
                .add(new ArriveEvent(new Customer(3, 1.5, 1.0), 1.5));
        Shop shop4 = new Shop(2);
        // Test 5
        PQ<Event> test5 = new PQ<Event>()
                .add(new ArriveEvent(new Customer(1, 0.5, 1.1), 0.5))
                .add(new ArriveEvent(new Customer(2, 0.6, 0.9), 0.6))
                .add(new ArriveEvent(new Customer(3, 0.7, 0.7), 0.7))
                .add(new ArriveEvent(new Customer(4, 1.5, 0.1), 1.5))
                .add(new ArriveEvent(new Customer(5, 1.6, 0.2), 1.6))
                .add(new ArriveEvent(new Customer(6, 1.7, 0.3), 1.7));
        Shop shop5 = new Shop(2);

        State testState1 = new State(test1, shop1);
        State testState2 = new State(test2, shop2);
        State testState3 = new State(test3, shop3);
        State testState4 = new State(test4, shop4);
        State testState5 = new State(test5, shop5);

        // debug(testState1, "Test 1");
        // debug(testState2, "Test 2");
        // debug(testState3, "Test 3");
        // debug(testState4, "Test 4");
        // debug(testState5, "Test 5");
        
        test(testState1, "Test 1", 1);
        test(testState2, "Test 2", 2);
        test(testState3, "Test 3", 3);
        test(testState4, "Test 4", 4);
        test(testState5, "Test 5", 5);


    }

    public static void debug(State startState, String testName) {
        System.err.println(testName);
        Scanner sc = new Scanner(System.in);
        while (true) {
            // System.err.println(startState.pq); // Only if public it
            // System.err.println(startState.shop); // Only if u edit shop.toString()
            State temp = startState.next();
            if (temp.isEmpty()) {
                break;
            }
            startState = temp;
            System.err.println(startState);
            // sc.nextLine(); // Click enter to continue
        }
    }

    public static void test(State startState, String testName, int testid) {
        System.err.println("TESTING : " + testName);
        String value = Stream.<State>iterate(startState, state -> !state.isEmpty(),
                                           state -> state.next())
                     .map(state -> state.toString())
                     .filter(str -> !str.isEmpty())
                .reduce("", (x, y) -> x + y + "\n");
        if (value.equals(EXPRECTED_RESULT[testid - 1])) {
            System.err.println("Test " + (testid) + " Passed\n");
        } else {
            System.err.println("Test " + (testid) + " Failed\n");
        }
    }


    public static final String[] EXPRECTED_RESULT = {
        "0.5 customer 1 arrives\n" + 
        "0.5 customer 1 serve by server 1\n" + 
        "0.6 customer 2 arrives\n" + 
        "0.6 customer 2 serve by server 2\n" + 
        "0.7 customer 3 arrives\n" + 
        "0.7 customer 3 serve by server 3\n" + 
        "1.5 customer 1 done\n" + 
        "1.6 customer 2 done\n" + 
        "1.7 customer 3 done\n",
        
        "0.5 customer 1 arrives\n" +
        "0.5 customer 1 serve by server 1\n" +
        "0.6 customer 2 arrives\n" +
        "0.6 customer 2 serve by server 2\n" +
        "0.7 customer 3 arrives\n" +
        "0.7 customer 3 serve by server 3\n" +
        "1.5 customer 1 done\n" +
        "1.5 customer 4 arrives\n" +
        "1.5 customer 4 serve by server 1\n" +
        "1.6 customer 2 done\n" +
        "1.6 customer 5 arrives\n" +
        "1.6 customer 5 serve by server 2\n" +
        "1.7 customer 3 done\n" +
        "1.7 customer 6 arrives\n" +
        "1.7 customer 6 serve by server 3\n" +
        "2.5 customer 4 done\n" +
        "3.6 customer 5 done\n" +
        "4.7 customer 6 done\n",
                
        "0.5 customer 1 arrives\n" +
        "0.5 customer 1 serve by server 1\n" +
        "0.6 customer 2 arrives\n" +
        "0.6 customer 2 serve by server 2\n" +
        "0.7 customer 3 arrives\n" +
        "0.7 customer 3 leaves\n" +
        "1.5 customer 1 done\n" +
        "1.6 customer 2 done\n",
                
        "0.5 customer 1 arrives\n" +
        "0.5 customer 1 serve by server 1\n" +
        "0.6 customer 2 arrives\n" +
        "0.6 customer 2 serve by server 2\n" +
        "1.5 customer 1 done\n" +
        "1.5 customer 3 arrives\n" +
        "1.5 customer 3 serve by server 1\n" +
        "1.6 customer 2 done\n" +
        "2.5 customer 3 done\n",
                                
        "0.5 customer 1 arrives\n" +
        "0.5 customer 1 serve by server 1\n" +
        "0.6 customer 2 arrives\n" +
        "0.6 customer 2 serve by server 2\n" +
        "0.7 customer 3 arrives\n" +
        "0.7 customer 3 leaves\n" +
        "1.5 customer 2 done\n" +
        "1.5 customer 4 arrives\n" +
        "1.5 customer 4 serve by server 2\n" +
        "1.6 customer 1 done\n" +
        "1.6 customer 4 done\n" +
        "1.6 customer 5 arrives\n" +
        "1.6 customer 5 serve by server 1\n" +
        "1.7 customer 6 arrives\n" +
        "1.7 customer 6 serve by server 2\n" +
        "1.8 customer 5 done\n" +
        "2.0 customer 6 done\n"
    };
}
