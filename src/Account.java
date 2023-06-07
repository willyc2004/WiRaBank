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
        System.out.println("Transaction History:");
        int number = 1;
        for (Transaction transaction : transactionHistory) {
            if (transaction instanceof DepositTransaction) {
                System.out.println(number + ". Deposit: +" + transaction.amount);
            } else if (transaction instanceof WithdrawTransaction) {
                System.out.println(number + ". Withdrawal: -" + transaction.amount);
            } else if (transaction instanceof TransferTransaction) {
                TransferTransaction transferTransaction = (TransferTransaction) transaction;
                Account receiver = transferTransaction.getReceiver();
                System.out.println(number + ". Transfer: -" + transaction.amount + " to Account Number " + receiver.getAccountNumber());
            }
            number++;
        }
    }

    public void removeAccountHistory() {
        transactionHistory.clear();
    }
}
