package com.fxxkyiwangchangxue.yiwangnochangxue.entity;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

public class StudyTask implements Serializable {
    private Integer taskId;
    private Integer fori_teacherId;
    private Integer taskType;
    private String title;
    private String body;
    private byte[] file;
    private Date startTime;
    private Date endTime;

    public StudyTask(Integer taskId, Integer fori_teacherId, Integer taskType, String title, String body, byte[] file, Date startTime, Date endTime) {
        this.taskId = taskId;
        this.fori_teacherId = fori_teacherId;
        this.taskType = taskType;
        this.title = title;
        this.body = body;
        this.file = file;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public StudyTask() {
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Integer getFori_teacherId() {
        return fori_teacherId;
    }

    public void setFori_teacherId(Integer fori_teacherId) {
        this.fori_teacherId = fori_teacherId;
    }

    public Integer getTaskType() {
        return taskType;
    }

    public void setTaskType(Integer taskType) {
        this.taskType = taskType;
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

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "StudyTask{" +
                "taskId=" + taskId +
                ", fori_teacherId=" + fori_teacherId +
                ", taskType=" + taskType +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", file=" + Arrays.toString(file) +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
