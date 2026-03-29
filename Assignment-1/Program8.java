// 1. The Custom Exception Class
// By extending Exception, we are creating a "Checked Exception", meaning 
// Java will force the programmer to handle it using a try-catch block.
class InsufficientFundsException extends Exception {
    private final double amountShortfall;

    /**
     * Constructor for our custom exception.
     * @param amountShortfall The exact amount the account is lacking.
     */
    public InsufficientFundsException(double amountShortfall) {
        // Pass a default error message to the parent Exception class
        super("Transaction failed: Insufficient funds.");
        this.amountShortfall = amountShortfall;
    }

    /**
     * A custom getter to retrieve the shortfall amount.
     */
    public double getAmountShortfall() {
        return amountShortfall;
    }
}

// 2. The Bank Account Class
class BankAccount {
    private String accountNumber;
    private double balance;

    public BankAccount(String accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    /**
     * Withdraws money. Notice the 'throws InsufficientFundsException' in the signature.
     * This warns any code calling this method that it might throw this specific error.
     */
    public void withdraw(double amount) throws InsufficientFundsException {
        System.out.printf("Attempting to withdraw: $%.2f%n", amount);
        
        if (amount > balance) {
            // Calculate how much they are short
            double shortfall = amount - balance;
            
            // "throw" physically triggers the error and stops the method's execution
            throw new InsufficientFundsException(shortfall);
        }
        
        // If we reach this line, no exception was thrown, so the transaction is safe
        balance -= amount;
        System.out.printf("Successfully withdrew: $%.2f. New balance: $%.2f%n", amount, balance);
    }
}

// 3. The Main Demonstration Class
public class CustomExceptionDemo {
    public static void main(String[] args) {
        System.out.println("=== Bank Withdrawal System ===");
        BankAccount myAccount = new BankAccount("123-ABC", 500.00);
        
        System.out.printf("Current Balance: $%.2f%n%n", myAccount.getBalance());

        // We MUST wrap the withdraw method in a try-catch block because 
        // InsufficientFundsException is a Checked Exception.
        try {
            // Valid withdrawal
            myAccount.withdraw(200.00);
            System.out.println();
            
            // Invalid withdrawal (This will trigger our custom exception)
            myAccount.withdraw(400.00);
            
            // This line will NEVER print because the exception above stops execution
            System.out.println("This will not be printed.");
            
        } catch (InsufficientFundsException e) {
            // Here we "catch" the error and handle it gracefully without crashing the program
            System.out.println("🛑 ERROR CAUGHT: " + e.getMessage());
            System.out.printf("You need an additional $%.2f to complete this transaction.%n", e.getAmountShortfall());
        } finally {
            // The finally block ALWAYS executes, whether an error occurred or not.
            // It is typically used for cleanup (like closing files or database connections).
            System.out.println("\n--- Thank you for using our bank. ---");
        }
    }
}
