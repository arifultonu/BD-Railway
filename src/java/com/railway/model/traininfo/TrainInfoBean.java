/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.railway.model.traininfo;

import com.railway.model.passenger.PassengerInfoBean;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import com.railway.util.Database;
import com.railway.util.LoginUtil;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ENAMUL
 */
@ManagedBean
@SessionScoped
public class TrainInfoBean {

    private String tnumber;
    private String tname;
    private String stto;
    private String stfrom;
    private String deptime;
    private String atime;
    private String doff;
    private String stations;
    private int tid;
    private int route_id;
    private int sidto;
    private int sidfrom;
    private int stid;
    private String routename;
    private String stname;
    private String stname2;
    private String dname;
    private int trainrow;

    /**
     * @return the tnumber
     */
    public String getTnumber() {
        return tnumber;
    }

    /**
     * @param tnumber the tnumber to set
     */
    public void setTnumber(String tnumber) {
        this.tnumber = tnumber;
    }

    /**
     * @return the tname
     */
    public String getTname() {
        return tname;
    }

    /**
     * @param tname the tname to set
     */
    public void setTname(String tname) {
        this.tname = tname;
    }

    /**
     * @return the stto
     */
    public String getStto() {
        return stto;
    }

    /**
     * @param stto the stto to set
     */
    public void setStto(String stto) {
        this.stto = stto;
    }

    /**
     * @return the stfrom
     */
    public String getStfrom() {
        return stfrom;
    }

    /**
     * @param stfrom the stfrom to set
     */
    public void setStfrom(String stfrom) {
        this.stfrom = stfrom;
    }

    /**
     * @return the deptime
     */
    public String getDeptime() {
        return deptime;
    }

    /**
     * @param deptime the deptime to set
     */
    public void setDeptime(String deptime) {
        this.deptime = deptime;
    }

    /**
     * @return the atime
     */
    public String getAtime() {
        return atime;
    }

    /**
     * @param atime the atime to set
     */
    public void setAtime(String atime) {
        this.atime = atime;
    }

    /**
     * @return the doff
     */
    public String getDoff() {
        return doff;
    }

    /**
     * @param doff the doff to set
     */
    public void setDoff(String doff) {
        this.doff = doff;
    }

    /**
     * @return the tid
     */
    public int getTid() {
        return tid;
    }

    /**
     * @param tid the tid to set
     */
    public void setTid(int tid) {
        this.tid = tid;
    }

    /**
     * @return the route_id
     */
    public int getRoute_id() {
        return route_id;
    }

    /**
     * @param route_id the route_id to set
     */
    public void setRoute_id(int route_id) {
        this.route_id = route_id;
    }

    /**
     * @return the stations
     */
    public String getStations() {
        return stations;
    }

    /**
     * @param stations the stations to set
     */
    public void setStations(String stations) {
        this.stations = stations;
    }

    public String getRoutename() {
        return routename;
    }

    public void setRoutename(String routename) {
        this.routename = routename;
    }

    public int getSidfrom() {
        return sidfrom;
    }

    public void setSidfrom(int sidfrom) {
        this.sidfrom = sidfrom;
    }

    public int getSidto() {
        return sidto;
    }

    public void setSidto(int sidto) {
        this.sidto = sidto;
    }

    public String getStname() {
        return stname;
    }

    public void setStname(String stname) {
        this.stname = stname;
    }

    public int getStid() {
        return stid;
    }

    public void setStid(int stid) {
        this.stid = stid;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getStname2() {
        return stname2;
    }

    public void setStname2(String stname2) {
        this.stname2 = stname2;
    }

    public int getTrainrow() {
        return trainrow;
    }

    public void setTrainrow(int trainrow) {
        this.trainrow = trainrow;
    }

    public List<TrainInfoBean> getAllTrain() {
        Connection conn = null;
        Statement st = null;
        List<TrainInfoBean> list = new ArrayList<TrainInfoBean>();
        String query1 = "select train_id, train_number, train_name, s.st_name, s.st_name, departure_time,"
                + " arrival_time, day_off, r.route_name from train_info ti join station s join route r where"
                + " (ti.st_id_to = s.st_id and ti.st_id_from = s.st_id and ti.route_id = r.route_id);";
        String query = "select t.train_id, t.train_number, t.train_name, s.st_name,(select st_name from station where st_id_from = st_id) "
                + "as stationfrom, t.departure_time, t.arrival_time, t.day_off, r.route_name from train_info t inner join station s on "
                + "t.st_id_to = s.st_id inner join route r on t.route_id = r.route_id;";
        try {
            st = Database.getConnection().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                TrainInfoBean um1 = new TrainInfoBean();
                //train_id, train_number, train_name, st_id_to, st_id_from, departure_time, arrival_time, day_off, route_id
                um1.setTid(rs.getInt("t.train_id"));
                um1.setTnumber(rs.getString("t.train_number"));
                um1.setTname(rs.getString("t.train_name"));
                um1.setStto(rs.getString("s.st_name"));
                um1.setStfrom(rs.getString("stationfrom"));
                um1.setDeptime(rs.getString("t.departure_time"));
                um1.setAtime(rs.getString("t.arrival_time"));
                um1.setDoff(rs.getString("t.day_off"));
                um1.setRoutename(rs.getString("r.route_name"));
                list.add(um1);
            }
            //makeFieldBlank();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Database.close(conn);
        }
        return list;
    }

    public String getUpdateTrain() {
        return "trainInfo-edit?faces-redirect=true";
    }
public void totalTrain() {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        HttpSession session = LoginUtil.getSession();
        String sql = "select count(*) FROM railway_ticket_booking.train_info";
        try {
            con = Database.getConnection();
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {                
                this.setTrainrow(rs.getInt("count(*)"));
                session.setAttribute("trainrow", trainrow);                
            }
        } catch (Exception ex) {
            Logger.getLogger(TrainInfoBean.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Database.close(con);
        }
    }
    public int allTrain() {
        totalTrain();
        return trainrow;
    }
    public void deleteTrain() {
        Connection con = null;
        PreparedStatement ps = null;
        String sql = "DELETE FROM railway_ticket_booking.train_info WHERE train_id=?;";

        try {
            con = Database.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, this.getTid());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Database.close(con);
        }

    }

    public List<TrainInfoBean> getAllRoute() {
        Connection conn = null;
        Statement st = null;
        List<TrainInfoBean> list = new ArrayList<TrainInfoBean>();
        try {
            st = Database.getConnection().createStatement();
            ResultSet rs = st.executeQuery("select * from route");
            while (rs.next()) {
                TrainInfoBean um1 = new TrainInfoBean();
                um1.setRoute_id(rs.getInt("route_id"));
                um1.setRoutename(rs.getString("route_name"));
                list.add(um1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Database.close(conn);
        }
        return list;
    }

    public List<TrainInfoBean> getAllStation() {
        Connection conn = null;
        Statement st = null;
        List<TrainInfoBean> list = new ArrayList<TrainInfoBean>();
        try {
            st = Database.getConnection().createStatement();
            String sql = "SELECT st_name FROM station";
            ResultSet rs1 = st.executeQuery(sql);
            while (rs1.next()) {
                TrainInfoBean sb2 = new TrainInfoBean();
                sb2.setStid(rs1.getInt("st_id"));
                sb2.setStname(rs1.getString("st_name"));
                list.add(sb2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Database.close(conn);
        }
        return list;
    }

    public List<TrainInfoBean> getRouteWiseStation() {
        Connection conn = null;
        Statement st = null;
        List<TrainInfoBean> list = new ArrayList<TrainInfoBean>();
        try {
            st = Database.getConnection().createStatement();
            String sql = "SELECT st_name FROM station s where route_id=(select route_id from route where route_name='" + this.routename + "');";
            ResultSet rs1 = st.executeQuery(sql);
            while (rs1.next()) {
                TrainInfoBean sb2 = new TrainInfoBean();
                //sb2.setSidto(rs1.getInt("st_id"));
                sb2.setStname(rs1.getString("st_name"));
                list.add(sb2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Database.close(conn);
        }
        return list;
    }

    public List<TrainInfoBean> getRouteWiseStation2() {
        Connection conn = null;
        Statement st = null;
        List<TrainInfoBean> list = new ArrayList<TrainInfoBean>();
        try {
            st = Database.getConnection().createStatement();
            String sql = "SELECT st_name FROM station s where route_id=(select route_id from route where route_name='" + this.routename + "');";
            ResultSet rs1 = st.executeQuery(sql);
            while (rs1.next()) {
                TrainInfoBean sb2 = new TrainInfoBean();
                // sb2.setSidfrom(rs1.getInt("st_id"));
                sb2.setStname2(rs1.getString("st_name"));
                list.add(sb2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Database.close(conn);
        }
        return list;
    }

    public List<TrainInfoBean> getDayName() {
        String[] day = {"Sunday", "Monday", "Tuesday", "Wetnesday", "Thursday", "Friday", "Saturday"};
        List<TrainInfoBean> list = new ArrayList<TrainInfoBean>();
        try {
            for (int i = 0; i < 7; i++) {
                TrainInfoBean sb2 = new TrainInfoBean();
                sb2.setDname(day[i].toString());
                list.add(sb2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public String insertData() {
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "insert into train_info (train_number, train_name,st_id_to, st_id_from,"
                + " departure_time, arrival_time, day_off, route_id) values (?,?,"
                + "(select st.st_id from station st where st.st_name=? and route_id= "
                + "(Select route_id from route where route_name=?)),(select st.st_id from "
                + "station st where st.st_name=? and route_id=(Select route_id from "
                + "route where route_name=?)), ?,?,?, (select "
                + "route_id from route where route_name=?));";
        try {
            ps = Database.getConnection().prepareStatement(sql);
            ps.setString(1, this.getTnumber());
            ps.setString(2, this.getTname());
            ps.setString(3, this.getStname());
            ps.setString(4, this.getRoutename());
            ps.setString(5, this.getStname2());
            ps.setString(6, this.getRoutename());
            ps.setString(7, this.getDeptime());
            ps.setString(8, this.getAtime());
            ps.setString(9, this.getDname());
            ps.setString(10, this.getRoutename());
            int r = ps.executeUpdate();
            makeFieldBlank();
            if (r > 0) {
                return "trainInfo-index?faces-redirect=true";
            } else {
                return "trainInfo-create?faces-redirect=true";
            }
//                FacesMessage msg = new FacesMessage("Succesful Save Data");
//                FacesContext.getCurrentInstance().addMessage(null, msg); 
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            Database.close(conn);
        }
//        System.out.println("number:"+this.getTnumber());
//        System.out.println("name:"+this.getTname());
//        //System.out.println("To Station id:"+this.getSidto());
//        System.out.println("To station Name:"+this.getStname());
//        //System.out.println("From Station:"+this.getSidfrom());
//        System.out.println("From Station Name:"+this.getStname2());
//        System.out.println("dep time:"+this.getDeptime());
//        System.out.println("av time:"+this.getAtime());
//        System.out.println("day off:"+this.getDname());
//        //System.out.println("Route:"+this.getRoute_id());
//        System.out.println("Route Name:"+ this.getRoutename());
//        System.out.println(">>>>"+stname);
    }

    public String updateData() {
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "update train_info set train_number=?, train_name=?,"
                + "st_id_to=(select st_id from station where st_name=? and route_id=(select route_id "
                + "from route where route_name = ?)), "
                + "st_id_from =(Select st_id from station where st_name=? and route_id = (select route_id "
                + "from route where route_name = ?)),"
                + "departure_time=?, arrival_time=?, day_off=?, route_id= "
                + "(Select route_id from route where route_name=?) where train_id=?";
        try {
            ps = Database.getConnection().prepareStatement(sql);
            ps.setString(1, this.getTnumber());
            ps.setString(2, this.getTname());
            ps.setString(3, this.getStname());
            ps.setString(4, this.getRoutename());
            ps.setString(5, this.getStname2());
            ps.setString(6, this.getRoutename());
            ps.setString(7, this.getDeptime());
            ps.setString(8, this.getAtime());
            ps.setString(9, this.getDname());
            ps.setString(10, this.getRoutename());
            ps.setInt(11, this.getTid());
            int r = ps.executeUpdate();
            makeFieldBlank();
            if (r > 0) {
                return "trainInfo-index?faces-redirect=true";
            } else {
                return "trainInfo-edit?faces-redirect=true";
            }
//                FacesMessage msg = new FacesMessage("Succesful Save Data");
//                FacesContext.getCurrentInstance().addMessage(null, msg); 
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            Database.close(conn);
        }

    }

    public void makeFieldBlank() {
        this.setTnumber("");
        this.setTname("");
        this.setRoutename("");
        this.setStname("");
        this.setStname2("");
        this.setDeptime("");
        this.setAtime("");
        this.setDname("");
    }
}
