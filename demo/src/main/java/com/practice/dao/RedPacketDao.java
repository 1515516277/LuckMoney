package com.practice.dao;

import com.practice.pojo.dto.RedPacketDTO;
import com.practice.pojo.model.RedPacketModel;

import java.util.List;

public interface RedPacketDao {
    /**
     * 添加红包
     * @param redPacket
     * @return
     */
    int addRedPacket(RedPacketModel redPacket);

    /**
     * 修改红包
     * @param redPacket
     * @return
     */
    int updateRedPacket(RedPacketModel redPacket);

    /**
     * 查询红包列表
     * @param redPacket
     * @return
     */
    List<RedPacketDTO> selectRedPacketList(RedPacketModel redPacket);

    /**
     * 根据id查询红包详情
     * @param id
     * @return
     */
    RedPacketDTO selectRedPacketById(int id);

    /**
     * 修改随机红包
     * @param redPacket
     * @return
     */
    int editWarsRedPacket(RedPacketModel redPacket);

    /**
     * 修改普通红包
     * @param redPacket
     * @return
     */
    int editCommonRedPacket(RedPacketModel redPacket);
}
