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
    private String fileName;
    private byte[] file;
    private Long fileSize;
    private Date startTime;
    private Date endTime;
    public static final int HOMEWORK = 0;
    public static final int EXAM = 1;

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

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String toString() {
        return "StudyTask{" +
                "taskId=" + taskId +
                ", fori_teacherId=" + fori_teacherId +
                ", taskType=" + taskType +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", fileName='" + fileName + '\'' +
                ", file=" + Arrays.toString(file) +
                ", fileSize=" + fileSize +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }
}
