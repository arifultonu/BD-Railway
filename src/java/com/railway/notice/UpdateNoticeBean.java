/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.railway.notice;

import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Enamul
 */
@ManagedBean
@SessionScoped
public class UpdateNoticeBean {

    private NoticeBean selectRow;
    private int noticeId;
    private String noticeTitle;
    private String notice;
    private UploadedFile noticeIamge;
    private Date publishDate;
    private Date expireDate;

    /**
     * Creates a new instance of UpdateNoticeBean
     */
    public UpdateNoticeBean() {
    }

    public NoticeBean getSelectRow() {
        return selectRow;
    }

    public void setSelectRow(NoticeBean selectRow) {
        this.selectRow = selectRow;
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
    
    public void doSelectRow(){
    setNoticeId(getSelectRow().getNoticeId());
    setNoticeTitle(getSelectRow().getNoticeTitle());
    setPublishDate(getSelectRow().getPublishDate());
    setExpireDate(getSelectRow().getExpireDate());
    setNotice(getSelectRow().getNotice());
    }
}
