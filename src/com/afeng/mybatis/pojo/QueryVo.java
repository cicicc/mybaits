package com.afeng.mybatis.pojo;

/**
 * 包装数据对象 用来包装对象 以方便传递不同的参数
 */
public class QueryVo {
    //包含其他的pojo类
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
