package com.practice.service;

import com.practice.pojo.dto.RedPacketDTO;
import com.practice.pojo.dto.RedPacketUserDTO;

import java.util.List;
import java.util.Map;

public interface RedPacketService {
    int addRedPacket(RedPacketDTO redPacket);
    int updateRedPacket(RedPacketDTO redPacket);
    int deleteRedPacketById(int id);
    List<RedPacketDTO> selectRedPacketList(RedPacketDTO redPacket);
    RedPacketDTO selectRedPacketById(int id);
    Map redWars(RedPacketDTO redPacketDTO);
    Map redCommon(RedPacketDTO redPacketDTO);
    Map addUserDraw(RedPacketUserDTO redPacketUserDTO);
}
