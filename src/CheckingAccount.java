
public class CheckingAccount extends Account{

	//Data members
	private int lastCheckIssued;
	private int lastCheckUsed;
	private final int maxNumChecks;

	//Constructor
	public CheckingAccount(int number, String owner, double balance, int lastCheckIssued, int lastCheckUsed,
			int maxNumChecks) {
		super(number, owner, balance);
		this.lastCheckIssued = lastCheckIssued;
		this.lastCheckUsed = lastCheckUsed;
		this.maxNumChecks = maxNumChecks;
	}

	//Getters and Setters
	public int getLastCheckIssued() {
		return lastCheckIssued;
	}

	public void setLastCheckIssued(int lastCheckIssued) {
		this.lastCheckIssued = lastCheckIssued;
	}

	public int getLastCheckUsed() {
		return lastCheckUsed;
	}

	public void setLastCheckUsed(int lastCheckUsed) {
		this.lastCheckUsed = lastCheckUsed;
	}

	public int getMaxNumChecks() {
		return maxNumChecks;
	}



	//Methods
	protected void withdraw(double amt) {
		double temp = getBalance() - amt;
		setBalance(temp);
	}

	protected void transfer(double amt) {
		double t = getBalance() - amt;
		setBalance(t);
	}

	protected void deposit(double amt) {
		double c = getBalance() + amt;
		setBalance(c);
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
