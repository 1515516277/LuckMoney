package com.practice.pojo.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 数据库实体
 */
@Data
public class RedPacketModel implements Serializable {
    //主键
    private int id;
    //总金额
    private Integer sumMoney;
    //单个金额
    private Integer singleMoney;
    //类型  1:拼手气  2:普通红包
    private Integer type;
    //红包个数
    private Integer sumCount;
    //开始日期
    private Date beginDate;
    //结束日期
    private Date endDate;
    //备注
    private String remark;
    //适用人群
    private Integer rppId;
    //剩余个数
    private Integer surplusCount;
    //剩余金额
    private Integer surplusMoney;
    //状态
    private boolean state=true;
    //店铺id
    private Long sId;
    //选中联系人
    private String peoples;

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getPeoples() {
        return peoples;
    }

    public void setPeoples(String peoples) {
        this.peoples = peoples;
    }

    public Long getsId() {
        return sId;
    }

    public void setsId(Long sId) {
        this.sId = sId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getSumMoney() {
        return sumMoney;
    }

    public void setSumMoney(Integer sumMoney) {
        this.sumMoney = sumMoney;
    }

    public Integer getSingleMoney() {
        return singleMoney;
    }

    public void setSingleMoney(Integer singleMoney) {
        this.singleMoney = singleMoney;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getSumCount() {
        return sumCount;
    }

    public void setSumCount(Integer sumCount) {
        this.sumCount = sumCount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getRppId() {
        return rppId;
    }

    public void setRppId(Integer rppId) {
        this.rppId = rppId;
    }

    public Integer getSurplusCount() {
        return surplusCount;
    }

    public void setSurplusCount(Integer surplusCount) {
        this.surplusCount = surplusCount;
    }

    public Integer getSurplusMoney() {
        return surplusMoney;
    }

    public void setSurplusMoney(Integer surplusMoney) {
        this.surplusMoney = surplusMoney;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}
