package com.practice.dao;

import com.practice.pojo.dto.RedPacketLogDTO;

import java.util.List;

public interface RedPacketLogDao {
    /**
     * 添加抢红包日志
     * @param redPacketLogDTO
     * @return
     */
    int addRedPacketLog(RedPacketLogDTO redPacketLogDTO);

    /**
     * 获取日志列表
     * @param redPacketLogDTO
     * @return
     */
    List<RedPacketLogDTO> getAllRedPacketLog(RedPacketLogDTO redPacketLogDTO);

}
