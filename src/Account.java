import java.util.LinkedList;

public class Account {

    private int accountnumber;
    private String username, password;
    private double balance;

    private LinkedList<History> history;

    public Account(int accountnumber, String username, double balance, String password) {
        this.accountnumber = accountnumber;
        this.username = username;
        this.balance = balance;
        this.password = password;
        history = new LinkedList<>();
    }

    // Getter methods
    public int getAccountnumber() {
        return accountnumber;
    }

    public String getPassword(){
        return password;
    }

    public String getUsername() {
        return username;
    }

    public double getBalance() {
        return balance;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setAccountnumber(int accountnumber) {
        this.accountnumber = accountnumber;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void addTransaction(History transaction) {
        history.add(transaction);
    }

    public void displayTransaction() {
        if (history.isEmpty()) {
            System.out.println("No transactions found.");
            return;
        }

        for (History transaction : history) {
            System.out.println(transaction.getType() + ": " + transaction.getAmount() + " on " + transaction.getDate());
        }
    }

    public void display() {
        System.out.println("Account number: " + accountnumber);
        System.out.println("Account holder username: " + username);
        System.out.println("Account balance: " + balance);
        System.out.println("Account password: " + password);
    }

}
