
//import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {

	/**
	 * Returnerar max nbrOfRows rader från filen som en vektor av Applicant-objekt.
	 * Läser i filen tills det inte finns fler rader eller tills man läst nbrOfRows
	 * rader (det som inträffar först). Returnerar null om filen inte finns.
	 */
	public static ArrayList<BankAccount> readFromFile() {
		Scanner scan;
//		BufferedReader reader;

		// Try to read Ledger.txt
		try {
			scan = new Scanner(new File("Ledger.txt"));
		} catch (FileNotFoundException e) {
			System.err.println("File not found");
			e.printStackTrace();
			return null;
		}

		ArrayList<BankAccount> accounts = new ArrayList<BankAccount>();

		String name, idString, balanceString, accountNrString;
		double balance;
		long holderId;
		int accountNr;

		while (scan.hasNext()) {
			name = scan.next();

			// Try to parse SSN to long.
			try {
				idString = scan.next();
				holderId = Long.parseLong(idString);
			} catch (NumberFormatException e) {
				System.err.println("Could not parse SSN to long.");
				return null;
			}

			// Try to parse balance to double.
			try {
				balanceString = scan.next();
				balance = Double.parseDouble(balanceString);
			} catch (NumberFormatException e) {
				System.err.println("Could not parse balance to double.");
				return null;
			}

			// Try to parse account number to int.
			try {
				accountNrString = scan.next();
				accountNr = Integer.parseInt(accountNrString);
			} catch (NumberFormatException e) {
				System.err.println("Could not parse account number to int.");
				return null;
			}
			
			BankAccount curr = new BankAccount(name, holderId, balance, accountNr);

			accounts.add(curr);

		}
		scan.close();
		return accounts;
	}

	public static boolean writeToFile(ArrayList<BankAccount> list) {
		BufferedWriter writer;

		// Try to write to file
		try {
			writer = new BufferedWriter(new FileWriter("Ledger.txt"));
			for (int i = 0; i < list.size(); i++) {
				writer.write(list.get(i).getHolder().getName() + " " + list.get(i).getHolder().getIdNr() + " "
						+ list.get(i).getBalance() + " " + list.get(i).getAccountNumber() + "\n");
			}
			writer.close();
		} catch (IOException e) {
			System.err.println("Could not write to file.");
			e.printStackTrace();
			return false;
		}
		return true;

	}
}
