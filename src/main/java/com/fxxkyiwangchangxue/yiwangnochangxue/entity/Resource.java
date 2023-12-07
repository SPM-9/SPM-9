package com.fxxkyiwangchangxue.yiwangnochangxue.entity;

import java.io.Serializable;
import java.util.Arrays;

public class Resource implements Serializable {
    private Integer resId;
    private String title;
    private String body;
    private String fileName;
    private byte[] file;

    public Resource() {
    }

    public Integer getResId() {
        return resId;
    }

    public void setResId(Integer resId) {
        this.resId = resId;
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

    @Override
    public String toString() {
        return "ResourceEntity{" +
                "resId=" + resId +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", fileName='" + fileName + '\'' +
                ", file=" + Arrays.toString(file) +
                '}';
    }
}
