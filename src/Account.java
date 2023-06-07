import java.util.Stack;

public class Account {
    private int balance;
    private String accountNumber, pin;
    private Stack<Transaction> transactionHistory;

    public Account(String accountNumber, String pin, int balance) {
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = balance;
        this.transactionHistory = new Stack<>();
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
            Transaction depositTransaction = new DepositTransaction(amount);
            depositTransaction.execute(this);
            transactionHistory.push(depositTransaction);
        } else {
            System.out.println("Invalid amount. Deposit failed.");
        }
    }

    public void withdraw(int amount) {
        if (amount > 0 && amount <= balance) {
            Transaction withdrawalTransaction = new WithdrawTransaction(amount);
            withdrawalTransaction.execute(this);
            transactionHistory.push(withdrawalTransaction);
        } else {
            System.out.println("Invalid amount. Withdrawal failed.");
        }
    }

    public void transfer(Account receiver, int amount) {
        if (amount > 0 && amount <= balance) {
            Transaction transferTransaction = new TransferTransaction(receiver, amount);
            transferTransaction.execute(this);
            transactionHistory.push(transferTransaction);
        } else {
            System.out.println("Invalid amount. Transfer failed.");
        }
    }

    public void displayTransactionHistory() {
        Stack<Transaction> temp = new Stack<>();
        System.out.println("Transaction history: ");
        int number = 1;
        while (!transactionHistory.isEmpty()) {
            System.out.print(number+". ");
            Transaction transaction = transactionHistory.pop();
            System.out.println(transaction);
            temp.push(transaction);
            number++;
        }
        while (!temp.isEmpty()) {
            transactionHistory.push(temp.pop());
        }
    }



    public void removeAccountHistory() {
        transactionHistory.clear();
    }
}
