
abstract class Transaction {
    protected int amount;

    public Transaction(int amount) {
        this.amount = amount;
    }

    public abstract void execute(Account account);

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String toString() {
        return "Transaction: " + this.amount;
    }
}