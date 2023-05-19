import java.util.LinkedList;

public class Bank {
    // Instance variable
    private LinkedList<Account> accounts;

    public Bank() {
        accounts = new LinkedList<Account>();
    }

    public void addAccount(Account account) {

        // Add the account object to the linked list
        accounts.add(account);

        System.out.println("Account added successfully.");

    }

    public void deleteAccount(int number) {

        for (int i = 0; i < accounts.size(); i++) {

            // Get the account object at the current index
            Account current = accounts.get(i);

            if (current.getAccountnumber() == number) {
                accounts.remove(i);
                System.out.println("Account deleted successfully.");
                return;
            }

        }

        System.out.println("Account not found. Deletion failed.");

    }

    public void updateAccount(int number, String newName, double newBalance) {
        for (int i = 0; i < accounts.size(); i++) {
            Account current = accounts.get(i);

            if (current.getAccountnumber() == number) {
                current.setUsername(newName);
                current.setBalance(newBalance);
                System.out.println("Account updated successfully.");
                return;
            }

        }
        System.out.println("Account not found. Update failed.");

    }

    public void displayAllAccounts() {

        if (accounts.isEmpty()) {
            System.out.println("No accounts found.");
            return;
        }

        for (Account account : accounts) {
            account.display();
            System.out.println();
        }

    }

}
