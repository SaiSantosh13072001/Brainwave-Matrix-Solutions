import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AtmInterface {

    private static double balance = 100000.0; // Initial balance
    private static final int PIN = 1234; // Default PIN for authentication
    private static List<String> transactionHistory = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        if (!authenticateUser(scanner)) {
            System.out.println("Authentication failed. Exiting...");
            scanner.close();
            return;
        }

        int choice;
        do {
            displayMenu();
            System.out.print("Enter your choice: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.next();
            }
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    checkBalance();
                    break;
                case 2:
                    withdrawMoney(scanner);
                    break;
                case 3:
                    depositMoney(scanner);
                    break;
                case 4:
                    viewTransactionHistory();
                    break;
                case 5:
                    System.out.println("Thank you for using the ATM. Have a nice day! Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);

        scanner.close();
    }

    private static boolean authenticateUser(Scanner scanner) {
        System.out.print("Enter your 4-digit PIN: ");
        int enteredPin = scanner.nextInt();
        return enteredPin == PIN;
    }

    private static void displayMenu() {
        System.out.println("\nATM Menu:");
        System.out.println("1. Check Balance");
        System.out.println("2. Withdraw Money");
        System.out.println("3. Deposit Money");
        System.out.println("4. View Transaction History");
        System.out.println("5. Exit");
    }

    private static void checkBalance() {
        System.out.printf("Your current balance is: $%.2f%n", balance);
    }

    private static void withdrawMoney(Scanner scanner) {
        System.out.print("Enter the amount to withdraw: ");
        double amount = scanner.nextDouble();

        if (amount > balance) {
            System.out.println("Insufficient funds. Your balance is $" + balance);
        } else if (amount <= 0) {
            System.out.println("Invalid amount. Please enter a positive value.");
        } else {
            balance -= amount;
            transactionHistory.add("Withdrawn: $" + amount);
            System.out.printf("You have withdrawn $%.2f. Your new balance is $%.2f%n", amount, balance);
        }
    }

    private static void depositMoney(Scanner scanner) {
        System.out.print("Enter the amount to deposit: ");
        double amount = scanner.nextDouble();

        if (amount <= 0) {
            System.out.println("Invalid amount. Please enter a positive value.");
        } else {
            balance += amount;
            transactionHistory.add("Deposited: $" + amount);
            System.out.printf("You have deposited $%.2f. Your new balance is $%.2f%n", amount, balance);
        }
    }

    private static void viewTransactionHistory() {
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions found.");
        } else {
            System.out.println("Transaction History:");
            for (String transaction : transactionHistory) {
                System.out.println(transaction);
            }
        }
    }
}