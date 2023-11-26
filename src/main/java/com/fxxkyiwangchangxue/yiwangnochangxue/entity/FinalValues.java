package com.fxxkyiwangchangxue.yiwangnochangxue.entity;

public class FinalValues {
    public static final int NOLOGIN = 0;
    public static final int STUDENT = 1;
    public static final int STAFF = 2;
    public static final User anonymous = new User(-1, "anonymous", "114514",
            "匿名用户", "114514@qq.com", NOLOGIN, 0, false);
}
