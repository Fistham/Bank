import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class BankApplication {
	private Scanner scan = new Scanner(System.in);
	Bank bank = new Bank();

	public static void main(String[] args) {
		BankApplication bankApp = new BankApplication();
		bankApp.runApplication();
	}

	private void runApplication() {
		int choice;
		String ledger;
		System.out.println("Welcome to Jimbob Banking!\n");
		if (isLedgerEmpty()) {
			System.out.print("Would you like add accounts from ledger (y/n) : ");
			ledger = scan.nextLine();
			newLine();
			if (ledger.trim().toLowerCase().equals("y")) {
				bank.addAccountsFromLedger();
			}
		}
		while (true) {
			choice = 0;
			printMenu();
			try {
				choice = Integer.parseInt(scan.nextLine());
				newLine();
			} catch (NumberFormatException e) {
				System.out.println("\nThe option you have selected is invalid. Please try again!\n");
			}
			if (choice != 0) {
				switch (choice) {
				case 1:
					print(getCustomerAccounts());
					break;
				case 2:
					print(searchForCustomer());
					break;
				case 3:
					deposit();
					break;
				case 4:
					withdraw();
					break;
				case 5:
					transfer();
					break;
				case 6:
					createAccount();
					break;
				case 7:
					removeAccount();
					break;
				case 8:
					printAllAccounts();
					break;
				case 9:
//					if (bank.addAccountsFromLedger()) {
//						System.out.println("Read Successful!\n");
//					} else {
//						System.out.println("Read Unsuccessful!\n");
//					}
					break;
				case 10:
					if (bank.addAccountsToLedger()) {
						System.out.println("Write Successful!\n");
					} else {
						System.out.println("Write Unsuccessful!\n");
					}
					break;
				case 11:
					scan.close();
					quit();
					break;
				default:
					System.out.println("\nThe option you have selected is invalid. Please try again!\n");
					break;
				}
			}

		}
	}

	private boolean isLedgerEmpty() {
		if (FileHandler.readFromFile() != null) {
			return true;
		} else {
			return false;
		}
	}

	private ArrayList<BankAccount> getCustomerAccounts() {
		System.out.print("Please enter Customer SSN to access accounts: ");
		String idString;
		long id;
		try {
			idString = scan.nextLine();
			id = Long.parseLong(idString);
			newLine();
		} catch (NumberFormatException e) {
			System.out.println("\nThe Customer SSN you have added is not found in our database!\n");
			return null;
		}

		return bank.findAccountsForHolder(id);

	}

	private ArrayList<Customer> searchForCustomer() {
		System.out.print("Please enter Customer name or part of name: ");
		return bank.findByPartofName(scan.nextLine());
	}

	private void deposit() {
		double deposit;
		int accNr;
		System.out.print("Please enter Account Number where you want to deposit to: ");
		try {
			accNr = Integer.parseInt(scan.nextLine());
			newLine();
		} catch (NumberFormatException e) {
			System.out.println("You have entered an invalid account number. Returning to Main Menu.");
			return;
		}
		if (bank.findByNumber(accNr) != null) {
			System.out.print("Please enter amount to be deposited: ");
			try {
				deposit = Double.parseDouble(scan.nextLine());
				newLine();
			} catch (NumberFormatException e) {
				System.out.println("The amount you entered is invalid. Returning to Main Menu.");
				return;
			}
			if (bank.findByNumber(accNr).deposit(deposit)) {
				System.out.println("The deposit was successful!");
			} else {
				System.out.println("The deposit was unsuccessful!");
			}

		} else {
			System.out.println("The account does not exist. Returning to Main Menu.");
			return;
		}
	}

	private void withdraw() {
		double withdraw;
		int accNr;
		System.out.print("Please enter Account Number where you want to withdraw from: ");
		try {
			accNr = Integer.parseInt(scan.nextLine());
			newLine();
		} catch (NumberFormatException e) {
			System.out.println("You have entered an invalid account number. Returning to Main Menu.");
			return;
		}
		if (bank.findByNumber(accNr) != null) {
			System.out.print("Please enter amount to be withdrawn: ");
			try {
				withdraw = Double.parseDouble(scan.nextLine());
				newLine();
			} catch (InputMismatchException e) {
				System.out.println("The amount you entered is invalid. Returning to Main Menu.");
				return;
			}
			if (bank.findByNumber(accNr).withdraw(withdraw)) {
				System.out.println("The withdrawal was successful!");
			} else {
				System.out.println("The withdrawal was unsuccessful!");
			}

		} else {
			System.out.println("The account does not exist. Returning to Main Menu.");
			return;
		}
	}

	private void transfer() {
		double transfer;
		int accNrFrom, accNrTo;

		System.out.print("Please enter Account Number where you want to transfer from: ");
		try {
			accNrFrom = Integer.parseInt(scan.nextLine());
			newLine();
		} catch (InputMismatchException e) {
			System.out.println("You have entered an invalid account number. Returning to Main Menu.");
			return;
		}
		System.out.print("Please enter Account Number where you want to transfer to: ");
		try {
			accNrTo = Integer.parseInt(scan.nextLine());
			newLine();
		} catch (InputMismatchException e) {
			System.out.println("You have entered an invalid account number. Returning to Main Menu.");
			return;
		}
		if (bank.findByNumber(accNrTo) != null) {
			System.out.print("Please enter amount to be transferred: ");
			try {
				transfer = Double.parseDouble(scan.nextLine());
				newLine();
			} catch (InputMismatchException e) {
				System.out.println("The amount you entered is invalid. Returning to Main Menu.");
				return;
			}
			if (bank.findByNumber(accNrTo).deposit(transfer) && bank.findByNumber(accNrFrom).withdraw(transfer)) {
				System.out.println("The transfer was successful!");
			} else {

				System.out.println("The transfer was unsuccessful!");
			}
		} else {
			System.out.println("The account does not exist. Returning to Main Menu.");
			return;
		}

	}

	private void createAccount() {
		String holderName, choice;
		long idNr;
		System.out.print("Please confirm that you want to create an account (Y/N): ");
		choice = scan.nextLine();
		newLine();
		switch (choice.toLowerCase()) {
		case "y":
			System.out.print("\nPlease enter Customer Name: ");
			holderName = scan.nextLine();
			System.out.print("\nPlease enter Customer SSN: ");
			try {
				idNr = Long.parseLong(scan.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("The SSN you have entered is invalid. You will be returned to the Main Menu.");
				e.printStackTrace();
				return;
			}
			bank.addAccount(holderName, idNr);
			newLine();
			System.out.println("Your Account has been created successfully!");
			break;
		default:
			System.out.println("You will be returned to the Main Menu.");
			break;
		}

	}

	private void removeAccount() {
		int accNr;
		System.out.print("Please enter account number of the account you want removed: ");
		try {
			accNr = Integer.parseInt(scan.nextLine());
			newLine();
		} catch (NumberFormatException e) {
			System.out.println("The account number you have entered is invalid! Returning to Main Menu.");
			e.printStackTrace();
			return;
		}
		BankAccount temp = bank.findByNumber(accNr);
		if (temp != null) {
			newLine();
			System.out.print("Are you sure you want the account removed (Y/N): ");
			String choiceString = scan.nextLine();
			if (choiceString.toLowerCase().equals("y")) {
				if (bank.removeAccount(temp.getAccountNumber())) {
					System.out.println("\nRemoving account for " + temp.getHolder().getName() + "\nAccount number: "
							+ temp.getAccountNumber());
					System.out.println("\nRemoval successful. Returning to Main Menu.");
				} else {
					System.out.println("\nRemoval unsuccessful... Returning to Main Menu.");
					return;
				}

			} else {
				System.out.println("Returning to Main Menu.");
				return;
			}
		} else {
			System.out.println("\nThe account does not exist! Returning to the Main Menu.");
			return;
		}

	}

	private void printAllAccounts() {
		print(bank.getAllAccounts());
	}

	private static void quit() {
		System.out.println("\nThank You for using Jimbob Banking!\nHave a nice day.\n");
		System.exit(0);
	}

	private static void printMenu() {
		System.out.println("\nMENU\n" + "1  : Find Account(s) for Customer\n" + "2  : Search for Customer\n"
				+ "3  : Deposit\n" + "4  : Withdraw\n" + "5  : Transfer between Accounts\n" + "6  : Create Account\n"
				+ "7  : Remove Account\n" + "8  : Print all Accounts\n" + "9  : Read from ledger\n"
				+ "10 : Write to ledger\n" + "11 : Exit\n");
		System.out.print("Enter your choice: ");
	}

	private <E> void print(ArrayList<E> e) {
		if (!e.isEmpty()) {
			System.out.println("----------------------------------------\n");
			for (int i = 0; i < e.size(); i++) {
				System.out.println(e.get(i).toString());

				newLine();
			}
			System.out.println("----------------------------------------");
		} else {
			System.out.println("\nNo information was found! Please try again!");
		}
		newLine();
	}

	private static void newLine() {
		System.out.println();
	}

}
