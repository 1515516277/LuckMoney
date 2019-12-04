package com.practice.design.redPacket;

import com.practice.pojo.dto.RedPacketUserDTO;

import java.util.Map;

public interface DrowCountStrategy {
    Map addCount(RedPacketUserDTO redPacketUserDTO,String productId);
}
