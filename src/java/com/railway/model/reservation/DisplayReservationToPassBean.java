/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.railway.model.reservation;

import com.railway.model.route.RouteInfoBean;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import javaClass.PurcheseValidation;

@ManagedBean
@SessionScoped
public class DisplayReservationToPassBean implements Serializable {

    private static final long serialVersionUID = 1L;
//insert into reservation (journey_dt, pass_id, train_id, class_id, adult, child, ptn, reserve_status, fare_id) values (?,?,?,?,?,?,?,?,?);
    //private int bid; // Booking Id
    private String jdate; // Journey Date
    private int pasid; // Passenger Id
    private String tname; // Train Name
    private String classname; // Class Name
    private int aap; // Amount of Audalt Passenger
    private int anap; // Amount of Non Adult Passenger
    private String ptn; // Ptn Number
    //private String ptn2; // Ptn Number 2
    //private int status; // Status 
    private int fa; // Fare Amount
    //private String criname;
    private int passid2;
    private int book_id;

//    public int getBid() {
//        return bid;
//    }
//
//    public void setBid(int bid) {
//        this.bid = bid;
//    }
    public String getJdate() {
        return jdate;
    }

    public void setJdate(String jdate) {
        this.jdate = jdate;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

//    public String getPassname() {
//        return passname;
//    }
//
//    public void setPassname(String passname) {
//        this.passname = passname;
//    }
    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public int getAap() {
        return aap;
    }

    public void setAap(int aap) {
        this.aap = aap;
    }

    public int getAnap() {
        return anap;
    }

    public void setAnap(int anap) {
        this.anap = anap;
    }

    public int getFa() {
        return fa;
    }

    public void setFa(int fa) {
        this.fa = fa;
    }

    public String getPtn() {
        return ptn;
    }

    public void setPtn(String ptn) {
        this.ptn = ptn;
    }

    //    public String getPtn2() {
    //        return ptn2;
    //    }
    //
    //    public void setPtn2(String ptn2) {
    //        this.ptn2 = ptn2;
    //    }
    //
    //    public int getStatus() {
    //        return status;
    //    }
    //
    //    public void setStatus(int status) {
    //        this.status = status;
    //    }
    //
    //    public String getCriname() {
    //        return criname;
    //    }
    //
    //    public void setCriname(String criname) {
    //        this.criname = criname;
    //    }
    public int getPassid2() {
        return passid2;
    }

    public void setPassid2(int passid2) {
        this.passid2 = passid2;
    }

    public int getPasid() {
        return pasid;
    }

    public void setPasid(int pasid) {
        this.pasid = pasid;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    
    public List<DisplayReservationToPassBean> getPassReservation() {
        Connection con = null;
        Statement st = null;
        iPass();
        List<DisplayReservationToPassBean> list = new ArrayList<DisplayReservationToPassBean>();
        String sql = "Select r.book_id, r.journey_dt,ti.train_name, c.class_name, ptn from reservation r inner join "
                + " train_info ti on r.train_id = ti.train_id inner join class_name c on r.class_id = c.class_id "
                + " where  pass_id = " + passid2 + " and reserve_status = 0;";
        String sql2 = "Select r.book_id, r.journey_dt,ti.train_name, c.class_name,r.child,r.adult, ptn, f.fare_amount*r.child+f.fare_amount*adult as 'Total_Fare' "
                + "from reservation r inner join train_info ti on r.train_id = ti.train_id inner join class_name c on r.class_id = c.class_id "
                + "inner join fare f on r.fare_id = f.fare_id where  pass_id = " + passid2 + " and reserve_status = 0;";
        //System.out.println(sql);
        try {
            con = Database.getConnection();
            st = con.createStatement();
            ResultSet rs = st.executeQuery(sql2);
            while (rs.next()) {
                DisplayReservationToPassBean dsr = new DisplayReservationToPassBean();
                dsr.setBook_id(rs.getInt("book_id"));
                dsr.setJdate(rs.getString("journey_dt"));
                dsr.setTname(rs.getString("train_name"));
                dsr.setClassname(rs.getString("class_name"));
                dsr.setAap(rs.getInt("adult"));
                dsr.setAnap(rs.getInt("child"));
                String ptn = rs.getString("ptn");
                String ptnNumber = ptn.substring(0, 1);
                //System.out.println(ptnNumber);
                ptnNumber += ptn.substring(9, 10);
                //System.out.println(ptnNumber);
                ptnNumber += ptn.substring(14, 15);
                //System.out.println(ptnNumber);
                ptnNumber += ptn.substring(19, 20);
                //System.out.println(ptnNumber);
                ptnNumber += ptn.substring(24, 25);
                //System.out.println(ptnNumber);
                dsr.setPtn(ptnNumber);
                dsr.setFa(rs.getInt("Total_Fare"));
                //System.out.println(rs.getString("train_name"));
                list.add(dsr);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Database.close(con);
        }
        return list;
    }

    public List<DisplayReservationToPassBean> getSuccessConfirmation() {
        Connection con = null;
        Statement st = null;
        iPass();
        PurcheseValidation pv = new PurcheseValidation();
        int bookId = pv.lestBookId();
        List<DisplayReservationToPassBean> list = new ArrayList<DisplayReservationToPassBean>();
        String sql = "Select r.journey_dt,ti.train_name, c.class_name, ptn from reservation r inner join "
                + " train_info ti on r.train_id = ti.train_id inner join class_name c on r.class_id = c.class_id "
                + " where  pass_id = " + passid2 + " and reserve_status = 0;";
        String sql2 = "Select r.journey_dt,ti.train_name, c.class_name,r.child,r.adult, ptn, f.fare_amount*r................child/2+f.fare_amount*adult as 'Total_Fare',pass_id "
                + "from reservation r inner join train_info ti on r.train_id = ti.train_id inner join class_name c on r.class_id = c.class_id "
                + "inner join fare f on r.fare_id = f.fare_id where  pass_id = " + passid2 + "  and  r.book_id=" + bookId + ";";
        //System.out.println(sql);
        try {
            con = Database.getConnection();
            st = con.createStatement();
            ResultSet rs = st.executeQuery(sql2);
            while (rs.next()) {
                DisplayReservationToPassBean dsr = new DisplayReservationToPassBean();
                dsr.setJdate(rs.getString("journey_dt"));
                dsr.setTname(rs.getString("train_name"));
                dsr.setClassname(rs.getString("class_name"));
                dsr.setAap(rs.getInt("adult"));
                dsr.setAnap(rs.getInt("child"));
                String ptn = rs.getString("ptn");
                String ptnNumber = ptn.substring(0, 1);
                //System.out.println(ptnNumber);
                ptnNumber += ptn.substring(9, 10);
                //System.out.println(ptnNumber);
                ptnNumber += ptn.substring(14, 15);
                //System.out.println(ptnNumber);
                ptnNumber += ptn.substring(19, 20);
                //System.out.println(ptnNumber);
                ptnNumber += ptn.substring(24, 25);
                //System.out.println(ptnNumber);
                dsr.setPtn(ptnNumber);
                dsr.setFa(rs.getInt("Total_Fare"));
                //System.out.println(rs.getString("train_name"));
                dsr.setPasid(rs.getInt("pass_id"));
                list.add(dsr);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Database.close(con);
        }
        return list;
    }

    public void printTicket() {
    }

    public void cancelReservation() {
        Connection con = null;
        PreparedStatement ps = null;
        
        String sql = "DELETE from railway_ticket_booking.reservation where book_id = ?;";
        try {
           con = Database.getConnection();
           ps = con.prepareStatement(sql);
           ps.setInt(1, this.book_id);
           ps.executeUpdate();
        } catch (Exception ex) {
           Logger.getLogger(DisplayReservationToPassBean.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            Database.close(con);
        }
}

    public void iPass() {
        Connection con = null;
        Statement st = null;
        String username = LoginUtil.getUserName();
        System.out.println(""+ username);
        //System.out.println(pass);
        String sql = "select p.pass_id from user u inner join passenger_info p "
                + "on u.user_id = p.p_user_id where u.uname = '" + username + "';";
        try {
            con = Database.getConnection();
            st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                this.setPassid2(rs.getInt("pass_id"));
                System.out.println("Passenger Id from session " + rs.getInt("pass_id"));
            }
            Database.close(con);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Database.close(con);
        }
    }
}
