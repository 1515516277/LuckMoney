package com.practice.design.impl.redPacket;

import com.alibaba.fastjson.JSONObject;
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
 * 产品代理
 */
@Service
public class ProductIdImpl implements DrowCountStrategy, InitializingBean {
    @Autowired
    private RedPacketUserDao redPacketUserDao;

    @Override
    public Map addCount(RedPacketUserDTO redPacketUserDTO,String product) {
        Map resultMap = new HashMap();
        try {
            if (redPacketUserDTO.getProductId().contains(product)) {
                resultMap.put("code", 0);
                resultMap.put("msg", "一个产品代理只能有一次抽奖机会！");
            } else {
                redPacketUserDTO.setProductId(redPacketUserDTO.getProductId() + "," + product);
                redPacketUserDTO.setDrowCount(redPacketUserDTO.getDrowCount() + 1);
                redPacketUserDao.updateRedPacketUser(redPacketUserDTO);
                resultMap.put("code", 1);
                resultMap.put("msg", "ok");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("code", 0);
            resultMap.put("msg", "exception");
        }
        return resultMap;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        DrowCountFactory.registryCount("product",this);
    }
}
