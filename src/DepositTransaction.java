class DepositTransaction extends Transaction {
    public DepositTransaction(int amount) {
        super(amount);
    }

    @Override
    public void execute(Account account) {
        account.setBalance(account.getBalance() + amount);
        System.out.println("Deposit successful. New balance: " + account.getBalance());
    }
}