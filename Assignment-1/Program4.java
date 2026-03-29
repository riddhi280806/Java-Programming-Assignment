public class BankAccount {
    // Instance variables are marked private to enforce encapsulation
    private String accountNumber;
    private String accountHolderName;
    private double balance;

    /**
     * Constructor to initialize a new bank account.
     */
    public BankAccount(String accountNumber, String accountHolderName, double initialBalance) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        
        // Ensure the starting balance is not negative
        if (initialBalance >= 0) {
            this.balance = initialBalance;
        } else {
            System.out.println("Initial balance cannot be negative. Setting balance to 0.0");
            this.balance = 0.0;
        }
    }

    /**
     * Deposits the specified amount into the account.
     */
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.printf("Successfully deposited: $%.2f%n", amount);
        } else {
            System.out.println("Deposit amount must be greater than zero.");
        }
    }

    /**
     * Withdraws the specified amount from the account if funds are sufficient.
     */
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be greater than zero.");
        } else if (amount > balance) {
            System.out.println("Transaction failed: Insufficient funds.");
        } else {
            balance -= amount;
            System.out.printf("Successfully withdrew: $%.2f%n", amount);
        }
    }

    /**
     * Balance inquiry: Returns the current account balance.
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Displays standard information about the account.
     */
    public void displayAccountInfo() {
        System.out.println("\n--- Account Details ---");
        System.out.println("Holder: " + accountHolderName);
        System.out.println("Account #: " + accountNumber);
        System.out.printf("Current Balance: $%.2f%n", balance);
        System.out.println("-----------------------\n");
    }
}
