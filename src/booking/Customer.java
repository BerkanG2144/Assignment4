package booking;

/**
 * Represents a customer with a unique ID and name.
 * Two customers are considered equal if their first and last names match.
 * The customer ID is not considered in equality checks.
 *
 * @author ujnaa
 */
public final class Customer {

    private final int customerId;
    private final String firstName;
    private final String lastName;

    /**
     * Constructs a new Customer.
     *
     * @param customerId the unique ID of the customer
     * @param firstName the customer's first name
     * @param lastName the customer's last name
     */
    public Customer(int customerId, String firstName, String lastName) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Returns the customer ID.
     *
     * @return the customer ID
     */
    public int getCustomerId() {
        return customerId;
    }


    /**
     * Checks whether this customer equals another object.
     * Two customers are equal if their first and last names match.
     *
     * @param obj the object to compare with
     * @return true if the names are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Customer other)) {
            return false;
        }
        return this.firstName.equals(other.firstName) && this.lastName.equals(other.lastName);
    }

    /**
     * Returns a hash code based on the customer's name.
     *
     * @return the hash code
     */
    @Override
    public int hashCode() {
        return (firstName + lastName).hashCode();
    }
}
