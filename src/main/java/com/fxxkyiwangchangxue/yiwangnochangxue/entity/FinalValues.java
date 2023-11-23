package com.fxxkyiwangchangxue.yiwangnochangxue.entity;

public class FinalValues {
    static final int NOLOGIN = 0;
    static final int STUDENT = 1;
    static final int STAFF = 2;
    static final User anonymous = new User(-1, "anonymous", "114514",
            "匿名用户", "114514@qq.com", NOLOGIN, 0);
}
