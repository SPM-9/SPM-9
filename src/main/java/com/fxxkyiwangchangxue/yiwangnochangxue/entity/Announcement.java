package com.fxxkyiwangchangxue.yiwangnochangxue.entity;

import java.io.Serializable;
import java.util.Date;

public class Announcement implements Serializable {
    private Integer annId;
    private Integer fori_teacherId;
    private Integer annType;
    private String title;
    private String body;
    private Date uploadTime;

    public Announcement(Integer annId, Integer fori_teacherId, Integer annType, String title, String body, Date uploadTime) {
        this.annId = annId;
        this.fori_teacherId = fori_teacherId;
        this.annType = annType;
        this.title = title;
        this.body = body;
        this.uploadTime = uploadTime;
    }

    public Announcement() {
    }

    public Integer getAnnId() {
        return annId;
    }

    public void setAnnId(Integer annId) {
        this.annId = annId;
    }

    public Integer getFori_teacherId() {
        return fori_teacherId;
    }

    public void setFori_teacherId(Integer fori_teacherId) {
        this.fori_teacherId = fori_teacherId;
    }

    public Integer getAnnType() {
        return annType;
    }

    public void setAnnType(Integer annType) {
        this.annType = annType;
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
        return "Announcement{" +
                "annId=" + annId +
                ", fori_teacherId=" + fori_teacherId +
                ", annType=" + annType +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", uploadTime=" + uploadTime +
                '}';
    }
}
