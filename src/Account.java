import java.util.LinkedList;

public class Account {

    private String username, password, accountnumber;
    private int balance;

    private LinkedList<History> history;

    public Account(String accountnumber, String username, int balance, String password) {
        this.accountnumber = accountnumber;
        this.username = username;
        this.balance = balance;
        this.password = password;
        history = new LinkedList<>();
    }

    // Getter methods
    public String getAccountnumber() {
        return accountnumber;
    }

    public String getPassword(){
        return password;
    }

    public String getUsername() {
        return username;
    }

    public int getBalance() {
        return balance;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setAccountnumber(String accountnumber) {
        this.accountnumber = accountnumber;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void display() {
        System.out.println("Account number: " + accountnumber);
        System.out.println("Account holder username: " + username);
        System.out.println("Account balance: " + balance);
        System.out.println("Account password: " + password);
    }

}
