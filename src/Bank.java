import java.util.LinkedList;

public class Bank {
    // Instance variable
    private LinkedList<Account> accounts;

    public Bank() {
        accounts = new LinkedList<Account>();
    }

    public void addAccount(Account account) {
        accounts.add(account);
        System.out.println("Account added successfully.");
    }

    public void deleteAccount(Account account){
        accounts.remove(account);
        System.out.println("Account removed successfully");
    }

    public void updateAccount(int number, String newName, double newBalance) {
        for (int i = 0; i < accounts.size(); i++) {
            Account current = accounts.get(i);

            if (current.getAccountnumber() == number) {
                current.setUsername(newName);
                current.setBalance(newBalance);
                System.out.println("Account updated successfully.");
                return;
            }
        }
        System.out.println("Account not found. Update failed.");
    }

    public void deposit(Account account, double amount) {
        if (amount > 0) {
            account.setBalance(account.getBalance() + amount);
            System.out.println("Deposit successful. New balance: " + account.getBalance());
        } else {
            System.out.println("Invalid amount. Deposit failed.");
        }
    }

    public void withdraw(Account account, double amount) {
        if (amount > 0 && amount <= account.getBalance()) {
            account.setBalance(account.getBalance() - amount);
            System.out.println("Withdraw successful. New balance: " + account.getBalance());
        } else {
            System.out.println("Invalid amount or insufficient funds. Withdrawal failed.");
        }
    }

    public void transferMoney(Account sender, Account receiver, double amount) {
        if (amount > 0 && amount <= sender.getBalance()) {
            sender.setBalance(sender.getBalance() - amount);
            receiver.setBalance(receiver.getBalance() + amount);
            System.out.println("Transfer successful. New balance: " + sender.getBalance());
        } else {
            System.out.println("Invalid amount or insufficient funds. Transfer failed.");
        }
    }

    public void displayAllAccounts() {

        if (accounts.isEmpty()) {
            System.out.println("No accounts found.");
            return;
        }

        for (Account account : accounts) {
            account.display();
            System.out.println();
        }

    }

}
