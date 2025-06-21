package booking;


public record Customer(int customerId, String firstName, String lastName) {

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Customer other)) return false;
        return this.firstName.equals(other.firstName) && this.lastName.equals(other.lastName);
    }

    @Override
    public int hashCode() {
        return (firstName + lastName).hashCode();
    }
}
