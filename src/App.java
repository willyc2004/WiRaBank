import java.util.Scanner;
import java.util.Random;

public class App {

    //Untuk APP flow kita berdua yg buat
    Scanner inp = new Scanner(System.in);
    Account curr;
    Bank bank = new Bank();
    Connector conn = new Connector();

    public void run(){
        // Mengambil semua account dari database
        conn.getAccount(bank);
        // Masuk ke login page
        loginAccount();
    }

    public void mainMenu() {
        //ini buat update balance dari semua user.
        for (int i = 0; i < bank.getAccountTotals(); i++) {
            conn.setBalanceAcc(bank.getAccounts().get(i).getAccountNumber(), bank.getAccounts().get(i).getBalance());
        }
        System.out.println("Welcome to Wirabank!");
        curr.displayBalance();
        String currAccNum = curr.getAccountNumber();
        int landing = 999;
        try {
            Scanner s = new Scanner(System.in);
            System.out.println("1. Withdraw\n" +
                    "2. Deposit\n" +
                    "3. Transfer\n" +
                    "4. Delete Account\n" +
                    "5. Change Pin\n" +
                    "6. Account Information\n" +
                    "7. Transaction History\n" +
                    "0. Back");
            System.out.print("Choose: ");
            landing = s.nextInt();
        } catch (Exception e) {

        }
        switch (landing) {
            case 1: // Withdraw
                curr.displayBalance();

                int amount = 0;
                try {
                    Scanner s = new Scanner(System.in);
                    System.out.print("Enter the amount you want to withdraw : ");
                    amount = s.nextInt();
                } catch (Exception e) {

                }
                curr.withdraw(amount, currAccNum);
                mainMenu();
                break;
            case 2: // Deposit
                amount = 0;
                try {
                    Scanner s = new Scanner(System.in);
                    System.out.print("Enter the amount you want to deposit : ");
                    amount = s.nextInt();
                } catch (Exception e) {

                }

                curr.deposit(amount, currAccNum);
                mainMenu();
                break;
            case 3: // Transfer
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
                if (menuLogin - 1 >= jumlahAkun || menuLogin < 1) {
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
                        System.out.print("Enter the amount you want to deposit to " + curr.getAccountNumber() +": ");
                        amount = s.nextInt();
                    } catch (Exception e) {

                    }
                    curr.transfer(receiver, amount);
                }
                mainMenu();
                break;
            case 4: // Delete current Account
                System.out.println("Are you sure you want to delete your account?");
                System.out.println("Your Balance is " + curr.getBalance());
                System.out.println("If Account is Deleted, all details will be gone");
                System.out.print("Y/N : ");
                String cek = inp.next() + inp.nextLine();
                if (cek.equalsIgnoreCase("y")) {
                    // Delete account di database
                    conn.deleteAccount(currAccNum);
                    bank.deleteAccount(curr);
                    loginAccount();
                } else {
                    mainMenu();
                }
                break;
            case 5: // Change Pin
                changePin();
                mainMenu();
                break;
            case 6: // Account Info
                System.out.println("This is your account information!");
                curr.display();
                System.out.println();
                mainMenu();
                break;
            case 7: // History
                System.out.println("What type of transaction history do you want to see? \n" +
                        "1. Deposit \n" +
                        "2. Withdraw \n" +
                        "3. Transfer \n" +
                        "0. Back");
                System.out.print("Your choice: ");
                int history = inp.nextInt();
                switch (history){
                    case 1:
                        curr.showHistoryDepo();
                        break;
                    case 2:
                        curr.showHistoryWithdraw();
                        break;
                    case 3:
                        curr.showHistoryTransfer();
                        break;
                    default:
                        break;
                }
                mainMenu();
                break;
            case 0: // Back
                loginAccount();
                break;
            default: // error handling
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
        // Add Account to database
        conn.addAccount(accountNumber, pin, 0);
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

        if (menuLogin - 1 >= jumlahAkun || menuLogin < 1) {
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
                loginAccount();
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
        //ngeprint logo wirabank.
        Logo();
        //suru user login/register account, account nanti masuk ke Database
        //account number di random 8 digit
        int landing = 999;
        try {
            Scanner s = new Scanner(System.in);
            System.out.println("Welcome to Wirabank!");
            System.out.println("1. Login Account\n2. Register Account\n3. View All Accounts\n0. Exit");
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
            case 0:
                System.out.println("=================================================");
                System.out.println("Thank You For Using WiraBank, Your Trusted BANK!");
                Logo();
                System.exit(0);
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

        String accountNumber = curr.getAccountNumber();
        // Change Pin in Database
        conn.changePin(accountNumber, pin);
    }
}
