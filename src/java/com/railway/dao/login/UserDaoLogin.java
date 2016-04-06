/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.railway.dao.login;

import com.railway.util.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Tonu
 */
public class UserDaoLogin {

    public static boolean userLogin(String username, String password) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = Database.getConnection();
            ps = con.prepareStatement("select uname, pass from railway_ticket_booking.user where uname= ? and pass= ? ");
            ps.setString(1, username);
            ps.setString(2, password);       

            ResultSet rs = ps.executeQuery();
            if (rs.next()) // found
            {
                System.out.println(rs.getString("uname"));
                System.out.println(rs.getString("pass"));
//                System.out.println(rs.getInt("role_id"));
                
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            System.out.println("Error in login() -->" + ex.getMessage());
            return false;
        } finally {
            Database.close(con);
        }
    }
}
