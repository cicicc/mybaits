package com.afeng.mybatis.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 订单对象
 */
public class Orders  implements Serializable {
    private Integer id; //订单id
    private Integer userId; //创建此订单的用户的id
    private String number; //订单号
    private Date createtime;//订单创建时间
    private String note; //备注信息
    private User user;//订单所属用户 在这里先测试一对关联

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userId=" + userId +
                ", number='" + number + '\'' +
                ", createtime=" + createtime +
                ", note='" + note + '\'' +
                '}';
    }
}
