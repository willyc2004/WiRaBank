public class Account {
    private int balance;
    private String accountNumber, pin;

    public Account(String accountNumber, String pin, int balance) {
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public void display() {
        System.out.println("Account number: " + accountNumber);
        System.out.println("Account pin: " + pin);
        System.out.println("Account balance: " + balance);
    }

    public void displayBalance() {
        System.out.println("Account number: " + accountNumber);
        System.out.println("Account balance: " + balance);
    }

    public void deposit(int amount) {
        if (amount > 0) {
            balance = balance + amount;
            System.out.println("Deposit successful. New balance: " + balance);
        } else {
            System.out.println("Invalid amount. Deposit failed.");
        }
    }

    public void withdraw(int amount) {
        if (amount > 0 && amount <= balance) {
            balance = balance - amount;
            System.out.println("Withdraw successful. New balance: " + balance);
        } else {
            System.out.println("Invalid amount or insufficient funds. Withdrawal failed.");
        }
    }

    public void transfer(Account receiver, int amount) {
        if (amount > 0 && amount <= balance) {
            balance = balance - amount;
            receiver.setBalance(receiver.getBalance() + amount);
            System.out.println("Transfer successful. New balance: " + balance);
        } else {
            System.out.println("Invalid amount or insufficient funds. Transfer failed.");
        }
    }
}
