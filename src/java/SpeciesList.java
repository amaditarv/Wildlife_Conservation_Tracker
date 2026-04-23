import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/SpeciesList")
public class SpeciesList extends HttpServlet {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/wct";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    // CORS: OPTIONS handler
    @Override
    protected void doOptions(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        setCorsHeaders(response);
        response.setStatus(HttpServletResponse.SC_OK);
    }

    // CORS headers setter
    private void setCorsHeaders(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS, PUT, DELETE");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization, X-Requested-With");
        response.setHeader("Access-Control-Max-Age", "3600");
    }

    // Handle GET Request
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // CORS headers
        setCorsHeaders(response);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        Gson gson = new Gson();
        JsonObject responseJson = new JsonObject();
        JsonArray speciesArray = new JsonArray();

        try {
            // Register JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection
            try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
                String query = """
                    SELECT 
                        s.id AS species_id,
                        MIN(a.kode) AS kode_awal,
                        MAX(a.kode) AS kode_akhir,
                        COUNT(a.animal_id) AS total
                    FROM species s
                    LEFT JOIN animals a ON s.id = a.species_id
                    GROUP BY s.id
                    ORDER BY s.id
                """;

                try (PreparedStatement stmt = conn.prepareStatement(query);
                     ResultSet rs = stmt.executeQuery()) {

                    while (rs.next()) {
                        JsonObject species = new JsonObject();
                        species.addProperty("id", rs.getInt("species_id"));

                        String kodeAwal = rs.getString("kode_awal");
                        String kodeAkhir = rs.getString("kode_akhir");

                        species.addProperty("kode_awal", kodeAwal != null ? kodeAwal : "-");
                        species.addProperty("kode_akhir", kodeAkhir != null ? kodeAkhir : "-");
                        species.addProperty("total", rs.getInt("total"));

                        speciesArray.add(species);
                    }

                    responseJson.addProperty("status", "success");
                    responseJson.add("data", speciesArray);
                }
            }

        } catch (SQLException | ClassNotFoundException e) {
            responseJson.addProperty("status", "error");
            responseJson.addProperty("message", e.getMessage());
            e.printStackTrace();
        }

        // Output response
        try (PrintWriter out = response.getWriter()) {
            out.print(gson.toJson(responseJson));
            out.flush();
        }
    }
}
