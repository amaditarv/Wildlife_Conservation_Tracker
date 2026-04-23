
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.*;
import java.sql.Date;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Sintia Dwi
 */
public class DBConnect {
    String url ="jdbc:mysql://localhost:3306/wct";
    String user ="root";
    String password ="";
    public void testConnection(){
        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to XAMPP MySQL!");

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM users");

            while (rs.next()) {
                System.out.println("User: " + rs.getString("name") + ", Email: " + rs.getString("email"));
            }

            conn.close();
    } catch (SQLException e) {
        System.out.println("Connection failed: " + e.getMessage());    
    }
}
       public void insertAnimal(Animal a) {
        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            String sql = "INSERT INTO animals (name, gender, weight, status_animal, photo_url, date_of_birth, kode) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, a.getName());
            pst.setString(2, a.getGender());
            pst.setDouble(3, a.getWeight());
            pst.setString(4, a.getStatus());
            pst.setString(5, a.getPhoto_url());
            pst.setDate(6, a.getDate());

            // Contoh kode unik manual: "A" + waktu millis
            String kodeUnik = "A" + System.currentTimeMillis();
            pst.setString(7, kodeUnik);

            pst.executeUpdate();
            pst.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
    

