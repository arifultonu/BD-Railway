/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.railway.model.ticketmanager;

import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.railway.util.Database;
import java.sql.SQLException;

/**
 *
 * @author Enamul
 */

public class DisplayTicketManagerPhoto extends HttpServlet {

    @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    ResultSet rs;
    InputStream stdPhoto;
    try {
      String id = request.getParameter("tman_pic");
      Statement st = Database.getConnection().createStatement();
      rs = st.executeQuery("SELECT photo FROM ticket_manager  WHERE tman_id='" + id + "';");
      if (rs.next()) {
        byte[] bytearray = new byte[1048576];
        int size = 0;
        stdPhoto = rs.getBinaryStream("photo");
        if (stdPhoto != null) {
          response.reset();
          response.setContentType("image/jpeg");
          while ((size = stdPhoto.read(bytearray)) != -1) {
            response.getOutputStream().write(bytearray, 0, size);
          }
        }

      }
    } catch (SQLException ex) {
      System.out.println("Error from noticeServlet----------->" + ex.getMessage());
    }
  }
}
