import java.util.Scanner;
import java.util.Random;

public class App {
    Scanner inp = new Scanner(System.in);
    Account curr;
    User user;

    public void mainMenu(){
        System.out.println("Welcome to Wirabank!");
        curr.displayBalance();
        int landing = 0;
        try {
            Scanner s = new Scanner(System.in);
            System.out.println("1. Withdraw\n2. Deposit\n3. Transfer\n4. Account Information\n5. Transaction History\n6. Back");
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
                    System.out.println("Enter the amount you want to withdraw");
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
                    System.out.println("Enter the amount you want to deposit");
                    amount = s.nextInt();
                } catch (Exception e) {

                }

                curr.deposit(amount);
                mainMenu();
                break;
            case 3:
                int jumlahAkun = user.getAccountTotals();
                System.out.println("List of account number:");
                user.displayAccountNumber();

                if (user.getAccountTotals() <= 1) {
                    System.out.println("You only Have 1 account, can't transfer");
                    mainMenu();
                }
                int menuLogin = 0;
                try {
                    Scanner s = new Scanner(System.in);
                    System.out.print("Transfer to which Account:");
                    menuLogin = s.nextInt();
                } catch (Exception e) {

                }
                if (menuLogin - 1 > jumlahAkun || menuLogin < 1) {
                    System.out.println("Invalid Input!");
                    mainMenu();
                } else {
                    Account receiver;
                    receiver = (Account) user.getAccounts().get(menuLogin - 1);
                    if(curr == receiver) {
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
                System.out.println("This is your account information!");
                curr.display();
                System.out.println();
                mainMenu();
                break;
            case 5:
//              transactionHistory();
                mainMenu();
                break;
            case 6:
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

        System.out.print("Please deposit some money into your account: ");
        int balance = inp.nextInt();
        Account account = new Account(accountNumber, pin, balance);
        user.addAccount(account);
        loginAccount();
    }

    public void menuLogin() {
        int jumlahAkun = user.getAccountTotals();
        System.out.println("List of account number:");
        user.displayAccountNumber();

        if (user.getAccountTotals() == 0) {
            System.out.println("You don't have any account, please create account!");
            loginAccount();
        }

        int menuLogin = 0;
        try {
            Scanner s = new Scanner(System.in);
            System.out.print("Choose which account to log in:");
            menuLogin = s.nextInt();
        } catch (Exception e) {

        }

        if (menuLogin - 1 > jumlahAkun || menuLogin < 1) {
            System.out.println("Invalid Input!");
            menuLogin();
        } else {
            curr = (Account) user.getAccounts().get(menuLogin - 1);
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

    public void loginAccount(){
        int landing = 0;
        try {
            Scanner s = new Scanner(System.in);
            System.out.println("Welcome to Wirabank " + user.getUsername() + "!");
            System.out.println("1. Login Account\n2. Register Account\n3. View All Accounts\n4. Change Pin\n5. Back");
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
                user.displayAllAccount();
                loginAccount();
                break;
            case 4:
                ChangePin();
                break;
            case 5:
                run();
                break;
            default:
                System.out.println("Invalid Input!");
                loginAccount();
                break;
        }
    }

    public void Forgot(){
        Logo();
        System.out.println("Your old username : " + user.getUsername());
        System.out.print("Change your username : ");
        String username = inp.next() + inp.nextLine();
        user.setUsername(username);
        System.out.println("Your old password : " + user.getPassword());
        System.out.print("Change your password : ");
        String pwd = inp.next() + inp.nextLine();
        user.setPassword(pwd);
        System.out.println("Account details successfully changed, please Login!");
        run();
    }

    public void ChangePin(){
        Logo();
        int jumlahAkun = user.getAccountTotals();
        System.out.println("List of account number:");
        user.displayAccountNumber();

        if (user.getAccountTotals() == 0) {
            System.out.println("You don't have any account, please create account!");
            loginAccount();
        }

        int menuLogin = 0;
        try {
            Scanner s = new Scanner(System.in);
            System.out.print("Choose which account to Change Pin:");
            menuLogin = s.nextInt();
        } catch (Exception e) {

        }

        if (menuLogin - 1 > jumlahAkun || menuLogin < 1) {
            System.out.println("Invalid Input!");
            loginAccount();
        } else {
            curr = (Account) user.getAccounts().get(menuLogin - 1);
            System.out.println("Your Old Pin: " + curr.getPin());
            System.out.print("Your New Pin: ");
            String pin = inp.next() + inp.nextLine();
            while (!pin.matches("\\d+")) {
                System.out.print("Invalid input! PIN must contain only digits. Please try again: ");
                pin = inp.next();
            }
            curr.setPin(pin);
            System.out.println("Pin Successfully Changed!");
            loginAccount();
        }

    }

    public void Login() {
        if (user == null) {
            System.out.println("Please Register First!");
            run();
        } else {
            Logo();
            System.out.println("Welcome to Wirabank " + user.getUsername() + "!");
            System.out.print("Please input your password : ");
            String password = inp.next() + inp.nextLine();
            if (user.login(password)) {
                System.out.println("Login Successful");
                loginAccount();
            } else {
                System.out.println("Incorrect Password!!");
                run();
            }

        }
    }

    public void Register() {
        Logo();
        System.out.print("Input your username : ");
        String username = inp.next() + inp.nextLine();
        System.out.print("Input your password : ");
        String pwd = inp.next() + inp.nextLine();
        user = new User(username, pwd);
        System.out.println("User successfully created, please Login!");
        run();
    }

    public void run() {
        Logo();
        if (user == null) {
            int landing = 0;
            try {
                Scanner s = new Scanner(System.in);
                System.out.println("Welcome To Wirabank!\n1. Login\n2. Register");
                System.out.print("Choose: ");
                landing = s.nextInt();
            } catch (Exception e) {

            }

            switch (landing) {
                case 1:
                    Login();
                    break;
                case 2:
                    Register();
                    break;
                default:
                    System.out.println("Invalid Input!");
                    run();
                    break;
            }
        } else {
            int landing = 0;
            try {
                Scanner s = new Scanner(System.in);
                System.out.println("Welcome To Wirabank!\n1. Login\n2. Forgot Username/Password?");
                System.out.print("Choose: ");
                landing = s.nextInt();
            } catch (Exception e) {

            }
            switch (landing) {
                case 1:
                    Login();
                    break;
                case 2:
                    Forgot();
                    break;
                default:
                    System.out.println("Invalid Input!");
                    run();
                    break;
            }
        }
    }
}