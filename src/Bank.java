import java.util.LinkedList;
import java.util.Random;

public class Bank {
    // Instance variable
    private LinkedList<Account> accounts;

    public Bank() {
        accounts = new LinkedList<Account>();
    }

    Connector conn = new Connector();
    public void addAccount(Account account) {
        accounts.add(account);
        System.out.println("Account added successfully.");
    }

    public void addDB(Account account){
        String accountNumber = account.getAccountnumber();
        String password = account.getPassword();
        String username = account.getUsername();
        int balance = account.getBalance();

        conn.addAccount(accountNumber, password, username, balance);
    }

    public void deleteAccount(Account account){
        // Delete account yang ada di Database
        String accountNumber = account.getAccountnumber();
        conn.deleteAccount(accountNumber);

        // Remove dari LinkedList
        accounts.remove(account);
        System.out.println("Account removed successfully");
    }

    public void updateAccount(String number, String newName, int newBalance) {
        for (int i = 0; i < accounts.size(); i++) {
            Account current = accounts.get(i);

            if (current.getAccountnumber().equals(number)) {
                current.setUsername(newName);
                current.setBalance(newBalance);
                System.out.println("Account updated successfully.");
                return;
            }
        }
        System.out.println("Account not found. Update failed.");
    }

    public void deposit(Account account, int amount) {
        if (amount > 0) {
            account.setBalance(account.getBalance() + amount);
            System.out.println("Deposit successful. New balance: " + account.getBalance());
        } else {
            System.out.println("Invalid amount. Deposit failed.");
        }
    }

    public void withdraw(Account account, int amount) {
        if (amount > 0 && amount <= account.getBalance()) {
            account.setBalance(account.getBalance() - amount);
            System.out.println("Withdraw successful. New balance: " + account.getBalance());
        } else {
            System.out.println("Invalid amount or insufficient funds. Withdrawal failed.");
        }
    }

    public void transferMoney(Account sender, Account receiver, int amount) {
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

    public void displayAccountNumber() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts found.");
            return;
        }

        for (int i = 0; i < accounts.size(); i++) {
            System.out.println((i+1) + ". " + accounts.get(i).getAccountnumber());
        }
    }

    public String setAccountNumber() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        // Generate random digits for the account number
        for (int i = 0; i < 8; i++) {
            int digit = random.nextInt(10);
            sb.append(digit);
        }

        return sb.toString();
    }

    public int getAccountTotals(){
        return this.accounts.size();
    }

    public LinkedList listAccount(){
        return accounts;
    }

}
