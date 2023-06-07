class WithdrawTransaction extends Transaction {
    public WithdrawTransaction(int amount) {
        super(amount);
    }

    @Override
    public void execute(Account account) {
        if (amount <= account.getBalance()) {
            account.setBalance(account.getBalance() - amount);
            System.out.println("Withdrawal successful. New balance: " + account.getBalance());
        } else {
            System.out.println("Insufficient funds. Withdrawal failed.");
        }
    }
}