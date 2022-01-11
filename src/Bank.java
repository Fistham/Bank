import java.util.ArrayList;
import java.util.Collections;

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

	public int addAccountsFromLedger() {
		ArrayList<BankAccount> ledgerAccounts = FileHandler.readFromFile();
		ArrayList<BankAccount> diffAccounts = new ArrayList<BankAccount>();

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

	public boolean addAccountsToLedger() {
		return FileHandler.writeToFile(getAllAccounts());
	}

}
