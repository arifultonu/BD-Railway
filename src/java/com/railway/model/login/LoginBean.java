/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.railway.model.login;

import com.railway.dao.login.UserDaoLogin;
import com.railway.util.Database;
import com.railway.util.LoginUtil;
import java.util.Date;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Tonu
 */
@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private int user_id;
    private int role_id;
    private String role_name;
    private String uname;
    private String pass;
    private String isactive;
    private String sec_question;
    private String sec_answer;
    private Date last_login;
    boolean remember;
    public String remember1 = "";
    private String username;

    public LoginBean() {
        checkCookie();
    }

    /**
     * @return the user_id
     */
    /*Start For Remember login*/
    public boolean isRemember() {
        return remember;
    }

    public void setRemember(boolean remember) {
        this.remember = remember;
    }

    public String getRemember1() {
        return remember1;
    }

    public void setRemember1(String remember1) {
        this.remember1 = remember1;
    }
    /*End For Remember login*/

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

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    /**
     * @return the uname
     */
    public String getUname() {
        if (remember == false) {
            uname = "";
            return uname;
        } else {
            return uname;
        }
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
        if (remember == false) {
            pass = "";
            return pass;
        } else {
            return pass;
        }
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
    public String getIsactive() {
        return isactive;
    }

    /**
     * @param isactive the isactive to set
     */
    public void setIsactive(String isactive) {
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

    /**
     * @return the last_login
     */
    public Date getLast_login() {
        return last_login;
    }

    /**
     * @param last_login the last_login to set
     */
    public void setLast_login(Date last_login) {
        this.last_login = last_login;
    }

    public String loginProject() {
    String returnPage = "index";
        boolean result = UserDaoLogin.userLogin(uname, pass);
        if (result) {
            // get Http Session and store username
            HttpSession session = LoginUtil.getSession();
            session.setAttribute("username", uname);

            FacesContext facesContext = FacesContext.getCurrentInstance();
            // Save the uname and password in a cookie
            Cookie btuser = new Cookie("btuser", uname);
            Cookie btpasswd = new Cookie("btpasswd", pass);
            if (remember == false) {
                remember1 = "false";
            } else {
                remember1 = "true";
            }
            Cookie btremember = new Cookie("btremember", remember1);
            btuser.setMaxAge(3600);
            btpasswd.setMaxAge(3600);
            ((HttpServletResponse) facesContext.getExternalContext().getResponse()).addCookie(btuser);
            ((HttpServletResponse) facesContext.getExternalContext().getResponse()).addCookie(btpasswd);
            ((HttpServletResponse) facesContext.getExternalContext().getResponse()).addCookie(btremember);
            userRoleName();
            userRoleID();
            switch (this.getRole_id()) {
                case 1:
                    returnPage = "admin/admin-home?faces-redirect=true";
                    break;
                case 2:
                    returnPage = "ticketManager/manager-home?faces-redirect=true";
                    break;
                case 3:
                    returnPage = "passenger/pas-home?faces-redirect=true";
            }

        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Error User Name or Password!", "Please Try Again!"));
            // invalidate session, and redirect to other pages
            //message = "Invalid Login. Please Try Again!";
            return "index?faces-redirect=true";
        }
        return returnPage;
    }

    public String logout() {
      //  System.out.println("Logout called");
        HttpSession session = LoginUtil.getSession();
        session.invalidate();
        return "logout";
    }

    public void checkCookie() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        String cookieName = null;
        Cookie cookie[] = ((HttpServletRequest) facesContext.getExternalContext().getRequest()).getCookies();
        if (cookie != null && cookie.length > 0) {
            for (int i = 0; i < cookie.length; i++) {
                cookieName = cookie[i].getName();
                if (cookieName.equals("btuser")) {
                    uname = cookie[i].getValue();
                } else if (cookieName.equals("btpasswd")) {
                    pass = cookie[i].getValue();
                } else if (cookieName.equals("btremember")) {
                    remember1 = cookie[i].getValue();
                    if (remember1.equals("false")) {
                        remember = false;
                    } else if (remember1.equals("true")) {
                        remember = true;
                    }
                }
            }
        } else {
            System.out.println("Cannot find any cookie");
        }
    }

    public String userName() {
        return uname;
    }

    public void userRoleID() {
        Connection con = null;
        Statement st = null;
        username = LoginUtil.getUserName();
        System.out.println(username);
        String sql = "select role_id from railway_ticket_booking.user where uname = '" + username + "';";
        try {
            con = Database.getConnection();
            st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                this.setRole_id(rs.getInt("role_id"));

               // System.out.println("Role Id from session " + rs.getInt("role_id"));

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Database.close(con);
        }
    }

    public void userRoleName() {
        HttpSession session = LoginUtil.getSession();
        Connection con = null;
        Statement st = null;
        String sql = "select role_name from railway_ticket_booking.user_role where role_id = '" + LoginUtil.getRoleId() + "';";
        try {
            con = Database.getConnection();
            st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                this.setRole_name(rs.getString("role_name"));
                session.setAttribute("role_name", role_name);
               // System.out.println("Role Id from session " + rs.getInt("role_name"));

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Database.close(con);
        }
    }
}
