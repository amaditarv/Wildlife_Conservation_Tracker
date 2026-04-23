/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
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
/**
 *
 * @author amadita
 */
@WebServlet("/getAnimalCount")
public class getAnimalCount extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        JsonObject json = new JsonObject();

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/wct", "root", "");
             PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) AS total FROM animals");
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                int total = rs.getInt("total");
                json.addProperty("status", "success");
                json.addProperty("total", total);
            } else {
                json.addProperty("status", "error");
                json.addProperty("message", "No data found");
            }

        } catch (Exception e) {
            e.printStackTrace();
            json.addProperty("status", "error");
            json.addProperty("message", "Database error: " + e.getMessage());
        }

        response.getWriter().write(json.toString());
    }
}

