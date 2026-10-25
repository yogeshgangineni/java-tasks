import java.util.Scanner;

class InvalidWithdrawalAmountException extends Exception {
    InvalidWithdrawalAmountException(String message) {
        super(message);
    }
}

class InsufficientBalanceException extends Exception {
    InsufficientBalanceException(String message) {
        super(message);
    }
}

class WithdrawalLimitExceededException extends Exception {
    WithdrawalLimitExceededException(String message) {
        super(message);
    }
}

class InvalidDepositAmountException extends Exception {
    InvalidDepositAmountException(String message) {
        super(message);
    }
}

// Bank account class
class BankAccount {
    String accountNumber;
    String holderName;
    double balance;
    double maxLimit;

    int successful = 0;
    int unsuccessful = 0;

    BankAccount(String number, String name,
                double balance, double limit) {
        accountNumber = number;
        holderName = name;
        this.balance = balance;
        maxLimit = limit;
    }

    void withdraw(double amount)
            throws InvalidWithdrawalAmountException,
                   InsufficientBalanceException,
                   WithdrawalLimitExceededException {

        if (amount <= 0) {
            throw new InvalidWithdrawalAmountException(
                "Amount must be greater than zero.");
        }

        if (amount > balance) {
            throw new InsufficientBalanceException(
                "Insufficient account balance.");
        }

        if (amount > maxLimit) {
            throw new WithdrawalLimitExceededException(
                "Withdrawal limit exceeded.");
        }

        // Update balance only after all validations
        balance -= amount;
        successful++;

        System.out.println("Withdrawal successful!");
        System.out.println("Withdrawn: Rs. " + amount);
        System.out.println("Balance: Rs. " + balance);
    }

    void deposit(double amount)
            throws InvalidDepositAmountException {

        if (amount <= 0) {
            throw new InvalidDepositAmountException(
                "Deposit amount must be greater than zero.");
        }

        balance += amount;
        successful++;

        System.out.println("Deposit successful!");
        System.out.println("Deposited: Rs. " + amount);
        System.out.println("Balance: Rs. " + balance);
    }

    void showSummary() {
        System.out.println("\n----- ACCOUNT SUMMARY -----");
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Holder: " + holderName);
        System.out.println("Final Balance: Rs. " + balance);
        System.out.println("Successful Transactions: " + successful);
        System.out.println("Unsuccessful Transactions: " + unsuccessful);
    }
}

// Main class
public class BankTransactionSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter account number: ");
        String number = sc.nextLine();

        System.out.print("Enter account holder name: ");
        String name = sc.nextLine();

        System.out.print("Enter initial balance: ");
        double balance = sc.nextDouble();

        System.out.print("Enter maximum withdrawal limit: ");
        double limit = sc.nextDouble();

        if (balance < 0 || limit < 0) {
            System.out.println("Balance and limit cannot be negative.");
            sc.close();
            return;
        }

        BankAccount account =
            new BankAccount(number, name, balance, limit);

        int choice;

        do {
            System.out.println("\n1. Withdraw");
            System.out.println("2. Deposit");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            if (choice == 1) {
                System.out.print("Enter withdrawal amount: ");
                double amount = sc.nextDouble();

                try {
                    account.withdraw(amount);
                } catch (InvalidWithdrawalAmountException |
                         InsufficientBalanceException |
                         WithdrawalLimitExceededException e) {
                    account.unsuccessful++;
                    System.out.println("Transaction failed: "
                                       + e.getMessage());
                }

            } else if (choice == 2) {
                System.out.print("Enter deposit amount: ");
                double amount = sc.nextDouble();

                try {
                    account.deposit(amount);
                } catch (InvalidDepositAmountException e) {
                    account.unsuccessful++;
                    System.out.println("Transaction failed: "
                                       + e.getMessage());
                }

            } else if (choice != 3) {
                System.out.println("Invalid choice.");
            }

        } while (choice != 3);

        account.showSummary();
        sc.close();
    }
}