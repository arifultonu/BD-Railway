/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import com.railway.util.Database;

/**
 *
 * @author ENAMUL
 */
public class PurcheseValidation {

    /**
     *
     * @validToByTicket(String date, int passengerid)
     * @return int value.
     */
    public int validToByTicket(String date, int passengerid) {
        Connection con = null;
        Statement st = null;
        int ad, nad, total = 0;
        String sql = "select adult,child from reservation where journey_dt = '" + date + "' and pass_id=" + passengerid + ";";
        try {
            con = Database.getConnection();
            st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                ad = rs.getInt("adult");
                nad = rs.getInt("child");
                total += ad + nad;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
        Database.close(con);
        }
        return total;
    }

    /**
     *
     * @isFirstBook(String date, String classname)
     * @return Boolean value.
     */
    public int isFirstBook(String date, String classname) {
        Connection con = null;
        Statement st = null;
        String sql = "select train_id from reservation where journey_dt ='" + date + "'"
                + "and class_id=(select class_id from class_name cn where cn.class_name='" + classname + "');";
        try {
             con = Database.getConnection();
             st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                //System.out.println(rs.getString("train_id"));
                return 1;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }finally{
        Database.close(con);
        }
        return 0;

    }

    /**
     *
     * @int passId,String date, String trainName, String stationFrom, String
     * stationTo, String routeName, String className
     * @return int value.
     * @This Method use to retrun total reservation by Spcific date ,
     * Passenger_id, Treain_id , Class_id;
     * @see This Method shoud be to use total reservation on a train
     */
    public int bookedDateWise(int passId, String date, String trainName, String stationFrom, String stationTo, String routeName, String className) {
        Connection con = null;
        Statement st = null;
        int total = 0;
        String sql = "select adult, child from reservation where journey_dt = '" + date + "' and "
                + "pass_id = " + passId + " and train_id=(select train_id from train_info "
                + "where train_name='" + trainName + "' and st_id_from =(select st_id from station "
                + "where st_name ='" + stationFrom + "' and route_id=(Select route_id from route "
                + "where route_name='" + routeName + "')) and st_id_to =(select st_id from station "
                + "where st_name = '" + stationTo + "' and route_id=(Select route_id from route "
                + "where route_name='" + routeName + "'))) and class_id = (select class_id from class_name "
                + "where class_name='" + className + "');";
        String sql2 = "select adult, child from reservation where journey_dt = '2013-09-09' and pass_id =1 and train_id=(select train_id from train_info where train_name='SubornaExpress' and st_id_from =(select st_id from station where st_name ='Azampur' and route_id=(Select route_id from route where route_name='Sylhet1')) and st_id_to =(select st_id from station where st_name = 'Sylhet' and route_id=(Select route_id from route where route_name='Sylhet1'))) and class_id = (select class_id from class_name where class_name='Sovon_Chair_A')";
        //select adult, child from reservation where journey_dt = '2013-09-09' and pass_id = 1 and train_id=(select train_id from train_info where train_name='SubornaExpress' and st_id_from =(select st_id from station where st_name ='Azampur' and route_id=(Select route_id from route where route_name='Sylhet')) and st_id_to =(select st_id from station where st_name = 'Sylhet1' and route_id=(Select route_id from route where route_name='Sylhet1'))) and class_id = (select class_id from class_name where class_name='Sovon_Chair_A');
        System.out.println(sql);
        try {
             con = Database.getConnection();
             st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                total += rs.getInt("adult");
                total += rs.getInt("child");
            }
            
            return total;
        } catch (Exception e) {
        }finally{
        Database.close(con);
        }
        return -1;
    }

    /**
     *
     * @genearatePtnNumber()
     * @return String value.
     *
     */
    public String genearatePtnNumber() {
        Connection con = null;
        Statement st = null;
        String uuid = "";
        String sql = "Select UUID() as uid";
        try {
             con = Database.getConnection();
             st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                uuid = rs.getString("uid");
            }
           
            return uuid;
        } catch (Exception e) {
        }finally {
        Database.close(con);
        }
        return null;
    }

    /**
     *
     * @lastInsertToReservatin()
     * @return String value.
     */
    public String lastInsertToSeatInfo(String className, String date) {
        Connection con = null;
        Statement st = null;
        String SeatNo = null;
        //String sql = "select max(last_insert_id(Book_id)) from reservation;";
        String sql2 = "SELECT max(seat_no) as seatno FROM seat_info where class_id=(select class_id from class_name where class_name='" + className + "') and date = '" + date + "';";
        try {
             con = Database.getConnection();
             st = con.createStatement();
            ResultSet rs = st.executeQuery(sql2);
            if (rs.next()) {
                SeatNo = rs.getString("seatno");
            }
            
            return SeatNo;
        } catch (Exception e) {
        }finally{
        Database.close(con);
        }
        return null;
    }

    /**
     *
     * @avilableSeat()
     * @return int value.
     */
    public int avilableSeat() {
         Connection con = null;
        Statement st = null;
        String avSeat = null;
        int avSeat2 = 0;
        String sql2 = "select seat_no from seat_info where seat_id = (select max(last_insert_id(seat_id)) from seat_info);";
        try {
             con = Database.getConnection();
             st = con.createStatement();
            ResultSet rs = st.executeQuery(sql2);
            if (rs.next()) {
                avSeat = rs.getString("seat_no");
            }
            int lenth = avSeat.length();
            int findSpace = avSeat.indexOf("-");
            String seatNo2 = avSeat.substring((findSpace + 1), lenth);
            avSeat2 = Integer.parseInt(seatNo2);
           
            return avSeat2;
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
        Database.close(con);
        }
        return 0;
    }

    /**
     *
     * @availableSeat(String date, String trainName, String stationFrom, String
     * stationTo, String routeName,String className)
     * @return int value.
     */
    public int availableSeat(String date, String trainName, String stationFrom, String stationTo, String routeName, String className) {
        Connection con = null;
        Statement st = null;
        int ad, nad, total = 0;
        String sql = "select adult,child from reservation where journey_dt = '" + date + "' "
                + "and train_id= (select train_id from train_info "
                + "where train_name='" + trainName + "' and st_id_from =(select st_id from station "
                + "where st_name ='" + stationFrom + "' and route_id=(Select route_id from route "
                + "where route_name='" + routeName + "')) and st_id_to =(select st_id from station "
                + "where st_name = '" + stationTo + "' and route_id=(Select route_id from route "
                + "where route_name='" + routeName + "')))"
                + "and class_id=(select class_id from class_name where class_name='" + className + "');";
        //System.out.println(sql);
        try {
             con = Database.getConnection();
             st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                ad = rs.getInt("adult");
                nad = rs.getInt("child");
                total += ad + nad;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
        Database.close(con);
        }
        return total;
    }

    /**
     * @ lestBookId();
     * @return int value.
     */
    public int lestBookId() {
         Connection con = null;
        Statement st = null;
        int lastBookId = 0;
        String sql = "select max(last_insert_id(book_id)) as bookid from reservation";
        try {
             con = Database.getConnection();
             st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                lastBookId = rs.getInt("bookid");
            }
           
            return lastBookId;
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
        Database.close(con);
        }
        return 0;
    }

    /**
     * @ classSeat() ;
     * @return int value.
     */
    public int classSeat(String className) {
         Connection con = null;
        Statement st = null;
        int classSeat = 0;
        String sql = "select class_seat from class_name where class_name = '" + className + "';";
        try {
             con = Database.getConnection();
             st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                classSeat = rs.getInt("class_seat");
            }
            
            return classSeat;
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
        Database.close(con);
        }
        return 0;
    }

    /**
     * @ Search Reservation with formated ptn number searchResByPtn() ;
     * @return int value.
     */
    public void searchResByPtn(String first3letter, String last3letter) {
         Connection con = null;
        Statement st = null;
        String sql = "SELECT * FROM reservation r where ptn like '" + first3letter + "%' and ptn like '%" + last3letter + "';";
        try {
             con = Database.getConnection();
             st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
        Database.close(con);
        }
    }
//    public String genarateNextTrainSeat() {
//        String seatNo = lastInsertToReservatin();
//        int lenth = seatNo.length();
//        int findSpace = seatNo.indexOf("-");
//        String seatNo2 = seatNo.substring((findSpace + 1), lenth);
//        int firstNo = Integer.parseInt(seatNo2) + 1;
//        int SecondNo = 
//        return "";
//    }
    //public static void main(String[] args) {
    //     PurcheseValidation v = new PurcheseValidation();
//        int i1 = v.validToByTicket("2013-10-05", 1);
//        System.out.println("ValidToByTicke==>>" + i1);
////        int i = v.bookedDateWise(1, "2013-10-02", "SubornaExpress", "Sylhet", "Azampur", "Sylhet1", "Sovon_B");
    //       System.out.println(v.availableSeat(""+"2013-10-07", "SubornaExpress", "Azampur", "Sylhet", "Sylhet1", "Sovon_B"));
//        System.out.println(v.classSeat("Sovon_B"));
//       int i = v.classSeat("Sovon_B") - v.availableSeat("2013-10-07", "SubornaExpress", "Sylhet", "Azampur", "Sylhet1", "Sovon_B");
    //      System.out.println(i);
//        System.out.println("last Seat Number "+v.lastInsertToSeatInfo("Sovon_Chair_A"));
//        System.out.println("Last Book Id==>>"+v.lestBookId());
    //}
}
