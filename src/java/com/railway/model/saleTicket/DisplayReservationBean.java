/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.railway.model.saleTicket;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import com.railway.util.Database;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;

@ManagedBean
@SessionScoped
public class DisplayReservationBean implements Serializable {

    private static final long serialVersionUID = 1L;
//insert into reservation (journey_dt, pass_id, train_id, class_id, adult, child, ptn, reserve_status, fare_id) values (?,?,?,?,?,?,?,?,?);
    private int bid; // Booking Id
    private String jdate; // Journey Date
    private String passname; // Passenger Name
    private String tname; // Train Name
    private String classname; // Class Name
    private int aap; // Amount of Audalt Passenger
    private int anap; // Amount of Non Adult Passenger
    private String ptn; // Ptn Number
    private String ptn2; // Ptn Number 2
    private int status; // Status 
    private int fa; // Fare Amount
    private String criname;
    private int passId;

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

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

    public String getPassname() {
        return passname;
    }

    public void setPassname(String passname) {
        this.passname = passname;
    }

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

    public String getPtn2() {
        return ptn2;
    }

    public void setPtn2(String ptn2) {
        this.ptn2 = ptn2;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCriname() {
        return criname;
    }

    public void setCriname(String criname) {
        this.criname = criname;
    }

    public int getPassId() {
        return passId;
    }

    public void setPassId(int passId) {
        this.passId = passId;
    }

  public List<DisplayReservationBean> getReservationInfo() {
      Connection con = null;
      Statement st = null;
        List<DisplayReservationBean> list = new ArrayList<DisplayReservationBean>();
        String sql = "select journey_dt, pass_id, train_id, class_id, adult, child, ptn, reserve_status, fare_id from reservation;";
        String sql2 = "SELECT r.book_id,r.journey_dt,p.fname,p.lname,tf.train_name,c.class_name,r.adult,r.child,r.ptn,"
                + "r.reserve_status,r.fare_id FROM ((reservation r INNER JOIN train_info tf ON (r.train_id = tf.train_id)) "
                + "INNER JOIN class_name c ON (r.class_id = c.class_id)) INNER JOIN passenger_info p ON (r.pass_id = p.pass_id);";
        try {
            con = Database.getConnection();
            st = con.createStatement();
            ResultSet rs = st.executeQuery(sql2);
            while (rs.next()) {
                DisplayReservationBean ptb = new DisplayReservationBean();
                ptb.setBid(rs.getInt("r.book_id"));
                ptb.setJdate(rs.getString("r.journey_dt"));
//        String fn = rs.getString("p.fname");
//        String ln = rs.getString("p.lname");
//        String n = fn +"  "+ ln;
                ptb.setPassname(rs.getString("p.fname") + " " + rs.getString("p.lname"));
                ptb.setTname(rs.getString("tf.train_name"));
                ptb.setClassname(rs.getString("c.class_name"));
                ptb.setAap(rs.getInt("r.adult"));
                ptb.setAnap(rs.getInt("r.child"));
                ptb.setPtn(rs.getString("r.ptn"));
                ptb.setStatus(rs.getInt("reserve_status"));
                ptb.setFa(rs.getInt("r.fare_id"));
                list.add(ptb);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            Database.close(con);
        }
        return list;
    }

    public List<DisplayReservationBean> getSearchCritaria() {
        String critariaName[] = {"journey_dt", "pass_id", "ptn"};
        List<DisplayReservationBean> list = new ArrayList<DisplayReservationBean>();
        for (int i = 0; i < 4; i++) {
            DisplayReservationBean ptb = new DisplayReservationBean();
            ptb.setClassname(critariaName[i]);
            list.add(ptb);
        }
        return list;
    }
    public int isPayed(){
         Connection con = null;
        Statement st = null;
        int status1 = 0;
        String S = "_____";
        String S1 = "_________";//f2fe1a64-[8]0ba-1031-ba77-78679ad043de
        String S2 = S1 + S;//f2fe1a64-80ba-[1]031-ba77-78679ad043de
        String S3 = S2 + S;//f2fe1a64-80ba-1031-[b]a77-78679ad043de
        String S4 = S3 + S;//f2fe1a64-80ba-1031-ba77-[7]8679ad043de
        String ptn1 = this.ptn2.substring(0, 1);
        String ptn2 = this.ptn2.substring(1, 2);
        String ptn3 = this.ptn2.substring(2, 3);
        String ptn4 = this.ptn2.substring(3, 4);
        String ptn5 = this.ptn2.substring(4);
        String sql2 = "SELECT "
                + "r.reserve_status "
                + " FROM ((reservation r INNER JOIN train_info tf ON (r.train_id = tf.train_id)) "
                + "INNER JOIN class_name c ON (r.class_id = c.class_id)) INNER JOIN passenger_info p ON (r.pass_id = p.pass_id) "
                + " inner join fare f on r.fare_id = f.fare_id  where "
                + "ptn like '" + ptn1 + "%' and ptn like '" + S1 + ptn2 + "%' and ptn like '" + S2 + ptn3 + "%' and ptn "
                + " like '" + S3 + ptn4 + "%' and ptn like '" + S4 + ptn5 + "%' and r.pass_id =" + passId + ";";
        try {
            con = Database.getConnection();
            st = con.createStatement();
            ResultSet rs = st.executeQuery(sql2);
            while (rs.next()) {
                status1 = rs.getInt("r.reserve_status");                
            }
            return status1;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Database.close(con);
        }   
    return status1;
    }
    public String serchderction() {
        int i = isPayed();
        if(i==1){
        return "paid-reseult?faces-redirect=true";
        }else{
        return "search-result?faces-redirect=true";
        }
    }

    public List<DisplayReservationBean> getSearchR() {
         Connection con = null;
        Statement st = null;
//        if(ptn.length()==6){
//        String first3letter = ptn2.substring(0, 4);
//        String last3letter = ptn2.substring(4, 7);
        //5696e

        String S = "_____";
        String S1 = "_________";//f2fe1a64-[8]0ba-1031-ba77-78679ad043de
        String S2 = S1 + S;//f2fe1a64-80ba-[1]031-ba77-78679ad043de
        String S3 = S2 + S;//f2fe1a64-80ba-1031-[b]a77-78679ad043de
        String S4 = S3 + S;//f2fe1a64-80ba-1031-ba77-[7]8679ad043de
//
//    System.out.println (S);
//
//    System.out.println (S1);
//
//    System.out.println (S2);
//
//    System.out.println (S3);
//
//    System.out.println (S4);
        String ptn1 = this.ptn2.substring(0, 1);
        String ptn2 = this.ptn2.substring(1, 2);
        String ptn3 = this.ptn2.substring(2, 3);
        String ptn4 = this.ptn2.substring(3, 4);
        String ptn5 = this.ptn2.substring(4);

        //System.out.println(ptn1+ "--" + ptn2 + "--" + ptn3 + "--" + ptn4 + "--" + ptn5);
        List<DisplayReservationBean> list = new ArrayList<DisplayReservationBean>();
        //String sql = "select journey_dt, pass_id, train_id, class_id, adult, child, ptn, reserve_status, fare_id from reservation;";
        String sql2 = "SELECT r.book_id,r.journey_dt,p.fname,p.lname,tf.train_name,c.class_name,r.adult,r.child,r.ptn,"
                + "r.reserve_status,f.fare_amount*r.adult+f.fare_amount*r.child/2 as 'Total_fare' "
                + " FROM ((reservation r INNER JOIN train_info tf ON (r.train_id = tf.train_id)) "
                + "INNER JOIN class_name c ON (r.class_id = c.class_id)) INNER JOIN passenger_info p ON (r.pass_id = p.pass_id) "
                + " inner join fare f on r.fare_id = f.fare_id  where "
                + "ptn like '" + ptn1 + "%' and ptn like '" + S1 + ptn2 + "%' and ptn like '" + S2 + ptn3 + "%' and ptn "
                + " like '" + S3 + ptn4 + "%' and ptn like '" + S4 + ptn5 + "%' and r.pass_id =" + passId + ";";
        // System.out.println(sql2);
        try {
            con = Database.getConnection();
            st = con.createStatement();
            ResultSet rs = st.executeQuery(sql2);
            while (rs.next()) {
                DisplayReservationBean ptb = new DisplayReservationBean();
                ptb.setBid(rs.getInt("r.book_id"));
                ptb.setJdate(rs.getString("r.journey_dt"));
//        String fn = rs.getString("p.fname");
//        String ln = rs.getString("p.lname");
//        String n = fn +"  "+ ln;
                ptb.setPassname(rs.getString("p.fname") + " " + rs.getString("p.lname"));
                ptb.setTname(rs.getString("tf.train_name"));
                ptb.setClassname(rs.getString("c.class_name"));
                ptb.setAap(rs.getInt("r.adult"));
                ptb.setAnap(rs.getInt("r.child"));
                ptb.setPtn(rs.getString("r.ptn"));
                ptb.setStatus(rs.getInt("r.reserve_status"));
                ptb.setFa(rs.getInt("Total_fare"));
                list.add(ptb);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            Database.close(con);
        }

        return list;
//        }else{
//        return null;
//        }
    }
   

    public void pay() {
        System.out.println("pay called");
        Connection con = null;
        PreparedStatement ps = null;       
        String sql = "UPDATE railway_ticket_booking.reservation set reserve_status = ? where book_id = "+ this.getBid() ;
        try {
            con = Database.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, 1);
            ps.executeUpdate();            
        } catch (Exception ex) {            
             Logger.getLogger(DisplayReservationBean.class.getName()).log(Level.SEVERE, null, ex);             
        }finally {
            Database.close(con);
        }         
    }

    public String pticket() {
        return "ticket";
    }

    public List<DisplayReservationBean> getNaPass() {
        List<DisplayReservationBean> list = new ArrayList<DisplayReservationBean>();
        int gnap = aap;
        int[] ap = new int[4 - gnap];
        for (int i = 0; i < ap.length; i++) {
            DisplayReservationBean ptb = new DisplayReservationBean();
            ptb.setAnap(i + 1);
            list.add(ptb);
        }
        return list;
    }

    
}
