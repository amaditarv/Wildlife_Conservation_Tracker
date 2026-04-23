import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/WCT/searchAnimal")
public class searchAnimal extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String query = request.getParameter("query");
        PrintWriter out = response.getWriter();

        if (query == null || query.trim().length() < 1) {
            out.print("[]");
            out.flush();
            return;
        }

        JsonArray resultArray = new JsonArray();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/wct", "root", "");

            String sql = "SELECT animal_id, name, photo_url FROM animals WHERE LOWER(name) LIKE ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + query.toLowerCase() + "%");

            rs = stmt.executeQuery();

            while (rs.next()) {
                JsonObject obj = new JsonObject();
                obj.addProperty("animal_id", rs.getInt("animal_id"));
                obj.addProperty("name", rs.getString("name"));
                obj.addProperty("photo_url", rs.getString("photo_url"));
                resultArray.add(obj);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {}
            try { if (stmt != null) stmt.close(); } catch (Exception e) {}
            try { if (conn != null) conn.close(); } catch (Exception e) {}
        }

        out.print(resultArray.toString());
        out.flush();
    }
}
