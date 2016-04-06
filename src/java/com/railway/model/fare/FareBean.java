/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.railway.model.fare;

import com.railway.util.Database;
import java.io.Serializable;
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
@SessionScoped
@ManagedBean
public class FareBean implements Serializable {

    String stationfrom, stationto, classname;
    int fareid, fareamount;
    
    //int fareid,stationfrom, stationto, classname, fareamount;

    public FareBean() {
    }

    public String getStationfrom() {
        return stationfrom;
    }

    public void setStationfrom(String stationfrom) {
        this.stationfrom = stationfrom;
    }

    public String getStationto() {
        return stationto;
    }

    public void setStationto(String stationto) {
        this.stationto = stationto;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public int getFareamount() {
        return fareamount;
    }

    public void setFareamount(int fareamount) {
        this.fareamount = fareamount;
    }

    public int getFareid() {
        return fareid;
    }

    public void setFareid(int fareid) {
        this.fareid = fareid;
    }

    public String goEditPage() {
        return "fare-edit?faces-redirect=true";
    }

    public List<FareBean> getAllFareInfo() {
        Connection con = null;
        Statement st = null;
        List<FareBean> list = new ArrayList<FareBean>();
        String sql = "SELECT fare_id,(select st_name from railway_ticket_booking.station st where st.st_id=f.st_id_from) as station_from, "
                + "(select st_name from railway_ticket_booking.station sf where sf.st_id=f.st_id_to) as station_to, "
                + "(select class_name from railway_ticket_booking.class_name c where c.class_id=f.class_id) "
                + "as class_name, fare_amount FROM railway_ticket_booking.fare f order by station_from";
        try {
            //PreparedStatement ps= Database.getConnection().prepareStatement(sql);
             con = Database.getConnection();
             st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                FareBean fb = new FareBean();
                fb.setStationfrom(rs.getString("station_from"));
                fb.setStationto(rs.getString("station_to"));
                fb.setClassname(rs.getString("class_name"));
                fb.setFareamount(rs.getInt("fare_amount"));
                fb.setFareid(rs.getInt("fare_id"));
                list.add(fb);
                //fare_id, st_id_to, st_id_from, class_id, fare_amount
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally{
        Database.close(con);
        }

        return list;
    }

    //////////////////////////////////////////////////////
    public void delete() {
        Connection con = null;
        PreparedStatement ps = null;        
        String sql = "delete FROM railway_ticket_booking.fare where fare_id=?";        
        try {
            ps = Database.getConnection().prepareStatement(sql);
            ps.setInt(1, this.getFareid());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FareBean.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
        Database.close(con);
        }
    }
    //////////////////////////////////////////////////////

    public String update() {
        Connection con = null;
        PreparedStatement ps = null;
        String sqlupdate = "UPDATE railway_ticket_booking.fare set fare_amount=? where fare_id= "+fareid ;
         
        try {
            ps = Database.getConnection().prepareStatement(sqlupdate);
            ps.setDouble(1, this.getFareamount());           
            int i = ps.executeUpdate();
            
            if (i > 0) {                
                return "fare-index?faces-redirect=true";                
            } else {
                return "fare-edit?faces-redirect=true";
            }

        } catch (Exception ex) {
             Logger.getLogger(FareBean.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
        Database.close(con);
        }
        
    }
}
