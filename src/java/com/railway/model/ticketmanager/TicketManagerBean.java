/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.railway.model.ticketmanager;

import com.railway.validation.DublicateCheck;
import com.railway.util.Database;
import com.railway.util.LoginUtil;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author TCL
 */
@ManagedBean
@SessionScoped
public class TicketManagerBean implements Serializable {

    private static long serialVersionUID = 1L;
    //For Ticket Manager table
    private int tman_id;
    private String tman_name;
    private String tman_address;
    private String tman_phone;
    private String tman_email;
    private int st_id;
    private Date dob;
    private int route_id;
    private String sex;
    private String photo;
    private int t_user_id;
    private Date reg_date;
    private UploadedFile file;
    private int imageid;
    //User Table
    private int user_id;
    private int role_id;
    private String uname;
    private String pass;
    private boolean isactive = true;
    private String sec_question;
    private String sec_answer;
    private Date last_login;
    //For Others Table
    private String routename;
    private String stationname;
    private int ticketmanagerrow;

    public TicketManagerBean() {
    }

    /**
     * @return the tman_id
     */
    public int getTman_id() {
        return tman_id;
    }

    /**
     * @param tman_id the tman_id to set
     */
    public void setTman_id(int tman_id) {
        this.tman_id = tman_id;
    }

    /**
     * @return the tman_name
     */
    public String getTman_name() {
        return tman_name;
    }

    /**
     * @param tman_name the tman_name to set
     */
    public void setTman_name(String tman_name) {
        this.tman_name = tman_name;
    }

    /**
     * @return the tman_address
     */
    public String getTman_address() {
        return tman_address;
    }

    /**
     * @param tman_address the tman_address to set
     */
    public void setTman_address(String tman_address) {
        this.tman_address = tman_address;
    }

    /**
     * @return the tman_phone
     */
    public String getTman_phone() {
        return tman_phone;
    }

    /**
     * @param tman_phone the tman_phone to set
     */
    public void setTman_phone(String tman_phone) {
        this.tman_phone = tman_phone;
    }

    /**
     * @return the tman_email
     */
    public String getTman_email() {
        return tman_email;
    }

    /**
     * @param tman_email the tman_email to set
     */
    public void setTman_email(String tman_email) {
        this.tman_email = tman_email;
    }

    /**
     * @return the st_id
     */
    public int getSt_id() {
        return st_id;
    }

    /**
     * @param st_id the st_id to set
     */
    public void setSt_id(int st_id) {
        this.st_id = st_id;
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

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Date getReg_date() {
        return reg_date;
    }

    public void setReg_date(Date reg_date) {
        this.reg_date = reg_date;
    }

    public Date getLast_login() {
        return last_login;
    }

    public void setLast_login(Date last_login) {
        this.last_login = last_login;
    }

    /**
     * @return the sex
     */
    public String getSex() {
        return sex;
    }

    /**
     * @param sex the sex to set
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * @return the photo
     */
    public String getPhoto() {
        return photo;
    }

    /**
     * @param photo the photo to set
     */
    public void setPhoto(String photo) {
        this.photo = photo;
    }

    /**
     * @return the t_user_id
     */
    public int getT_user_id() {
        return t_user_id;
    }

    /**
     * @param t_user_id the t_user_id to set
     */
    public void setT_user_id(int t_user_id) {
        this.t_user_id = t_user_id;
    }

    /**
     * @return the user_id
     */
    public int getUser_id() {
        return user_id;
    }

    /**
     * @param user_id the user_id to set
     */
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    /**
     * @return the role_id
     */
    public int getRole_id() {
        return role_id;
    }

    /**
     * @param role_id the role_id to set
     */
    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    /**
     * @return the uname
     */
    public String getUname() {
        return uname;
    }

    /**
     * @param uname the uname to set
     */
    public void setUname(String uname) {
        this.uname = uname;
    }

    /**
     * @return the pass
     */
    public String getPass() {
        return pass;
    }

    /**
     * @param pass the pass to set
     */
    public void setPass(String pass) {
        this.pass = pass;
    }

    /**
     * @return the isactive
     */
    public boolean isIsactive() {
        return isactive;
    }

    /**
     * @param isactive the isactive to set
     */
    public void setIsactive(boolean isactive) {
        this.isactive = isactive;
    }

    /**
     * @return the sec_question
     */
    public String getSec_question() {
        return sec_question;
    }

    /**
     * @param sec_question the sec_question to set
     */
    public void setSec_question(String sec_question) {
        this.sec_question = sec_question;
    }

    /**
     * @return the sec_answer
     */
    public String getSec_answer() {
        return sec_answer;
    }

    /**
     * @param sec_answer the sec_answer to set
     */
    public void setSec_answer(String sec_answer) {
        this.sec_answer = sec_answer;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public int getImageid() {
        return imageid;
    }

    public void setImageid(int imageid) {
        this.imageid = imageid;
    }

    public String getRoutename() {
        return routename;
    }

    public void setRoutename(String routename) {
        this.routename = routename;
    }

    public String getStationname() {
        return stationname;
    }

    public void setStationname(String stationname) {
        this.stationname = stationname;
    }

    public int getTicketmanagerrow() {
        return ticketmanagerrow;
    }

    public void setTicketmanagerrow(int ticketmanagerrow) {
        this.ticketmanagerrow = ticketmanagerrow;
    }

    public String goEditPage() {
//        routename = "";
//        stationname = "";
        return "ticketManager-edit?faces-redirect=true";
    }
    //Methods-------------

    public List<TicketManagerBean> getAllTicketManagerInfo() {
        Connection con = null;
        Statement st = null;
        List<TicketManagerBean> list = new ArrayList<TicketManagerBean>();
        String sql = "SELECT tman_id,tman_name, tman_address, tman_phone, tman_email, "
                + "(select st_name from station s where s.st_id=t.st_id) as st_name, dob,(select route_name from route r where t.route_id=r.route_id) as route_name, "
                + "sex, t_user_id, reg_date FROM railway_ticket_booking.ticket_manager t order "
                + "by tman_name;";
        try {
            con = Database.getConnection();
            st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                TicketManagerBean vtm = new TicketManagerBean();
                vtm.setTman_id(rs.getInt("tman_id"));
                // System.out.println(this.getTman_id());
                vtm.setTman_name(rs.getString("tman_name"));
                vtm.setTman_address(rs.getString("tman_address"));
                vtm.setTman_phone(rs.getString("tman_phone"));
                vtm.setTman_email(rs.getString("tman_email"));
                vtm.setStationname(rs.getString("st_name"));
                vtm.setDob(rs.getDate("dob"));
                vtm.setRoutename(rs.getString("route_name"));
                vtm.setSex(rs.getString("sex"));
                vtm.setImageid(rs.getInt("t_user_id"));
                vtm.setReg_date(rs.getDate("reg_date"));
                list.add(vtm);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Database.close(con);
        }
        return list;
    }

    public void totalTicketmanager() {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        HttpSession session = LoginUtil.getSession();
        String sql = "select count(*) FROM railway_ticket_booking.ticket_manager";
        try {
            con = Database.getConnection();
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                this.setTicketmanagerrow(rs.getInt("count(*)"));
                session.setAttribute("ticketmanagerrow", ticketmanagerrow);
            }
        } catch (Exception ex) {
            Logger.getLogger(TicketManagerBean.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Database.close(con);
        }
    }

    public int allticketmanager() {
        totalTicketmanager();
        return ticketmanagerrow;
    }

    public List<TicketManagerBean> getAticketmanager() {
        Connection con = null;
        Statement st;
        ResultSet rs;
        List<TicketManagerBean> list = new ArrayList<TicketManagerBean>();
//        String sql = "SELECT t.tman_id, t.tman_name, t.tman_address, t.tman_phone, "
//                + "t.tman_email, t.st_id, t.dob, t.route_id, t.sex, t.reg_date FROM "
//                + "ticket_manager t inner join user u on t.t_user_id = u.user_id where "
//                + "uname ='" + LoginUtil.getUserName() + "';";
        String sql="SELECT t.tman_id, t.tman_name, t.tman_address, t.tman_phone, t.tman_email, (select st_name from station s where t.st_id=s.st_id)as st_name, t.dob, (select route_name from route r where t.route_id=r.route_id)as route_name, t.sex, t.reg_date FROM ticket_manager t inner join user u on t.t_user_id = u.user_id where uname ='"+LoginUtil.getUserName()+"';";
        try {
            con = Database.getConnection();
            st = con.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {
                TicketManagerBean atm = new TicketManagerBean();
                atm.setTman_id(rs.getInt("tman_id"));
                atm.setTman_name(rs.getString("tman_name"));
                atm.setTman_address(rs.getString("tman_address"));
                atm.setTman_phone(rs.getString("tman_phone"));
                atm.setTman_email(rs.getString("tman_email"));
               // atm.setSt_id(rs.getInt("st_id"));
                atm.setStationname(rs.getString("st_name"));
                atm.setDob(rs.getDate("dob"));
                //atm.setRoute_id(rs.getInt("route_id"));
                atm.setRoutename(rs.getString("route_name"));
                atm.setSex(rs.getString("sex"));
                atm.setReg_date(rs.getDate("reg_date"));
                list.add(atm);
                System.out.println(rs.getInt("tman_id"));
            }
        } catch (Exception ex) {
            Logger.getLogger(TicketManagerBean.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Database.close(con);
        }
        return list;
    }

    public List<TicketManagerBean> getAllRoute() {
        Connection con = null;
        Statement st = null;
        List<TicketManagerBean> list = new ArrayList<TicketManagerBean>();
        String sql = "SELECT * FROM railway_ticket_booking.route";
        try {
            con = Database.getConnection();
            st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                TicketManagerBean um1 = new TicketManagerBean();
                um1.setRoutename(rs.getString("route_name"));
                list.add(um1);
            }
        } catch (Exception ex) {
            Logger.getLogger(TicketManagerBean.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Database.close(con);
        }
        return list;
    }

    public List<TicketManagerBean> getAllStation() {
        Connection con = null;
        Statement st = null;
        List<TicketManagerBean> list = new ArrayList<TicketManagerBean>();
        String sql = "SELECT st_name FROM railway_ticket_booking.station s where route_id=(select route_id from route where route_name='" + this.routename + "');";
        try {
            con = Database.getConnection();
            st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                TicketManagerBean sb2 = new TicketManagerBean();
                // System.out.println(rs1.getString("st_name"));
                sb2.setStationname(rs.getString("st_name"));
                list.add(sb2);
            }
        } catch (Exception ex) {
            Logger.getLogger(TicketManagerBean.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Database.close(con);
        }
        return list;
    }

    public void routeName() {
        Connection con = null;
        Statement st = null;
        String sql = "SELECT route_id from railway_ticket_booking.route where route_name='" + this.routename + "';";
        try {
            con = Database.getConnection();
            st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                route_id = rs.getInt("route_id");
            }
        } catch (Exception ex) {
            Logger.getLogger(TicketManagerBean.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Database.close(con);
        }
    }

    public void statioNname() {
        Connection con = null;
        Statement st = null;
        String sql = "select st_id from railway_ticket_booking.station where st_name='" + this.stationname + "';";
        try {
            con = Database.getConnection();
            st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                st_id = rs.getInt("st_id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Database.close(con);
        }
    }

    public List<String> getAllUserName() {
        List<String> data = new ArrayList<String>();
        String sql = "SELECT uname FROM user;";
        try {
            Statement st = Database.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                data.add(rs.getString("uname"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TicketManagerBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }

    public void doInsert() {
        if (this.getFile() != null) {
            save();
        } else {
            FacesMessage msg = new FacesMessage("Please select image!!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }

    }

    public boolean save() {
        boolean reValue = false;

        boolean isBecomeDuplicate = new DublicateCheck<String>().isDublicate(this.getUname(), getAllUserName());
        if (isBecomeDuplicate) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Duplicate User Name not allowed here");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            insertTManagerInfo();
        }
        return reValue;
    }

    public String insertTManagerInfo() {
        Connection con = null;
        PreparedStatement ps = null;
        Date date = new Date();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String cdate = df.format(date);
        String birth = df.format(this.getDob());
        String sql = "INSERT INTO railway_ticket_booking.user"
                + " (role_id, uname, pass, isactive, sec_question, sec_answer, last_login)"
                + " VALUES( ?, ?, ?, ?, ?, ?, ?);";


        try {
            con = Database.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, 2);
            ps.setString(2, this.getUname());
            ps.setString(3, this.getPass());
            ps.setBoolean(4, this.isIsactive());
            ps.setString(5, "No Q");
            ps.setString(6, "No Ans");
            ps.setString(7, cdate);
            int i = ps.executeUpdate();

            if (i > 0) {
                String selectId = "SELECT MAX(last_insert_id(user_id)) as user_id FROM railway_ticket_booking.user;";
                int tuser_id = 0;
                try {
                    Statement st = Database.getConnection().createStatement();
                    ResultSet rs = st.executeQuery(selectId);
                    if (rs.next()) {
                        tuser_id = rs.getInt("user_id");
                       // System.out.println(tuser_id);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(TicketManagerBean.class.getName()).log(Level.SEVERE, null, ex);
                }
                String sql2 = "INSERT into railway_ticket_booking.ticket_manager"
                        + " (tman_name, tman_address, tman_phone, "
                        + "tman_email, st_id, dob, route_id, sex, photo, "
                        + "t_user_id, reg_date)"
                        + " VALUES(?,?,?,?,?,?,?,?,?,?,?);";
                PreparedStatement ps2;
                try {
                    System.out.println(file.getFileName());
                    InputStream fin2 = file.getInputstream();
                    ps2 = Database.getConnection().prepareStatement(sql2);
                    ps2.setString(1, this.getTman_name());
                    ps2.setString(2, this.getTman_address());
                    ps2.setString(3, this.getTman_phone());
                    ps2.setString(4, this.getTman_email());
                    ps2.setInt(5, this.st_id);
                    ps2.setString(6, birth);
                    ps2.setInt(7, this.route_id);
                    ps2.setString(8, this.getSex());
                    ps2.setBinaryStream(9, fin2, file.getSize());
                    ps2.setInt(10, tuser_id);
                    ps2.setString(11, cdate);
                    int count = ps2.executeUpdate();
                    clear();
                    if (count > 0) {
                        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Information", "All data save successfully");
                        FacesContext.getCurrentInstance().addMessage(null, msg);
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }

        } catch (IOException ex) {
            Logger.getLogger(TicketManagerBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(TicketManagerBean.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Database.close(con);
        }

        return "ticketManager-index?faces-redirect=true";
    }

    public String updateInfo() {
        Connection con = null;
        PreparedStatement ps = null;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String d = df.format(this.getDob());

        String sql = "update railway_ticket_booking.ticket_manager set tman_name=?, "
                + "tman_address=?, tman_phone=?, tman_email=?,route_id=(select route_id from route where route_name=?), "
                + "dob=?, st_id=(select st_id FROM station where st_name=?), "
                + "sex=? where tman_id=?";

        try {
            con = Database.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, this.getTman_name());
            ps.setString(2, this.getTman_address());
            ps.setString(3, this.getTman_phone());
            ps.setString(4, this.getTman_email());
            ps.setString(5, this.getRoutename());
            ps.setString(6, d);
            ps.setString(7, this.getStationname());
            ps.setString(8, this.getSex());
            ps.setInt(9, this.getImageid());
            // ps.setInt(10, this.getTman_id());
            int i = ps.executeUpdate();

            if (i > 0) {
                System.out.println("Updated");
                return "index?faces-redirect=true";

            } else {
                return "ticketManager-edit?faces-redirect=true";
//                FacesContext context = FacesContext.getCurrentInstance();
//                context.addMessage(null, new FacesMessage("Data is Updated!", "click Go Back"));
            }
            // clear();
        } catch (SQLException ex) {
            // Logger.getLogger(ViewTicketManagerBean.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        } finally {
            Database.close(con);
        }
        return null;
    }

    public void deleteTicketManagerInfo() {
        Connection con = null;
        PreparedStatement ps = null;
        String sql = ("DELETE FROM railway_ticket_booking.ticket_manager WHERE tman_id=?;");

        try {
            con = Database.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, this.getTman_id());
            ps.executeUpdate();
            //System.out.println("delete");
        } catch (SQLException ex) {
            Logger.getLogger(TicketManagerBean.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Database.close(con);
        }
    }
    
    public void clear(){
    tman_name="";
    tman_address="";
    tman_email="";
    tman_phone="";
    dob=null;
    stationname="";
    routename="";
    uname="";
    pass="";
    }
}
