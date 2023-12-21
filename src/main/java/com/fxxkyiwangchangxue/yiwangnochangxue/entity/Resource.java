package com.fxxkyiwangchangxue.yiwangnochangxue.entity;

import java.io.Serializable;
import java.util.Arrays;

public class Resource implements Serializable {
    private Integer resId;
    private String title;
    private String body;
    private String fileName;
    private byte[] file;
    private long fileSize;

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

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    @Override
    public String toString() {
        return "Resource{" +
                "resId=" + resId +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", fileName='" + fileName + '\'' +
                ", file=" + Arrays.toString(file) +
                ", fileSize=" + fileSize +
                '}';
    }
}
