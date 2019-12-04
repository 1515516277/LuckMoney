package com.practice.dao;

import com.practice.pojo.dto.RedPacketUserDTO;
import org.apache.ibatis.annotations.Param;

public interface RedPacketUserDao {
    /**
     * 查询是否有抢红包机会
     * @param uId
     * @param rId
     * @return
     */
    RedPacketUserDTO getByUidAndRid(@Param("uId") Long uId,@Param("rId") Integer rId);

    /**
     * 添加抢红包机会
     * @param redPacketUserDTO
     * @return
     */
    int addRedPacketUser(RedPacketUserDTO redPacketUserDTO);

    /**
     * 修改抢红包机会
     * @param redPacketUserDTO
     * @return
     */
    int updateRedPacketUser(RedPacketUserDTO redPacketUserDTO);
}
