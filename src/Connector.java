import java.sql.*;
public class Connector {
    public void addData(int id, String usernameValue, String passwordValue){
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/wirabank", "root", "");
            System.out.println("Koneksi ke database berhasil!");

            // Query SQL untuk menambahkan data
            String query = "INSERT INTO users (user_id, name, password) VALUES (?, ?, ?)";

            // Membuat prepared statement
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.setString(2, usernameValue);
            statement.setString(3, passwordValue);

            // Menjalankan pernyataan SQL
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Data berhasil ditambahkan ke tabel Users.");
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

}