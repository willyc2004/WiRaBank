import java.util.Scanner;
import java.util.LinkedList;
public class App {
    Scanner inp = new Scanner(System.in);
    Account curr;
    public void run(){
        Bank bank = new Bank();
        Connector mySql = new Connector();

        mySql.getAccount(bank);
        System.out.println(
                "██╗    ██╗██╗██████╗  █████╗ ██████╗  █████╗ ███╗   ██╗██╗  ██╗\n" +
                "██║    ██║██║██╔══██╗██╔══██╗██╔══██╗██╔══██╗████╗  ██║██║ ██╔╝\n" +
                "██║ █╗ ██║██║██████╔╝███████║██████╔╝███████║██╔██╗ ██║█████╔╝ \n" +
                "██║███╗██║██║██╔══██╗██╔══██║██╔══██╗██╔══██║██║╚██╗██║██╔═██╗ \n" +
                "╚███╔███╔╝██║██║  ██║██║  ██║██████╔╝██║  ██║██║ ╚████║██║  ██╗\n" +
                " ╚══╝╚══╝ ╚═╝╚═╝  ╚═╝╚═╝  ╚═╝╚═════╝ ╚═╝  ╚═╝╚═╝  ╚═══╝╚═╝  ╚═╝\n");

        System.out.println("Welcome To Wirabank! \n" +
                "1. Login \n" +
                "2. Register");
        System.out.print("Choose: ");
        int landing = inp.nextInt();

        switch (landing) {
            case 1:
                menuLogin(bank);
                break;
            case 2:
                menuRegis(bank);
                break;
            default:
                System.out.println("Invalid Input!");
                run();
                break;
        }
    }

    public void menuLogin(Bank bank){
        int jumlahAkun = bank.getAccountTotals();
        System.out.println("List of account number:");
        bank.displayAccountNumber();

        if (bank.getAccountTotals() == 0){
            return;
        }

        System.out.print("Choose which account to log in:");
        int menuLogin = inp.nextInt();

        if (menuLogin-1 > jumlahAkun || menuLogin < 1){
            System.out.println("Invalid Input!");
            menuLogin(bank);
        } else {
            curr = (Account) bank.listAccount().get(menuLogin - 1);
            String pass = curr.getPassword();

            System.out.println("Password: ");
            String curPass = inp.next() + inp.nextLine();

            if (curPass.equals(pass)){
                mainMenu(curr, bank);
            } else {
                System.out.println("Wrong Password!");
                menuLogin(bank);
            }
        }
    }

    public void menuRegis(Bank bank){
        // Randomize account number
        String accountNumber = bank.setAccountNumber();
        System.out.print("Input your Name: ");
        String name = inp.next() + inp.nextLine();

        System.out.print("Create your Password: ");
        String pwd = inp.next() + inp.nextLine();

        System.out.print("Please deposit some money into your account: ");
        int balance = inp.nextInt();
        Account account = new Account(accountNumber, name, balance, pwd);
        bank.addAccount(account); // Add to LinkedList
        bank.addDB(account); // Add to Database

        run();
    }

    public void mainMenu(Account current, Bank bank){
        System.out.println(
                "███╗   ███╗███████╗███╗   ██╗██╗   ██╗\n" +
                "████╗ ████║██╔════╝████╗  ██║██║   ██║\n" +
                "██╔████╔██║█████╗  ██╔██╗ ██║██║   ██║\n" +
                "██║╚██╔╝██║██╔══╝  ██║╚██╗██║██║   ██║\n" +
                "██║ ╚═╝ ██║███████╗██║ ╚████║╚██████╔╝\n" +
                "╚═╝     ╚═╝╚══════╝╚═╝  ╚═══╝ ╚═════╝ \n");
        System.out.println("Account Number: " + current.getAccountnumber() + "\n" +
                "1. Withdraw \n" +
                "2. Deposit \n" +
                "3. Transfer \n" +
                "4. Account Information \n" +
                "5. Transaction History \n" +
                "0. Exit");
        System.out.print("Your Choice: ");
        int mainMenu = inp.nextInt();

        switch (mainMenu){
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 0:
                System.out.println("Bye-bye");
                System.exit(0);
            default:
                System.out.println("Invalid Input!");
                break;
        }
    }

}