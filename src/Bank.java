import java.util.ArrayList;

public class Bank {
	private ArrayList<BankAccount> accounts;

	/**
	 * Constructor for creating a Bank.
	 */
	public Bank() {
		accounts = new ArrayList<BankAccount>();
	}

	/**
	 * Constructor when reading accounts from file.
	 * 
	 * @param accounts
	 */
	public Bank(ArrayList<BankAccount> accounts) {
		this.accounts = new ArrayList<BankAccount>();
		for (int i = 0; i < accounts.size(); i++) {
			this.accounts.add(accounts.get(i));
		}
	}

	/**
	 * Adds account to holder/Customer.
	 * 
	 * @param holderName
	 * @param idNr
	 * @return account number
	 */
	public int addAccount(String holderName, long idNr) {
		BankAccount account;
		for (int i = 0; i < accounts.size(); i++) {
			if (holderName.toLowerCase().equals(accounts.get(i).getHolder().getName().toLowerCase())
					&& idNr == accounts.get(i).getHolder().getIdNr()) {
				account = new BankAccount(accounts.get(i).getHolder());
				accounts.add(account);
				return account.getAccountNumber();
			}
		}
		account = new BankAccount(holderName, idNr);
		accounts.add(account);
		return account.getAccountNumber();
	}

	/**
	 * Finds holder in Bank.
	 * 
	 * @param idNr
	 * @return Customer
	 */
	public Customer findHolder(long idNr) {
		Customer holder = null;
		for (int i = 0; i < accounts.size(); i++) {
			if (idNr == accounts.get(i).getHolder().getIdNr()) {
				holder = accounts.get(i).getHolder();
			} else {
				System.out.println("\nThe customer ID you have entered does not exist.");
			}
		}

		return holder;
	}

	/**
	 * Removes account with account number.
	 * 
	 * @param accNumber
	 * @return boolean
	 */
	public boolean removeAccount(int accNumber) {
		for (int i = 0; i < accounts.size(); i++) {
			if (accounts.get(i).getAccountNumber() == accNumber) {
				accounts.remove(i);
				return true;
			}
		}
		return false;
	}

	/**
	 * Gets all accounts in Bank object.
	 * 
	 * @return ArrayList<BankAccount>
	 */
	public ArrayList<BankAccount> getAllAccounts() {
		return accounts;
	}

	/**
	 * Finds account by account number.
	 * 
	 * @param number
	 * @return BankAccount
	 */
	public BankAccount findByNumber(int number) {
		for (int i = 0; i < accounts.size(); i++) {
			if (accounts.get(i).getAccountNumber() == number) {
				return accounts.get(i);
			}
		}
		return null;
	}

	/**
	 * Finds accounts for Customer with ID number/SSN
	 * 
	 * @param idNr
	 * @return ArrayList<BankAccount>
	 */
	public ArrayList<BankAccount> findAccountsForHolder(long idNr) {
		ArrayList<BankAccount> holderAccounts = new ArrayList<BankAccount>();
		for (int i = 0; i < accounts.size(); i++) {
			if (accounts.get(i).getHolder().getIdNr() == idNr) {
				holderAccounts.add(accounts.get(i));
			}
		}

		return holderAccounts;
	}

	/**
	 * Finds customers by searching for part of name.
	 * 
	 * @param namePart
	 * @return ArrayList<Customer>
	 */
	public ArrayList<Customer> findByPartofName(String namePart) {
		ArrayList<Customer> customerList = new ArrayList<Customer>();
		for (int i = 0; i < accounts.size(); i++) {
			if (accounts.get(i).getHolder().getName().toLowerCase().contains(namePart.toLowerCase())) {
				customerList.add(accounts.get(i).getHolder());
			}
		}
		return customerList;

	}

	/**
	 * Adds accounts from ledger.
	 * 
	 * @return int
	 */
	public int addAccountsFromLedger() {
		ArrayList<BankAccount> ledgerAccounts = FileHandler.readFromFile();

		if (ledgerAccounts.size() != 0) {
			if (ledgerAccounts.size() < getAllAccounts().size()) {
				addAccountsToLedger();
				return 2;
			} else if (ledgerAccounts.size() > getAllAccounts().size()) {
				accounts.clear();
				for (int i = 0; i < ledgerAccounts.size(); i++) {
					accounts.add(ledgerAccounts.get(i));
				}
				return 1;
			} else {
				if (ledgerAccounts.equals(getAllAccounts())) {
					return 0;
				} else {
					accounts.clear();
					for (int i = 0; i < ledgerAccounts.size(); i++) {
						accounts.add(ledgerAccounts.get(i));
					}
					return 1;
				}
			}
		} else {
			return -1;
		}

	}

	/**
	 * Gets the accounts that differ in ledger vs bank
	 * 
	 * @param ledgerAccounts
	 * @return ArrayList<BankAccount>
	 */
	private ArrayList<BankAccount> getAccountDiff(ArrayList<BankAccount> ledgerAccounts) {
		ArrayList<BankAccount> banksAccounts = accounts;
		ArrayList<BankAccount> ledgersAccounts = ledgerAccounts;
		ArrayList<BankAccount> diffAccounts = new ArrayList<BankAccount>();

		for (int i = 0; i < banksAccounts.size(); i++) {
			for (int j = 0; j < ledgersAccounts.size(); j++) {
				
				
				
				
				
				
				
				
				
				
				
				
				
			}
		}

		return diffAccounts;
	}

	/**
	 * Adds accounts to ledger.
	 * 
	 * @return boolean
	 */
	public boolean addAccountsToLedger() {
		return FileHandler.writeToFile(getAllAccounts());
	}

}
