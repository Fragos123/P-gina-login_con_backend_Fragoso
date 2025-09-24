/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.sistemalogin1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String usuario = request.getParameter("usuario");
        String password = request.getParameter("password");
        
        response.setContentType("text/html;charset=UTF-8");
        
        try (PrintWriter out = response.getWriter()) {
            
            Connection conn = DBConnection.getConnection();
            String sql = "SELECT * FROM usuarios WHERE usuario = ? AND password = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, usuario);
            stmt.setString(2, password);
            
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                // Login exitoso
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Bienvenido</title>");
                out.println("<style>");
                out.println("body { font-family: Arial, sans-serif; text-align: center; margin-top: 50px; background-color: #f4f6f9; }");
                out.println(".container { background: white; padding: 30px; border-radius: 10px; box-shadow: 0 4px 6px rgba(0,0,0,0.2); max-width: 400px; margin: 0 auto; }");
                out.println("h2 { color: #28a745; }");
                out.println("a { color: #007BFF; text-decoration: none; }");
                out.println("a:hover { text-decoration: underline; }");
                out.println("</style>");
                out.println("</head>");
                out.println("<body>");
                out.println("<div class='container'>");
                out.println("<h2>✅ Bienvenido!</h2>");
                out.println("<p>Has iniciado sesión correctamente como: <strong>" + usuario + "</strong></p>");
                out.println("<br>");
                out.println("<a href='index.html'>🔙 Cerrar sesión</a>");
                out.println("</div>");
                out.println("</body></html>");
            } else {
                // Login fallido
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Error de Login</title>");
                out.println("<style>");
                out.println("body { font-family: Arial, sans-serif; text-align: center; margin-top: 50px; background-color: #f4f6f9; }");
                out.println(".container { background: white; padding: 30px; border-radius: 10px; box-shadow: 0 4px 6px rgba(0,0,0,0.2); max-width: 400px; margin: 0 auto; }");
                out.println("h2 { color: #dc3545; }");
                out.println("a { color: #007BFF; text-decoration: none; margin: 10px; display: inline-block; }");
                out.println("a:hover { text-decoration: underline; }");
                out.println("</style>");
                out.println("</head>");
                out.println("<body>");
                out.println("<div class='container'>");
                out.println("<h2>❌ Error de Login</h2>");
                out.println("<p>Usuario o contraseña incorrectos</p>");
                out.println("<br>");
                out.println("<a href='index.html'>🔙 Volver al login</a>");
                out.println("<a href='registro.html'>📝 Registrarse</a>");
                out.println("</div>");
                out.println("</body></html>");
            }
            
            rs.close();
            stmt.close();
            conn.close();
            
        } catch (Exception e) {
            throw new ServletException("Error en la conexión o consulta: " + e.getMessage(), e);
        }
    }
}