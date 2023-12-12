package com.fxxkyiwangchangxue.yiwangnochangxue.entity;

import java.io.Serializable;
import java.util.Date;

public class Homework implements Serializable {
    private Integer homeworkId;
    private String title;
    private String body;
    private Date uploadTime;

    public Homework() {
    }

    public Homework(Integer homeworkId, String title, String body, Date uploadTime) {
        this.homeworkId = homeworkId;
        this.title = title;
        this.body = body;
        this.uploadTime = uploadTime;
    }

    public Integer getHomeworkId() {
        return homeworkId;
    }

    public void setHomeworkId(Integer homeworkId) {
        this.homeworkId = homeworkId;
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
        return "HomeWorkNotification{" +
                "homeworkId=" + homeworkId +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", uploadTime=" + uploadTime +
                '}';
    }
}