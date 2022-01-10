import java.util.Random;

public class BankAccount {
	private Customer holder;
	private double balance;
	private int accountNr;
	private Random rand = new Random();

	public BankAccount(String holderName, long holderId) {

		holder = new Customer(holderName, holderId);
		String s = "8300" + Integer.toString(rand.nextInt(10000));
		accountNr = Integer.parseInt(s);
		balance = 0;
	}

	public BankAccount(Customer holder) {
		this.holder = holder;
		String s = "8300" + Integer.toString(rand.nextInt(10000));
		accountNr = Integer.parseInt(s);
		balance = 0;
	}
	
	public BankAccount(String holderName, long holderId, double balance, int accountNr) {
		holder = new Customer(holderName, holderId);
		
	}

	public Customer getHolder() {
		return holder;
	}

	public int getAccountNumber() {
		return accountNr;
	}

	public double getBalance() {
		return balance;
	}

	public boolean deposit(double amount) {
		if (amount > 0) {
			balance += amount;
			return true;
		} else {
			System.out.println(
					"\nThe amount you tried to deposit cannot be processed correctly." + "\nPlease try again\n");
			return false;
		}

	}

	public boolean withdraw(double amount) {
		if (amount > 0) {
			balance -= amount;
			return true;
		} else {
			System.out.println(
					"\nThe amount you tried to withdraw cannot be processed correctly." + "\nPlease try again\n");
			return false;
		}

	}

	@Override
	public String toString() {
		String s = "Customer: " + holder.getName() + "\nAccountNr: " + accountNr + "\nBalance: " + balance;
		return s;
	}

}
