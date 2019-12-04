package com.practice.design.impl.redPacket;

import com.practice.dao.RedPacketUserDao;
import com.practice.design.redPacket.DrowCountFactory;
import com.practice.design.redPacket.DrowCountStrategy;
import com.practice.pojo.dto.RedPacketUserDTO;
import com.sun.org.apache.xml.internal.security.Init;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 店铺签到
 */
@Service
public class CheckInImpl implements DrowCountStrategy, InitializingBean {
    @Autowired
    private RedPacketUserDao redPacketUserDao;

    @Override
    public Map addCount(RedPacketUserDTO redPacketUserDTO,String product) {
        Map resultMap = new HashMap();
        try {
            if (redPacketUserDTO.isCheckIn()) {
                resultMap.put("code", 0);
                resultMap.put("msg", "店铺签到只能有一次抽奖机会！");
            } else {
                redPacketUserDTO.setDrowCount(redPacketUserDTO.getDrowCount() + 1);
                redPacketUserDTO.setCheckIn(true);
                redPacketUserDao.updateRedPacketUser(redPacketUserDTO);
                resultMap.put("code", 1);
                resultMap.put("msg", "ok");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("code", 0);
            resultMap.put("msg", "异常");
        }
        return resultMap;
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        DrowCountFactory.registryCount("checkIn",this);
    }
}
