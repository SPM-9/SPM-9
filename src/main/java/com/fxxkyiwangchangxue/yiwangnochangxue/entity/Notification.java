package com.fxxkyiwangchangxue.yiwangnochangxue.entity;

import java.io.Serializable;
import java.util.Date;

public class Notification implements Serializable {
    private Integer notifId;
    private Integer fori_taskId;
    private Integer fori_annId;
    private Integer notifType;
    private String title;
    private String body;
    private Date uploadTime;
    public static final int TASK = 0;
    public static final int ANNOUNCEMENT = 1;

    public Notification() {
    }

    public Integer getNotifId() {
        return notifId;
    }

    public void setNotifId(Integer notifId) {
        this.notifId = notifId;
    }

    public Integer getFori_taskId() {
        return fori_taskId;
    }

    public void setFori_taskId(Integer fori_taskId) {
        this.fori_taskId = fori_taskId;
    }

    public Integer getFori_annId() {
        return fori_annId;
    }

    public void setFori_annId(Integer fori_annId) {
        this.fori_annId = fori_annId;
    }

    public Integer getNotifType() {
        return notifType;
    }

    public void setNotifType(Integer notifType) {
        this.notifType = notifType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "notifId=" + notifId +
                ", fori_taskId=" + fori_taskId +
                ", fori_annId=" + fori_annId +
                ", notifType=" + notifType +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", uploadTime=" + uploadTime +
                '}';
    }
}
