package com.practice.pojo.dto;

import com.practice.pojo.model.RedPacketUserModel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.junit.Test;

import java.io.Serializable;

public class RedPacketUserDTO extends RedPacketUserModel implements Serializable {

    /**
     * 获取抽奖方式
     * checkIn 签到
     * collect 注册
     * product 产品代理
     * other   其他不唯一方式
     */
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


}
