package com.practice.service.impl;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import com.practice.dao.RedPacketDao;
import com.practice.dao.RedPacketLogDao;
import com.practice.dao.RedPacketUserDao;
import com.practice.design.redPacket.DrowCountFactory;
import com.practice.design.redPacket.DrowCountStrategy;
import com.practice.pojo.dto.RedPacketDTO;
import com.practice.pojo.dto.RedPacketLogDTO;
import com.practice.pojo.dto.RedPacketUserDTO;
import com.practice.pojo.model.RedPacketModel;
import com.practice.service.RedPacketService;
import com.practice.thread.SendMsgExecutor;
import com.practice.thread.SendMsgThread;
import com.practice.util.BeanMapperUtil;
import com.practice.util.RandomUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import sun.security.provider.MD5;

import java.util.*;
import java.util.concurrent.ThreadPoolExecutor;

@Service
public class RedPacketServiceImpl implements RedPacketService {
    @Autowired
    private RedPacketDao redPacketDao;
    @Autowired
    private RedPacketLogDao redPacketLogDao;
    @Autowired
    private RedPacketUserDao redPacketUserDao;


    Mapper mapper = DozerBeanMapperBuilder.buildDefault();

    @Transactional
    @Override
    public int addRedPacket(RedPacketDTO redPacket) {
        RedPacketModel map = BeanMapperUtil.map(redPacket, RedPacketModel.class);
        if (redPacket.getRppId() == 8 || redPacket.getRppId() == 9) {
//            sendMsg(redPacket.getPoperlId());
        }
        return redPacketDao.addRedPacket(map);
    }

    @Override
    public int updateRedPacket(RedPacketDTO redPacket) {
        RedPacketModel map = mapper.map(redPacket, RedPacketModel.class);
        return redPacketDao.updateRedPacket(map);
    }

    @Override
    public int deleteRedPacketById(int id) {
        RedPacketModel map = new RedPacketModel();
        map.setState(false);
        map.setId(id);
        return redPacketDao.updateRedPacket(map);
    }

    @Override
    public List<RedPacketDTO> selectRedPacketList(RedPacketDTO redPacket) {

        RedPacketModel redPacketModel = mapper.map(redPacket, RedPacketModel.class);
        return redPacketDao.selectRedPacketList(redPacketModel);
    }

    @Override
    public RedPacketDTO selectRedPacketById(int id) {
        return redPacketDao.selectRedPacketById(id);
    }

    @Transactional
    @Override
    public Map redWars(RedPacketDTO redPacketDTO) {
        Map resultMap = new HashMap();

        int resultValue = 0;
        try {
            if (!isExist(redPacketDTO.getuId(), redPacketDTO.getId())) {
                resultMap.put("code", "0");
                resultMap.put("msg", "您没有抽奖机会！");
                return resultMap;
            }
            RedPacketDTO redPacketInfo = redPacketDao.selectRedPacketById(redPacketDTO.getId());
            if (redPacketInfo != null && redPacketInfo.getSurplusCount() > 0) {
                //调用随机抽奖方法
                Map map = RandomUtil.redWars(redPacketInfo);
                if (!StringUtils.isEmpty(map)) {
                    RedPacketModel redPacketModel = BeanMapperUtil.map(map.get("redPacketDTO"), RedPacketModel.class);
                    redPacketDao.editWarsRedPacket(redPacketModel);
                    resultValue = Integer.parseInt(map.get("randomMoney").toString());
                    RedPacketLogDTO redPacketLogDTO = new RedPacketLogDTO();
                    redPacketLogDTO.setsMoney(resultValue);
                    redPacketLogDTO.setsId(redPacketDTO.getsId());
                    redPacketLogDTO.setrId(redPacketDTO.getId());
                    redPacketLogDao.addRedPacketLog(redPacketLogDTO);
                    resultMap.put("code", "1");
                    resultMap.put("msg", "成功");
                    resultMap.put("money", resultValue);
                    return resultMap;
                }
            } else {
                resultMap.put("code", "0");
                resultMap.put("msg", "红包被抢完了！");
                return resultMap;
            }
        } catch (Exception e) {
            resultMap.put("code", "0");
            resultMap.put("msg", "异常");
            e.printStackTrace();
        }
        return resultMap;
    }

    @Override
    public Map redCommon(RedPacketDTO redPacketDTO) {
        Map resultMap = new HashMap();
        if (isExist(redPacketDTO.getuId(), redPacketDTO.getId())) {
            RedPacketDTO redPacketInfo = redPacketDao.selectRedPacketById(redPacketDTO.getId());
            if (redPacketInfo != null && redPacketInfo.getSurplusCount() > 0) {
                redPacketInfo.setSurplusCount(redPacketInfo.getSumCount() - 1);
                resultMap.put("code", "1");
                resultMap.put("msg", "成功");
                resultMap.put("money", redPacketInfo.getSingleMoney());
                return resultMap;
            }
            resultMap.put("code", "0");
            resultMap.put("msg", "红包被抢完了！");
            return resultMap;
        }
        resultMap.put("code", "0");
        resultMap.put("msg", "您没有抽奖机会！");
        return resultMap;
    }

    @Override
    public Map addUserDraw(RedPacketUserDTO redPacketUserDTO) {
        RedPacketUserDTO byUidAndRid = redPacketUserDao.getByUidAndRid(redPacketUserDTO.getuId(), redPacketUserDTO.getrId());
        if (byUidAndRid == null) {
            Map map = new HashMap();
            redPacketUserDao.addRedPacketUser(redPacketUserDTO);
            map.put("code", 1);
            map.put("msg", "成功");
            return map;
        } else {
            //有值 修改 判断是否满足条件 走策略、工厂模式
            DrowCountStrategy drowCountStrategy = DrowCountFactory.getDrowCountStrategy(redPacketUserDTO.getType());
            Map map = drowCountStrategy.addCount(byUidAndRid, redPacketUserDTO.getProductId());
            return map;
        }
    }

    /**
     * 判断是否满足抽奖条件
     *
     * @param uId
     * @param rId
     * @return
     */
    public boolean isExist(Long uId, Integer rId) {
        RedPacketUserDTO byUidAndRid = redPacketUserDao.getByUidAndRid(uId, rId);
        if (byUidAndRid != null && byUidAndRid.getDrowCount() > 0) {
            return true;
        }
        return false;
    }

    /**
     * 推送消息
     *
     * @param lists
     */
    public void sendMsg(String lists) {
        ThreadPoolExecutor executor = SendMsgExecutor.getInstance();
        String msg = "抢红包了！";
        //如果没传递用户id则为全站
        if (StringUtils.isEmpty(lists)) {
            //查询全站联系人
            //查询总数
            //每次发送50个
            //使用多线程
            List<Integer> list = new ArrayList<>();
            SendMsgExecutor.addExecuteTask(new SendMsgThread(list, msg));
        } else {
            String[] split = lists.split(",");
            List<String> integers = Arrays.asList(split);
            List<Integer> list = new ArrayList<>();
            integers.forEach(e -> list.add(Integer.parseInt(e)));
            SendMsgExecutor.addExecuteTask(new SendMsgThread(list, msg));
        }


    }


    @Test
    public void test() {
//        List a = new ArrayList();
//        for (int i = 0; i < 10; i++) {
//            a.add(i);
//        }
//        for (Object list : splitList(a, 20)) {
//            System.out.println(list);
//        }
        String a=null;
        System.out.println(null+"2");

    }


    public List<List> splitList(List list, int len) {
        if (list == null || list.size() == 0 || len < 1) {
            return null;
        }
        List<List> result = new ArrayList<List>();
        int size = list.size();
        int count = (size + len - 1) / len;
        for (int i = 0; i < count; i++) {
            List subList = list.subList(i * len, ((i + 1) * len > size ? size : len * (i + 1)));
            result.add(subList);
        }
        return result;
    }


}
