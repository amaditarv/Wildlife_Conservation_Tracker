import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.io.BufferedReader;
import java.sql.*;
import java.sql.Timestamp;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.text.SimpleDateFormat;
import java.util.Date;


@WebServlet("/updateAnimal")
public class updateAnimal extends HttpServlet {

    @Override
    protected void doOptions(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        setCorsHeaders(response);
        response.setStatus(HttpServletResponse.SC_OK);
    }

    private void setCorsHeaders(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization, X-Requested-With");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Credentials", "true");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        setCorsHeaders(response);
        response.setContentType("application/json");
        Gson gson = new Gson();
        JsonObject result = new JsonObject();

        StringBuilder sb = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        String requestBody = sb.toString();
        System.out.println("Request Body: " + requestBody);
        

        try {
            JsonObject json = gson.fromJson(requestBody, JsonObject.class);

            if (!json.has("animal_id") || json.get("animal_id").isJsonNull()) {
                result.addProperty("status", "error");
                result.addProperty("message", "animal_id tidak boleh kosong.");
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write(gson.toJson(result));
                return;
            }

            int animalId = json.get("animal_id").getAsInt();
            String name = json.has("name") ? json.get("name").getAsString() : "";
            double berat = json.has("weight") ? json.get("weight").getAsDouble() : 0;
            double tinggi = json.has("height") ? json.get("height").getAsDouble() : 0;
            double panjang = json.has("length") ? json.get("length").getAsDouble() : 0;
            String zona = json.has("zona") ? json.get("zona").getAsString() : "";

            // Sebelum menyimpan status, normalisasi dulu
            String status = json.has("status_animal") ? json.get("status_animal").getAsString(): "";

            String warna = json.has("color") ? json.get("color").getAsString() : "";
            String ciri = json.has("distinguishing_features") ? json.get("distinguishing_features").getAsString() : "";
            String asal = json.has("province") ? json.get("province").getAsString() : "";
            String aktivitas = json.has("daily_activity") ? json.get("daily_activity").getAsString() : "";
            String polaMakan = json.has("diet") ? json.get("diet").getAsString() : "";
            String sosial = json.has("social_behavior") ? json.get("social_behavior").getAsString() : "";
            String musim = json.has("mating_season") ? json.get("mating_season").getAsString() : "";

           Timestamp observationTime = null;

if (json.has("observation_time")) {
    try {
        String waktu = json.get("observation_time").getAsString().trim();
        System.out.println("observation_time received: [" + waktu + "]");

        // Handle empty or "null" string
        if (waktu.isEmpty() || "null".equalsIgnoreCase(waktu)) {
            observationTime = null;
        } else {
            // Try parsing with different formats if needed
            try {
                // First try standard format
                observationTime = Timestamp.valueOf(waktu);
            } catch (IllegalArgumentException e) {
                // If standard format fails, try parsing with timezone or other formats
                // Example: "yyyy-MM-dd'T'HH:mm:ss" (ISO format)
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                Date date = sdf.parse(waktu);
                observationTime = new Timestamp(date.getTime());
            }
        }
    } catch (Exception e) {
        System.out.println("Error parsing observation_time: " + e.getMessage());
        observationTime = null;
    }
}


            String lokasi = json.has("location") ? json.get("location").getAsString() : "";
//            String kondisi = json.has("animal_condition") ? json.get("animal_condition").getAsString() : "";
            String notes = json.has("notes") ? json.get("notes").getAsString() : "";

            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/wct", "root", "")) {
                boolean updated = false;
                

                try (PreparedStatement stmt = conn.prepareStatement("UPDATE animals SET name = ?, weight = ?, status_animal =? WHERE animal_id = ?")) {
                    stmt.setString(1, name);
                    stmt.setDouble(2, berat);
                    stmt.setString(3, status);
                    stmt.setInt(4, animalId);
                    updated |= stmt.executeUpdate() > 0;
                }

                try (PreparedStatement stmt = conn.prepareStatement("UPDATE animal_details SET height = ?, length = ?, color = ?, distinguishing_features = ?, province = ? WHERE animal_id = ?")) {
                    stmt.setDouble(1, tinggi);
                    stmt.setDouble(2, panjang);
                    stmt.setString(3, warna);
                    stmt.setString(4, ciri);
                    stmt.setString(5, asal);
                    stmt.setInt(6, animalId);
                    updated |= stmt.executeUpdate() > 0;
                }

                try (PreparedStatement stmt = conn.prepareStatement("UPDATE animal_behavior SET daily_activity = ?, diet = ?, social_behavior = ?, mating_season = ? WHERE animal_id = ?")) {
                    stmt.setString(1, aktivitas);
                    stmt.setString(2, polaMakan);
                    stmt.setString(3, sosial);
                    stmt.setString(4, musim);
                    stmt.setInt(5, animalId);
                    updated |= stmt.executeUpdate() > 0;
                }

                try (PreparedStatement stmt = conn.prepareStatement("UPDATE animal_observations SET observation_time = ?, location = ?, notes = ?, zona=? WHERE animal_id = ?")) {
                    stmt.setTimestamp(1, observationTime);
                    stmt.setString(2, lokasi);
//                    stmt.setString(3, kondisi);
                    stmt.setString(3, notes);
                    stmt.setString(4, zona);
                    stmt.setInt(5, animalId);
                    updated |= stmt.executeUpdate() > 0;
                }

                if (updated) {
                    result.addProperty("status", "success");
                    result.addProperty("message", "Data berhasil diperbarui.");
                } else {
                    result.addProperty("status", "error");
                    result.addProperty("message", "Tidak ada data yang diperbarui.");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            result.addProperty("status", "error");
            result.addProperty("message", "Terjadi kesalahan: " + e.getMessage());
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

        response.getWriter().write(gson.toJson(result));
    }
}
