
abstract class Transaction {
    protected int amount;

    public Transaction(int amount) {
        this.amount = amount;
    }

    public abstract void execute(Account account);


    public String toString() {
        return "Transaction: " + this.amount;
    }
}