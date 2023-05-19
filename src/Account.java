public class Account {

    private int accountnumber;
    private String username;
    private double balance;

    public Account(int accountnumber, String username, double balance) {
        this.accountnumber = accountnumber;
        this.username = username;
        this.balance = balance;
    }

    // Getter methods
    public int getAccountnumber() {
        return accountnumber;
    }

    public String getUsername() {
        return username;
    }

    public double getBalance() {
        return balance;
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

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposit successful. New balance: " + balance);
        } else {
            System.out.println("Invalid amount. Deposit failed.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawal successful. New balance: " + balance);
        } else {
            System.out.println("Invalid amount or insufficient funds. Withdrawal failed.");
        }
    }

    public void display() {
        System.out.println("Account number: " + accountnumber);
        System.out.println("Account holder username: " + username);
        System.out.println("Account balance: " + balance);
    }

}
