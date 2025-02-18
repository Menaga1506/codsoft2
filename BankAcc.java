import java.util.Scanner;
class BankAcc{
    private double balance;

    public BankAcc(double balance) {
        this.balance = balance;
    }
    public String deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            return "Successfully deposited $" + amount + ". New balance: $" + balance;
        } else {
            return "Deposit amount must be greater than zero.";
        }
    }     
    public String withdraw(double amount) {
        if (amount > 0) {
            if (balance >= amount) {
                balance -= amount;
                return "Successfully withdrew $" + amount + ". New balance: $" + balance;
            } else {
                return "Insufficient funds.";
            }
        } else {
            return "Withdrawal amount must be greater than zero.";
        }
    }

    
    public String checkBalance() {
        return "Current balance: $" + balance;
    }
}

class ATM {
    private BankAcc account;
    private Scanner scanner;

    public ATM(BankAcc account) {
        this.account = account;
        this.scanner = new Scanner(System.in);
    }

    // Display menu to the user
    public void displayMenu() {
        System.out.println("\nATM Menu:");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Exit");
    }

 
    public void processOption(int option) {
        switch (option) {
            case 1:
                System.out.println(account.checkBalance());
                break;
            case 2:
                System.out.print("Enter amount to deposit: ");
                double depositAmount = scanner.nextDouble();
                System.out.println(account.deposit(depositAmount));
                break;
            case 3:
                System.out.print("Enter amount to withdraw: ");
                double withdrawAmount = scanner.nextDouble();
                System.out.println(account.withdraw(withdrawAmount));
                break;
            case 4:
                System.out.println("Exiting. Thank you for using the ATM!");
                break;
            default:
                System.out.println("Invalid option, please try again.");
        }
    }

    // Start the ATM
    public void start() {
        int option;
        while (true) {
            displayMenu();
            try {
                System.out.print("Choose an option: ");
                option = scanner.nextInt();
                if (option == 4) {
                    break;
                }
                processOption(option);
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Consume invalid input
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        // Create a bank account with an initial balance of $1000
        BankAcc userAccount = new BankAcc(1000);
        ATM atm = new ATM(userAccount);
        atm.start();
    }
}
