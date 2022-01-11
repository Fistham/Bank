import java.util.Random;

public class BankAccount {
	private Customer holder;
	private double balance;
	private int accountNr;
	private Random rand = new Random();

	/**
	 * Constructor for when the customer does not exist.
	 * 
	 * @param holderName
	 * @param holderId
	 */
	public BankAccount(String holderName, long holderId) {

		holder = new Customer(holderName, holderId);
		String s = "8300" + Integer.toString(rand.nextInt(10000));
		accountNr = Integer.parseInt(s);
		balance = 0;
	}

	/**
	 * Constructor for when a customer exists.
	 * 
	 * @param holder
	 */
	public BankAccount(Customer holder) {
		this.holder = holder;
		String s = "8300" + Integer.toString(rand.nextInt(10000));
		accountNr = Integer.parseInt(s);
		balance = 0;
	}

	/**
	 * Constructor for FileHandlers reader from Ledger.txt
	 * 
	 * @param holderName
	 * @param holderId
	 * @param balance
	 * @param accountNr
	 */
	public BankAccount(String holderName, long holderId, double balance, int accountNr) {
		holder = new Customer(holderName, holderId);
		this.balance = balance;
		this.accountNr = accountNr;
	}

	/**
	 * Gets holder (customer).
	 * 
	 * @return holder/customer
	 */
	public Customer getHolder() {
		return holder;
	}

	/**
	 * Gets account number.
	 * 
	 * @return accountNr
	 */
	public int getAccountNumber() {
		return accountNr;
	}

	/**
	 * Gets account balance.
	 * 
	 * @return balance
	 */
	public double getBalance() {
		return balance;
	}

	/**
	 * Deposits amount in account.
	 * 
	 * @param amount
	 * @return true if deposit successful
	 */
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

	/**
	 * Withdraws amount from account.
	 * 
	 * @param amount
	 * @return true if withdraw successful
	 */
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

//	@Override
//	public boolean equals(Object obj) {
//		if(obj instanceof BankAccount) {
//			
//		}else {
//			
//		}
//		return false;
//	}

}
