package com.fxxkyiwangchangxue.yiwangnochangxue.entity;

import java.io.Serializable;
import java.util.Date;

public class UserSigns implements Serializable {
    private Integer signId;
    private Integer fori_teacherId;
    private String signUId;

    private Date startTime;
    private Date endTime;

    public UserSigns() {
    }

    public UserSigns(Integer signId, Integer fori_teacherId, String signUId, Date startTime, Date endTime) {
        this.signId = signId;
        this.fori_teacherId = fori_teacherId;
        this.signUId = signUId;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * 获取
     * @return signId
     */
    public Integer getSignId() {
        return signId;
    }

    /**
     * 设置
     * @param signId
     */
    public void setSignId(Integer signId) {
        this.signId = signId;
    }

    /**
     * 获取
     * @return teacherId
     */
    public Integer getFori_teacherId() {
        return fori_teacherId;
    }

    /**
     * 设置
     * @param fori_teacherId
     */
    public void setFori_teacherId(Integer fori_teacherId) {
        this.fori_teacherId = fori_teacherId;
    }

    /**
     * 获取
     * @return signUId
     */
    public String getSignUId() {
        return signUId;
    }

    /**
     * 设置
     * @param signUId
     */
    public void setSignUId(String signUId) {
        this.signUId = signUId;
    }

    /**
     * 获取
     * @return startTime
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * 设置
     * @param startTime
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * 获取
     * @return endTime
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 设置
     * @param endTime
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String toString() {
        return "UserSigns{signId = " + signId + ", teacherId = " + fori_teacherId + ", signUId = " + signUId + ", startTime = " + startTime + ", endTime = " + endTime + "}";
    }
}

