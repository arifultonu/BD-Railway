/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.railway.model.fare;

/**
 *
 * @author Tonu
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import com.railway.util.Database;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@SessionScoped
@ManagedBean
public class AddFareBean implements Serializable {

    private int routeid, stattionidfrom, roleid, classid, fareamount, stationidto;
    String routename, station, rolename, classname, station2;

    /**
     * Creates a new instance of FareInfoBean2
     */
    public AddFareBean() {
    }

    public String getRoutename() {
        return routename;
    }

    public void setRoutename(String routename) {
        this.routename = routename;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
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

    public String getStation2() {
        return station2;
    }

    public void setStation2(String station2) {
        this.station2 = station2;
    }

   /*  
     //** This Method used to get all Route name from database
     */

    public List<AddFareBean> getAllRoute() {
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        List<AddFareBean> list = new ArrayList<AddFareBean>();

        try {
             con = Database.getConnection();
             stm = con.createStatement();
             rs = stm.executeQuery("select route_name from railway_ticket_booking.route");
            while (rs.next()) {
                AddFareBean um1 = new AddFareBean();
                um1.setRoutename(rs.getString("route_name"));
                list.add(um1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
        Database.close(con);
        }
        return list;
    }
    /*  
     //** This Method used to get all Station name from database based on Route name in where close
     */

    public List<AddFareBean> getAllStation() {
        Connection con = null;
        List<AddFareBean> list = new ArrayList<AddFareBean>();
        try {
             con = Database.getConnection();
            Statement st = con.createStatement();
            String sql = "SELECT st_name FROM station where route_id=(select route_id from route where route_name='" + this.routename + "');";
            ResultSet rs1 = st.executeQuery(sql);
            while (rs1.next()) {
                AddFareBean sb2 = new AddFareBean();
                // System.out.println(rs1.getString("st_name"));
                sb2.setStation(rs1.getString("st_name"));
                list.add(sb2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            Database.close(con);
        }
        return list;
    }

    /**
     *
     ** This Method used to get all Station name from database based on route
     *
     * @return array list
     */
    public List<AddFareBean> getAllStation2() {
        Connection con = null;
        List<AddFareBean> list = new ArrayList<AddFareBean>();

        try {
             con = Database.getConnection();
            Statement st = con.createStatement();
            String sql = "SELECT st_name FROM railway_ticket_booking.station s where "
                    + "route_id=(select route_id from railway_ticket_booking.route where route_name='" + this.routename + "') and st_name "
                    + "<> '" + station + "';";
            ResultSet rs1 = st.executeQuery(sql);
            while (rs1.next()) {
                AddFareBean sb2 = new AddFareBean();
                // System.out.println(rs1.getString("st_name"));
                sb2.setStation(rs1.getString("st_name"));
                list.add(sb2);
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
        Database.close(con);
        }
        return list;
    }

    /**
     *
     ** This Method used to get all classes name from database
     *
     * @return array list
     */
    public List<SelectItem> getAllClass() { 
        Connection con = null;
        List<SelectItem> data = new ArrayList<SelectItem>();
        try {
             con = Database.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = null;
            rs = stm.executeQuery("select class_name from class_name");
            while (rs.next()) {
                data.add(new SelectItem(rs.getString("class_name")));
            }
        } catch (Exception e) {
        }finally{
        Database.close(con);
        }
        return data;
    }

    /**
     ** This Method used to Insert in Fare Table to database
     */
    public String save() {
        try {
            Connection con = null;
            Statement st = null;
            ResultSet rs = null;
            con = Database.getConnection();
            st = con.createStatement();
            int count = 0;
//            String sql = "SELECT * FROM fare where st_id_from='" + stattionidfrom + "' and st_id_to='" 
//                    + stationidto + "' and class_id='" + classid + "'";
            //On the abave writen query may return worong data beause 
            // This query may return another routes station 
            String sql = "select fare_id from fare f inner join station s on f.st_id_from = s.st_id "
                    + "inner join station st on f.st_id_to = st.st_id "
                    + "where st_id_from = (select st_id from station "
                    + "where st_name='" + station + "' and route_id= (select route_id from route "
                    + "where route_name = '" + routename + "')) and st_id_to = (select st_id from station "
                    + "where st_name='" + station2 + "' and route_id= (select route_id from route "
                    + "where route_name = '" + routename + "')) and class_id = (select c.class_id from class_name c "
                    + "where c.class_name = '" + classname + "');";
            rs = st.executeQuery(sql);
            while (rs.next()) {
                //System.out.println("privious data found");
                count = rs.getRow();
            }
            if (count > 0) {
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage("Data is Exist!", "Please Try Again"));
                clear();
            } else {
                //System.out.println("i am here to insert!");
                String sql2 = "INSERT INTO fare (st_id_from, st_id_to, class_id, fare_amount) "
                        + "values ((select st_id from station "
                        + "where st_name= '" + station + "' and route_id= (select route_id from route "
                        + "where route_name = '" + this.routename + "')),(select st_id from station "
                        + "where st_name= '" + station2 + "' and route_id= (select route_id from route "
                        + "where route_name = '" + this.routename + "')),(select c.class_id from class_name c "
                        + "where c.class_name = '" + classname + "'),?)";
                System.out.println(sql2);
                try {
                    PreparedStatement ps = con.prepareStatement(sql2);
//                    ps.setString(1, this.station);
//                    ps.setString(2, this.station2);
//                    ps.setString(3, this.classname);
                    ps.setInt(1, this.fareamount);
                    int i = ps.executeUpdate();
                    clear();
                    if (i > 0) {
                        return "fare-index?faces-redirect=true";
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }finally{
                Database.close(con);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * UNUSED.................................. * This Method used to get all
     * Station name from database based on route
     *
     * @return array list
     */
//    public void stationName2() {
//        try {
//            Connection con = Database.getConnection();
//            Statement st = con.createStatement();
//            ResultSet rs = null;
//            //rs = st.executeQuery("select st_id from station where st_name='" + station2 + "'");
//            rs = st.executeQuery("select st_id from railway_ticket_booking.station f where f.st_name='" + station2 + "'");
//
//            while (rs.next()) {
//                stationidto = rs.getInt("st_id");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void stationName() {
//        try {
//            Connection con = Database.getConnection();
//            Statement st = con.createStatement();
//            ResultSet rs = null;
//            rs = st.executeQuery("select st_id from station t where t.st_name='" + station + "'");
//            while (rs.next()) {
//                stattionidfrom = rs.getInt("st_id");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void routeName() {
//        try {
//            Connection con = Database.getConnection();
//            Statement st = con.createStatement();
//            ResultSet rs = null;
//            rs = st.executeQuery("select route_id from route where route_name='" + routename + "'");
//            while (rs.next()) {
//                routeid = rs.getInt("route_id");
//            }
//        } catch (Exception e) {
//        }
//    }
//        ////////////////////////
//    public void classId() {
//        try {
//            Connection con = Database.getConnection();
//            Statement stm = con.createStatement();
//            ResultSet rs = null;
//            rs = stm.executeQuery("select class_id from railway_ticket_booking.class_name where "
//                    + "class_name='" + classname + "'");
//            while (rs.next()) {
//                classid = rs.getInt("class_id");
//                //  System.out.println(med_group_id);
//                // System.out.println(getMed_group_name());
//            }
//        } catch (Exception e) {
//        }
//    }
    public void clear() {
        routename = "";
        station = "";
        station2 = "";
        classname = "";
        fareamount = 0;
    }
}
