package com.practice.thread;

import java.util.List;

public class SendMsgThread implements Runnable{
    public List<Integer> list;
    public String name;
    public SendMsgThread(List<Integer> list,String name){
        this.list=list;
        this.name=name;
    }
    @Override
    public void run() {
        //执行推送操作
        list.forEach(e->{
            //给消息列表添加信息
            System.out.println(name+"~~"+e);
        });
    }
}
