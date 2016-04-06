package com.railway.notice;

import com.railway.util.Database;
import java.io.IOException;
import java.io.InputStream;
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
import org.primefaces.model.UploadedFile;


@ManagedBean
@SessionScoped
public class NoticeBean {

    private int noticeId;
    private String noticeTitle;
    private String notice;
    private UploadedFile noticeIamge;
    private Date publishDate;
    private Date expireDate;

    /**
     * Creates a new instance of NoticeBean
     */
    public NoticeBean() {
    }

    public int getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(int noticeId) {
        this.noticeId = noticeId;
    }

    public String getNoticeTitle() {
        return noticeTitle;
    }

    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public UploadedFile getNoticeIamge() {
        return noticeIamge;
    }

    public void setNoticeIamge(UploadedFile noticeIamge) {
        this.noticeIamge = noticeIamge;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public void insertNotice() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String pd = df.format(getPublishDate());
        String expire = df.format(getExpireDate());
        if (noticeIamge != null) {
            String sql = "INSERT INTO railway_notice (notice_title, notice, publish_date, expire_date,notice_img)"
                    + "VALUES (?,?,?,?,?)";
            try {
                InputStream streamfile = getNoticeIamge().getInputstream();
                PreparedStatement ps = Database.getConnection().prepareStatement(sql);
                ps.setString(1, getNoticeTitle());
                ps.setString(2, getNotice());
                // ps.setBinaryStream(3, streamfile, getNoticeIamge().getSize());
                ps.setString(3, pd);
                ps.setString(4, expire);
                ps.setBinaryStream(5, streamfile, getNoticeIamge().getSize());
                int i = ps.executeUpdate();
                clear();
                if (i > 0) {
                    FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Information", "New Notice add Successfully");
                    FacesContext.getCurrentInstance().addMessage(null, msg);
                } else {
                    FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Fail to save notice");
                    FacesContext.getCurrentInstance().addMessage(null, msg);
                }
            } catch (SQLException ex) {
                System.out.println("Error from insert----------->" + ex.getMessage());
            } catch (IOException ex) {
                System.out.println("Error from insert----------->" + ex.getMessage());
            }
        } else {
            String sql = "INSERT INTO railway_notice (notice_title, notice, publish_date, expire_date)"
                    + "VALUES (?,?,?,?)";
            try {
                PreparedStatement ps = Database.getConnection().prepareStatement(sql);
                ps.setString(1, getNoticeTitle());
                ps.setString(2, getNotice());
                ps.setString(3, pd);
                ps.setString(4, expire);
                int i = ps.executeUpdate();
                clear();
                if (i > 0) {
                    FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Information", "New Notice add Successfully");
                    FacesContext.getCurrentInstance().addMessage(null, msg);
                } else {
                    FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Fail to save notice");
                    FacesContext.getCurrentInstance().addMessage(null, msg);
                }
            } catch (SQLException ex) {
                System.out.println("Error from insert----------->" + ex.getMessage());
            }
        }

    }

    public List<NoticeBean> getAllNotice() {
        List<NoticeBean> data = new ArrayList<NoticeBean>();
        String sql = "SELECT * FROM railway_notice  ORDER BY notice_id DESC LIMIT 3;";
        try {
            Statement st = Database.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                NoticeBean nb = new NoticeBean();
                nb.setNoticeId(rs.getInt("notice_id"));
                nb.setNoticeTitle(rs.getString("notice_title"));
                nb.setNotice(rs.getString("notice"));
                //nb.setNoticeImage(file);
                nb.setPublishDate(rs.getDate("publish_date"));
                nb.setExpireDate(rs.getDate("expire_date"));

                data.add(nb);
            }
        } catch (Exception e) {
        }
        return data;
    }

    public void clear() {
        notice = "";
        noticeTitle = "";
        publishDate = null;
        expireDate = null;
    }
    public void deleteData() {
    String sql = "DELETE FROM railway_notice  WHERE notice_id=?;";
    try {
      PreparedStatement pst = Database.getConnection().prepareStatement(sql);
      pst.setInt(1, this.getNoticeId());
      int i = pst.executeUpdate();
      if (i > 0) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Information", "One row deleted");
        FacesContext.getCurrentInstance().addMessage(null, msg);
      } else {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Fail to delete data");
        FacesContext.getCurrentInstance().addMessage(null, msg);
      }
    } catch (SQLException ex) {
      Logger.getLogger(NoticeBean.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
}
