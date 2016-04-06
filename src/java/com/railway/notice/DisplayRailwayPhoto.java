/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.railway.notice;

import com.railway.util.Database;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author MASHUK
 */
public class DisplayRailwayPhoto extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    ResultSet rs;
    InputStream stdPhoto;
    try {
      String noticeId = request.getParameter("noti_id");
      Statement st = Database.getConnection().createStatement();
      rs = st.executeQuery("SELECT notice_img FROM railway_notice  WHERE notice_id='" + noticeId + "';");
      if (rs.next()) {
        byte[] bytearray = new byte[1048576];
        int size = 0;
        stdPhoto = rs.getBinaryStream("notice_img");
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
