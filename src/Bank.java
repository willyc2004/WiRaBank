import java.util.LinkedList;

public class Bank {

    //bank ini gunanya untuk menyimpan LinkedList dari account, sama ada method2 untuk display all account/account total dll
    private LinkedList<Account> accounts;

    public Bank() {
        this.accounts = new LinkedList<>();
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public LinkedList<Account> getAccounts() {
        return accounts;
    }

    public int getAccountTotals() {
        return this.accounts.size();
    }

    public void displayAllAccount() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts found.");
            return;
        }

        for (int i = 0; i < accounts.size(); i++) {
            System.out.println((i + 1) + ".");
            System.out.println("Account Number : " + accounts.get(i).getAccountNumber());
            System.out.println("Account Pin : " + accounts.get(i).getPin());
            System.out.println("Balance : " + accounts.get(i).getBalance());
        }
    }

    public void displayAccountNumber() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts found.");
            return;
        }

        for (int i = 0; i < accounts.size(); i++) {
            System.out.println((i + 1) + ". " + accounts.get(i).getAccountNumber());
        }
    }

    public void deleteAccount(Account account) {
        if (accounts.contains(account)) {
            accounts.remove(account);
            System.out.println("Account has been successfully deleted.");
        } else {
            System.out.println("Account not found.");
        }
    }
}
