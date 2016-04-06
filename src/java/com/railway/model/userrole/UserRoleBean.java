/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.railway.model.userrole;

import java.sql.Connection;
import com.railway.util.Database;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Tonu
 */
@ManagedBean
@SessionScoped
public class UserRoleBean {

    private int roleid;
    private String rolename;

    public UserRoleBean() {
    }

    public int getRoleid() {
        return roleid;
    }

    public void setRoleid(int roleid) {
        this.roleid = roleid;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public String goEditPage() {
        return "userRole-edit?faces-redirect=true";
    }

    public void clearAll() {
        int roleid = 0;
        String rolename = "";
    }

    public List<UserRoleBean> getAllUserRoleInfo() {
        Connection con = null;
        Statement stm = null;
        List<UserRoleBean> list = new ArrayList<UserRoleBean>();
        String sql = "select role_id, role_name from user_role";

        try {
             con = Database.getConnection();
            stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                UserRoleBean urole = new UserRoleBean();
                urole.setRoleid(rs.getInt("role_id"));
                urole.setRolename(rs.getString("role_name"));
                list.add(urole);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally{
        Database.close(con);
        }
        clearAll();
        return list;
    }

    public String addUserRole() {
        Connection con = null;
        PreparedStatement ps = null;
        String sql = "INSERT into railway_ticket_booking.user_role( role_id, role_name) values (?, ?)";
        try {
             con = Database.getConnection();
             ps = con.prepareStatement(sql);
            ps.setInt(1, this.getRoleid());
            ps.setString(2, this.getRolename());

            int i = ps.executeUpdate();
            if (i > 0) {
                return "userRole-index?faces-redirect=true";
            } else {
                return "userRole-create?faces-redirect=true";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
        Database.close(con);
        }
        clearAll();
        return "addUserRole";

    }

    public void deleteUserRole() {
        
        PreparedStatement ps = null;
        String sql = "DELETE from railway_ticket_booking.user_role where role_id = ?";

        try {
             ps = Database.getConnection().prepareStatement(sql);
            ps.setInt(1, this.getRoleid());
            ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(UserRoleBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        clearAll();
    }

    public String editUserRole() {
       
        PreparedStatement ps = null;
        String sql = "UPDATE railway_ticket_booking.user_role set role_name=? where role_id=" + roleid;
        try {
             ps = Database.getConnection().prepareStatement(sql);
            ps.setString(1, this.getRolename());

            int i = ps.executeUpdate();
            if (i > 0) {
                return "userRole-index?faces-redirect=true";
            } else {
                return "userRole-edit?faces-redirect=true";
            }
        } catch (Exception ex) {
            Logger.getLogger(UserRoleBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        clearAll();
        return "editUserRole";
    }
}
