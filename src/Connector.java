import java.sql.*;
import java.util.Stack;

public class Connector {
    public void getAccount(Bank bank) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/wirabank", "root", "");

            Statement statement = con.createStatement();

            // nnti pas ubah jadi class keknya "users" nya bisa dikosongin jd nnti bisa ganti tabel sg lain
            String query = "SELECT * FROM account";
            ResultSet resultSet = statement.executeQuery(query); // Ini kek "get" function gitu lek ga salah

            while (resultSet.next()) {
                String account_number = resultSet.getString("account_number");
                String pin = resultSet.getString("pin");
                int balance = resultSet.getInt("balance");

                Account account = new Account(account_number, pin, balance);
                bank.addAccount(account);
            }
            statement.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void setBalanceAcc(String accountNumber, int balance){
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/wirabank", "root", "");

            // Query SQL untuk mengubah pin
            String query = "UPDATE account SET balance = ? WHERE account_number = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, balance);
            statement.setString(2, accountNumber);
            statement.executeUpdate();

            statement.close();
        } catch (SQLException e){
            System.out.println("Error : " + e.getMessage());
        }
    }

    public void addAccount(String accountNumber, String passwordValue, int balance) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/wirabank", "root", "");

            // Query SQL untuk menambahkan data
            String query = "INSERT INTO account (account_number, pin, balance) VALUES (?, ?, ?)";

            // Membuat prepared statement
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, accountNumber);
            statement.setString(2, passwordValue);
            statement.setInt(3, balance);

            // Menjalankan pernyataan SQL
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Account succesfully added");
            }

            // Menutup koneksi
            statement.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void deleteAccount(String accountNumber) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/wirabank", "root", "");

            // Query SQL untuk menghapus data berdasarkan nomor akun
            String query = "DELETE FROM account WHERE account_number = ?";

            // Membuat prepared statement
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, accountNumber);

            // Menjalankan pernyataan SQL
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Account : " + accountNumber + " has been deleted.");
            } else {
                System.out.println("Account : " + accountNumber + " not found.");
            }
            statement.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void changePin(String accountNumber, String newPin) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/wirabank", "root", "");

            // Query SQL untuk mengubah pin
            String query = "UPDATE account SET pin = ? WHERE account_number = ?";

            // Membuat prepared statement
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, newPin);
            statement.setString(2, accountNumber);

            // Menjalankan pernyataan SQL
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("PIN changed successfully");
            } else {
                System.out.println("Failed to change PIN");
            }

            // Menutup koneksi
            statement.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public Stack<Transaction> getDeposit(Account account) {
        String account_number = account.getAccountNumber();
        Stack<Transaction> depositStack = account.getTransactionHistory(); // Mengambil Stack transaksi dari akun
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/wirabank", "root", "");

            // nnti pas ubah jadi class keknya "users" nya bisa dikosongin jd nnti bisa ganti tabel sg lain
            String query = "SELECT * FROM deposit WHERE account_number = ?";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, account_number);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int amount = resultSet.getInt("amount");

                DepositTransaction deposit = new DepositTransaction(amount, account_number);
                depositStack.push(deposit);
            }
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return depositStack;
    }


    public void addDeposit(String accountNumber, int amount) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/wirabank", "root", "");

            // Query SQL untuk menambahkan data
            String query = "INSERT INTO deposit (amount, account_number) VALUES (?, ?)";

            // Membuat prepared statement
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, amount);
            statement.setString(2, accountNumber);

            // Menjalankan pernyataan SQL
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Deposit Successful!");
            } else {
                System.out.println("Failed to Deposit!");
            }
            // Menutup koneksi
            statement.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public Stack<Transaction> getWithdraw(Account account) {
        String account_number = account.getAccountNumber();
        Stack<Transaction> withdrawStack = account.getTransactionHistory(); // Mengambil Stack transaksi dari akun
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/wirabank", "root", "");

            // nnti pas ubah jadi class keknya "users" nya bisa dikosongin jd nnti bisa ganti tabel sg lain
            String query = "SELECT * FROM withdraw WHERE account_number = ?";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, account_number);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int amount = resultSet.getInt("amount");

                WithdrawTransaction withdraw = new WithdrawTransaction(amount, account_number);
                withdrawStack.push(withdraw);
            }
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return withdrawStack;
    }

    public void addWithdraw(String accountNumber, int amount) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/wirabank", "root", "");

            // Query SQL untuk menambahkan data
            String query = "INSERT INTO withdraw (amount, account_number) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, amount);
            statement.setString(2, accountNumber);

            // Menjalankan pernyataan SQL
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Withdraw Successful!");
            } else {
                System.out.println("Failed to Withdraw!");
            }
            statement.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public Stack<Transaction> getTransfer(Account account) {
        String sender = account.getAccountNumber();
        Stack<Transaction> transferStack = account.getTransactionHistory(); // Mengambil Stack transaksi dari akun
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/wirabank", "root", "");

            // nnti pas ubah jadi class keknya "users" nya bisa dikosongin jd nnti bisa ganti tabel sg lain
            String query = "SELECT * FROM transfer WHERE sender = ?";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, sender);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int amount = resultSet.getInt("amount");
                String receiver = resultSet.getString("receiver");

                Account recieverAcc = getAccountById(receiver);

                TransferTransaction transfer = new TransferTransaction(recieverAcc, amount, sender);
                transferStack.push(transfer);
            }
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return transferStack;
    }

    public void addTransfer(String sender, String receiver, int amount) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/wirabank", "root", "");

            // Query SQL untuk menambahkan data
            String query = "INSERT INTO transfer (amount, sender, receiver) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, amount);
            statement.setString(2, sender);
            statement.setString(3, receiver);

            // Menjalankan pernyataan SQL
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Transfer Successful!");
            } else {
                System.out.println("Failed to Transfer!");
            }
            statement.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public Account getAccountById(String account_number) {
        Account account = null;
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/wirabank", "root", "");

            // nnti pas ubah jadi class keknya "users" nya bisa dikosongin jd nnti bisa ganti tabel sg lain
            String query = "SELECT * FROM account WHERE account_number = ?";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, account_number);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String pin = resultSet.getString("pin");
                int balance = resultSet.getInt("balance");
                account = new Account(account_number, pin, balance);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return account;
    }
}