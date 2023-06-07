import java.util.Scanner;
import java.util.Random;

public class App {
    Scanner inp = new Scanner(System.in);
    Account curr;
    Bank bank = new Bank();


    public void mainMenu() {
        System.out.println("Welcome to Wirabank!");
        curr.displayBalance();
        int landing = 999;
        try {
            Scanner s = new Scanner(System.in);
            System.out.println("1. Withdraw\n2. Deposit\n3. Transfer\n4. Delete Account\n5. Change Pin\n6. Account Information\n7. Transaction History\n0. Back");
            System.out.print("Choose: ");
            landing = s.nextInt();
        } catch (Exception e) {

        }
        switch (landing) {
            case 1:
                curr.displayBalance();

                int amount = 0;
                try {
                    Scanner s = new Scanner(System.in);
                    System.out.print("Enter the amount you want to withdraw : ");
                    amount = s.nextInt();
                } catch (Exception e) {

                }
                curr.withdraw(amount);
                mainMenu();
                break;
            case 2:
                amount = 0;
                try {
                    Scanner s = new Scanner(System.in);
                    System.out.print("Enter the amount you want to deposit : ");
                    amount = s.nextInt();
                } catch (Exception e) {

                }

                curr.deposit(amount);
                mainMenu();
                break;
            case 3:
                int jumlahAkun = bank.getAccountTotals();
                System.out.println("List of account number:");
                bank.displayAccountNumber();

                if (bank.getAccountTotals() <= 1) {
                    System.out.println("You only Have 1 account, can't transfer");
                    mainMenu();
                }
                int menuLogin = 0;
                try {
                    Scanner s = new Scanner(System.in);
                    System.out.print("Transfer to which Account : ");
                    menuLogin = s.nextInt();
                } catch (Exception e) {

                }
                if (menuLogin - 1 > jumlahAkun || menuLogin < 1) {
                    System.out.println("Invalid Input!");
                    mainMenu();
                } else {
                    Account receiver;
                    receiver = (Account) bank.getAccounts().get(menuLogin - 1);
                    if (curr == receiver) {
                        System.out.println("You can't transfer to yourself!");
                        mainMenu();
                    }

                    amount = 0;
                    try {
                        Scanner s = new Scanner(System.in);
                        System.out.println("Enter the amount you want to deposit to " + curr.getAccountNumber());
                        amount = s.nextInt();
                    } catch (Exception e) {

                    }
                    curr.transfer(receiver, amount);
                }
                mainMenu();
                break;

            case 4:
                System.out.println("Are you sure you want to delete your account?");
                System.out.println("Your Balance is " + curr.getBalance());
                System.out.println("If Account is Deleted, all details will be gone");
                System.out.print("Y/N : ");
                String cek = inp.next() + inp.nextLine();
                if (cek.equalsIgnoreCase("y")) {
                    bank.deleteAccount(curr);
                    loginAccount();
                } else {
                    mainMenu();
                }
                break;
            case 5:
                changePin();
                mainMenu();
                break;
            case 6:
                System.out.println("This is your account information!");
                curr.display();
                System.out.println();
                mainMenu();
                break;
            case 7:
                curr.displayTransactionHistory();
                mainMenu();
                break;
            case 0:
                loginAccount();
                break;
            default:
                System.out.println("Invalid Input!");
                mainMenu();
                break;
        }
    }

    public String setAccountNumber() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 8; i++) {
            int digit = random.nextInt(10);
            sb.append(digit);
        }

        return sb.toString();
    }

    public void menuRegis() {
        System.out.println("Randomly Generating Account Number");
        String accountNumber = setAccountNumber();
        System.out.println("Your account number is : " + accountNumber);
        System.out.print("Create your PIN: ");
        String pin = inp.next() + inp.nextLine();
        while (!pin.matches("\\d+")) {
            System.out.print("Invalid input! PIN must contain only digits. Please try again: ");
            pin = inp.next();
        }

        Account account = new Account(accountNumber, pin, 0);
        bank.addAccount(account);
        loginAccount();
    }

    public void menuLogin() {
        int jumlahAkun = bank.getAccountTotals();
        System.out.println("List of account number:");
        bank.displayAccountNumber();

        if (bank.getAccountTotals() == 0) {
            System.out.println("You don't have any account, please create account!");
            loginAccount();
        }

        int menuLogin = 0;
        try {
            Scanner s = new Scanner(System.in);
            System.out.print("Choose which account to log in : ");
            menuLogin = s.nextInt();
        } catch (Exception e) {

        }

        if (menuLogin - 1 > jumlahAkun || menuLogin < 1) {
            System.out.println("Invalid Input!");
            menuLogin();
        } else {
            curr = (Account) bank.getAccounts().get(menuLogin - 1);
            String pass = curr.getPin();

            System.out.print("Pin: ");
            String curPass = inp.next() + inp.nextLine();

            if (curPass.equals(pass)) {
                System.out.println("Login Account Successful!");
                mainMenu();
            } else {
                System.out.println("Wrong Password!");
                menuLogin();
            }
        }
    }

    public void Logo() {
        System.out.println(
                "██╗    ██╗██╗██████╗  █████╗ ██████╗  █████╗ ███╗   ██╗██╗  ██╗\n" +
                        "██║    ██║██║██╔══██╗██╔══██╗██╔══██╗██╔══██╗████╗  ██║██║ ██╔╝\n" +
                        "██║ █╗ ██║██║██████╔╝███████║██████╔╝███████║██╔██╗ ██║█████╔╝ \n" +
                        "██║███╗██║██║██╔══██╗██╔══██║██╔══██╗██╔══██║██║╚██╗██║██╔═██╗ \n" +
                        "╚███╔███╔╝██║██║  ██║██║  ██║██████╔╝██║  ██║██║ ╚████║██║  ██╗\n" +
                        " ╚══╝╚══╝ ╚═╝╚═╝  ╚═╝╚═╝  ╚═╝╚═════╝ ╚═╝  ╚═╝╚═╝  ╚═══╝╚═╝  ╚═╝\n");
    }

    public void loginAccount() {
        int landing = 999;
        try {
            Scanner s = new Scanner(System.in);
            System.out.println("Welcome to Wirabank!");
            System.out.println("1. Login Account\n2. Register Account\n3. View All Accounts");
            System.out.print("Choose: ");
            landing = s.nextInt();
        } catch (Exception e) {

        }
        switch (landing) {
            case 1:
                menuLogin();
                break;
            case 2:
                menuRegis();
                break;
            case 3:
                bank.displayAllAccount();
                loginAccount();
                break;
            default:
                System.out.println("Invalid Input!");
                loginAccount();
                break;
        }
    }

    public void changePin() {
        Logo();
        System.out.println("Your Old Pin : " + curr.getPin());
        System.out.print("Your New Pin : ");
        String pin = inp.next() + inp.nextLine();
        while (!pin.matches("\\d+")) {
            System.out.print("Invalid input! PIN must contain only digits. Please try again: ");
            pin = inp.next();
        }
        curr.setPin(pin);
        System.out.println("Pin Successfully Changed!");
    }
}
