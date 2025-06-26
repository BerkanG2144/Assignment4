package booking;

import java.util.HashMap;
import java.util.Map;

public class CustomerManager {

    private final Map<Customer, Integer> customers = new HashMap<>();
    private int nextId = 1;

    public int getOrAddCustomerId(String firstName, String lastName) {
        Customer customer = new Customer(0, firstName, lastName); // ID erstmal egal
        if (customers.containsKey(customer)) {
            return customers.get(customer);
        } else {
            int id = nextId++;
            customers.put(new Customer(id, firstName, lastName), id);
            return id;
        }
    }

    public Customer getCustomer(String firstName, String lastName) {
        for (Customer c : customers.keySet()) {
            if (c.firstName().equals(firstName) && c.lastName().equals(lastName)) {
                return c;
            }
        }
        return null;
    }
}
