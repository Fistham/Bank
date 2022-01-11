import java.util.ArrayList;

public class Bank {
	private ArrayList<BankAccount> accounts;

	public Bank() {
		accounts = new ArrayList<BankAccount>();
	}

	public Bank(ArrayList<BankAccount> accounts) {
		this.accounts = new ArrayList<BankAccount>();
		for (int i = 0; i < accounts.size(); i++) {
			this.accounts.add(accounts.get(i));
		}
	}

	public long addAccount(String holderName, long idNr) {
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

	public boolean removeAccount(int accNumber) {
		for (int i = 0; i < accounts.size(); i++) {
			if (accounts.get(i).getAccountNumber() == accNumber) {
				accounts.remove(i);
				return true;
			}
		}
		return false;
	}

	public ArrayList<BankAccount> getAllAccounts() {
		return accounts;
	}

	public BankAccount findByNumber(int number) {
		for (int i = 0; i < accounts.size(); i++) {
			if (accounts.get(i).getAccountNumber() == number) {
				return accounts.get(i);
			}
		}
		return null;
	}

	public ArrayList<BankAccount> findAccountsForHolder(long idNr) {
		ArrayList<BankAccount> holderAccounts = new ArrayList<BankAccount>();
		for (int i = 0; i < accounts.size(); i++) {
			if (accounts.get(i).getHolder().getIdNr() == idNr) {
				holderAccounts.add(accounts.get(i));
			}
		}

		return holderAccounts;
	}

	public ArrayList<Customer> findByPartofName(String namePart) {
		ArrayList<Customer> customerList = new ArrayList<Customer>();
		for (int i = 0; i < accounts.size(); i++) {
			if (accounts.get(i).getHolder().getName().toLowerCase().contains(namePart.toLowerCase())) {
				customerList.add(accounts.get(i).getHolder());
			}
		}
		return customerList;

	}

	public void addAccountsFromLedger() {
		ArrayList<BankAccount> ledgerAccounts = FileHandler.readFromFile();
		for (int i = 0; i < ledgerAccounts.size(); i++) {
			for (int j = 0; j < accounts.size(); j++) {
				if (accounts.get(j).equals(ledgerAccounts.get(i))) {

				}
			}
		}

//		if (accounts != null) {
//			return true;
//		} else {
//			return false;
//		}

	}

	public boolean addAccountsToLedger() {
		return FileHandler.writeToFile(getAllAccounts());
	}

}
