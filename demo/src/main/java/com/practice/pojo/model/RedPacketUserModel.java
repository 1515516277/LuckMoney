package com.practice.pojo.model;

import java.io.Serializable;

public class RedPacketUserModel implements Serializable {
    //主键
    private Integer id;
    //用户id
    private Long uId;
    //红包id
    private Integer rId;
    //使用次数
    private Integer drowCount;
    //商品代理id 每个商品第一次有效
    private String productId;
    //收藏店铺 第一次有效
    private boolean collect;
    //店铺签到  第一次有效
    private boolean checkIn;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public boolean isCollect() {
        return collect;
    }

    public void setCollect(boolean collect) {
        this.collect = collect;
    }

    public boolean isCheckIn() {
        return checkIn;
    }

    public void setCheckIn(boolean checkIn) {
        this.checkIn = checkIn;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getuId() {
        return uId;
    }

    public void setuId(Long uId) {
        this.uId = uId;
    }

    public Integer getrId() {
        return rId;
    }

    public void setrId(Integer rId) {
        this.rId = rId;
    }

    public Integer getDrowCount() {
        return drowCount;
    }

    public void setDrowCount(Integer drowCount) {
        this.drowCount = drowCount;
    }
}
