
abstract class Transaction {
    protected int amount;
    protected String accountNumber;

    public Transaction(int amount, String accountNumber) {
        this.amount = amount;
        this.accountNumber = accountNumber;
    }

    public abstract void execute(Account account);


    public String toString() {
        return "Transaction: " + this.amount;
    }
}