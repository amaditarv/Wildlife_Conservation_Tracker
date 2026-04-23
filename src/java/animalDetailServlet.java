import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;

import java.io.IOException;
import jakarta.servlet.http.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;

import java.io.BufferedReader;
import java.sql.*;
import java.sql.Timestamp;

@WebServlet("/animalDetail")
public class animalDetailServlet extends HttpServlet {

    @Override
    protected void doOptions(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        setCorsHeaders(response);
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        response.setContentType("text/plain");
        response.getWriter().write("GET request berhasil - Servlet aktif");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        setCorsHeaders(response);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        Gson gson = new Gson();
        StringBuilder jsonBuffer = new StringBuilder();
        String line;

        try (BufferedReader reader = request.getReader()) {
            while ((line = reader.readLine()) != null) {
                jsonBuffer.append(line);
            }
        }

        JsonObject jsonRequest = gson.fromJson(jsonBuffer.toString(), JsonObject.class);
        int animalIdFromClient = jsonRequest.get("animal_id").getAsInt();

        JsonObject jsonResponse = new JsonObject();
        String status = "error";
        String message = "Animal not found";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/wct", "root", "");

            String sql = "SELECT a.*, s.name AS species_name FROM animals a JOIN species s ON a.species_id = s.id WHERE a.animal_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, animalIdFromClient);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                JsonObject animal = new JsonObject();
                animal.addProperty("animal_id", rs.getInt("animal_id"));
                animal.addProperty("name", rs.getString("name"));
                animal.addProperty("gender", rs.getString("gender"));
                animal.addProperty("estimated_age", rs.getInt("estimated_age"));
                animal.addProperty("weight", rs.getDouble("weight"));
                animal.addProperty("status_animal", rs.getString("status_animal").toLowerCase()); 
                animal.addProperty("photo_url", rs.getString("photo_url"));
                animal.addProperty("species_name", rs.getString("species_name"));

                // Ambil detail berdasarkan animal_id, bukan kode
                animal.add("detail", getDetailData(conn, animalIdFromClient));

                jsonResponse.add("animal", animal);
                status = "success";
                message = "Animal data loaded";
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            message = "Database error: " + e.getMessage();
            e.printStackTrace();
        }

        jsonResponse.addProperty("status", status);
        jsonResponse.addProperty("message", message);
        response.getWriter().write(gson.toJson(jsonResponse));
    }

    private JsonObject getDetailData(Connection conn, int animalId) throws SQLException {
        JsonObject detail = new JsonObject();

        // Detail fisik
        PreparedStatement detailStmt = conn.prepareStatement("SELECT * FROM animal_details WHERE animal_id = ?");
        detailStmt.setInt(1, animalId);
        ResultSet detailRs = detailStmt.executeQuery();
        if (detailRs.next()) {
            detail.addProperty("detail_id", detailRs.getInt("detail_id"));
            detail.addProperty("detail_code", detailRs.getString("detail_code"));
            detail.addProperty("height", detailRs.getDouble("height"));
            detail.addProperty("length", detailRs.getDouble("length"));
            detail.addProperty("color", detailRs.getString("color"));
            detail.addProperty("distinguishing_features", detailRs.getString("distinguishing_features"));
            detail.addProperty("province", detailRs.getString("province"));
        }
        detailRs.close();
        detailStmt.close();

        // Perilaku
        PreparedStatement behStmt = conn.prepareStatement("SELECT * FROM animal_behavior WHERE animal_id = ?");
        behStmt.setInt(1, animalId);
        ResultSet behRs = behStmt.executeQuery();
        if (behRs.next()) {
            detail.addProperty("behavior_id", behRs.getInt("behavior_id"));
            detail.addProperty("behavior_code", behRs.getString("behavior_code"));
            detail.addProperty("daily_activity", behRs.getString("daily_activity"));
            detail.addProperty("diet", behRs.getString("diet"));
            detail.addProperty("social_behavior", behRs.getString("social_behavior"));
            detail.addProperty("mating_season", behRs.getString("mating_season"));
        }
        behRs.close();
        behStmt.close();

        // Observasi
        PreparedStatement obsStmt = conn.prepareStatement("SELECT * FROM animal_observations WHERE animal_id = ?");
        obsStmt.setInt(1, animalId);
        ResultSet obsRs = obsStmt.executeQuery();
        JsonArray observations = new JsonArray();
        while (obsRs.next()) {
            JsonObject obs = new JsonObject();
            obs.addProperty("observation_id", obsRs.getInt("observation_id"));
            obs.addProperty("observation_code", obsRs.getString("observation_code"));
            Timestamp timestamp = obsRs.getTimestamp("observation_time");
            obs.addProperty("observation_time", timestamp != null ? timestamp.toString() : "");
            obs.addProperty("location", obsRs.getString("location"));
//            obs.addProperty("animal_condition", obsRs.getString("animal_condition"));
            obs.addProperty("notes", obsRs.getString("notes"));
            obs.addProperty("zona", obsRs.getString("zona"));
            observations.add(obs);
        }
        detail.add("observations", observations);
        obsRs.close();
        obsStmt.close();

        return detail;
    }

    private void setCorsHeaders(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
    }
}
