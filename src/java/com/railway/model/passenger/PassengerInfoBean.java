package com.railway.model.passenger;

import com.railway.model.ticketmanager.TicketManagerBean;
import java.util.Date;
import java.sql.Connection;
import com.railway.util.Database;
import com.railway.util.LoginUtil;
import com.railway.validation.DublicateCheck;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
//import java.sql.Date;

/**
 *
 * @author Tonu
 */
@ManagedBean
@SessionScoped
public class PassengerInfoBean implements Serializable {

//    passenger table
    private static final long serialVersionUID = 1L;
    private int passid;
    private String fname;
    private String lname;
    private String address;
    private String phone;
    private String email;
    private Date dob;
    private String sex;
    private Date reg_date;
    private int p_user_id;
    private int user_id;
    private int role_id;
    private String uname;
    private String pass;
    private boolean isactive = true;
    private String sec_question;
    private String sec_answer;
    private Date last_login;
    private String username;
    private int totalrow;

    public PassengerInfoBean() {
    }

    public int getPassid() {
        return passid;
    }

    public void setPassid(int passid) {
        this.passid = passid;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getReg_date() {
        return reg_date;
    }

    public void setReg_date(Date reg_date) {
        this.reg_date = reg_date;
    }

    public int getP_user_id() {
        return p_user_id;
    }

    public void setP_user_id(int p_user_id) {
        this.p_user_id = p_user_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public boolean isIsactive() {
        return isactive;
    }

    public void setIsactive(boolean isactive) {
        this.isactive = isactive;
    }

    public String getSec_question() {
        return sec_question;
    }

    public void setSec_question(String sec_question) {
        this.sec_question = sec_question;
    }

    public String getSec_answer() {
        return sec_answer;
    }

    public void setSec_answer(String sec_answer) {
        this.sec_answer = sec_answer;
    }

    public Date getLast_login() {
        return last_login;
    }

    public void setLast_login(Date last_login) {
        this.last_login = last_login;
    }

    public int getTotalrow() {
        return totalrow;
    }

    public void setTotalrow(int totalrow) {
        this.totalrow = totalrow;
    }

    public String goEditPage() {
        return "passenger-edit?faces-redirect=true";
    }

    public String goIndexPage() {
        return "passenger-index?faces-redirect=true";
    }

    public String clearAll() {
        passid = 0;
        fname = "";
        lname = "";
        address = "";
        phone = "";
        email = "";
        sex = "";
        return null;
    }

//        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//        String d = df.format(dob);
//        String rd = df.format(reg_date);
    public List<PassengerInfoBean> getAllPassengerInfo() {
        Connection con = null;
        Statement st = null;
        List<PassengerInfoBean> list = new ArrayList<PassengerInfoBean>();
        String sql = "SELECT pass_id, fname, lname, address, phone, email, dob, sex, reg_date, p_user_id from "
                + "railway_ticket_booking.passenger_info ";
        try {
            con = Database.getConnection();
            st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                PassengerInfoBean pinfo = new PassengerInfoBean();
                pinfo.setPassid(rs.getInt("pass_id"));
                pinfo.setFname(rs.getString("fname"));
                pinfo.setLname(rs.getString("lname"));
                pinfo.setAddress(rs.getString("address"));
                pinfo.setPhone(rs.getString("phone"));
                pinfo.setEmail(rs.getString("email"));
                pinfo.setDob(rs.getDate("dob"));
                pinfo.setSex(rs.getString("sex"));
                pinfo.setReg_date(rs.getDate("reg_date"));
                pinfo.setP_user_id(rs.getInt("p_user_id"));
                list.add(pinfo);
            }

        } catch (Exception ex) {
            Logger.getLogger(PassengerInfoBean.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Database.close(con);
        }
        return list;
    }

    public void totalPassenger() {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        HttpSession session = LoginUtil.getSession();
        String sql = "select count(*) FROM passenger_info";
        try {
            con = Database.getConnection();
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                this.setTotalrow(rs.getInt("count(*)"));
                session.setAttribute("totalrow", totalrow);
            }
        } catch (Exception ex) {
            Logger.getLogger(PassengerInfoBean.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Database.close(con);
        }
    }

    public int allpassenger() {
        totalPassenger();
        return totalrow;
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
        save();

    }

    public boolean save() {
        boolean reValue = false;

        boolean isBecomeDuplicate = new DublicateCheck<String>().isDublicate(this.getUname(), getAllUserName());
        if (isBecomeDuplicate) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Duplicate User Name not allowed here");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            insertPassengerInfo();

        }
        return reValue;
    }

    public String insertPassengerInfo() {
        Connection con = null;
        PreparedStatement ps = null;
        Statement st = null;
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
            ps.setInt(1, 3);
            ps.setString(2, this.getUname());
            ps.setString(3, this.getPass());
            ps.setBoolean(4, this.isIsactive());
            ps.setString(5, this.getSec_question());
            ps.setString(6, this.getSec_answer());
            ps.setString(7, cdate);
            int i = ps.executeUpdate();
            if (i > 0) {
                String selectId = "SELECT MAX(last_insert_id(user_id)) as user_id FROM railway_ticket_booking.user;";
                int puser_id = 0;
                try {
                    con = Database.getConnection();
                    st = con.createStatement();
                    ResultSet rs = st.executeQuery(selectId);
                    if (rs.next()) {
                        puser_id = rs.getInt("user_id");
                        // System.out.println(puser_id);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(PassengerInfoBean.class.getName()).log(Level.SEVERE, null, ex);
                }

                String sql2 = "INSERT into railway_ticket_booking.passenger_info "
                        + "(fname, lname, address, phone, email, "
                        + "dob, sex, reg_date, p_user_id ) "
                        + "VALUES (?,?,?,?,?,?,?,?,?);";

                PreparedStatement ps2 = null;
                try {
                    con = Database.getConnection();
                    ps2 = con.prepareStatement(sql2);
                    ps2.setString(1, this.getFname());
                    ps2.setString(2, this.getLname());
                    ps2.setString(3, this.getAddress());
                    ps2.setString(4, this.getPhone());
                    ps2.setString(5, this.getEmail());
                    ps2.setString(6, birth);
                    ps2.setString(7, this.getSex());
                    ps2.setString(8, cdate);
                    ps2.setInt(9, puser_id);
                    int count = ps2.executeUpdate();
                    clear();
                    if (count > 0) {

                        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Information", "All data save successfully");
                        FacesContext.getCurrentInstance().addMessage(null, msg);
                    }
                } catch (Exception ex) {
                    Logger.getLogger(PassengerInfoBean.class.getName()).log(Level.SEVERE, null, ex);
                }
                return "reg-successful?faces-redirect=true";
            }
        } catch (Exception ex) {
            Logger.getLogger(PassengerInfoBean.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Database.close(con);
        }
        return "insertPassengerInfo";
    }

    public String editPassInfo() {
        Connection con = null;
        PreparedStatement ps = null;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String birth = df.format(this.getDob());

        String sql = "UPDATE railway_ticket_booking.passenger_info SET fname=?, lname=?, address=?, phone=?, "
                + "email=?, sex=?, dob=?  where p_user_id=" + p_user_id;
        try {
            con = Database.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, this.getFname());
            ps.setString(2, this.getLname());
            ps.setString(3, this.getAddress());
            ps.setString(4, this.getPhone());
            ps.setString(5, this.getEmail());
            ps.setString(6, this.getSex());
            ps.setString(7, birth);

            int i = ps.executeUpdate();
            if (i > 0) {
                return "passenger-index?faces-redirect=true";
            } else {
                return "passenger-edit?faces-redirect=true";
            }
        } catch (Exception ex) {
            Logger.getLogger(PassengerInfoBean.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            Database.close(con);
        }
    }

    public void deletePassInfo() {
        Connection con = null;
        PreparedStatement ps = null;
        String sql = "Delete from railway_ticket_booking.passenger_info where pass_id = ?";
        try {
            con = Database.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, this.getPassid());
            ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(PassengerInfoBean.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Database.close(con);
        }
    }

//    public List<PassengerInfoBean> aPasserger() {
//        Connection con = null;
//        Statement st = null;
//        List <PassengerInfoBean> list = new ArrayList<PassengerInfoBean>();
//        username = LoginUtil.getUserName();
//        System.out.println("username");
//        String sql = "select p.fname, p.lname, p.address, p.phone, p.email, p.dob, p.sex, p.reg_date, p.p_user_id "
//                + "from passenger_info p inner join user u on p.p_user_id = u.user_id where uname = '"+uname+"';";
//        
//        try {
//            con = Database.getConnection();
//            st = con.createStatement();
//            ResultSet rs = st.executeQuery(sql);
//            while (rs.next()) {
//                PassengerInfoBean passe = new PassengerInfoBean();
//                passe.setFname(rs.getString("fname"));
//                passe.setLname(rs.getString("lname"));
//                passe.setAddress(rs.getString("address"));
//                passe.setPhone(rs.getString("phone"));
//                passe.setEmail(rs.getString("email"));
//                passe.setDob(rs.getDate("dob"));
//                passe.setSex(rs.getString("sex"));
//                passe.setReg_date(rs.getDate("reg_date"));
//                passe.setP_user_id(rs.getInt("p_user_id"));
//                list.add(passe);
//               
//            }
//        } catch (Exception ex) {
//             Logger.getLogger(PassengerInfoBean.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            Database.close(con);
//        }
//        return list;
//    }
    public List<PassengerInfoBean> getAPassengerInfo() {
        Connection con = null;
        Statement st = null;
        List<PassengerInfoBean> list = new ArrayList<PassengerInfoBean>();
        String sql = "select  p.fname, p.lname, p.address, p.phone, p.email, p.dob, p.sex, p.reg_date, p.pass_id "
                + "from passenger_info p inner join user u on p.p_user_id = u.user_id where uname = '" + LoginUtil.getUserName() + "';";
        try {
            con = Database.getConnection();
            st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                PassengerInfoBean pinfo = new PassengerInfoBean();
                pinfo.setFname(rs.getString("fname"));
                pinfo.setLname(rs.getString("lname"));
                pinfo.setAddress(rs.getString("address"));
                pinfo.setPhone(rs.getString("phone"));
                pinfo.setEmail(rs.getString("email"));
                pinfo.setDob(rs.getDate("dob"));
                pinfo.setSex(rs.getString("sex"));
                pinfo.setReg_date(rs.getDate("reg_date"));
                pinfo.setPassid(rs.getInt("pass_id"));
                list.add(pinfo);
            }

        } catch (Exception ex) {
            Logger.getLogger(PassengerInfoBean.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Database.close(con);
        }
        return list;
    }

    public void clear() {
        fname = "";
        lname = "";
        sex = null;
        dob = null;
        address = "";
        email = "";
        phone = "";
        pass = "";
        uname = "";
        sec_question = "";
        sec_answer = "";
        //return null;
    }
}
