import java.util.Scanner;
import java.util.LinkedList;
public class App {
    Scanner inp = new Scanner(System.in);
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

        System.out.print("Choose which account to log in:");
        int menuLogin = inp.nextInt();

        if (menuLogin-1 > jumlahAkun || menuLogin < 1){
            System.out.println("Invalid Input!");
            menuLogin(bank);
        } else {
            Account curr = (Account) bank.listAccount().get(menuLogin - 1);
            String pass = curr.getPassword();

            System.out.println("Password: ");
            String curPass = inp.next() + inp.nextLine();

            if (curPass.equals(pass)){
                mainMenu(curr);
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
    }

    public void mainMenu(Account current){

    }

}