/**
 * Names: Nicole Edoziem, Wolf Pickens
 * Course: CMS 270 Object-Oriented Design and Development
 * Assignment: Assignment 2 Inheritance and Polymorphism
 * Date: 
 *
 */

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.ArrayList;

public class BatchProcessor {

	// Add the double Amt of money to the Account a
	public static Account processDeposit(Account a, double Amt) {
		
		// Deposits need no security check, so directly deposit the money
		a.deposit(Amt);
		return a;
		
	}
	
	// Subtract the double Amt of money from the Account a if the String owner matches the owner of Account a
	public static Account processWithdrawal(Account a, double Amt, String owner) {
		
		// Check if the given owner is the one on the account and deny the transaction if not
		if (a.getOwner().equals(owner)) {
			
			a.withdraw(Amt);
			return a;
			
		} else {
			
			System.out.println("Account " + Integer.toString(a.getNumber()) + " withdrawal denied. Withdrawals must be performed by the owner of the account.");
			return a;
			
		}
		
	}
	
	// Transfer the double Amt of money from Account a to Account b if the String owner matches the owner of Account a
	public static Account processTransfer(Account a, Account b, double Amt, String owner) {
		
		// Check if the given owner is the one on the account and deny the transaction if not
		if (a.getOwner().equals(owner)) {
			
			a.transfer(Amt);
			b.setBalance(b.getBalance() + Amt);
			return a;
			
		} else {
			
			System.out.println("Account " + Integer.toString(a.getNumber()) + " trasnfer denied. Transfers must be performed by the owner of the account.");
			return a;
			
		}
		
	}
	
	// Check if the close is done by the valid String owner and on a valid account and return a dummy account if true
	// Otherwise, return the original Account a
	public static Account processClose(Account a, String owner) {
		
		SavingsAccount dummyAcct = new SavingsAccount(-1, "Jane Doe", 0.00);
		
		// Check if the given owner is the one on the account and deny the close if not
		if (a.getOwner().equals(owner)) {
			
			// Check if the balance is valid and deny the close if not
			if (a.getBalance() >= 0) {
				
				a.close();
				return dummyAcct;
				
			} else {
				
				System.out.println("Account " + Integer.toString(a.getNumber()) + " close denied. An account must have a balance greater than or equal to zero to be closed.");
				return a;
				
			}
			
		} else {
			
			System.out.println("Account " + Integer.toString(a.getNumber()) + " close denied. Closings must be performed by the owner of the account.");
			return a;
			
		}
	
	}
	
	public static void main(String[] args) {
		
		// Create the internal list of accounts for the bank
		ArrayList<Account> accountsList = new ArrayList<Account>();
		
		// Try the file reading and, if unable to find Accounts.txt, send an error message
		try {
			
			// Import Accounts.txt and create a Scanner for it
			File accountsFile = new File("Accounts.txt");
			Scanner fileReader = new Scanner(accountsFile);
			
			// Process each line of Accounts.txt
			while (fileReader.hasNextLine()) {
				
				// Create a string array of the current line of the file
				String line = fileReader.nextLine();
				String[] words = line.split(" ");
				
				// Handle which account to create (and thus how long the line's string array should be) based on the account letter
				if (words[1].equals("C")) {
					
					// Convert each part of the String array to the needed data type and store them
					int tempAcctNumber = Integer.parseInt(words[0]);
					String tempName = (words[2] + " " + words[3]);
					double tempBalance = Double.parseDouble(words[4]);
					int tempLastCheckIssued = Integer.parseInt(words[5]);
					int tempLastCheckUsed = Integer.parseInt(words[6]);
					int tempMaxNumChecks = Integer.parseInt(words[7]);
					
					// Create a checking account with the stored String array data and add it to the accountsList ArrayList
					CheckingAccount a = new CheckingAccount(tempAcctNumber, tempName, tempBalance, tempLastCheckIssued, tempLastCheckUsed, tempMaxNumChecks);
					accountsList.add(a);
					
				} else if (words[1].equals("S")) {
					
					// Convert each part of the String array to the needed data type and store them
					int tempAcctNumber = Integer.parseInt(words[0]);
					String tempName = (words[2] + " " + words[3]);
					double tempBalance = Double.parseDouble(words[4]);
					
					// Create a savings account with the stored String array data and add it to the accountsList ArrayList
					SavingsAccount a = new SavingsAccount(tempAcctNumber, tempName, tempBalance);
					accountsList.add(a);
					
				} else {
					
					System.out.println("Invalid account type, unable to register into bank record.");
					
				}
				
			}
			
			// Close the Scanner
			fileReader.close();
			
		} catch (Exception e) {
			
			System.out.println("Unable to import bank accounts from Accounts.txt");
			
		}
		
		
		// Try the file reading and, if unable to find Batch.txt, send an error message
		try {
			
			// Import Accounts.txt and create a Scanner for it
			File batchFile = new File("Batch.txt");
			Scanner fileReader = new Scanner(batchFile);
			
			// Process each line of Batch.txt
			while (fileReader.hasNextLine()) {
				
				// Create a string array of the current line of the file
				String line = fileReader.nextLine();
				String[] words = line.split(" ");
				
				// Determine the transaction type and perform it
				if (words[0].equals("D")) {
					
					// Loop through the list of accounts until finding the account with the specified number
					for (int i = 0; i < accountsList.size(); i++) {
						
						// If account is found, process the deposit
						if (Integer.parseInt(words[1]) == accountsList.get(i).getNumber()) {
							
							processDeposit(accountsList.get(i), Double.parseDouble(words[2]));
							
						}
						
					}
					
				} else if (words[0].equals("W")) {
					
					// Loop through the list of accounts until finding the account with the specified number
					for (int i = 0; i < accountsList.size(); i++) {
						
						// If account is found, process the withdrawal
						if (Integer.parseInt(words[1]) == accountsList.get(i).getNumber()) {
							
							processWithdrawal(accountsList.get(i), Double.parseDouble(words[2]), (words[3] + " " + words[4]));
							
						}
						
					}
					
				} else if (words[0].equals("T")) {
					
					// Storage variable for the first account
					Account tempAcct;
					
					// Loop through the list of accounts until finding the first account
					for (int i = 0; i < accountsList.size(); i++) {
						
						// If account is found, store it in the temporary variable and carry out the transaction
						if (Integer.parseInt(words[1]) == accountsList.get(i).getNumber()) {
							
							tempAcct = accountsList.get(i);
							
							// Loop through the list of accounts until finding the account to be transferred to
							for (int a = 0; a < accountsList.size(); a++) {
								
								// If the second account is found, process the transfer
								if (Integer.parseInt(words[2]) == accountsList.get(a).getNumber()) {
									
									processTransfer(tempAcct, accountsList.get(a), Double.parseDouble(words[3]), (words[4] + " " + words[5]));
									
								}
								
							}
							
						}
						
					}
					
				} else if (words[0].equals("C")) {
					
					// Loop through the list of accounts until finding the account with the specified number
					for (int i = 0; i < accountsList.size(); i++) {
						
						// If account is found, process the close
						if (Integer.parseInt(words[1]) == accountsList.get(i).getNumber()) {
							
							if (processClose(accountsList.get(i), (words[2] + " " + words[3])).getNumber() == -1) {
								accountsList.remove(i);
							}
							
						}
						
					}
					
				} else {
					
					System.out.println("Invalid transfer type, unable to process.");
					
				}
				
			}
			
			// Close the Scanner
			fileReader.close();
				
		} catch (Exception e) {
			
			System.out.println("Unable to process transfers from Batch.txt");
			
		}
		
		// Re-write the Accounts.txt file to have the updated information
		try {
			
			// Create a File Writer
			FileWriter output = new FileWriter("Accounts.txt");
			
			// Loop through the accounts list and write each account's details
			for (int i = 0; i < accountsList.size(); i++) {
				
				// Check which account type each account is and print accordingly
				if (accountsList.get(i) instanceof CheckingAccount) {
					
					// Store checking account data in temporary variables for ease of printing
					String tempNumber = Integer.toString(accountsList.get(i).getNumber());
					String tempOwner = accountsList.get(i).getOwner();
					double tempBalance = accountsList.get(i).getBalance();
					String tempLastCheckIssued = Integer.toString(((CheckingAccount) accountsList.get(i)).getLastCheckIssued());
					String tempLastCheckUsed = Integer.toString(((CheckingAccount) accountsList.get(i)).getLastCheckUsed());
					String tempMaxNumChecks = Integer.toString(((CheckingAccount) accountsList.get(i)).getMaxNumChecks());
					
					// Write the checking account onto the file
					output.write(tempNumber + " C " + tempOwner + " " + String.format("%.2f", tempBalance) + " " + tempLastCheckIssued + " " + tempLastCheckUsed + " " + tempMaxNumChecks + " ");
					
				} else if (accountsList.get(i) instanceof SavingsAccount) {
					
					// Store savings account data in temporary variables for ease of printing
					String tempNumber = Integer.toString(accountsList.get(i).getNumber());
					String tempOwner = accountsList.get(i).getOwner();
					double tempBalance = accountsList.get(i).getBalance();
					
					// Write the checking account onto the file
					output.write(tempNumber + " S " + tempOwner + " " + String.format("%.2f", tempBalance) + " ");
					
				} else {
					
					output.write("Invalid Account ");
					
				}
				
				// Print a new line for proper formatting if it isn't the last account
				if (i != (accountsList.size() - 1)) {
					output.write("\n");
				}
				
			}
			
			// Close the File Writer
			output.close();
			
		} catch (Exception e) {
			
			System.out.println("Unable to write file.");
			
		}

	}

}