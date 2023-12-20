package com.fxxkyiwangchangxue.yiwangnochangxue.entity;

public class Comment {
    private String name;
    private String email;
    private String subject;
    private String content;

    public Comment() {
    }

    public Comment(String name, String email, String subject, String content) {
        this.name = name;
        this.email = email;
        this.subject = subject;
        this.content = content;
    }

    /**
     * 获取
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取
     * @return subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * 设置
     * @param subject
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * 获取
     * @return content
     */
    public String getcontent() {
        return content;
    }

    /**
     * 设置
     * @param content
     */
    public void setcontent(String content) {
        this.content = content;
    }

    public String toString() {
        return "content{name = " + name + ", email = " + email + ", subject = " + subject + ", content = " + content + "}";
    }
}

