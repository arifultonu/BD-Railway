/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.railway.model.classname;

import com.railway.util.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class ClassNameBean {

    String classname;
    int classseat;
    int classid;

    /**
     * Creates a new instance of DisplayClass
     */
    public ClassNameBean() {
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public int getClassseat() {
        return classseat;
    }

    public void setClassseat(int classseat) {
        this.classseat = classseat;
    }

    public int getClassid() {
        return classid;
    }

    public void setClassid(int classid) {
        this.classid = classid;
    }

    public String goEditPage() {
        return "class-edit?faces-redirect=true";
    }

    public List<ClassNameBean> getAllclassInfo() {
        Connection con = null;
        Statement st = null;
        List<ClassNameBean> list = new ArrayList<ClassNameBean>();
        String sql = "select class_id , class_name, class_seat from class_name";
        try {
            con = Database.getConnection();
            st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                ClassNameBean dis = new ClassNameBean();
                dis.setClassname(rs.getString("class_name"));
                dis.setClassseat(rs.getInt("class_seat"));
                dis.setClassid(rs.getInt("class_id"));
                list.add(dis);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Database.close(con);
        }
        return list;
    }

    public String insertClassInfo() {
        Connection con = null;
        PreparedStatement ps = null;
        String sql = "insert into class_name( class_name, class_seat) values (?, ?)";
        try {
            con = Database.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, this.getClassname());
            ps.setInt(2, this.getClassseat());
            int i = ps.executeUpdate();
            if (i > 0) {
                return "class-index?faces-redirect=true";
            } else {
                return "class-create?faces-redirect=true";
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Database.close(con);
        }
        return "insertClassInfo";
    }

    public void deleteClass() {
        Connection con = null;
        PreparedStatement ps = null;
        String sql = "DELETE FROM railway_ticket_booking.class_name WHERE class_id=?";

        try {
            con = Database.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, this.getClassid());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ClassNameBean.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Database.close(con);
        }
    }

    public String updateClass() {
        Connection con = null;
        PreparedStatement ps = null;
        String sql = "update railway_ticket_booking.class_name set class_name=?, class_seat=? where class_id=" + classid;
        try {
            con = Database.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, this.getClassname());
            ps.setInt(2, this.getClassseat());
//            ps.setInt(3, this.getClassid());
            int i = ps.executeUpdate();
            if (i > 0) {
                return "class-index?faces-redirect=true";
            } else {
                return "class-edit?faces-redirect=true";
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Database.close(con);
        }
        return "updateClass";
    }
}
