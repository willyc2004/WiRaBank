import java.sql.*;
public class Connector {
    public void getAccount(Bank bank){
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/wirabank", "root", "");

            Statement statement = con.createStatement();

            // nnti pas ubah jadi class keknya "users" nya bisa dikosongin jd nnti bisa ganti tabel sg lain
            String query = "SELECT * FROM account";
            ResultSet resultSet = statement.executeQuery(query); // Ini kek "get" function gitu lek ga salah

            while (resultSet.next()){
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

    public void addAccount(String accountNumber, String passwordValue, int balance){
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

//    public void getDeposit(){
//        try{
//            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/wirabank", "root", "");
//
//            Statement statement = con.createStatement();
//
//            // nnti pas ubah jadi class keknya "users" nya bisa dikosongin jd nnti bisa ganti tabel sg lain
//            String query = "SELECT * FROM account";
//            ResultSet resultSet = statement.executeQuery(query); // Ini kek "get" function gitu lek ga salah
//
//            while (resultSet.next()){
//                String account_number = resultSet.getString("account_number");
//                String pin = resultSet.getString("pin");
//                int balance = resultSet.getInt("balance");
//
//                Account account = new Account(account_number, pin, balance);
//                bank.addAccount(account);
//            }
//            statement.close();
//        } catch (SQLException e) {
//            System.out.println("Error: " + e.getMessage());
//        }
//    }

    public void addDeposit(String accountNumber, int amount){
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

}