import java.util.Scanner;

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        if (initialBalance >= 0) {
            this.balance = initialBalance;
        } else {
            System.out.println("Initial balance cannot be negative. Setting balance to 0.");
            this.balance = 0;
        }
    }

    public double getBalance() {
        return balance;
    }

    public boolean deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            return true;
        } else {
            System.out.println("Deposit amount must be positive.");
            return false;
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0) {
            if (balance >= amount) {
                balance -= amount;
                return true;
            } else {
                System.out.println("Insufficient balance for withdrawal.");
                return false;
            }
        } else {
            System.out.println("Withdrawal amount must be positive.");
            return false;
        }
    }
}

class ATM {
    private BankAccount account;
    private Scanner scanner;

    public ATM(BankAccount account) {
        this.account = account;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        boolean exit = false;
        while (!exit) {
            displayMenu();
            int choice = getUserChoice();
            switch (choice) {
                case 1:
                    performWithdrawal();
                    break;
                case 2:
                    performDeposit();
                    break;
                case 3:
                    displayBalance();
                    break;
                case 4:
                    exit = true;
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }

    private void displayMenu() {
        System.out.println("\nATM Main Menu:");
        System.out.println("1. Withdraw");
        System.out.println("2. Deposit");
        System.out.println("3. Check Balance");
        System.out.println("4. Exit");
    }

    private int getUserChoice() {
        System.out.print("Please select an option: ");
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. Please enter a number between 1 and 4: ");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private void performWithdrawal() {
        System.out.print("Enter amount to withdraw: ");
        double amount = getPositiveAmount();
        if (account.withdraw(amount)) {
            System.out.println("Withdrawal of Rs" + amount + " successful.");
        }
    }

    private void performDeposit() {
        System.out.print("Enter amount to deposit: ");
        double amount = getPositiveAmount();
        if (account.deposit(amount)) {
            System.out.println("Deposit of Rs" + amount + " successful.");
        }
    }

    private void displayBalance() {
        System.out.println("Your current balance is: Rs"2
        + account.getBalance());
    }

    private double getPositiveAmount() {
        double amount = -1;
        while (amount <= 0) {
            while (!scanner.hasNextDouble()) {
                System.out.print("Invalid input. Please enter a positive number: ");
                scanner.next();
            }
            amount = scanner.nextDouble();
            if (amount <= 0) {
                System.out.print("Amount must be positive. Please enter again: ");
            }
        }
        return amount;
    }
}

public class ATMInterface {
    public static void main(String[] args) {
        BankAccount userAccount = new BankAccount(1000.00); 
        ATM atm = new ATM(userAccount);
        atm.start();
    }
}
