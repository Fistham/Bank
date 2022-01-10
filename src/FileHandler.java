

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
	 * rader (det som inträffar först). 
	 * Returnerar null om filen inte finns.
	 */
	public static ArrayList<BankAccount> readFromFile(String fileName) {
		Scanner scan;
		try {
			scan = new Scanner(new File("Ledger.txt"), "utf-8");
		} catch (FileNotFoundException e) {
			System.err.println("File not found" + fileName);
			e.printStackTrace();
			return null;
		}
		//Här kan du använda Scannern för att läsa från filen fileName.
		//Varje rad motsvarar en Applicant. Kontrollera vad Applicants konstruktor kräver
		//Alla Applicant-objekt (max nbrOfRows stycken) ska lagras i en Applicant-vektor och returneras på slutet
		return null; //Byt ut denna rad mot hela lösningen
	}
	
	public static void writeToFile(String fileName, ArrayList<BankAccount> list) {
		BufferedWriter writer;
		try {
			writer = new BufferedWriter(new FileWriter("Ledger.txt"));
			for(int i = 0; i < list.size(); i++) {
				writer.write(list.get(i).toString());
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
}
