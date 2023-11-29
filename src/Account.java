/**
 * Names: Nicole Edoziem, Wolf Pickens
 * Course: CMS 270 Object-Oriented Design and Development
 * Assignment: Assignment 2 Inheritance and Polymorphism
 * Date: 
 *
 */
public abstract class Account {
	//Data members
	private int number;
	private String owner;
	private double balance;

	//Constructor
	public Account(int number, String owner, double balance) {
		this.number = number;
		this.owner = owner;
		this.balance = balance;
	}

	//Getters and Setters
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	protected abstract void withdraw(double amt);
	protected abstract void transfer(double amt);
	protected abstract void deposit(double amt);
	protected abstract void close();

}
