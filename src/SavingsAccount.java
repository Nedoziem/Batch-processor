
public class SavingsAccount extends Account{

	//Data members
	private static final int MINBALANCE = 100;
	private static final int MAINTFEE = 25;
	private boolean hasCharged;

	//Constructor
	public SavingsAccount(int number, String owner, double balance) {
		super(number, owner, balance);
		hasCharged = false;

	}

	//Getters and Setters
	public void setHasCharged(boolean hasCharged) {
		this.hasCharged = hasCharged;
	}

	public static int getMinbalance() {
		return MINBALANCE;
	}

	public static int getMaintfee() {
		return MAINTFEE;
	}


	//Methods
	protected void withdraw(double amt) {
		double x = getBalance() - amt;
		setBalance(x);
		if ((getBalance() < MINBALANCE) && hasCharged == false) {
			setBalance(getBalance() - MAINTFEE);
			hasCharged = true;
		}
		System.out.println("Withdrawal successful!");
	}

	protected void transfer(double amt) {
		double y = getBalance() - amt;
		setBalance(y);
		if ((getBalance() < MINBALANCE) && (hasCharged == false)) {
			setBalance(getBalance() - MAINTFEE);
			hasCharged = true;
		}
		System.out.println("Transfer Successful!");

	}

	protected void deposit(double amt) {
		double d = getBalance() + amt;
		setBalance(d);
		System.out.println("Deposit Successful");

	}

	protected void close() {
		if (getBalance() >= 0) {
			System.out.println("Closed successfully!");
		}
		else {
			System.out.println("Failed to close account");
		}

	}
}
