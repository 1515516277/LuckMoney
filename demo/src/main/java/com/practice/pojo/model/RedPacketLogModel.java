package com.practice.pojo.model;


import java.io.Serializable;
import java.util.Date;

public class RedPacketLogModel implements Serializable {
    //主键
    private Integer id;
    //红包id
    private Integer rId;
    //店铺id
    private Long sId;
    //中奖金额
    private Integer sMoney;
    //中奖时间
    private Date drowDate;

    public Integer getrId() {
        return rId;
    }

    public void setrId(Integer rId) {
        this.rId = rId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getsId() {
        return sId;
    }

    public void setsId(Long sId) {
        this.sId = sId;
    }

    public Integer getsMoney() {
        return sMoney;
    }

    public void setsMoney(Integer sMoney) {
        this.sMoney = sMoney;
    }

    public Date getDrowDate() {
        return drowDate;
    }

    public void setDrowDate(Date drowDate) {
        this.drowDate = drowDate;
    }
}
