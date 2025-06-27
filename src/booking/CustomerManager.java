package booking;

import java.util.HashMap;
import java.util.Map;

/**
 * Manages customer records and assigns unique customer IDs.
 *
 * Each unique combination of first and last name corresponds to one customer ID.
 * Customer equality is based solely on first and last name.
 *
 * @author ujnaa
 */
public class CustomerManager {

    private final Map<Customer, Integer> customers = new HashMap<>();
    private int nextId = 1;

    /**
     * Returns the existing customer ID if the customer already exists,
     * or assigns a new one otherwise.
     *
     * @param firstName the customer's first name
     * @param lastName the customer's last name
     * @return the unique customer ID
     */
    public int getOrAddCustomerId(String firstName, String lastName) {
        Customer customer = new Customer(0, firstName, lastName); // temporary ID
        if (customers.containsKey(customer)) {
            return customers.get(customer);
        } else {
            int id = nextId++;
            customers.put(new Customer(id, firstName, lastName), id);
            return id;
        }
    }

    /**
     * Returns the {@code Customer} instance for the given name,
     * or {@code null} if no such customer exists.
     *
     * @param firstName the customer's first name
     * @param lastName the customer's last name
     * @return the Customer object or null
     */
    public Customer getCustomer(String firstName, String lastName) {
        for (Customer c : customers.keySet()) {
            if (c.getFirstName().equals(firstName) && c.getLastName().equals(lastName)) {
                return c;
            }
        }
        return null;
    }
}
