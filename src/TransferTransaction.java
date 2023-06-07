class TransferTransaction extends Transaction {
    private Account receiver;

    public TransferTransaction(Account receiver, int amount) {
        super(amount);
        this.receiver = receiver;
    }

    @Override
    public void execute(Account account) {
        if (amount <= account.getBalance()) {
            account.setBalance(account.getBalance() - amount);
            receiver.setBalance(receiver.getBalance() + amount);
            System.out.println("Transfer successful. New balance: " + account.getBalance());
        } else {
            System.out.println("Insufficient funds. Transfer failed.");
        }
    }

    public Account getReceiver() {
        return receiver;
    }
}