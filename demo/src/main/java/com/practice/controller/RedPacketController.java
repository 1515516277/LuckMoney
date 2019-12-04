package com.practice.controller;

import com.alibaba.fastjson.JSONObject;
import com.practice.pojo.dto.RedPacketDTO;
import com.practice.pojo.dto.RedPacketUserDTO;
import com.practice.pojo.vo.RedPacketVO;
import com.practice.service.RedPacketService;
import com.practice.util.BeanMapperUtil;
import com.practice.util.TaskTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Timer;

/**
 * 红包
 */
@RequestMapping("redPacket")
@RestController
public class RedPacketController {
    @Autowired
    private RedPacketService redPacketService;

    /**
     * 获取红包列表
     *
     * @param redPacketDTO
     * @return
     */
    @RequestMapping("getList")
    public List<RedPacketVO> getRedPacketList(RedPacketDTO redPacketDTO) {
        List<RedPacketDTO> redPacketDTOS = redPacketService.selectRedPacketList(redPacketDTO);
        List<RedPacketVO> map = BeanMapperUtil.mapList(redPacketDTOS, RedPacketVO.class);
        return map;
    }

    /**
     * 根据id查询红包详情
     *
     * @param id
     * @return
     */
    @RequestMapping("getById")
    public RedPacketVO getRedPacketById(int id) {
        RedPacketDTO redPacketDTO = redPacketService.selectRedPacketById(id);
        RedPacketVO map = BeanMapperUtil.map(redPacketDTO, RedPacketVO.class);
        return map;
    }

    /**
     * 更改红包
     *
     * @param jsonObject
     */
    @RequestMapping("updateInfo")
    public void updateRedPacketById(@RequestBody JSONObject jsonObject) {
        RedPacketDTO redPacketDTO = jsonObject.toJavaObject(RedPacketDTO.class);
        int i = redPacketService.updateRedPacket(redPacketDTO);
    }

    /**
     * 添加红包
     *
     * @param jsonObject
     */
    @RequestMapping("insertInfo")
    public void insertRedPacketById(@RequestBody JSONObject jsonObject) {
        RedPacketDTO redPacketDTO = jsonObject.toJavaObject(RedPacketDTO.class);
        int i = redPacketService.addRedPacket(redPacketDTO);
    }

    /**
     * 删除红包
     *
     * @param id
     */
    @RequestMapping("delById")
    public void deleteRedPacketById(int id) {
        redPacketService.deleteRedPacketById(id);
    }


    /**
     * 拼手气红包
     *
     * @param jsonObject
     * @return
     */
    @RequestMapping("redWars")
    public Map redWars(@RequestBody JSONObject jsonObject) {
        RedPacketDTO redPacketDTO = jsonObject.toJavaObject(RedPacketDTO.class);

        return redPacketService.redWars(redPacketDTO);
    }

    /**
     * 普通红包
     *
     * @param jsonObject
     * @return
     */
    @RequestMapping("redCommon")
    public Map redCommon(@RequestBody JSONObject jsonObject) {
        RedPacketDTO redPacketDTO = jsonObject.toJavaObject(RedPacketDTO.class);
        return redPacketService.redCommon(redPacketDTO);
    }

    /**
     * 记录用户抽奖机会
     *
     * @param jsonObject
     * @return
     */
    @RequestMapping("addUserDraw")
    public Map addUserDraw(@RequestBody JSONObject jsonObject) {
        RedPacketUserDTO redPacketUserDTO = jsonObject.toJavaObject(RedPacketUserDTO.class);
        redPacketService.addUserDraw(redPacketUserDTO);
        return null;
    }

    @RequestMapping("test")
    public String test(String id){
        return id;
    }


    @RequestMapping("task")
    public void test(){
        for(int i=0;i<3;i++){
            Timer timer=new Timer();
            timer.schedule(new TaskTest(),100+i*1000);
        }
    }

}
