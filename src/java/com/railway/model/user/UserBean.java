/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.railway.model.user;

import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Tonu
 */
@ManagedBean
@SessionScoped
public class UserBean {
    //    user table
    private int uesr_id;
    private int role_id;
    private String uname;
    private String pass;
    private boolean isactive;
    private String sec_question;
    private String sec_answer;
    private Date last_login;
    
    public UserBean(){
    
    }

    public int getUesr_id() {
        return uesr_id;
    }

    public void setUesr_id(int uesr_id) {
        this.uesr_id = uesr_id;
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

    public void setSec_answare(String sec_answer) {
        this.sec_answer = sec_answer;
    }

    public Date getLast_login() {
        return last_login;
    }

    public void setLast_login(Date last_login) {
        this.last_login = last_login;
    }
    
    
}
