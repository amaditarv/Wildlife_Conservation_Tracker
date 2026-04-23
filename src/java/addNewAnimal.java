import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.*;
import java.time.LocalDate;
import java.time.Period;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.http.Part;

@WebServlet("/addAnimal")
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024,
    maxFileSize = 1024 * 1024 * 5,
    maxRequestSize = 1024 * 1024 * 5
)
public class addNewAnimal extends HttpServlet {
    // ... [previous methods remain the same until doPost]
    
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
        response.setCharacterEncoding("UTF-8");

        Gson gson = new Gson();
        JsonObject jsonResponse = new JsonObject();
        String status = "error";
        String message = "Animal gagal ditambahkan";

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            // Parse request parameters
            String name = request.getParameter("name");
            String gender = request.getParameter("gender");
            double weight = Double.parseDouble(request.getParameter("weight"));
            String statusAnimal = request.getParameter("status_animal");
            String dob = request.getParameter("dob");
            int speciesId = Integer.parseInt(request.getParameter("species_id"));
            int estimatedAge = Integer.parseInt(request.getParameter("estimated_age"));
            String warna = request.getParameter("color");
            String ciri = request.getParameter("distinguishing_features");
            String asal = request.getParameter("province");
            String aktivitas = request.getParameter("daily_activity");
            String polaMakan = request.getParameter("diet");
            String sosial = request.getParameter("social_behavior");
            String musim = request.getParameter("mating_season");
            
            Timestamp observationTime = null;
            String waktu = request.getParameter("observation_time");
            if (waktu != null && !waktu.trim().isEmpty()) {
                observationTime = Timestamp.valueOf(waktu.trim());
            }

            String lokasi = request.getParameter("location");
            String kondisi = request.getParameter("animal_condition");
            String notes = request.getParameter("notes");

            // 2️⃣ Upload foto
            Part filePart = request.getPart("photo_url");
            String fileName = "default.jpeg";
            String photoUrl = "uploads/" + fileName;

           if (filePart != null && filePart.getSize() > 0) {
            fileName = System.currentTimeMillis() + "_" + Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

            // Path absolut di Mac ke folder Images di htdocs
            String uploadPath = "/Applications/XAMPP/xamppfiles/htdocs/wct/images";


            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) uploadDir.mkdirs();

            String filePath = uploadPath + File.separator + fileName;

            // Simpan file ke lokasi fisik
            filePart.write(filePath);

            File uploadedFile = new File(filePath);
            uploadedFile.setReadable(true, false);
            uploadedFile.setWritable(true, true);
            uploadedFile.setExecutable(false, false);


            // Ini yang disimpan di database (URL relatif dari http://localhost/)
            photoUrl = "Images/" + fileName;
        }

            Date sqlDob = Date.valueOf(dob);

            // DB connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/wct", "root", "");

            // Start transaction


            try {
                // Generate Kode
                String newKode = generateAnimalCode(conn, speciesId);

                // 1. INSERT into animals table
                String sql = "INSERT INTO animals (kode, name, gender, estimated_age, weight, status_animal, date_of_birth, photo_url, species_id) " +
                             "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

                stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                stmt.setString(1, newKode);
                stmt.setString(2, name);
                stmt.setString(3, gender);
                stmt.setInt(4, estimatedAge);
                stmt.setDouble(5, weight);
                stmt.setString(6, statusAnimal);
                stmt.setDate(7, sqlDob);
                stmt.setString(8, photoUrl);
                stmt.setInt(9, speciesId);

                int rows = stmt.executeUpdate();
                if (rows == 0) {
                    throw new SQLException("Gagal menambahkan hewan, tidak ada baris yang terpengaruh.");
                }

                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (!generatedKeys.next()) {
                    throw new SQLException("Gagal mendapatkan ID hewan.");
                }
                
                int animalId = generatedKeys.getInt(1);

                // 2. INSERT into animal_details
                String detailCode = generateDetailCode(conn);
                String insertDetail = "INSERT INTO animal_details (animal_id, detail_code, color, distinguishing_features, province) VALUES (?, ?, ?, ?, ?)";
                try (PreparedStatement detailStmt = conn.prepareStatement(insertDetail)) {
                    detailStmt.setInt(1, animalId);
                    detailStmt.setString(2, detailCode);
                    detailStmt.setString(3, warna);
                    detailStmt.setString(4, ciri);
                    detailStmt.setString(5, asal);
                    detailStmt.executeUpdate();
                }

                // 3. INSERT into animal_observations
                String insertObs = "INSERT INTO animal_observations (animal_id, observation_time, location, notes) VALUES (?, ?, ?, ?)";
                try (PreparedStatement obsStmt = conn.prepareStatement(insertObs)) {
                    obsStmt.setInt(1, animalId);
                    obsStmt.setTimestamp(2, observationTime);
                    obsStmt.setString(3, lokasi);
//                    obsStmt.setString(4, kondisi);
                    obsStmt.setString(4, notes);
                    obsStmt.executeUpdate();
                }

                // 4. INSERT into animal_behavior
                String behaviorCode = generateBehaviorCode(conn);
                String insertBeh = "INSERT INTO animal_behavior (animal_id, behavior_code, daily_activity, diet, social_behavior, mating_season) VALUES (?, ?, ?, ?, ?, ?)";
                try (PreparedStatement behStmt = conn.prepareStatement(insertBeh)) {
                    behStmt.setInt(1, animalId);
                    behStmt.setString(2, behaviorCode);
                    behStmt.setString(3, aktivitas);
                    behStmt.setString(4, polaMakan);
                    behStmt.setString(5, sosial);
                    behStmt.setString(6, musim);
                    behStmt.executeUpdate();
                }

//                 Commit transaction

                
                status = "success";
                message = "Animal dan detail berhasil ditambahkan.";
            } catch (SQLException e) {
                // Rollback transaction if any error occur
                throw e;
            }

        } catch (Exception e) {
            message = "Error: " + e.getMessage();
            e.printStackTrace();
            jsonResponse.addProperty("errorDetails", e.toString());
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) {
            
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        jsonResponse.addProperty("status", status);
        jsonResponse.addProperty("message", message);
        response.getWriter().write(gson.toJson(jsonResponse));
    }

    // Method untuk generate kode otomatis (misal: A0001, A0002...)
private String generateAnimalCode(Connection conn, int speciesId) throws SQLException {
    // Map species_id ke huruf (1=A, 2=B, dst.)
    char prefix = (char) ('A' + (speciesId - 1)); // Misal: 1 → A, 2 → B, 3 → C

    String sql = "SELECT kode FROM animals WHERE kode LIKE ? ORDER BY animal_id DESC LIMIT 1";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, prefix + "%");
        ResultSet rs = stmt.executeQuery();
        
        if (rs.next()) {
            String lastKode = rs.getString("kode"); // Misal: B0021
            int number = Integer.parseInt(lastKode.substring(1)) + 1;
            return prefix + String.format("%04d", number); // B0022
        } else {
            return prefix + "0001"; // Awalan baru
        }
    }
}
private String generateDetailCode(Connection conn) throws SQLException {
    String prefix = "DT";
    String sql = "SELECT detail_code FROM animal_details WHERE detail_code LIKE ? ORDER BY detail_id DESC LIMIT 1";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, prefix + "%");
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            String lastCode = rs.getString("detail_code");
            int number = Integer.parseInt(lastCode.substring(2)) + 1;
            return prefix + String.format("%03d", number);
        } else {
            return prefix + "001";
        }
    }
}
private String generateBehaviorCode(Connection conn) throws SQLException {
    String prefix = "BH";
    String sql = "SELECT behavior_code FROM animal_behavior WHERE behavior_code LIKE ? ORDER BY behavior_id DESC LIMIT 1";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, prefix + "%");
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            String lastCode = rs.getString("behavior_code");
            int number = Integer.parseInt(lastCode.substring(2)) + 1;
            return prefix + String.format("%03d", number);
        } else {
            return prefix + "001";
        }
    }
}


}