class DepositTransaction extends Transaction {
    public DepositTransaction(int amount, String accountNumber) {
        super(amount, accountNumber);
    }

    @Override
    public void execute(Account account) {
        account.setBalance(account.getBalance() + amount);
        System.out.println("Deposit successful. New balance: " + account.getBalance());
    }

    @Override
    public String toString() {
        return "Deposit: " + this.amount;
    }
}