package com.afeng.mybatis.pojo;

import java.util.List;

/**
 * 包装数据对象 用来包装对象 以方便传递不同的参数
 */
public class QueryVo {
    //包含其他的pojo类
    private User user;
    private List<User> list;
    private List<Integer> ids;

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }

    public List<User> getList() {
        return list;
    }

    public void setList(List<User> list) {
        this.list = list;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "QueryVo{" +
                "user=" + user +
                ", list=" + list +
                '}';
    }
}
