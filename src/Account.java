import java.util.Stack;

public class Account {
    private int balance;
    private String accountNumber, pin;
    private Stack<Transaction> transactionHistory;
    Connector con = new Connector();

    //account punya attributes berikut, ada attributes transaction history STACK, 1 user bisa banyak transaction history

    public Account(String accountNumber, String pin, int balance) {
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = balance;
        this.transactionHistory = new Stack<>();
    }

    //getter setter

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

    public Stack<Transaction> getTransactionHistory() {
        return transactionHistory;
    }

    public void setTransactionHistory(Stack<Transaction> transactionHistory) {
        this.transactionHistory = transactionHistory;
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

    //untuk logic deposit, withdraw, transfer antar akun willy yg buat
    //untuk logic masukkin ke MySQL, sama tampilin menggunakan STACK radhit yang buat.

    public void deposit(int amount, String accountNumber) {
        if (amount > 0) {
            Transaction depositTransaction = new DepositTransaction(amount, accountNumber);
            depositTransaction.execute(this);
            // menambahkan data ke tabel deposit dan set balance
            con.addDeposit(accountNumber, amount);
//            con.setBalanceAcc(accountNumber, balance);
        } else {
            System.out.println("Invalid amount. Deposit failed.");
        }
    }

    public void withdraw(int amount, String accountNumber) {
        if (amount > 0 && amount <= balance) {
            Transaction withdrawalTransaction = new WithdrawTransaction(amount, accountNumber);
            withdrawalTransaction.execute(this);
            // menambahkan data ke tabel withdraw dan set balance
            con.addWithdraw(accountNumber, amount);
//            con.setBalanceAcc(accountNumber, balance);
        } else {
            System.out.println("Invalid amount. Withdrawal failed.");
        }
    }

    public void transfer(Account receiver, int amount) {
        if (amount > 0 && amount <= balance) {
            Transaction transferTransaction = new TransferTransaction(receiver, amount, accountNumber);
            transferTransaction.execute(this);
            // Menambahkan data ke tabel transfer dan set balance
            con.addTransfer(accountNumber, receiver.getAccountNumber(), amount);
//            con.setBalanceAcc(accountNumber, balance);
        } else {
            System.out.println("Invalid amount. Transfer failed.");
        }
    }


    public void showHistoryDepo(){
        this.transactionHistory = con.getDeposit(this);
        System.out.println("Deposit history: ");
        int number = 1;
        while (!transactionHistory.isEmpty()) {
            System.out.print(number+". ");
            Transaction transaction = transactionHistory.pop();
            System.out.println(transaction);
            number++;
        }
    }

    public void showHistoryWithdraw(){
        this.transactionHistory = con.getWithdraw(this);
        System.out.println("Withdraw history: ");
        int number = 1;
        while (!transactionHistory.isEmpty()) {
            System.out.print(number+". ");
            Transaction transaction = transactionHistory.pop();
            System.out.println(transaction);
            number++;
        }
    }

    public void showHistoryTransfer(){
        this.transactionHistory = con.getTransfer(this);
        System.out.println("Transfer history: ");
        int number = 1;
        while (!transactionHistory.isEmpty()) {
            System.out.print(number+". ");
            Transaction transaction = transactionHistory.pop();
            System.out.println(transaction);
            number++;
        }
    }
}
