/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.railway.model.route;

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
import com.railway.util.Database;
import java.io.Serializable;
import java.sql.Connection;

/**
 *
 * @author ArifulTonu
 */
@ManagedBean
@SessionScoped
public class RouteInfoBean implements Serializable {

    private String rname;
    private String stname;
    private String stname2;
    private int rid;
    private int stid;

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    public String getStname2() {
        return stname2;
    }

    public void setStname2(String stname2) {
        this.stname2 = stname2;
    }

    public int getStid() {
        return stid;
    }

    public void setStid(int stid) {
        this.stid = stid;
    }

    public String getStname() {
        return stname;
    }

    public void setStname(String stname) {
        this.stname = stname;
    }
    
    public String goUpadateRoute() {
        return "route-edit?faces-redirect=true";
    }

    public String goUpadateStation() {
        return "station-edit";
    }

//    public String addNewRoute() {
//        return "create";
//    }
    
    public List<RouteInfoBean> getAllRoute() {
        Connection con = null;
        Statement st = null;
        List<RouteInfoBean> list = new ArrayList<RouteInfoBean>();
        try {
            con = Database.getConnection();
            st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from route");
            while (rs.next()) {
                RouteInfoBean um1 = new RouteInfoBean();
                um1.setRid(rs.getInt("route_id"));
                um1.setRname(rs.getString("route_name"));
                list.add(um1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Database.close(con);
        }
        return list;
    }

    public String insertRoute() {
        Connection con = null;
        PreparedStatement ps = null;
        String sql = "insert into railway_ticket_booking.route (route_name) values (?);";

        try {
            con = Database.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, this.getRname());
            int r = ps.executeUpdate();
            if (r > 0) {
                return "index";
            } else {
                return "create";
            }
        } catch (SQLException ex) {
            Logger.getLogger(RouteInfoBean.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            Database.close(con);
        }
    }

    public String updateReoute() {
        Connection con = null;
        PreparedStatement ps = null;
        String sql = "update railway_ticket_booking.route set route_name = ? where route_id =" + rid + "";

        try {
            con = Database.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, this.getRname());
            int r = ps.executeUpdate();
            if (r > 0) {
                return "route-index";
            } else {
                return "route-edit";
            }
        } catch (SQLException ex) {
            Logger.getLogger(RouteInfoBean.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            Database.close(con);
        }
    }

    public String insertStation() {
        Connection con = null;
        PreparedStatement ps = null;
        //System.out.println("i am hair");
        String sql = "insert into railway_ticket_booking.station (st_name,route_id) values (?,?);";
        try {
            con = Database.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, this.getStname2());
            //ps.setInt(2, Integer.parseInt(this.getRname()));
            ps.setInt(2, this.getRid());
            int r = ps.executeUpdate();
            if (r > 0) {
                return "station-index";
            } else {
                return "station-create";
            }
        } catch (SQLException ex) {
            Logger.getLogger(RouteInfoBean.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            Database.close(con);
        }
    }

    public String updateStation() {
        Connection con = null;
        PreparedStatement ps = null;
        String sql = "update railway_ticket_booking.station set st_name = ? where st_id =" + stid + "";
        try {
            con = Database.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, this.getStname());
            int r = ps.executeUpdate();
            if (r > 0) {
                return "station-index";
            } else {
                return "station-edit";
            }
        } catch (SQLException ex) {
            Logger.getLogger(RouteInfoBean.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            Database.close(con);
        }
    }

    

    

    public void deleteRoute() {
        Connection con = null;
        PreparedStatement ps = null;
        String sql = "DELETE FROM railway_ticket_booking.route WHERE route_id=?;";
        try {
            con = Database.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, this.getRid());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RouteInfoBean.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Database.close(con);
        }
    }

    public void deleteStation() {
        Connection con = null;
        PreparedStatement ps = null;
        String sql = "DELETE FROM railway_ticket_booking.station WHERE st_id=?;";
        try {
            con = Database.getConnection();
            con.prepareStatement(sql);
            ps.setInt(1, this.getStid());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RouteInfoBean.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Database.close(con);
        }
    }

    public List<RouteInfoBean> getAllStation() {
        Connection con = null;
        Statement st = null;
        List<RouteInfoBean> list = new ArrayList<RouteInfoBean>();
        try {
            con = Database.getConnection();
            st = con.createStatement();
            String sql = "select st.st_id,st.st_name, r.route_name from route r join station st where r.route_id = st.route_id;"; //"SELECT * FROM station;";
            //"SELECT * FROM station s where route_id=(select route_id from route where route_name='" + this.rname + "');";
            ResultSet rs1 = st.executeQuery(sql);
            while (rs1.next()) {
                RouteInfoBean sb2 = new RouteInfoBean();
                sb2.setStid(rs1.getInt("st.st_id"));
                sb2.setStname(rs1.getString("st.st_name"));
                sb2.setRname(rs1.getString("r.route_name"));
                list.add(sb2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Database.close(con);
        }
        return list;
    }
}
