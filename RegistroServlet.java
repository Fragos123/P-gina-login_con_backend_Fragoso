/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.sistemalogin1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "RegistroServlet", urlPatterns = {"/RegistroServlet"})
public class RegistroServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String usuario = request.getParameter("usuario");
        String password = request.getParameter("password");

        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            Connection conn = DBConnection.getConnection();

            String sql = "INSERT INTO usuarios (usuario, password) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, usuario);
            stmt.setString(2, password);

            int filas = stmt.executeUpdate();

            if (filas > 0) {
                out.println("<!DOCTYPE html>");
                out.println("<html><head><title>Registro Exitoso</title></head><body>");
                out.println("<h2>✅ Usuario registrado con éxito: " + usuario + "</h2>");
                out.println("<a href='index.html'>Ir a Login</a>");
                out.println("</body></html>");
            } else {
                out.println("<!DOCTYPE html>");
                out.println("<html><head><title>Error</title></head><body>");
                out.println("<h2>❌ No se pudo registrar el usuario</h2>");
                out.println("<a href='registro.html'>Intentar de nuevo</a>");
                out.println("</body></html>");
            }

            conn.close();
        } catch (Exception e) {
            throw new ServletException("Error al registrar usuario: " + e.getMessage(), e);
        }
    }
}
