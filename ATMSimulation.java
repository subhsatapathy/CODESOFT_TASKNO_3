import java.util.Scanner;

// ==========================================
// Bank Account Class
// ==========================================
class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        if (initialBalance >= 0) {
            this.balance = initialBalance;
        } else {
            this.balance = 0.0;
        }
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }
}

// ==========================================
// ATM Class
// ==========================================
class ATM {
    private BankAccount account;
    private Scanner scanner;

    public ATM(BankAccount account) {
        this.account = account;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        int choice = 0;
        do {
            System.out.println("\n==============================");
            System.out.println("       WELCOME TO THE ATM      ");
            System.out.println("==============================");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Exit");
            System.out.print("Choose an option (1-4): ");

            String input = scanner.nextLine();
            
            try {
                choice = Integer.parseInt(input.trim());
                switch (choice) {
                    case 1:
                        checkBalance();
                        break;
                    case 2:
                        depositMoney();
                        break;
                    case 3:
                        withdrawMoney();
                        break;
                    case 4:
                        System.out.println("\nThank you for using our ATM. Goodbye!");
                        break;
                    default:
                        System.out.println("\n[Error] Invalid option. Please select a number between 1 and 4.");
                        pressEnterToContinue();
                }
            } catch (NumberFormatException e) {
                System.out.println("\n[Error] Invalid input. Please enter a valid number.");
                pressEnterToContinue();
            }
        } while (choice != 4);
    }

    // Helper method to pause so the user can read messages
    private void pressEnterToContinue() {
        System.out.print("\nPress Enter to continue...");
        scanner.nextLine();
    }

    public void checkBalance() {
        System.out.println("\n--- Balance Inquiry ---");
        System.out.printf("Current Balance: $%.2f%n", account.getBalance());
        pressEnterToContinue(); // Pauses so you can read the balance
    }

    public void depositMoney() {
        System.out.println("\n--- Deposit Money ---");
        System.out.print("Enter amount to deposit: $");
        
        String input = scanner.nextLine();
        try {
            double amount = Double.parseDouble(input.trim());
            if (amount > 0) {
                account.deposit(amount);
                System.out.printf("[Success] Successfully deposited $%.2f%n", amount);
                System.out.printf("Updated Balance: $%.2f%n", account.getBalance());
            } else {
                System.out.println("[Error] Deposit amount must be greater than zero.");
            }
        } catch (NumberFormatException e) {
            System.out.println("[Error] Invalid amount format.");
        }
        pressEnterToContinue();
    }

    public void withdrawMoney() {
        System.out.println("\n--- Withdraw Money ---");
        System.out.print("Enter amount to withdraw: $");
        
        String input = scanner.nextLine();
        try {
            double amount = Double.parseDouble(input.trim());
            if (amount > 0) {
                boolean success = account.withdraw(amount);
                if (success) {
                    System.out.printf("[Success] Please take your cash. Successfully withdrew $%.2f%n", amount);
                    System.out.printf("Remaining Balance: $%.2f%n", account.getBalance());
                } else {
                    System.out.println("[Error] Transaction failed: Insufficient balance.");
                    System.out.printf("Your current balance is $%.2f%n", account.getBalance());
                }
            } else {
                System.out.println("[Error] Withdrawal amount must be greater than zero.");
            }
        } catch (NumberFormatException e) {
            System.out.println("[Error] Invalid amount format.");
        }
        pressEnterToContinue();
    }
}

// ==========================================
// Main Application Class
// ==========================================
public class ATMSimulation {
    public static void main(String[] args) {
        BankAccount myAccount = new BankAccount(1000.00);
        ATM myAtm = new ATM(myAccount);
        myAtm.start();
    }
}
