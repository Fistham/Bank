import java.util.Random;

public class Customer {
    private String name;
    private long idNr;
    private int customerNr;
    private Random rand = new Random();

    public Customer(String name, long idNr) {
        this.name = name;
        this.idNr = idNr;
        String customerNrString = Integer.toString(5525) + Integer.toString(rand.nextInt(100001));
        customerNr = Integer.parseInt(customerNrString);
    }

    public String getName() {
        return name;
    }

    public long getIdNr() {
        return idNr;
    }

    public long getCustomerNr() {
        return customerNr;
    }

    @Override
    public String toString() {
        String s = "Name: " + name + "\nSSN: " + idNr + "\nID:" + customerNr;
        return s;
    }

}

