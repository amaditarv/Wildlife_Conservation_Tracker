import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import jakarta.servlet.http.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/login")
public class Login extends HttpServlet {

    @Override
    protected void doOptions(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        setCorsHeaders(response);
        response.setStatus(HttpServletResponse.SC_OK);
    }

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        setCorsHeaders(response);

        // Baca JSON body
        StringBuilder jsonBuffer = new StringBuilder();
        String line;
        try (BufferedReader reader = request.getReader()) {
            while ((line = reader.readLine()) != null) {
                jsonBuffer.append(line);
            }
        }

        Gson gson = new Gson();
        
        JsonObject jsonObject = gson.fromJson(jsonBuffer.toString(), JsonObject.class);

        String email = jsonObject.get("email").getAsString();
        String pass = jsonObject.get("pass").getAsString();

        String message = "Username or Password invalid";
        String status = "error";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/wct"; // ganti dengan nama database kamu
            String user = "root"; // user MySQL (default XAMPP)
            String password = ""; // password MySQL (default kosong di XAMPP)

            Connection conn = DriverManager.getConnection(url, user, password);
            String sql = "SELECT * FROM users WHERE email = ? AND pass = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, pass);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Kalau login sukses
                message = "Login successful";
                status = "success";
                
                HttpSession session = request.getSession();
                session.setAttribute("user_id", rs.getInt("user_id"));
                session.setAttribute("full_name", rs.getString("full_name"));
                session.setAttribute("email", rs.getString("email"));
                session.setAttribute("phone_number", rs.getString("phone_number"));
                session.setAttribute("photo_url", rs.getString("photo_url"));
                session.setAttribute("role", rs.getString("role"));
                session.setAttribute("status_user", rs.getString("status_user"));

            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
            message = "Database error: " + e.getMessage();
        }

        // Kirim response JSON
        

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        JsonObject jsonResponse = new JsonObject();
        jsonResponse.addProperty("message", message);
        jsonResponse.addProperty("status", status);

        if ("success".equals(status)) {
            HttpSession session = request.getSession();
            jsonResponse.addProperty("full_name", (String) session.getAttribute("full_name"));
            jsonResponse.addProperty("email", (String) session.getAttribute("email"));
            jsonResponse.addProperty("photo_url", (String) session.getAttribute("photo_url"));
            jsonResponse.addProperty("phone_number", (String) session.getAttribute("phone_number"));
            jsonResponse.addProperty("role", (String) session.getAttribute("role"));
            jsonResponse.addProperty("status_user", (String) session.getAttribute("status_user"));
        }
        response.getWriter().write(gson.toJson(jsonResponse));
    }

    private void setCorsHeaders(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
    }
}
