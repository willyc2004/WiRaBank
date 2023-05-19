import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class History {
    private String type;
    private double amount;
    private Date date;
    private List<History> transactions;

    public History() {
        transactions = new ArrayList<>();
    }

    public History(String type, double amount) {
        this.type = type;
        this.amount = amount;
        this.date = new Date();
    }

    public void addTransaction(History transaction) {
        transactions.add(transaction);
    }

    public void displayHistory() {
        if (transactions.isEmpty()) {
            System.out.println("No transactions found.");
            return;
        }

        for (History transaction : transactions) {
            System.out.println(transaction.getType() + ": " + transaction.getAmount() + " on " + transaction.getDate());
        }
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public Date getDate() {
        return date;
    }
}
