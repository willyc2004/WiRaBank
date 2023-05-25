import java.sql.*;
public class Connector {
    public void addAccount(String accountNumber, String passwordValue, String usernameValue, int balance){
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/wirabank", "root", "");

            // Query SQL untuk menambahkan data
            String query = "INSERT INTO account (account_number, password, user_name, balance) VALUES (?, ?, ?, ?)";

            // Membuat prepared statement
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, accountNumber);
            statement.setString(2, passwordValue);
            statement.setString(3, usernameValue);
            statement.setInt(4, balance);

            // Menjalankan pernyataan SQL
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Data berhasil ditambahkan ke tabel Account.");
            }

            // Menutup koneksi
            statement.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void getAccount(Bank bank){
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/wirabank", "root", "");

            Statement statement = con.createStatement();

            // nnti pas ubah jadi class keknya "users" nya bisa dikosongin jd nnti bisa ganti tabel sg lain
            String query = "SELECT * FROM account";
            ResultSet resultSet = statement.executeQuery(query); // Ini kek "get" function gitu lek ga salah

            while (resultSet.next()){
                String account_number = resultSet.getString("account_number");
                String password = resultSet.getString("password");
                String user_name = resultSet.getString("user_name");
                int balance = resultSet.getInt("balance");

                Account account = new Account(account_number, user_name, balance, password);
                bank.addAccount(account);
            }

            statement.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void deleteAccount(String accountNumber) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/wirabank", "root", "");
            System.out.println("Koneksi ke database berhasil!");

            // Query SQL untuk menghapus data berdasarkan nomor akun
            String query = "DELETE FROM account WHERE account_number = ?";

            // Membuat prepared statement
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, accountNumber);

            // Menjalankan pernyataan SQL
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Akun dengan nomor " + accountNumber + " berhasil dihapus.");
            } else {
                System.out.println("Akun dengan nomor " + accountNumber + " tidak ditemukan.");
            }

            // Menutup koneksi
            statement.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}