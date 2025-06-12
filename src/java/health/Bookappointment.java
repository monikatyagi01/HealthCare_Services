/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package health;

import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 *
 * @author Mukul
 */
public class Bookappointment extends HttpServlet {

     private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession(false);

        if (session != null) {
            String Email = request.getParameter("p1");
            String patientname = request.getParameter("p2");
            String phoneno = request.getParameter("p3");
            String department = request.getParameter("p4");
            String appt_dt   = request.getParameter("p5");
            String time = request.getParameter("p6");
           
            Connection con = null;
            PreparedStatement ps = null;

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/HealthCare","root","Mukul4628H");
                ps = con.prepareStatement(
                        "INSERT INTO healthcare VALUES (?, ?, ?, ?, ?, ?)");

                ps.setString(1, Email);
                ps.setString(2, patientname);
                ps.setString(3, phoneno);
                ps.setString(4, department);
                ps.setString(5, appt_dt);
                ps.setString(6, time);
               

                int rowsAffected = ps.executeUpdate();

                if (rowsAffected > 0) {
                    
                      out.println("<html>");
                      out.println("<head>");
                      out.println("<title>Book Appointment</title>");
                      out.println("<style>");
                      out.println("body {");
                      out.println("  background-color: #f0f0f0;");
                      out.println("  font-family: Arial, sans-serif;");
                      out.println("}");
                      out.println(".message {");
                      out.println("  background-color: orange;");
                      out.println("  color: Orange;");
                      out.println("  text-align: center;");
                      out.println("  padding: 20px;");
                      out.println("  margin-top: 20px;");
                      out.println("}");
                      out.println("</style>");
                      out.println("</head>");
                      out.println("<body>");
                      out.println("<div class='message'>");
                      out.println("<h1>Appoint Book Successfully</h1><br>");
                      out.println("<h1>Add New Flight</h1>");
                      out.println("</div>");
                      out.println("</body>");
                      out.println("</html>");

                      RequestDispatcher rd = request.getRequestDispatcher("addflight.html");
                      rd.include(request, response);
                } else {
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Failed to bookAppoint</title>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<h1>Failed to save data</h1>");
                    out.println("</body>");
                    out.println("</html>");
                }
            } catch (ClassNotFoundException | SQLException e) {
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Error</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Error: " + e.getMessage() + "</h1>");
                out.println("</body>");
                out.println("</html>");
            } finally {
                try {
                    if (ps != null) {
                        ps.close();
                    }
                    if (con != null) {
                        con.close();
                    }
                } catch (SQLException e) {
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Error</title>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<h1>Error closing database connection: " + e.getMessage() + "</h1>");
                    out.println("</body>");
                    out.println("</html>");
                }
            }
        } else {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Login Required</title>");
            out.println("<style>");
            out.println("body {");
            out.println("  background-color: #f0f0f0;");
            out.println("  font-family: Arial, sans-serif;");
            out.println("}");
            out.println(".message {");
            out.println("  background-color: black;");
            out.println("  color: #fff;");
            out.println("  text-align: center;");
            out.println("  padding: 20px;");
            out.println("  margin-top: 20px;");
            out.println("}");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='message'>");
            out.println("<h1>Kindly login first</h1>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");

            RequestDispatcher rd = request.getRequestDispatcher("Boolappointment.html");
            rd.include(request, response);
        }
    }
}
