package com.fxxkyiwangchangxue.yiwangnochangxue.entity;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

public class Commit implements Serializable {
    private Integer commitId;
    private Integer fori_userId;
    private Integer fori_taskId;
    private Integer result;
    private Date uploadTime;
    private String body;
    private String fileName;
    private byte[] file;
    private Long fileSize;

    public Commit() {
    }

    public Integer getCommitId() {
        return commitId;
    }

    public void setCommitId(Integer commitId) {
        this.commitId = commitId;
    }

    public Integer getFori_userId() {
        return fori_userId;
    }

    public void setFori_userId(Integer fori_userId) {
        this.fori_userId = fori_userId;
    }

    public Integer getFori_taskId() {
        return fori_taskId;
    }

    public void setFori_taskId(Integer fori_taskId) {
        this.fori_taskId = fori_taskId;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    @Override
    public String toString() {
        return "Commit{" +
                "commitId=" + commitId +
                ", fori_userId=" + fori_userId +
                ", fori_taskId=" + fori_taskId +
                ", result=" + result +
                ", uploadTime=" + uploadTime +
                ", body=" + body +
                ", fileName='" + fileName + '\'' +
                ", file=" + Arrays.toString(file) +
                ", fileSize=" + fileSize +
                '}';
    }
}
