package com.fxxkyiwangchangxue.yiwangnochangxue.entity;

import javax.xml.stream.Location;
import java.util.Date;

public class UserSign {
    private Integer signId;
    private Integer studentId;
    private Date signTime;
    private Location signLocation;


    public UserSign() {
    }

    public UserSign(Integer signId, Integer studentId, Date signTime, Location signLocation) {
        this.signId = signId;
        this.studentId = studentId;
        this.signTime = signTime;
        this.signLocation = signLocation;
    }

    /**
     * 获取
     * @return studentId
     */
    public Integer getStudentId() {
        return studentId;
    }

    /**
     * 设置
     * @param studentId
     */
    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    /**
     * 获取
     * @return signTime
     */
    public Date getSignTime() {
        return signTime;
    }

    /**
     * 设置
     * @param signTime
     */
    public void setSignTime(Date signTime) {
        this.signTime = signTime;
    }

    /**
     * 获取
     * @return signLocation
     */
    public Location getSignLocation() {
        return signLocation;
    }

    /**
     * 设置
     * @param signLocation
     */
    public void setSignLocation(Location signLocation) {
        this.signLocation = signLocation;
    }

    public String toString() {
        return "SignEntity{studentId = " + studentId + ", signTime = " + signTime + ", signLocation = " + signLocation + "}";
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
}
