package com.practice.design.impl.redPacket;

import com.practice.dao.RedPacketUserDao;
import com.practice.design.redPacket.DrowCountFactory;
import com.practice.design.redPacket.DrowCountStrategy;
import com.practice.pojo.dto.RedPacketUserDTO;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 其他不唯一方式
 */
@Service
public class OhterImpl implements DrowCountStrategy, InitializingBean {
    @Autowired
    private RedPacketUserDao redPacketUserDao;

    @Override
    public Map addCount(RedPacketUserDTO redPacketUserDTO, String productId) {
        Map resultMap = new HashMap();
        try {
            redPacketUserDTO.setDrowCount(redPacketUserDTO.getDrowCount() + 1);
            redPacketUserDao.updateRedPacketUser(redPacketUserDTO);
            resultMap.put("code", 1);
            resultMap.put("msg", "ok");
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("code", 0);
            resultMap.put("msg", "异常");
        }
        return resultMap;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        DrowCountFactory.registryCount("other",this);
    }
}
