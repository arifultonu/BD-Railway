/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.railway.model.reservation;

import java.sql.PreparedStatement;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaClass.*;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import com.railway.util.Database;
import com.railway.util.LoginUtil;

/**
 *
 * @author ENAMUL
 */
@ManagedBean
@SessionScoped
public class SearchTrainBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private String sdate;// Search Date for Journey
    private int rid; // Route Id
    private String rname; // Route Name
    private String stname1; // Station From
    private String stname2; // station to
    private String offday; // Train Off Day
    private String offday2; // Train Off day
    private String availabaletrain; // Searched Avilable Train
    private String tnumber; //  Train Number 
    private String tname; // train Name
    private String dtime; // Dperarture Time
    private String atime; // Arrival Time
    private String od; // Off Day
    private String classname; //Class Name
    private int aseat; // Avilable Seat
    private int aseat2; // Vailable Seat after Reservation
    private int afare; // Fare 
    private int np; // Non Adult Passenger
    private int fid; // Fare Id
    private int aap; // Amount of Adult Passenger
    private int anap; // Amount of Non Adult Passenger
    private String msgdb; // Massege For dialogue Box
    private int passid;
    private String seatno; // Seat Number
    private String username;
    private int pass;

    public void SearchTrainBean() {
        this.setSdate(new Date().toString());
    }

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

    public String getSdate() {
        return sdate;
    }

    public void setSdate(String sdate) {
        this.sdate = sdate;
    }

    public String getStname1() {
        return stname1;
    }

    public void setStname1(String stname1) {
        this.stname1 = stname1;
    }

    public String getStname2() {
        return stname2;
    }

    public void setStname2(String stname2) {
        this.stname2 = stname2;
    }

    public String getOffday() {
        return offday;
    }

    public void setOffday(String offday) {
        this.offday = offday;
    }

    public String getAvailabaletrain() {
        return availabaletrain;
    }

    public void setAvailabaletrain(String availabaletrain) {
        this.availabaletrain = availabaletrain;
    }

    public String getAtime() {
        return atime;
    }

    public void setAtime(String atime) {
        this.atime = atime;
    }

    public String getDtime() {
        return dtime;
    }

    public void setDtime(String dtime) {
        this.dtime = dtime;
    }

    public String getOd() {
        return od;
    }

    public void setOd(String od) {
        this.od = od;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getTnumber() {
        return tnumber;
    }

    public void setTnumber(String tnumber) {
        this.tnumber = tnumber;
    }

    public int getAfare() {
        return afare;
    }

    public void setAfare(int afare) {
        this.afare = afare;
    }

    public int getAseat() {
        return aseat;
    }

    public void setAseat(int aseat) {
        this.aseat = aseat;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public int getNp() {
        return np;
    }

    public void setNp(int np) {
        this.np = np;
    }

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
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

    public String getMsgdb() {
        return msgdb;
    }

    public void setMsgdb(String msgdb) {
        this.msgdb = msgdb;
    }

    public int getAseat2() {
        return aseat2;
    }

    public void setAseat2(int aseat2) {
        this.aseat2 = aseat2;
    }

    public int getPassid() {
        return passid;
    }

    public void setPassid(int passid) {
        this.passid = passid;
    }

    //Get Journey Date from javaClasses.DinamicDate() Method 
    // And Return A List of 10 Dinamic Date
    public List<SearchTrainBean> getJourneyDate() {
        iPass();
        List<SearchTrainBean> list = new ArrayList<SearchTrainBean>();
        DinamicDate dd = new DinamicDate();
        try {
            String[] d = dd.dinamicDate();
            for (int i = 0; i < 10; i++) {
                SearchTrainBean sb = new SearchTrainBean();
                sb.setSdate(d[i].toString());
                //System.out.println((d[i].toString()));
                list.add(sb);
            }
        } catch (Exception e) {
            System.out.println("Dinami Date Genaration Faild!" + e);
        }
        return list;
    }
    // This Method use to get All Route from Databse 
    // It Return a list of All Route 

    public List<SearchTrainBean> getAllRoute() {
        Connection con = null;
        List<SearchTrainBean> list = new ArrayList<SearchTrainBean>();
        try {
            con = Database.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select route_name from route");
            while (rs.next()) {
                SearchTrainBean um1 = new SearchTrainBean();
                //System.out.println(rs.getString("route_name"));
                um1.setRname(rs.getString("route_name"));
                boolean add = list.add(um1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Database.close(con);
        }
        return list;
    }
    // This Method use to Return a list of To Station to journey
    // Based on Route 

    public List<SearchTrainBean> getAllStation() {
        Connection con = null;
        List<SearchTrainBean> list = new ArrayList<SearchTrainBean>();
        try {
            con = Database.getConnection();
            Statement st = con.createStatement();
            String sql = "SELECT st_name FROM station s where route_id=(select route_id from route where route_name='" + this.rname + "');";
            ResultSet rs1 = st.executeQuery(sql);
            while (rs1.next()) {
                SearchTrainBean sb2 = new SearchTrainBean();
                //System.out.println(rs1.getString("st_name"));
                sb2.setStname1(rs1.getString("st_name"));
                list.add(sb2);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Database.close(con);
        }
        return list;
    }
    // This Method use to Return a list of from Station 
    // From where journey will be start
    // Based on Route 
    // Except to Station

    public List<SearchTrainBean> getAllStation2() {
        Connection con = null;
        List<SearchTrainBean> list = new ArrayList<SearchTrainBean>();
        try {
            con = Database.getConnection();
            Statement st = con.createStatement();
            String sql = "SELECT st_name FROM station s where route_id=(select route_id from route "
                    + "where route_name='" + this.rname + "') and st_id<>(select st_id from station where st_name='" + this.stname1
                    + "' and route_id=(select route_id from route where route_name='" + this.rname + "'));";
            //System.out.println(sql);
            ResultSet rs1 = st.executeQuery(sql);
            while (rs1.next()) {
                SearchTrainBean sb2 = new SearchTrainBean();
                //System.out.println(rs1.getString("st_name"));
                sb2.setStname2(rs1.getString("st_name"));
                list.add(sb2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Database.close(con);
        }
        return list;
    }
    // This Method Return a list of all train
    // Under The Pascific Selected route
    // Mached with To Station and From Station

    public List<SearchTrainBean> getAllTrain() {
        Connection con = null;
        List<SearchTrainBean> list = new ArrayList<SearchTrainBean>();
        try {
            SearchTrainBean stb = new SearchTrainBean();
            con = Database.getConnection();
            Statement st = con.createStatement();
            String sql2 = "select train_name from train_info where st_id_from = (Select st_id from station where "
                    + "st_name='" + this.stname1 + "' and route_id=(select route_id from route where route_name='" + this.rname + "')) "
                    + "and st_id_to =(Select st_id from station where st_name='" + this.stname2 + "' and route_id=(select route_id "
                    + "from route where route_name='" + this.rname + "')) and day_off <> '" + offDay(this.sdate) + "';";
            //System.out.println("Print from getAllTrain()--" + sql2);
            ResultSet rs1 = st.executeQuery(sql2);

            //System.out.println(sql2);
            while (rs1.next()) {
                SearchTrainBean sb3 = new SearchTrainBean();
                sb3.setAvailabaletrain(rs1.getString("train_name"));
                //System.out.println(rs1.getString("train_name"));
                list.add(sb3);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Database.close(con);
        }

        return list;
    }

    // This Method use to get All Clsses 
    // Under the pacific Selectd Train
    public List<SearchTrainBean> getClasses() {
        Connection con = null;
        List<SearchTrainBean> list = new ArrayList<SearchTrainBean>();

        String sql = "select cn.class_name  from fare f join class_name cn on f.class_id = cn.class_id "
                + "where st_id_from=(select st_id from station where st_name='" + this.stname1 + "' and route_id=(select "
                + "route_id from route where route_name='" + this.rname + "')) and st_id_to=(select st_id from "
                + "station where st_name='" + this.stname2 + "' and route_id=(select route_id from route where "
                + "route_name='" + this.rname + "'));";
        //System.out.println(sql);
        try {
            con = Database.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                SearchTrainBean stb = new SearchTrainBean();
                stb.setClassname(rs.getString("class_name"));
                list.add(stb);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Database.close(con);
        }
        return list;
    }
    // **This Method Return useful information from train_info from Database
    // ***Besed on Route , Mached on To Station and From Station

    public List<SearchTrainBean> getSearchRes() {
        Connection con = null;
        setAseat2(0);
        List<SearchTrainBean> list = new ArrayList<SearchTrainBean>();
        String query = "select train_number, train_name, departure_time,arrival_time,"
                + "day_off from train_info t where train_name = '" + availabaletrain + "' "
                + "and st_id_from=(select st_id from station where st_name='" + this.stname1 + "' "
                + "and route_id=(select route_id from route where route_name='" + this.rname + "')) "
                + "and st_id_to=(select st_id from station where st_name='" + this.stname2 + "' "
                + "and route_id=(select route_id from route where route_name='" + this.rname + "'));";
        try {
            con = Database.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                SearchTrainBean tsb = new SearchTrainBean();
                tsb.setTnumber(rs.getString("train_number"));
                tsb.setTname(rs.getString("train_name"));
                tsb.setDtime(rs.getString("departure_time"));
                tsb.setAtime(rs.getString("arrival_time"));
                tsb.setOd(rs.getString("day_off"));
                list.add(tsb);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Database.close(con);
        }
        return list;
    }
    // This Method use to genarate number for 
    // Passenger to select Adult and non- Adult 
    // Passenger Number  With Spacific roule

    public List<SearchTrainBean> getPasAm() {
        int[] no = {1, 2, 3, 4};
        List<SearchTrainBean> list = new ArrayList<SearchTrainBean>();
        try {
            for (int i = 0; i < 7; i++) {
                SearchTrainBean sb2 = new SearchTrainBean();
                sb2.setNp(no[i]);
                list.add(sb2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<SearchTrainBean> getPurchaseInfo() {
        Connection con = null;
        List<SearchTrainBean> list = new ArrayList<SearchTrainBean>();
        PurcheseValidation pv = new PurcheseValidation();
        //System.out.println("total Reserve seat by date" + pv.availableSeat(sdate, availabaletrain, stname1, stname2, rname, classname));
        aseat2 = pv.classSeat(classname) - pv.availableSeat(sdate, availabaletrain, stname1, stname2, rname, classname);
        //System.out.println(pv.availableSeat(sdate, availabaletrain, stname1, stname2, rname, classname));
        //System.out.println(aseat2);
        String sql = "select f.fare_id,cn.class_name, class_seat as 'Avilable_Seat', f.fare_amount "
                + "as 'Fare_par_Set' from fare f inner join class_name cn on f.class_id = cn.class_id "
                + "where f.st_id_from=(select st_id from station where st_name='" + this.stname1 + "' "
                + "and route_id=(select route_id from route where route_name='" + this.rname + "')) "
                + "and f.st_id_to=(select st_id from station where st_name='" + this.stname2 + "' "
                + "and route_id=(select route_id from route where route_name='" + this.rname + "')) and cn.class_id "
                + " = (select cn1.class_id from class_name cn1 where cn1.class_name = '" + classname + "');";
        try {
            con = Database.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                SearchTrainBean stb = new SearchTrainBean();
                stb.setFid(rs.getInt("f.fare_id"));//fare_id,class_name,Avilable_Seat,Fare_par_Set
                stb.setClassname(rs.getString("cn.class_name"));
                //System.out.println(rs.getString("class_name"));
                stb.setAseat(rs.getInt("Avilable_Seat"));
                //System.out.println(rs.getInt("Avilable_Seat"));
                stb.setAfare(rs.getInt("Fare_par_Set"));
                //System.out.println(rs.getInt("Fare_par_Set"));
                list.add(stb);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("problem query on purcheseInfo()");
        } finally {
            Database.close(con);
        }
        return list;
    }

    // This Method is unused................
    public String goErr() {
        return "err?faces-redirect=true";
    }
    // This Method use to return day name from spacific 
    // date String to match of day of spacific train

    public String offDay(String pdate) {
        if (pdate != null) {
            int d = Integer.parseInt(pdate.substring(8));
            int m = Integer.parseInt(pdate.substring(5, 7));
            int y = Integer.parseInt(pdate.substring(0, 4));
            // System.out.println("Day "+d+",Month "+m+", Year"+y);
            String dateString = String.format("%d-%d-%d", y, m, d);
            // System.out.println(dateString);
            Date date = new Date();
            try {
                date = new SimpleDateFormat("yyyy-M-d").parse(dateString);
            } catch (ParseException ex) {
                Logger.getLogger(SearchTrainBean.class.getName()).log(Level.SEVERE, null, ex);
            }
            // Then get the day of week from the Date based on specific locale.
            String dayOfWeek = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date);
            System.out.println("Printed offday = " + dayOfWeek);
            return dayOfWeek;
        } else {
            System.out.println("Date Not Seted!");
            return null;
        }

    }
    // This Method use to return list of number
    // for selecting Adult passenger number

    public List<SearchTrainBean> getApass() {
        List<SearchTrainBean> list = new ArrayList<SearchTrainBean>();
        PurcheseValidation pv = new PurcheseValidation();
        int totalReservation = pv.validToByTicket(sdate, passid);
        //System.out.println("totalRes" + totalReservation);
        if (totalReservation >= 0 & totalReservation < 4) {
            this.msgdb = "Ticket Booking Successful!";
            int arrayNumber = 4 - totalReservation;
            for (int i = 0; i < arrayNumber; i++) {
                SearchTrainBean ptb = new SearchTrainBean();
                ptb.setAap(i + 1);
                list.add(ptb);
            }
        } else {
        }
        return list;
    }

    public List<SearchTrainBean> getNaPass() {
        List<SearchTrainBean> list = new ArrayList<SearchTrainBean>();
        PurcheseValidation pv = new PurcheseValidation();
        int totalReservation = pv.validToByTicket(sdate, passid);
        if (totalReservation >= 0 & totalReservation < 4) {
            int arrayNumber = 4 - totalReservation;
            int ap = arrayNumber - aap;
            //int[] ap = new int[4 - gnap];
            for (int i = 0; i < ap + 1; i++) {
                SearchTrainBean ptb = new SearchTrainBean();
                ptb.setAnap(i);
                list.add(ptb);
            }
        } else {
        }
        return list;
    }
    // This method use to empty
    // All field Value

    public void refresh() {
        this.setRid(0);
        this.setRname("");
        this.setSdate("");
        this.setStname1("");
        this.setStname2("");
        this.setOffday("");
        this.setAvailabaletrain("");
        this.setTnumber("");
        this.setTname("");
        this.setDtime("");
        this.setAtime("");
        this.setOd("");
        this.setClassname("");
        this.setAseat(0);
        this.setAfare(0);
        this.setNp(0);
    }
    // Thsi Method use to insert to Seat_Info  seat
    // used.......................

    public int insertToSeatInof() {
        Connection con = null;
        PreparedStatement ps = null;
        PurcheseValidation pv = new PurcheseValidation();
        int bookId = pv.lestBookId();
        String seatNo = genarateNextTrainSeat();
        //System.out.println(bookId + "===" + seatNo);
        String sql = "insert into seat_info (book_id,seat_no,class_id,date) values (?,?,(select class_id "
                + "from class_name where class_name=?),?);";
        try {
            con = Database.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, bookId);
            ps.setString(2, seatNo);
            ps.setString(3, this.classname);
            ps.setString(4, this.sdate);
            int i = ps.executeUpdate();
            if (i > 0) {
                //System.out.println("Available seat Update Successful!");
                return 1;
            } else {
                return 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
        Database.close(con);
        }
        return 0;

    }
    // This Method used to Reserve Seat 
    // On given information of passenger

    public String reservation() {
        Connection con = null;
        PreparedStatement ps = null;
        PurcheseValidation pv = new PurcheseValidation();
        //All Avilable Seat from a class
        int reservedSeatInAClass = pv.availableSeat(sdate, availabaletrain, stname1, stname2, rname, classname);
        //System.out.println(reservedSeatInAClass);
        //Avilable Seat from a passenger in a day 
        int reservedSeatForAPass = pv.validToByTicket(sdate, passid);
        //System.out.println(reservedSeatForAPass);
        //Total class Seat
        int totalClassSeat = pv.classSeat(classname);
        //System.out.println(totalClassSeat);
        if (reservedSeatInAClass < totalClassSeat) {
            if (reservedSeatForAPass < 4) {
                //book_id, journey_dt, pass_id, train_id, class_id, adult, child, ptn, reserve_status, fare_id
                String sql = "insert into reservation (journey_dt, pass_id, train_id, class_id, adult, "
                        + "child, ptn, reserve_status, fare_id) values "
                        + "(?,?,(select train_id from train_info where train_name=? "
                        + "and st_id_from =(select st_id from station where st_name = ? and "
                        + "route_id=(Select route_id from route where route_name=?)) and "
                        + "st_id_to =(select st_id from station where st_name = ? and "
                        + "route_id=(Select route_id from route where route_name=?))),"
                        + "(select class_id from class_name cn "
                        + "where cn.class_name=?) ,?,?,(select UUID()),?,(select fare_id from fare f inner join station s  "
                        + "on f.st_id_from = s.st_id "
                        + "inner join station st on f.st_id_to = st.st_id "
                        + "where st_id_from = (select st_id from station "
                        + "where st_name='" + this.stname1 + "' and route_id= (select route_id from route "
                        + "where route_name = '" + this.rname + "')) and st_id_to = (select st_id from station "
                        + "where st_name='" + this.stname2 + "' and route_id= (select route_id from route "
                        + "where route_name = '" + this.rname + "')) and class_id = (select c.class_id from class_name c "
                        + "where c.class_name = '" + this.classname + "')));";
                //System.out.println(sql);
                //System.out.println(this.sdate + "=date" + availabaletrain + "=tname" + classname + "=class Name" + aap + "==" + anap);
                try {

                    con = Database.getConnection();
                    ps = con.prepareStatement(sql);

                    ps.setString(1, this.sdate);//this.sdate
                    ps.setInt(2, passid);
                    ps.setString(3, availabaletrain);//tname
                    ps.setString(4, stname1);
                    ps.setString(5, rname);
                    ps.setString(6, stname2);
                    ps.setString(7, rname);
                    ps.setString(8, classname);//classname
                    ps.setInt(9, aap);//aap
                    ps.setInt(10, anap);//anap
                    ps.setInt(11, 0);
                    int st = ps.executeUpdate();
                    //Insert To Seat Inof
                    if (st > 0) {
                        System.out.println("I am to insert to seat Info " + st);
                        int i = insertToSeatInof();
                        if (i == 1) {
                            refresh();
                            return "success?faces-redirect=true";
                        } else {
                            return "err?faces-redirect=true";
                        }

                    } else {
                        goErr();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    Database.close(con);
                }
            } else {
            }
        } else {
        }
        return null;
    }
    
    
    /**
     * @ lestBookId();
     * @return int value.
     */
    public int lestBookId() {
        Connection con = null;
        int lastBookId = 0;
        String sql = "select max(last_insert_id(book_id)) as bookid from reservation";
        try {
            con = Database.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                lastBookId = rs.getInt("bookid");
            }

            return lastBookId;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Database.close(con);
        }
        return 0;
    }

    /**
     * @ this method use to generate seat number
     */
    public String genarateNextTrainSeat() {
        PurcheseValidation pv = new PurcheseValidation();
        String seatNo = pv.lastInsertToSeatInfo(this.classname, this.sdate);
        if (seatNo != null) {
            //System.out.println(pv.lastInsertToSeatInfo(this.classname));
            int lenth = seatNo.length();
            int findSpace = seatNo.indexOf("-");
            String seatNo2 = seatNo.substring((findSpace + 1), lenth);
            //System.out.println(seatNo2);
            int firstNo = Integer.parseInt(seatNo2) + 1;
            int SecondNo = Integer.parseInt(seatNo2) + (this.aap + this.anap);
            String nextSeatNo = String.valueOf(firstNo + "-" + SecondNo);
            return nextSeatNo;
        } else {
            int SecondNo = (this.aap + this.anap);
            String SeatNo = String.valueOf(1 + "-" + SecondNo);
            return SeatNo;
        }
    }

    public void iPass() {
        Connection con = null;
        username = LoginUtil.getUserName();
        //System.out.println(pass);
        String sql = "select p.pass_id from user u inner join passenger_info p "
                + "on u.user_id = p.p_user_id where uname = '" + username + "';";
        try {
            con = Database.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                this.setPassid(rs.getInt("pass_id"));
                //System.out.println("Passenger Id from session "+rs.getInt("pass_id"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Database.close(con);
        }
    }
}
