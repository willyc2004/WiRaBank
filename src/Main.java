// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        System.out.println("saya jago");

        // Create a bank object
        Bank bank = new Bank();


        // Create some account objects
        Account a1 = new Account(1001, "Alice", 5000, "sayajago");
        Account a2 = new Account(1002, "Bob", 3000, "password");
        Account a3 = new Account(1003, "Charlie", 4000, "sebentar");

        // Add some accounts to the bank
        bank.addAccount(a1);
        bank.addAccount(a2);
        bank.addAccount(a3);

        bank.withdraw(a1 , 20000000);
        bank.withdraw(a1, 2000);
        bank.deposit(a2, 300);

        // Display all accounts
        bank.displayAllAccounts();

        // Delete an account by its number
        bank.deleteAccount(a1);

        // Display all accounts again
        bank.displayAllAccounts();

        // Update an account by its number
        bank.updateAccount(1002, "Chloe", 4500);

        // Display all accounts again
        bank.displayAllAccounts();

    }

}