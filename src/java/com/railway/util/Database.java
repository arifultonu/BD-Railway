
package com.railway.util;

import java.sql.Connection;
import java.sql.DriverManager;


public class Database {
   public static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/railway_ticket_booking",
                    "root", "123");
            return con;
        } catch (Exception ex) {
            System.out.println("Database.getConnection() Error -->" + ex.getMessage());
            return null;
        }
    }
 
    public static void close(Connection con) {
        try {
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
