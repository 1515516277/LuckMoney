package com.practice.pojo.dto;

import com.practice.pojo.model.RedPacketModel;
import lombok.Data;

import java.io.Serializable;

/**
 * service业务对象
 */
@Data
public class RedPacketDTO extends RedPacketModel implements Serializable {

    //人群
    private String name;
    //类型 1：进行中 2：已结束
    private Integer openType;
    //用户id
    private Long uId;
    //联系人id
    private String peopleId;

    private Integer test;

    public Integer getTest() {
        return test;
    }

    public void setTest(Integer test) {
        if(test==1){
            setName("test1");
        }
        if(test==2){
            setName("test2");
        }
        if(test==3){
            setName("test3");
        }
        if(test==4){
            setName("test4");
        }
        this.test = test;
    }

    public Long getuId() {
        return uId;
    }

    public void nnnnsetuId(Long uId) {
        this.uId = uId;
    }

    public String getPeopleId() {
        return peopleId;
    }

    public void setPeopleId(String peopleId) {
        this.peopleId = peopleId;
    }

    public Integer getOpenType() {
        return openType;
    }

    public void setOpenType(Integer openType) {
        this.openType = openType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



}
