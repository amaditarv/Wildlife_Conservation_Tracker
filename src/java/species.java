import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;

import java.io.IOException;
import jakarta.servlet.http.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.sql.*;

@WebServlet("/species")
public class species extends HttpServlet {

    @Override
    protected void doOptions(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        setCorsHeaders(response);
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        setCorsHeaders(response);

        StringBuilder jsonBuffer = new StringBuilder();
        String line;
        try (BufferedReader reader = request.getReader()) {
            while ((line = reader.readLine()) != null) {
                jsonBuffer.append(line);
            }
        }

        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(jsonBuffer.toString(), JsonObject.class);

        int id = jsonObject.get("id").getAsInt();

        String message = "Species not found";
        String status = "error";
        String name = "";
        String photo_url = "";
        JsonArray animalArray = new JsonArray();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/wct";
            String user = "root";
            String password = "";

            Connection conn = DriverManager.getConnection(url, user, password);

            // Ambil data spesies
            String sql = "SELECT * FROM species WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                name = rs.getString("name");
                photo_url = rs.getString("photo_url");

                HttpSession session = request.getSession();
                session.setAttribute("id", id);
                session.setAttribute("name", name);
                session.setAttribute("photo_url", photo_url);

                status = "success";
                message = "Species data loaded";

                // Ambil daftar hewan berdasarkan nama spesies
                String animalSql = "SELECT * FROM animals WHERE species_id = ?";
                PreparedStatement animalStmt = conn.prepareStatement(animalSql);
                animalStmt.setInt(1, id);  // pakai ID dari tabel species
                ResultSet animalRs = animalStmt.executeQuery();

                while (animalRs.next()) {
                    JsonObject animalObj = new JsonObject();
                    animalObj.addProperty("animal_id", animalRs.getString("animal_id")); // atau animal_id
                    animalObj.addProperty("name", animalRs.getString("name"));
                    animalObj.addProperty("gender", animalRs.getString("gender"));
                    animalObj.addProperty("estimated_age", animalRs.getInt("estimated_age"));
                    animalObj.addProperty("weight", animalRs.getDouble("weight"));
                    animalObj.addProperty("status", animalRs.getString("status_animal"));
                    animalObj.addProperty("photo_url", animalRs.getString("photo_url"));
                    animalArray.add(animalObj);
                }

                animalRs.close();
                animalStmt.close();
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            message = "Database error: " + e.getMessage();
        }

        // Buat JSON response
        JsonObject jsonResponse = new JsonObject();
        jsonResponse.addProperty("status", status);
        jsonResponse.addProperty("message", message);
        jsonResponse.addProperty("name", name);
        jsonResponse.addProperty("photo_url", photo_url);
        jsonResponse.add("animals", animalArray);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(gson.toJson(jsonResponse));
    }

    private void setCorsHeaders(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
    }
}

