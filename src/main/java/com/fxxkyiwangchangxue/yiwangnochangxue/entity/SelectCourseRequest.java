package com.fxxkyiwangchangxue.yiwangnochangxue.entity;

public class SelectCourseRequest {
    Integer selReqId;
    Integer fori_userId;
    Integer fori_teacherId;
    boolean isAccept;

    public Integer getSelReqId() {
        return selReqId;
    }

    public void setSelReqId(Integer selReqId) {
        this.selReqId = selReqId;
    }

    public Integer getFori_userId() {
        return fori_userId;
    }

    public void setFori_userId(Integer fori_userId) {
        this.fori_userId = fori_userId;
    }

    public Integer getFori_teacherId() {
        return fori_teacherId;
    }

    public void setFori_teacherId(Integer fori_teacherId) {
        this.fori_teacherId = fori_teacherId;
    }

    public boolean isAccept() {
        return isAccept;
    }

    public void setAccept(boolean accept) {
        isAccept = accept;
    }

    @Override
    public String toString() {
        return "SelectCourseRequest{" +
                "selReqId=" + selReqId +
                ", fori_userId=" + fori_userId +
                ", fori_teacherId=" + fori_teacherId +
                ", isAccept=" + isAccept +
                '}';
    }
}
