import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/WCT/animal")
public class animalServlet extends HttpServlet {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/wct"; // sesuaikan dengan DB kamu
    private static final String DB_USER = "root";       // sesuaikan
    private static final String DB_PASSWORD = "";       // sesuaikan

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        List<Animal> animals = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);

            String sql = "SELECT * FROM animals";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String kode = rs.getString("kode");
                int animal_id = rs.getInt("animal_id");
                String name = rs.getString("name");
                String species = rs.getString("species");
                String gender = rs.getString("gender");
                java.sql.Date date_of_birth = rs.getDate("date_of_birth");
                int estimated_age = rs.getInt("estimated_age");
                double weight = rs.getDouble("weight");
                String status = rs.getString("status");
                String photo_url = rs.getString("photo_url");

                Animal animal = new Animal(kode, animal_id, name, species, gender, date_of_birth, estimated_age, weight, status, photo_url);
                animals.add(animal);
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Output JSON
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        Gson gson = new Gson();
        String json = gson.toJson(animals);
        out.print(json);
        out.flush();
    }
}
